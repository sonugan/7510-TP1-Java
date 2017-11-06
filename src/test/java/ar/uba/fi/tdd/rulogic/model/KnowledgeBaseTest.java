package ar.uba.fi.tdd.rulogic.model;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import java.util.*;

import ar.uba.fi.tdd.rulogic.exceptions.*;

public class KnowledgeBaseTest {

	@InjectMocks
	private KnowledgeBase knowledgeBase;

	private List<String> validSentences = new ArrayList<String>()
	{{
		add("varon(juan).");
		add("varon(pepe).");
		add("varon(hector).");
		add("varon(roberto).");
		add("varon(alejandro).");
		add("mujer(maria) .");
		add("mujer(cecilia).");
		add("padre(juan, pepe).");
		add("padre(juan, pepa).");
		add("padre(hector, maria).");
		add("padre(roberto, alejandro).");
		add("padre(roberto, cecilia).");
		add("hijo(X, Y) :- varon(X), padre(Y, X).");
		add("hija(X, Y) :- mujer(X), padre(Y, X).");
		add("hermano(nicolas, roberto).");
		add("hermano(roberto, nicolas).");
		add("varon ( nicolas ) .");
		add("tio(X, Y, Z):- varon(X),	hermano(X, Z),padre(Z, Y).");
		add("tia(X, Y, Z):- mujer(X),	hermano(X, Z),padre(Z, Y).");		
	}};

	@Before
	public void setUp() throws Exception {
		//initMocks(this);
		//this.knowledgeBase = new KnowledgeBase("c:\\gaston\\facultad\\tecnicas-disenio\\7510-TP1-Java\\src\\main\\resources\\rules.db");
		this.knowledgeBase = new KnowledgeBase(validSentences);
	}

	@Test
	public void testExistentFactVaronJuan() {
		try{
			Assert.assertTrue(this.knowledgeBase.answer("varon (juan)."));
		}catch(InvalidFormatException ex){
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testExistentFactHermano() {
		try{
			Assert.assertTrue(this.knowledgeBase.answer("hermano(nicolas, roberto)."));
		}catch(InvalidFormatException ex){
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testExistentFactNameInexistentFactParameter() {
		try{
			Assert.assertFalse(this.knowledgeBase.answer("varon (javier)."));
		}catch(InvalidFormatException ex){
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testExistentFactNameWithTooManyArguments() {
		try{
			Assert.assertFalse(this.knowledgeBase.answer("varon (juan, pedro)."));
		}catch(InvalidFormatException ex){
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testExistentFactWithNotEnoughtArguments() {
		try{
			Assert.assertFalse(this.knowledgeBase.answer("hermano(nicolas)."));
		}catch(InvalidFormatException ex){
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testInexistentFact() {
		try{
			Assert.assertFalse(this.knowledgeBase.answer("varona(nicolas)."));
		}catch(InvalidFormatException ex){
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testFactWithoutCloseParentesis() {
		try{
			this.knowledgeBase.answer("varon(nicolas.");
			Assert.assertTrue(false);
		}catch(InvalidFormatException ex){
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testFactWithoutOpenParentesis() {
		try{
			this.knowledgeBase.answer("varon nicolas).");
			Assert.assertTrue(false);
		}catch(InvalidFormatException ex){
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void testFactWithoutName() {
		try{
			this.knowledgeBase.answer("(nicolas).");
			Assert.assertTrue(false);
		}catch(InvalidFormatException ex){
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testFactWithTooManyBlanksInTheMiddle() {
		try{
			Assert.assertTrue(this.knowledgeBase.answer("varon     (      nicolas     )."));
		}catch(InvalidFormatException ex){
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testExistentRuleHijoPepeJuan() {
		try{
			Assert.assertTrue(this.knowledgeBase.answer("hijo(pepe,juan)."));
		}catch(InvalidFormatException ex){
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testExistentRuleTio() {
		try{
			Assert.assertTrue(this.knowledgeBase.answer("tio(nicolas,alejandro,roberto)."));
		}catch(InvalidFormatException ex){
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testExistentRuleWithTooManyArguments() {
		try{
			this.knowledgeBase.answer("hijo(pepe,juan,pepa).");
		}catch(InvalidFormatException ex){
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testExistentRuleWithNotEnoughArguments() {
		try{
			this.knowledgeBase.answer("hijo(pepe).");
		}catch(InvalidFormatException ex){
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testRuleWithNoName() {
		try{
			this.knowledgeBase.answer("(pepe,juan).");
		}catch(InvalidFormatException ex){
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testExistentRuleBadParameters() {
		try{
			Assert.assertFalse(this.knowledgeBase.answer("hijo(roberta,juana)."));
		}catch(InvalidFormatException ex){
			Assert.assertTrue(false);
		}
	}

	@Test
	public void testNoiseCharacters(){
		String s = "hijo (roberto) ";
		String s2 = s.replaceAll(RegexCollection.strNoisyCharacters, "");
		Assert.assertTrue(s2.equals("hijo(roberto)"));
	}
}

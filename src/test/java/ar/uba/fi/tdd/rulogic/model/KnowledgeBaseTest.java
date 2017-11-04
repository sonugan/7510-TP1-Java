package ar.uba.fi.tdd.rulogic.model;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import ar.uba.fi.tdd.rulogic.exceptions.*;

public class KnowledgeBaseTest {

	@InjectMocks
	private KnowledgeBase knowledgeBase;

	@Before
	public void setUp() throws Exception {
		//initMocks(this);
		this.knowledgeBase = new KnowledgeBase("c:\\gaston\\facultad\\tecnicas-disenio\\7510-TP1-Java\\src\\main\\resources\\rules.db");
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
	public void testFactWithTooManyBlanksInTheMiddle() {
		try{
			Assert.assertTrue(this.knowledgeBase.answer("varon     (      nicolas     )."));
		}catch(InvalidFormatException ex){
			Assert.assertTrue(false);
		}
	}

}

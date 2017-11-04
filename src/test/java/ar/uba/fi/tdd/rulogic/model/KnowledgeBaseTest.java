package ar.uba.fi.tdd.rulogic.model;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

public class KnowledgeBaseTest {

	@InjectMocks
	private KnowledgeBase knowledgeBase;

	@Before
	public void setUp() throws Exception {
		//initMocks(this);
		this.knowledgeBase = new KnowledgeBase("c:\\gaston\\facultad\\tecnicas-disenio\\7510-TP1-Java\\src\\main\\resources\\rules.db");
	}

	@Test
	public void test() {

		Assert.assertTrue(this.knowledgeBase.answer("varon (juan)."));

	}

}

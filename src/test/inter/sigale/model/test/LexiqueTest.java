/**
 * 
 */
package inter.sigale.model.test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import inter.sigale.model.Lexique;
import inter.sigale.model.LexiqueFactory;

/**
 * @author a608505
 *
 */
public class LexiqueTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		LexiqueFactory.getInstance().getLexique();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() {
		Lexique lexique = LexiqueFactory.getInstance().getLexique();
		
	//	fail("Not yet implemented");
		assertTrue(lexique.getListUniteLexicale().size() > 0);
	}

}

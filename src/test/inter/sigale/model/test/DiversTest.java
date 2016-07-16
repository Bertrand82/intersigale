package inter.sigale.model.test;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import inter.sigale.util.UtilDivers;

public class DiversTest {

	

	@Test
	public void testTemoveAccent() {
		String s ="eиащ";
		String s2 = UtilDivers.removeAccents(s);
		assertEquals(s2, "eeau");
	}

}

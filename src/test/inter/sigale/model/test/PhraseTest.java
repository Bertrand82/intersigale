package inter.sigale.model.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import inter.sigale.model.Lexique;
import inter.sigale.model.LexiqueFactory;
import inter.sigale.model.Phrase;

public class PhraseTest {
	String text = "selected visible pouet";
	Phrase phrase = new Phrase("selected visible pouet");

	public PhraseTest() {
		this.phrase.setSelected(9, 16);
	}
	
	@Test
	public void testNoVisible() {
		this.phrase.setSelected(0, 0);
		assertTrue(this.phrase.getTextVisible().length() == 0);
	}
	
	@Test
	public void testVisible() {
		testVisible(0, 0);
		testVisible(0, 1);
		testVisible(5, 10);
	}
	public void testVisible(int start, int end) {
		try {
			this.phrase.setText(text);
			this.phrase.setSelected(start, end);
			System.out.println("start "+start+"  "+phrase.getwStartVisible(0)+" end "+end+"  "+this.phrase.getTextVisible());
			assertTrue(this.phrase.getTextVisible().replace('.', ' ').trim().length() == (end-start));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

package inter.sigale.model.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.simpleframework.xml.core.Persister;

import inter.sigale.model.Lexique;
import inter.sigale.model.Phrase;
import inter.sigale.model.UniteLexicale;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SerialisationDeserialisationLexiqueTest {

	
	Lexique lexique = new Lexique();
	File file = new File("TestSimpleXml.xml");
	
	
	public SerialisationDeserialisationLexiqueTest() {
		Phrase phrase_0_ = new Phrase("blablabllaaaa");
		Phrase phrase_1_ = new Phrase("Blobloblooooo");
		UniteLexicale uniteLexicale = new UniteLexicale(phrase_0_, phrase_1_);
		Phrase phrase_0__ = new Phrase("blablabllaaaa2");
		Phrase phrase_1__ = new Phrase("Blobloblooooo2");
		UniteLexicale uniteLexicale_2 = new UniteLexicale(phrase_0__, phrase_1__);
		lexique.add(uniteLexicale);
		lexique.add(uniteLexicale_2);
	}
	
	
	
	@Test
	public void test_1_Serialisation() throws Exception{
		System.out.println("bgTest0000");
		 Persister persister = new Persister();
	    //  File file = new File("example2/example2.xml");
	    //  Example example = persister.read(Example.class, file);
	      
	 
		persister.write(lexique, System.out);
		
		FileOutputStream fout = new FileOutputStream(file);
		persister.write(lexique, fout);
		fout.close();
		System.out.println("\n File exists "+file.exists()+" "+file.getAbsolutePath());
		System.out.println("Serialisation done ");
	}
	
	@Test
	public void test_2_DeSerialisation() throws Exception{
		System.out.println("Desiarisation start");
		Persister persister = new Persister();
		FileInputStream fIn = new FileInputStream(file);
		Lexique lexique2 = persister.read(Lexique.class, fIn);
		fIn.close();
		System.out.println("Desiarisation done "+lexique.equals(lexique2));
	}
	
}

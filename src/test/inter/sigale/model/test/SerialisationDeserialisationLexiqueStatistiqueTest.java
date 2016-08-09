package inter.sigale.model.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.junit.Test;
import org.simpleframework.xml.core.Persister;

import inter.sigale.model.Lexique;
import inter.sigale.model.statistic.StatistiquesLexique;
import inter.sigale.model.statistic.StatistiquesUL;

public class SerialisationDeserialisationLexiqueStatistiqueTest {

	
	
	StatistiquesLexique lexique = new StatistiquesLexique();
	File file = new File("TestStatSimpleXml.xml");
	
	
	public SerialisationDeserialisationLexiqueStatistiqueTest() {
		StatistiquesUL sul_1 = new StatistiquesUL();
		StatistiquesUL sul_2 = new StatistiquesUL();
		lexique.getListStatistiqueUL().add(sul_1);
		lexique.getListStatistiqueUL().add(sul_2);
	
	}
	
	
	@Test
	public void test_1_Serialisation() throws Exception{
		System.out.println("bgTest_1");
		 Persister persister = new Persister();
	 
		persister.write(lexique, System.out);
		
		FileOutputStream fout = new FileOutputStream(file);
		persister.write(lexique, fout);
		fout.close();
		System.out.println("\n File exists "+file.exists()+" "+file.getAbsolutePath());
		System.out.println("Serialisation done ");
	}
	
	@Test
	public void test_2_DeSerialisation() throws Exception{
		System.out.println("bgTest_2 Desiarisation start");
		Persister persister = new Persister();
		FileInputStream fIn = new FileInputStream(file);
		Lexique lexique2 = persister.read(Lexique.class, fIn);
		fIn.close();
		System.out.println("Desiarisation done "+lexique.equals(lexique2));
	}
	
}

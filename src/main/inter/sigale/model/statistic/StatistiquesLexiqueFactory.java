package inter.sigale.model.statistic;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import inter.sigale.model.Lexique;
import inter.sigale.model.LexiqueFactory;
import inter.sigale.model.UniteLexicale;

public class StatistiquesLexiqueFactory {

	private static StatistiquesLexiqueFactory instance;
	
	public synchronized  static StatistiquesLexiqueFactory getInstance(){
		if (instance == null){
			instance = new StatistiquesLexiqueFactory();
		}
		return instance;
	}
	
	public static synchronized StatistiquesLexique getStatistiquesLexique() {
		System.out.println("StatistiquesLexique getStatistiquesLexique ");
		StatistiquesLexique statistiquesLexique = new StatistiquesLexique();
		Lexique lexique = LexiqueFactory.getInstance().getLexique();
		for(UniteLexicale ul : lexique.getListUniteLexicale()) {
			statistiquesLexique.getListStatistiqueUL().add(ul.getStatistique());
		}
		return statistiquesLexique;
	}

	public static void setStatistiqueLexique(StatistiquesLexique statistiqueLexique) {
		System.out.println("setStatistiqueLexique ");
		Lexique lexique = LexiqueFactory.getInstance().getLexique();
		for(UniteLexicale ul : lexique.getListUniteLexicale()) {
			String id  = ul.getId();
			StatistiquesUL statistique = statistiqueLexique.getStatistiqueULById(id);
			ul.setStatistique(statistique);
		}
	}

	public static File getFileStatistiqueFromName(String name) {
		
		return new File(name+"_statistic.xml");
	}

	public static File getFileStatistique() {
		
		Lexique lexique= LexiqueFactory.getInstance().getLexique();
		File f =  getFileStatistiqueFromName(lexique.getName());
		System.out.println("getFileStatistique "+f.getAbsolutePath()+" exists :"+f.exists());
		return f;
	}
	
	private Marshaller marshaller;
	private Unmarshaller unmarshaler;


	private Marshaller getXmlMarshaller() throws Exception {
		if (marshaller == null) {
			JAXBContext jaxbContext = JAXBContext.newInstance(StatistiquesLexique.class);
			marshaller = jaxbContext.createMarshaller();
			// output pretty printed
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		}
		return marshaller;
	}

	private Unmarshaller getXmlUnMarshaller() throws Exception {
		if (unmarshaler == null) {
			JAXBContext jaxbContext = JAXBContext.newInstance(StatistiquesLexique.class);
			unmarshaler = jaxbContext.createUnmarshaller();
			// unmarshaler.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		}
		return unmarshaler;
	}

	public void readStatistics(File selectedFile) throws Exception {
		FileInputStream is = new FileInputStream(selectedFile);
		Object o = getXmlUnMarshaller().unmarshal(is);
		StatistiquesLexique statistiqueLexique = (StatistiquesLexique) o;
		StatistiquesLexiqueFactory.setStatistiqueLexique(statistiqueLexique);
		System.out.println("StatistiquesLexique : " + statistiqueLexique);
		System.out.println("StatistiquesLexique : " + StatistiquesLexiqueFactory.getStatistiquesLexique());
	}
	
	public void saveStatistic(File selectedFile) throws Exception {
		System.out.println("saveStatistic start");
		System.out.println("saveStatistic size : "
				+ StatistiquesLexiqueFactory.getStatistiquesLexique().getListStatistiqueUL().size());

		// jaxbMarshaller.marshal(LexiqueFactory.getLexique(), file);
		getXmlMarshaller().marshal(StatistiquesLexiqueFactory.getStatistiquesLexique(), System.out);
		getXmlMarshaller().marshal(StatistiquesLexiqueFactory.getStatistiquesLexique(), selectedFile);

		System.out.println("saveStatistic done " + selectedFile.getAbsolutePath());

	}
	public void fetchStatistique() {
		fetchStatistiqueLocalInFile();
	}
	public void fetchStatistiqueLocalInFile() {
		File file = StatistiquesLexiqueFactory.getFileStatistique();
		System.out.println("readStatistic "+file.getAbsolutePath()+" exists "+file.exists());
		try {
			StatistiquesLexiqueFactory.getInstance().readStatistics(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void saveStatisticCurrentLexique() {

		File file = StatistiquesLexiqueFactory.getFileStatistique();
		System.out.println("saveStatistice You chose to open this file: " + file.getAbsolutePath()+"  exists : "+file.exists());
		try {
			StatistiquesLexiqueFactory.getInstance().saveStatistic(file);
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

	
}

package inter.sigale.model;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import inter.sigale.UtilInterSigale;

public class LexiqueFactory {
	
	private Marshaller marshaller;
	private Unmarshaller unmarshaler;
	public static final String KEY_LexiqueName="intersigale.lexique.name";

	private  Lexique lexique;

	private static LexiqueFactory instance = new LexiqueFactory();
	
	public static LexiqueFactory getInstance(){
	   return instance;
	}
	public  Lexique getLexique() {
		if (lexique == null){
			initLexique();
		}
		return lexique;
	}

	private  void initLexique() {
		this.lexique = getLexiqueDefault();
		String lexiqueName = getLexiqueName();
		String name = System.getProperty(KEY_LexiqueName);
		System.out.println("Lexique name :"+name);
		File fileLexique = getFileLexique(name);
		
		if (fileLexique== null){
			return ;
		}else {
			System.out.println("file "+fileLexique.getAbsolutePath()+"  exists: "+fileLexique.exists());
			if(fileLexique.exists()){
				try {
					chooseLexique(fileLexique);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}
	private String getLexiqueName() {		
		return System.getProperty(KEY_LexiqueName);
	}
	private static Lexique getLexiqueDefault() {
		Lexique lexique = new Lexique();
		lexique.add( new UniteLexicale( new Phrase("Qui a créé intersigale ?"),new Phrase("Bertrand")));
		lexique.add( new UniteLexicale( new Phrase("Pourquoi il a fait ça ?"),new Phrase("Pour apprendre")));
		lexique.add( new UniteLexicale( new Phrase("Pour apprendre quoi ?"),new Phrase("Tout")));
		return lexique;
	}

	
	
	public void saveLexique(File selectedFile) throws Exception {
		System.out.println("saveLexique 1");
		
		// jaxbMarshaller.marshal(LexiqueFactory.getLexique(), file);
		getXmlMarshaller().marshal(getLexique(), System.out);
		getXmlMarshaller().marshal(getLexique(), selectedFile);

		System.out.println("saveLexique done "+selectedFile.getAbsolutePath());

	}
	
	
	public void chooseLexique(File selectedFile) throws Exception{
		FileInputStream is = new FileInputStream(selectedFile);
		Object o = getXmlUnMarshaller().unmarshal(is);
		this.lexique = (Lexique) o;
		System.out.println("lexique : "+lexique);
	}
	
	private Marshaller getXmlMarshaller() throws Exception {
		if (marshaller == null){
			JAXBContext jaxbContext = JAXBContext.newInstance(Lexique.class);
			marshaller = jaxbContext.createMarshaller();
			// output pretty printed
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		}
		return marshaller;
	}
	
	private Unmarshaller getXmlUnMarshaller() throws Exception {
		if (unmarshaler == null){
			JAXBContext jaxbContext = JAXBContext.newInstance(Lexique.class);
			unmarshaler = jaxbContext.createUnmarshaller();
	//		unmarshaler.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		}
		return unmarshaler;
	}
	/**
	 * 
	 * @param name
	 * @throws Exception
	 */
	public void createLexique(String name) throws Exception{
		this.lexique = new Lexique();
		lexique.setName(name);
		File file = getFileLexique(name);
		this.saveLexique(file);
		UtilInterSigale.saveProperties(KEY_LexiqueName, name);
	}
	/**
	 * 
	 * @param name
	 * @return
	 */
	private File getFileLexique(String name){
		if (name == null){
			return null;
		}
		return new File(name+"_lexique.xml");
	}
	/**
	 * 
	 * @throws Exception
	 */
	public void saveLexique() throws Exception{
		String name = this.lexique.getName();
		File f = getFileLexique(name);
		this.saveLexique(f);
	}
	public void saveItem(UniteLexicale item) {
		try {
			this.saveLexique();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}

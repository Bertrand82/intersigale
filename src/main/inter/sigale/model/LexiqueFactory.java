package inter.sigale.model;

import java.io.File;
import java.io.FileInputStream;

import org.simpleframework.xml.core.Persister;

import inter.sigale.UtilInterSigale;
import inter.sigale.model.statistic.StatistiquesLexiqueFactory;

public class LexiqueFactory {
	private Persister persister = new Persister();
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
		this.lexique = null;
		String lexiqueName = getLexiqueName();
		
		
		File fileLexique = getFileLexique(lexiqueName);
		System.out.println("Lexique name :"+lexiqueName+"   "+fileLexique);
		if (fileLexique== null){
			lexique = getLexiqueDefault();
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
		lexique.add( new UniteLexicale( new Phrase("Qui a cr�� intersigale ?"),new Phrase("Bertrand")));
		lexique.add( new UniteLexicale( new Phrase("Pourquoi il a fait �a ?"),new Phrase("Pour apprendre")));
		lexique.add( new UniteLexicale( new Phrase("Pour apprendre quoi ?"),new Phrase("Tout")));
		return lexique;
	}

	
	
	public void saveLexique(File selectedFile) throws Exception {
		System.out.println("saveLexique 1");
		
		// jaxbMarshaller.marshal(LexiqueFactory.getLexique(), file);
		persister.write(getLexique(), System.out);
		persister.write(getLexique(), selectedFile);

		System.out.println("saveLexique done "+selectedFile.getAbsolutePath());

	}
	
	
	public void chooseLexique(File selectedFile) throws Exception{
		FileInputStream is = new FileInputStream(selectedFile);
		this.lexique = persister.read(Lexique.class, is);
		System.out.println("lexique : "+lexique);
		fetchStatistique();
	}
	
	private void fetchStatistique() {
		StatistiquesLexiqueFactory.getInstance().fetchStatistique();
		
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
	/**
	 * 
	 * @param item
	 */
	public void saveItem(UniteLexicale item) {
		try {
			this.saveLexique();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}

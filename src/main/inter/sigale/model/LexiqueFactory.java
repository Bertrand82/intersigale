package inter.sigale.model;

import java.io.File;
import java.io.FileInputStream;

import org.simpleframework.xml.core.Persister;

import inter.sigale.UtilInterSigale;
import inter.sigale.model.statistic.StatistiquesLexiqueFactory;
import inter.sigale.util.ILogListener;

public class LexiqueFactory implements ILogListener{
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
		log("Start Loading "+lexiqueName);
		
		File fileLexique = getFileLexique(lexiqueName);
		logTitle(lexiqueName);
		System.out.println("Lexique name :"+lexiqueName+"   "+fileLexique);
		if (fileLexique== null){
			log("No file ");
			
			lexique = getLexiqueDefault();
		}else {
			log("file "+fileLexique.getAbsolutePath()+"  exists: "+fileLexique.exists());
			
			if(fileLexique.exists()){
				try {
					chooseLexique(fileLexique);
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					log("Exception "+e.getMessage());
				}
			}else{
				log("No file Lexique");
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
		System.out.println("saveLexique 1 "+selectedFile.getPath());
		
		// jaxbMarshaller.marshal(LexiqueFactory.getLexique(), file);
		persister.write(getLexique(), System.out);
		persister.write(getLexique(), selectedFile);

		log("saveLexique done "+selectedFile.getAbsolutePath());

	}
	
	
	public void chooseLexique(File selectedFile) throws Exception{
		FileInputStream is = new FileInputStream(selectedFile);
		this.lexique = persister.read(Lexique.class, is);
		log("lexique : "+lexique);
		logTitle(this.lexique.getName());
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
	
	private ILogListener logListener;
	
	public void setLogListener(ILogListener logListener_) {
		this.logListener = logListener_;
	}
	public void log(String s) {
		if(logListener != null){
			logListener.log(s);
		}
		
	}
	public void logTitle(String s) {
		if(logListener != null){
			logListener.logTitle(s);
		}
	}
	
	

}

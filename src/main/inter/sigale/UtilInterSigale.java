package inter.sigale;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import inter.sigale.model.LexiqueFactory;

public class UtilInterSigale {
	
	public static final File fileProperties = new File("intersigale.properties");
	private static  Properties properties; 
      public static void initProperties(){
    	  Properties p = getProperties();
    	  // Si on a passé en ligne de commande la property, lors on ne l'ecrase pas
    	  if (System.getProperty(LexiqueFactory.KEY_LexiqueName) == null){
    		  // Si cette property est definie alors on la met dans system
    		  if (properties.getProperty(LexiqueFactory.KEY_LexiqueName) != null)
    		  System.setProperty(LexiqueFactory.KEY_LexiqueName, properties.getProperty(LexiqueFactory.KEY_LexiqueName));
    	  }
      }
      
      
    private static Properties  getProperties() {
    	if (properties == null){
    	  properties = new Properties();
    	  try {
    		  if (fileProperties.exists()){
			FileInputStream in = new FileInputStream(fileProperties);
			  properties.load(in);
			  in.close();
    		  }else {
    			  System.out.println("FIle Properties doen't exist "+fileProperties.getAbsolutePath());
    		  }
		}catch (Exception e) {
			e.printStackTrace();
		}
    	}
    	  return properties;
      }
    
    public static void saveProperties(String key, String value){
    	getProperties().setProperty(key, value);
    	System.getProperties().setProperty(key, value);
    	try {
			FileOutputStream out = new FileOutputStream(fileProperties);
			properties.store(out, "intersigale");
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	System.out.println("saveProperties "+key+"  "+value+"  "+fileProperties.getAbsolutePath()+"  exist :"+fileProperties.exists());
    }
}

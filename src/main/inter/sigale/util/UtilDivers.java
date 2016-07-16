package inter.sigale.util;

import java.text.Normalizer;

public class UtilDivers {

	public static String removeAccents(String s) {
		if (s == null){
			return "";
		}
	    s = Normalizer.normalize(s, Normalizer.Form.NFD);
	    s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
	    return s;
	}
	
}

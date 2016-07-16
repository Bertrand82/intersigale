package inter.sigale;

import inter.sigale.model.swing.MainFrameUI;

public class MainGUISwing {

	public static void main(String[] s){
		System.out.println("Start UI");
		UtilInterSigale.initProperties();
		new MainFrameUI();
	}
	
	
}

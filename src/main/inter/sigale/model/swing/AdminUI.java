package inter.sigale.model.swing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import inter.sigale.model.LexiqueFactory;

public class AdminUI implements ISwingable {

	private JPanel panelGlobal = new JPanel();
	JPanel panelCommand = new JPanel();
	private JButton buttonChooseLexique = new JButton("Choose Lexique");
	private JButton buttonSaveLexique = new JButton("Save Lexique");
	private JButton buttonSaveLexiqueIn = new JButton("Save Lexique in ...");
	private JButton buttonCreateLexique = new JButton("Create Lexique");
	private JFileChooser chooser = new JFileChooser();
	public AdminUI() {
		buttonChooseLexique.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.out.println("ChooseLexique action");
				chooseLexique();
			}
		});
	    buttonSaveLexiqueIn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.out.println("SaveLexique action");
				saveLexiqueIn();
			}
		});
		buttonSaveLexique.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.out.println("SaveLexique action");
				saveLexique();
			}
		});
		buttonCreateLexique.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.out.println("CreateLexique action");
				createLexique();
			}
		});

		
		panelCommand.setLayout(new BoxLayout(panelCommand, BoxLayout.PAGE_AXIS));
		panelCommand.add(buttonChooseLexique);
		panelCommand.add(buttonSaveLexique);
		panelCommand.add(buttonSaveLexiqueIn);
		panelCommand.add(buttonCreateLexique);
		panelGlobal.add(panelCommand);
	}

	private void chooseLexique() {

		FileNameExtensionFilter filter = new FileNameExtensionFilter("xml", "xml");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(panelGlobal);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
			try {
				LexiqueFactory.getInstance().chooseLexique(chooser.getSelectedFile());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void saveLexiqueIn() {

		FileNameExtensionFilter filter = new FileNameExtensionFilter("xml", "xml");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(panelGlobal);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
			try {
				LexiqueFactory.getInstance().saveLexique(chooser.getSelectedFile());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}



	private void saveLexique() {
		try {
			LexiqueFactory.getInstance().saveLexique();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private void createLexique() {
		String name = JOptionPane.showInputDialog(this.panelGlobal, "Lexique name :");
		System.out.println("name " + name);
		try {
			LexiqueFactory.getInstance().createLexique(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public JPanel getPanelGlobal() {
		return panelGlobal;

	}

	
	public void setBackground(Color bg, Color bg2) {
		this.panelGlobal.setBackground(bg);
		this.panelCommand.setBackground(bg);
	}

	
}

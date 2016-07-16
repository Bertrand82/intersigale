package inter.sigale.model.swing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import inter.sigale.model.Lexique;
import inter.sigale.model.LexiqueFactory;

public class AdminUI {

	private JPanel panelGlobal = new JPanel();
	private JButton buttonChooseLexique = new JButton("Choose Lexique");
	private JButton buttonSaveLexique = new JButton("Save Lexique");
	private JButton buttonCreateLexique = new JButton("Create Lexique");
	private JFileChooser chooser = new JFileChooser();
	public AdminUI() {
		buttonChooseLexique.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("ChooseLexique action");
				chooseLexique();
			}
		});

		buttonSaveLexique.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("SaveLexique action");
				saveLexique();
			}
		});
		buttonCreateLexique.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("CreateLexique action");
				createLexique();
			}
		});

		JPanel panelCommand = new JPanel();
		panelCommand.setLayout(new BoxLayout(panelCommand, BoxLayout.PAGE_AXIS));
		panelCommand.add(buttonChooseLexique);
		panelCommand.add(buttonSaveLexique);
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

	
}

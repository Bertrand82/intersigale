package inter.sigale.model.swing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import inter.sigale.model.statistic.StatistiquesLexiqueFactory;

public class DebugUI implements ISwingable{

	JPanel panelGlobal = new JPanel();
	JPanel panelCommand = new JPanel();
	JButton buttonReadStatisticFromFile = new JButton("Read Statistics from File");
	JButton buttonSaveStatisticInFile = new JButton("Save Statistics in File");
	JButton buttonReadStatistic = new JButton("Read Statistics");
	JButton buttonSaveStatistic = new JButton("Save Statistics ");
	JFileChooser chooser = new JFileChooser();

	public DebugUI() {
		buttonReadStatisticFromFile.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.out.println("ReadStatisticeFromFile action");
				readStatisticFromFile();
			}
		});
		buttonReadStatistic.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.out.println("ReadStatistice action");
				StatistiquesLexiqueFactory.getInstance().fetchStatistiqueLocalInFile();
			}
		});
		buttonSaveStatistic.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.out.println("SaveStatistic action");
				StatistiquesLexiqueFactory.getInstance().saveStatisticCurrentLexique();
			}
		});
		buttonSaveStatisticInFile.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.out.println("SaveStatisticInFile action");
				saveStatisticInFile();
			}
		});

		
		panelCommand.setLayout(new BoxLayout(panelCommand, BoxLayout.PAGE_AXIS));
		panelCommand.add(buttonReadStatisticFromFile);
		panelCommand.add(buttonSaveStatisticInFile);
		panelCommand.add(buttonReadStatistic);
		panelCommand.add(buttonSaveStatistic);
		panelGlobal.add(panelCommand);
	}

	private void readStatisticFromFile() {

		FileNameExtensionFilter filter = new FileNameExtensionFilter("xml", "xml");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(panelGlobal);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
			try {
				StatistiquesLexiqueFactory.getInstance().readStatistics(chooser.getSelectedFile());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}




	private void saveStatisticInFile() {

		FileNameExtensionFilter filter = new FileNameExtensionFilter("xml", "xml");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(panelGlobal);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("saveStatisticeInFile You chose to open this file: " + chooser.getSelectedFile().getName());
			try {
				StatistiquesLexiqueFactory.getInstance().saveStatistic(chooser.getSelectedFile());
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
	}

	

	
	
	public JPanel getPanelGlobal() {
		return panelGlobal;

	}

	
	public void setBackground(Color bg,Color bg2) {
		this.panelGlobal.setBackground(bg);
		this.panelCommand.setBackground(bg);
		this.buttonReadStatistic.setBackground(bg2);
		this.buttonSaveStatistic.setBackground(bg2);
		this.buttonSaveStatisticInFile.setBackground(bg2);
		this.buttonReadStatisticFromFile.setBackground(bg2);
	}

}

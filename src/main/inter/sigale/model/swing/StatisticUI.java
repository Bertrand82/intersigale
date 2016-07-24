package inter.sigale.model.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import inter.sigale.model.Lexique;
import inter.sigale.model.LexiqueFactory;

public class StatisticUI implements ISwingable {

	
	JPanel panelGlobal = new JPanel();
	JPanel panelGrid = new JPanel(new GridLayout(0,1,20,6));
	JLabel labelNbMtsOk = new JLabel("10");
	JLabel labelNbMotKO = new JLabel("2");
	JLabel labelNbMots= new JLabel("2");


	public StatisticUI() {
		
		panelGrid.add(getPanel("Nb Mots",labelNbMots));
		panelGrid.add(getPanel("test OK",labelNbMtsOk));
		panelGrid.add(getPanel("test KO",labelNbMotKO));
		initStatistique();
		panelGlobal.add(panelGrid);
	}
	private JPanel getPanel(String text, JLabel labelValue) {
		JPanel panel = new JPanel(new BorderLayout());
		JLabel label = new JLabel(text+" ");
		panel.add(label,BorderLayout.CENTER);
		panel.add(labelValue,BorderLayout.EAST);
		return panel;
	}
	public JPanel getPanelGlobal() {
		return panelGlobal;
	}
	
	private void initStatistique(){
		Lexique lexique = LexiqueFactory.getInstance().getLexique();
		int nbUniteLexicaleTotal = lexique.getListUniteLexicale().size();
		int nbUniteLexicaleOK = lexique.getNbUniteLexicale(true);
		int nbUniteLexicaleKO = lexique.getNbUniteLexicale(false);
		this.labelNbMotKO.setText(""+nbUniteLexicaleKO);
		this.labelNbMtsOk.setText(""+nbUniteLexicaleOK);
		this.labelNbMots.setText(""+nbUniteLexicaleTotal);
	}
	@Override
	public void setBackground(Color bg, Color bg2) {
		this.panelGlobal.setBackground(bg);
		this.panelGrid.setBackground(bg);
	}
}

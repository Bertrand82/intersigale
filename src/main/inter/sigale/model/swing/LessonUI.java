package inter.sigale.model.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import inter.sigale.model.Lexique;
import inter.sigale.model.LexiqueFactory;
import inter.sigale.model.UniteLexicale;

public class LessonUI  {
	
	Color colorNeutre = Color.YELLOW;
	Color colorOK = Color.GREEN;
	Color colorKO = new Color(0xFF, 0x10,0x19);
	private static String STR_VIDE= " ";
	
	JPanel panelGlobal = new JPanel(new BorderLayout());
	JPanel panelPhrases = new JPanel();
	JLabel labelRequest = new JLabel("Request");
	JLabel labelStat = new JLabel("");
	JButton buttonValidResult = new JButton("OK");
	JButton buttonNext = new JButton("Next");
	JTextField textFieldResponse = new JTextField(20);
	JLabel labelCorrection = new JLabel(STR_VIDE);
	public LessonUI() {
	    LexiqueFactory.getInstance().getLexique();
		buttonValidResult.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				validResult();
				
			}
		});
		buttonNext.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				nextPhrase();
				textFieldResponse.requestFocusInWindow();
			}
		});
		
		textFieldResponse.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//System.out.println("textField.action command: "+e.getActionCommand()+"  modifier: "+e.getModifiers());
				validResult();
			}
		});
		JPanel panelButtons = new JPanel();
		panelButtons.add(buttonValidResult);
		panelButtons.add(buttonNext);
		JPanel panelCorrection = new JPanel(new BorderLayout());
		panelCorrection.add(labelCorrection,BorderLayout.WEST);
		panelCorrection.add(new JLabel(),BorderLayout.CENTER);
		labelStat.setBackground(colorNeutre);
		labelStat.setOpaque(true);
		
		panelPhrases.setLayout(new BoxLayout(panelPhrases, BoxLayout.Y_AXIS));
		panelPhrases.add(labelRequest);
		panelPhrases.add(textFieldResponse);
		panelPhrases.add(panelCorrection);
		panelPhrases.add(panelButtons);
		panelPhrases.add(labelStat);
		
		panelGlobal.add(panelPhrases,BorderLayout.NORTH);
		panelGlobal.add(new JLabel(),BorderLayout.CENTER);
		displayUniteLexicaleCourante();
		setButtonsEtat(true);
	}
	public JPanel getPanelGlobal() {
		return panelGlobal;
	}

	private void validResult() {
		UniteLexicale ul =  getLexique().getUniteLexicaleCourante();
		if (ul != null){
		String text = ul.getPhrase_1().getText();
		UniteLexicale ulCourrante =  getLexique().getUniteLexicaleCourante();
		boolean ok = ulCourrante.resultProcess(this.textFieldResponse.getText());
		String stat = ulCourrante.getStatistiqueResume();
		System.out.println("ValidResult "+ok);
		//this.textFieldResponse.setText(text);
		setButtonsEtat(false);
		if (!ok){
			System.out.println("p0 :"+getLexique().getUniteLexicaleCourante().getPhrase_0().getText());
			System.out.println("p1 :"+getLexique().getUniteLexicaleCourante().getPhrase_1().getText());
			System.out.println("p2 :"+textFieldResponse.getText());
			labelCorrection.setText(getLexique().getUniteLexicaleCourante().getPhrase_1().getText());
		}
		
		displayResult(ok, stat);
		}
	}
	
	private void nextPhrase() {
		System.out.println("NextPhrase ");
		getLexique().next();
		displayUniteLexicaleCourante();
		labelCorrection.setText(STR_VIDE);
		displayResult(null," ");
		this.textFieldResponse.setText("");
		setButtonsEtat(true);
	}
	
	private void setButtonsEtat(boolean b){
		this.buttonValidResult.setEnabled(b);
		this.buttonNext.setEnabled(!b);

	}
	void displayUniteLexicaleCourante(){
		UniteLexicale ul = getLexique().getUniteLexicaleCourante();
		if (ul != null){
			labelRequest.setText(ul.getPhrase_0().getText());
		}
	}
	
	private void displayResult(Boolean ok, String stat){
		Color color ;
		if (ok == null){
			color = colorNeutre;
		}else if (ok){
			color = colorOK;
		}else {
			color = colorKO;
		}
		textFieldResponse.setBackground(color);
		labelStat.setBackground(color);
		labelStat.setText(stat);
	}
	
	private Lexique getLexique() {
		return  LexiqueFactory.getInstance().getLexique();
	}
}

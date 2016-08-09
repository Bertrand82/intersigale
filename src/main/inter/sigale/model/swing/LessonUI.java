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

public class LessonUI implements ISwingable  {
	
	Color colorNeutre = ConstantesSwing.color_start;
	Color colorOK =  ConstantesSwing.color_ok;
	Color colorKO = ConstantesSwing.color_err;;
	private static String STR_VIDE= " ";
	
	JPanel panelGlobal = new JPanel(new BorderLayout());
	JPanel panelPhrases = new JPanel();
	JPanel panelButtons = new JPanel();
	JPanel panelCorrection = new JPanel(new BorderLayout());
	JLabel labelRequest = new JLabel("Request");
	JLabel labelStat = new JLabel("xx");
	JButton buttonValidResult = new JButton("OK");
	JButton buttonNext = new JButton("Next");
	JTextField textFieldResponse = new JTextField(30);
	JLabel labelCorrection = new JLabel(" ");
	StatistiquePanel statistiquePanel = new StatistiquePanel();
	public LessonUI() {
		 LexiqueFactory.getInstance().getLexique();
		buttonValidResult.addActionListener(new ActionListener() {			
			
			public void actionPerformed(ActionEvent e) {
				validResult();
				
			}
		});
		buttonNext.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				nextPhrase();
				textFieldResponse.requestFocusInWindow();
			}
		});
		
		textFieldResponse.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				//System.out.println("textField.action command: "+e.getActionCommand()+"  modifier: "+e.getModifiers());
				validResult();
			}
		});
		
		panelButtons.add(buttonValidResult);
		panelButtons.add(buttonNext);
		
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
		panelPhrases.add(statistiquePanel);
		
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
		String stat = " ? ";
		this.statistiquePanel.updateStat(ulCourrante);
		System.out.println("ValidResult "+ok);
		//this.textFieldResponse.setText(text);
		setButtonsEtat(false);
		if (ok){
			stat = " ok ";
		}else {
			stat = " ko ";
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
		this.statistiquePanel.updateStat(null);
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
	
	
	public void setBackground(Color bg, Color bg2) {
		this.panelGlobal.setBackground(bg);
		this.panelButtons.setBackground(bg);
		this.panelCorrection.setBackground(bg);
		this.panelPhrases.setBackground(bg);
		this.statistiquePanel.setBackground(bg,bg2);
	}
}

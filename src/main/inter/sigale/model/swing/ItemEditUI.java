package inter.sigale.model.swing;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import inter.sigale.model.Lexique;
import inter.sigale.model.LexiqueFactory;
import inter.sigale.model.Phrase;
import inter.sigale.model.UniteLexicale;

public class ItemEditUI implements ISwingable {

	int i_deprecated = 0;

	UniteLexicale item;
	JPanel panelGlobal = new JPanel();
	JPanel panelAux = new JPanel();
	JTextField textField_0 = new JTextField(20);
	JTextField textField_1 = new JTextField(20);
	JTextField textField_2 = new JTextField(20);
	JButton buttonRecord = new JButton("Record");
	JButton buttonNext = new JButton(">");
	JButton buttonPrevious = new JButton("<");
	JButton buttonNew = new JButton("New");
	JLabel labelLog = new JLabel("");
	JTextField labelStatistic = new JTextField(20);

	public ItemEditUI() {
		
		super();
		textField_1.setCaret(new CaretHighlight());
		textField_2.setEditable(false);
		
		labelStatistic.setEditable(false);
		this.panelGlobal.addComponentListener(new ComponentListener() {
			
			
			public void componentShown(ComponentEvent e) {
				init();
			}
			
			
			public void componentResized(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		buttonNext.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				nextItem(1);
			}
		});
		buttonPrevious.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				nextItem(-1);
			}
		});
		buttonNew.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				createNew();				
			}
		});
		buttonRecord.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				record();
			}
		});
		
		panelAux.setLayout(new BoxLayout(panelAux, BoxLayout.PAGE_AXIS));
		panelAux.add(new JLabel("Question"));
		panelAux.add(textField_0);
		panelAux.add(new JLabel("Answer"));
		panelAux.add(textField_1);
		panelAux.add(new JLabel("Appearance"));
		panelAux.add(textField_2);
		panelAux.add(new JLabel(""));
		panelAux.add(buttonRecord);
		panelAux.add(new JLabel("Stat:"));
		panelAux.add(labelStatistic);
		
		JPanel panelNextPrevious = new JPanel();
		panelNextPrevious.setLayout(new BoxLayout(panelNextPrevious, BoxLayout.LINE_AXIS));
		panelNextPrevious.add(buttonPrevious);
		panelNextPrevious.add(new JLabel(" "));
		panelNextPrevious.add(buttonNext);
		panelNextPrevious.add(buttonNew);
		panelAux.add(panelNextPrevious);
		panelAux.add(labelLog);
		panelGlobal.setLayout(new BoxLayout(panelGlobal, BoxLayout.PAGE_AXIS));
		panelGlobal.add(panelAux);
		displayLog();
	}

	public JPanel getPanelGlobal() {
		return panelGlobal;
	}

	private Lexique getLexique() {
		return LexiqueFactory.getInstance().getLexique();
	}

	private void nextItem(int n) {
		if (checkIfRecordedOK()) {
			
			this.item = getLexique().next(n);
			
			displayItem();
			displayLog();
		}
		
	}
	
	private void init() {
		System.out.println("componentShown -------------------");
		this.item = LexiqueFactory.getInstance().getLexique().getUniteLexicaleCourante();
		displayItem();
	    displayLog();
	}

	private void displayItem() {
		this.textField_1.setSelectionStart(0);
		this.textField_1.setSelectionEnd(0);
		if (this.item == null) {
			this.textField_0.setText("");
			this.textField_1.setText("");
			this.textField_2.setText("");
			this.labelStatistic.setText("");
		} else {
			this.textField_0.setText(item.getPhrase_0().getText());	
			this.textField_1.setText(item.getPhrase_1().getText());
			this.textField_2.setText(item.getPhrase_1().getTextVisible());
			 int startSelected = item.getPhrase_1().getwStartVisible(0);
			 int endSelected = item.getPhrase_1().getEndVisible(0);
			 System.out.println("isShowText : "+item.getPhrase_1().isShowText()+" | startSelected "+startSelected+"  endSelected "+endSelected+" | listVvisible.size : "+item.getPhrase_1().getListVisible().size());
			if (item.getPhrase_1().isShowText()){
				 this.textField_1.setSelectionStart(startSelected);
				 this.textField_1.setSelectionEnd(endSelected);
				 this.textField_1.select(startSelected, endSelected);
				 this.textField_1.requestFocus();
			}
			 System.out.println("isShowText : xxx | startSelected "+textField_1.getSelectionStart()+"  endSelected "+textField_1.getSelectionEnd()+" | text "+textField_1.getSelectedText());
			 
			this.labelStatistic.setText(item.getStatistiqueResume());
		}
	}

	private void record() {
		System.out.println("Record ");
		if (this.item == null) {
			this.item = new UniteLexicale(new Phrase(), new Phrase());
			getLexique().getListUniteLexicale().add(item);
			displayLog();
		}
		this.item.getPhrase_0().setText(textField_0.getText());
		this.item.getPhrase_1().setText(textField_1.getText());
		String selectedTExt = textField_1.getSelectedText();
		System.out.println(" selectedTExt "+selectedTExt);
		int selectionStart = textField_1.getSelectionStart();
		int selectionEnd = textField_1.getSelectionEnd();
		System.out.println(" selectedTExt SelectionStart "+textField_1.getSelectionStart());
		System.out.println(" selectedTExt SelectionEnd "+textField_1.getSelectionEnd());
		this.item.getPhrase_1().setSelected(selectionStart, selectionEnd);
		LexiqueFactory.getInstance().saveItem(this.item);
		
	}

	private void displayLog() {
		Lexique lexique = LexiqueFactory.getInstance().getLexique();
		this.labelLog.setText(" " + (lexique.getiCourrante() + 1) + " / " + getLexique().getListUniteLexicale().size());
	}

	

	/**
	 * 
	 * @return
	 */
	private boolean checkIfRecordedOK() {
		boolean isTheSame = isTheSame();
		System.out.println("  isTheSame "+isTheSame);
		if (!isTheSame ) {
			int n = JOptionPane.showConfirmDialog(this.panelGlobal, "Save modifications ?",
					"An Inane Question", JOptionPane.YES_NO_CANCEL_OPTION);
			if (n==JOptionPane.YES_OPTION){
				record();
				return true;
			}else if (n== JOptionPane.CANCEL_OPTION){
				return false;
			}else {// No_Option 
				return true;
			}

		}

		return true;
	}

	private boolean isTheSame() {
		UniteLexicale ul = this.item;
		if (ul == null) {
			ul = new UniteLexicale(new Phrase(""), new Phrase(""));
		}
		boolean b_0 = this.textField_0.getText().equals(ul.getPhrase_0().getText());
		boolean b_1 = this.textField_1.getText().equals(ul.getPhrase_1().getText());
		return b_0 && b_1;
	}
	
	private void createNew(){
		if (checkIfRecordedOK()) {
			item = null;
			displayItem();
		}
	}
	
	public void setBackground(Color bg, Color bg2) {
		this.panelGlobal.setBackground(bg);
		this.panelAux.setBackground(bg);
	}
}

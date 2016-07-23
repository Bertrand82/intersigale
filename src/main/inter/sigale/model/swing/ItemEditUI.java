package inter.sigale.model.swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import inter.sigale.model.Lexique;
import inter.sigale.model.LexiqueFactory;
import inter.sigale.model.Phrase;
import inter.sigale.model.UniteLexicale;

public class ItemEditUI {

	int i_deprecated = 0;

	UniteLexicale item;
	JPanel panelGlobal = new JPanel();
	JTextField textField_0 = new JTextField(20);
	JTextField textField_1 = new JTextField(20);
	JButton buttonRecord = new JButton("Record");
	JButton buttonNext = new JButton(">");
	JButton buttonPrevious = new JButton("<");
	JButton buttonNew = new JButton("New");
	JLabel labelLog = new JLabel("");
	JTextField labelStatistic = new JTextField(20);

	public ItemEditUI() {
		
		super();
		labelStatistic.setEditable(false);
		this.panelGlobal.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {
				init();
			}
			
			@Override
			public void componentResized(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void componentHidden(ComponentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		buttonNext.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nextItem(1);
			}
		});
		buttonPrevious.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				nextItem(-1);
			}
		});
		buttonNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				createNew();				
			}
		});
		buttonRecord.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				record();
			}
		});
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.add(new JLabel("Question"));
		panel.add(textField_0);
		panel.add(new JLabel("Answer"));
		panel.add(textField_1);
		panel.add(new JLabel(""));
		panel.add(buttonRecord);
		panel.add(new JLabel("Stat:"));
		panel.add(labelStatistic);
		
		JPanel panelNextPrevious = new JPanel();
		panelNextPrevious.setLayout(new BoxLayout(panelNextPrevious, BoxLayout.LINE_AXIS));
		panelNextPrevious.add(buttonPrevious);
		panelNextPrevious.add(new JLabel(" "));
		panelNextPrevious.add(buttonNext);
		panelNextPrevious.add(buttonNew);
		panel.add(panelNextPrevious);
		panel.add(labelLog);
		panelGlobal.setLayout(new BoxLayout(panelGlobal, BoxLayout.PAGE_AXIS));
		panelGlobal.add(panel);
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
		if (this.item == null) {
			this.textField_0.setText("");
			this.textField_1.setText("");
			this.labelStatistic.setText("");
		} else {
			this.textField_0.setText(item.getPhrase_0().getText());
			this.textField_1.setText(item.getPhrase_1().getText());
			this.labelStatistic.setText(item.getStatistiqueResume());
		}
	}

	private void record() {
		if (this.item == null) {
			this.item = new UniteLexicale(new Phrase(), new Phrase());
			getLexique().getListUniteLexicale().add(item);
			displayLog();
		}
		this.item.getPhrase_0().setText(textField_0.getText());
		this.item.getPhrase_1().setText(textField_1.getText());
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
}

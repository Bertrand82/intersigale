package inter.sigale.model.swing;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import inter.sigale.model.LexiqueFactory;
import inter.sigale.model.statistic.StatistiquesLexiqueFactory;
import inter.sigale.util.ILogListener;


public class MainFrameUI implements ILogListener{

	private static final boolean debug = true;
	JFrame frame = new JFrame();
	JTabbedPane tabbedPane = new JTabbedPane();
	JLabel labelLog = new JLabel("labelLog");
	final LessonUI lessonUI ;
	final AdminUI adminUI ;
	final ItemEditUI itemEditUI;
	final StatisticUI statisticUI ;
	final DebugUI debugUI ;

	public MainFrameUI() {
		frame.setTitle("InterSigale");
		LexiqueFactory.getInstance().setLogListener(this);
		lessonUI = new LessonUI();
		adminUI = new AdminUI();
		itemEditUI = new ItemEditUI();
		statisticUI = new StatisticUI();
		debugUI = new DebugUI();
		setFontSize();
		tabbedPane.setBackground(ConstantesSwing.color_bg);
		initTab("Lesson", lessonUI);
		initTab("Admin", adminUI);
		initTab("Edit", itemEditUI);
		initTab("Stat", statisticUI);
		if (debug) {
			initTab("Debug", debugUI);
			//tabbedPane.addTab("debug", debugUI.getPanelGlobal());
		}
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				StatistiquesLexiqueFactory.getInstance().saveStatisticCurrentLexique();
				System.exit(0);
			}
		});
		JPanel panelGlobal = new JPanel(new BorderLayout());
		panelGlobal.add(tabbedPane,BorderLayout.NORTH);
		
		panelGlobal.add(labelLog, BorderLayout.CENTER);
		frame.add(panelGlobal);
		frame.setBackground(ConstantesSwing.color_bg);
		
		SwingUtilities.updateComponentTreeUI(frame);
		frame.pack();
		frame.setVisible(true);
		
	}

	
	
	private void initTab(String title, ISwingable swingable) {
		swingable.setBackground(ConstantesSwing.color_bg, ConstantesSwing.color_bg_button);
		tabbedPane.addTab(title, swingable.getPanelGlobal());
	}



	private  void setFontSize() {
		UIManager.put("Label.font",( (Font) UIManager.getFont("Label.font")).deriveFont(20.0f));
		UIManager.put("TextField.font",( (Font) UIManager.getFont("TextField.font")).deriveFont(20.0f));
		UIManager.put("Button.font",( (Font) UIManager.getFont("Button.font")).deriveFont(20.0f));
		UIManager.put("TabbedPane.font",( (Font) UIManager.getFont("Button.font")).deriveFont(20.0f));
		SwingUtilities.updateComponentTreeUI(frame);
	}



	public void log(String s) {
		if (s== null){
			s = "";
		}
		int lMax =40;
		if (s.length() >lMax){
			s =s.substring(0,lMax);
		}
		this.labelLog.setText(s);
	}



	public void logTitle(String s) {
		this.frame.setTitle("Intersigale " +s);
	}

}

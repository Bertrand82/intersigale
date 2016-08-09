package inter.sigale.model.swing;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import inter.sigale.model.statistic.StatistiquesLexiqueFactory;


public class MainFrameUI {

	private static final boolean debug = true;
	JFrame frame = new JFrame();
	JTabbedPane tabbedPane = new JTabbedPane();

	LessonUI lessonUI = new LessonUI();
	AdminUI adminUI = new AdminUI();
	ItemEditUI itemEditUI = new ItemEditUI();
	StatisticUI statisticUI = new StatisticUI();
	DebugUI debugUI = new DebugUI();

	public MainFrameUI() {
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
		frame.add(tabbedPane);
		frame.setBackground(ConstantesSwing.color_bg);
		frame.setTitle("InterSigale");
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

}

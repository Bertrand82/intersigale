package inter.sigale.model.swing;

import java.awt.Font;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

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
		tabbedPane.addTab("Leçon", lessonUI.getPanelGlobal());
		tabbedPane.addTab("Admin", adminUI.getPanelGlobal());
		tabbedPane.addTab("Edit", itemEditUI.getPanelGlobal());
		tabbedPane.addTab("Stat", statisticUI.getPanelGlobal());
		if (debug) {
			tabbedPane.addTab("debug", debugUI.getPanelGlobal());
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
		frame.setTitle("InterSigale");
		SwingUtilities.updateComponentTreeUI(frame);
		frame.pack();
		frame.setVisible(true);
	}

	
	
	private  void setFontSize() {
		UIManager.put("Label.font",( (Font) UIManager.getFont("Label.font")).deriveFont(20.0f));
		UIManager.put("TextField.font",( (Font) UIManager.getFont("TextField.font")).deriveFont(20.0f));
		UIManager.put("Button.font",( (Font) UIManager.getFont("Button.font")).deriveFont(20.0f));
		UIManager.put("TabbedPane.font",( (Font) UIManager.getFont("Button.font")).deriveFont(20.0f));
		SwingUtilities.updateComponentTreeUI(frame);
	}

}

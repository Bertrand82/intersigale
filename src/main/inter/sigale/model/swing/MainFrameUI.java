package inter.sigale.model.swing;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

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
		frame.pack();
		frame.setVisible(true);
	}
}

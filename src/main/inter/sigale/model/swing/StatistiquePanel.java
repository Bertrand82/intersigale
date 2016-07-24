package inter.sigale.model.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import inter.sigale.model.LexiqueFactory;
import inter.sigale.model.UniteLexicale;
import inter.sigale.model.statistic.StatistiquesItem;
import inter.sigale.model.statistic.StatistiquesUL;

public class StatistiquePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int w = 300;
	int marge_w = 20;
	int h_2 = 50;
	int h = 2 * h_2;
	Date date_1 = new Date();
	Date date_0;
	private UniteLexicale uniteLexicale;
	private StatistiquesUL statistique;
	private long duree;
	private CanvasStatistic canvasStatistic;
	private JLabel labelLog = new JLabel();
	JButton buttonWeek = new JButton("week");
	JButton buttonMonth = new JButton("month");
	JButton buttonDay = new JButton("Day");
	JLabel labelCenter = new JLabel("");
	
	public StatistiquePanel() {
		super();
		canvasStatistic = new CanvasStatistic();
		JPanel panelButtons = new JPanel(new GridLayout(1, 0));
		buttonDay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				initIntervalle(Calendar.DAY_OF_YEAR, "Day");
			}
		});
		buttonWeek.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				initIntervalle(Calendar.WEEK_OF_YEAR,"Week");
			}
		});
		buttonMonth.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				initIntervalle(Calendar.MONTH,"Month");
			}
		});
		initButton(buttonMonth);
		initButton(buttonDay);
		initButton(buttonWeek);
		panelButtons.add(buttonDay);
		panelButtons.add(buttonWeek);
		panelButtons.add(buttonMonth);
		JPanel panelSouth = new JPanel(new BorderLayout());
		panelSouth.add(panelButtons, BorderLayout.WEST);
		panelSouth.add(labelLog, BorderLayout.CENTER);
		this.setLayout(new BorderLayout());
		this.add(this.canvasStatistic, BorderLayout.WEST);
		this.add(this.labelCenter, BorderLayout.CENTER);
		this.add(panelSouth, BorderLayout.SOUTH);
		initIntervalle(Calendar.MONTH,"Month");
	}

	
	private void initButton(JButton button) {
		button.setBackground(ConstantesSwing.color_bg_button);
		
		Font labelFont = button.getFont();
		button.setFont(labelFont.deriveFont(16f));
	}


	private void initIntervalle(int field, String log) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.add(field, -1);
		this.date_0 = calendar.getTime();
		this.duree = this.date_1.getTime() - this.date_0.getTime();
		this.labelLog.setText(log);
		repaint();
	}

	public void updateStat(UniteLexicale ulCourrante) {
		this.uniteLexicale = ulCourrante;
		if (this.uniteLexicale != null) {
			this.statistique = this.uniteLexicale.getStatistique();
		} else {
			statistique = null;
		}
		this.canvasStatistic.updateUI();
		this.canvasStatistic.repaint();
	}
	
	
	public void setBackground(Color bg, Color bg2) {
		super.setBackground(bg);
		if (labelCenter != null){
			labelCenter.setBackground(bg);
		}
		if (buttonDay != null){
			buttonDay.setBackground(bg2);
		}
		if (buttonMonth != null){
			buttonMonth.setBackground(bg2);
		}
		if (buttonWeek != null){
			buttonWeek.setBackground(bg2);
		}
	}

	private class CanvasStatistic extends JPanel {
		
		private static final long serialVersionUID = 1L;

		private CanvasStatistic() {
			Dimension dimension = new Dimension(w + marge_w, h);
			super.setPreferredSize(dimension);
			super.setMinimumSize(dimension);

		}

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(Color.blue);

			g.fillRect(0, 0, w + marge_w, h);
			g.setColor(Color.black);
			g.drawLine(0, h_2, w, h_2);
			paintStatistique(g);
		}

		private void paintStatistique(Graphics g) {
			if (StatistiquePanel.this.statistique == null) {
				g.setColor(Color.black);
				g.drawString("No Stat", 20, h_2);
			} else {
				g.setColor(Color.black);

				List<StatistiquesItem> list = statistique.getListAfterDate(StatistiquePanel.this.date_0);
				g.drawString("Stat : " + list.size(), 20, h_2 - 20);
				for (StatistiquesItem statistiquesItem : list) {
					long deltaTime = statistiquesItem.getDate().getTime() - StatistiquePanel.this.date_0.getTime();
					int timeW = (int) ((deltaTime * w) / duree);
					int hStat;
					if(statistiquesItem.isSucces()){
						hStat=0;
					}else {
						hStat=h;
					}
					g.drawLine(timeW, h_2, timeW, hStat);
				}
			}

		}

	}

}

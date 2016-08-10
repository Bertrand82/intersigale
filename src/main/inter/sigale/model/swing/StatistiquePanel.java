package inter.sigale.model.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import inter.sigale.model.UniteLexicale;
import inter.sigale.model.statistic.StatistiquesItem;
import inter.sigale.model.statistic.StatistiquesUL;

public class StatistiquePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int w = 300;
	int marge_w_g = 150;
	int marge_w_d = 10;
	
	int h_2 = 50;
	int h = 2 * h_2;
	Date date_1 = new Date();
	Date date_0;
	String titre ="";
	private UniteLexicale uniteLexicale;
	private StatistiquesUL statistique;
	private long duree;
	private CanvasStatistic canvasStatistic;
	private JLabel labelLog = new JLabel();
	JButton buttonWeek = new JButton("week");
	JButton buttonMonth = new JButton("month");
	JButton buttonDay = new JButton("Day");
	JButton buttonHour = new JButton("Hour");
	JLabel labelCenter = new JLabel("");
	JPanel panelSouth = new JPanel(new FlowLayout());
	public StatistiquePanel() {
		super();
		canvasStatistic = new CanvasStatistic();
		JPanel panelButtons = new JPanel(new GridLayout(1, 0));
		buttonHour.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				initIntervalle(Calendar.HOUR_OF_DAY, "Hour");
			}
		});
		buttonDay.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				initIntervalle(Calendar.DAY_OF_YEAR, "Day");
			}
		});
		buttonWeek.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				initIntervalle(Calendar.WEEK_OF_YEAR,"Week");
			}
		});
		buttonMonth.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent e) {
				initIntervalle(Calendar.MONTH,"Month");
			}
		});
		initButton(buttonMonth);
		initButton(buttonDay);
		initButton(buttonWeek);
		panelButtons.add(buttonHour);
		panelButtons.add(buttonDay);
		panelButtons.add(buttonWeek);
		panelButtons.add(buttonMonth);
		
		panelSouth.add(panelButtons, BorderLayout.WEST);
		//labelLog.setBorder(new LineBorder(Color.RED));
		//panelSouth.add(labelLog, BorderLayout.CENTER);
		
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        canvasStatistic.setBorder(new LineBorder(Color.RED,5));
		this.add(this.canvasStatistic);
		//this.add(this.labelCenter, BorderLayout.CENTER);
	    this.add(panelSouth);
		initIntervalle(Calendar.MONTH,"Month");
		panelSouth.setBorder(new LineBorder(Color.RED));
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
		//this.labelLog.setText(log);
		this.titre =log;
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
		this.panelSouth.setBackground(bg);
		this.panelSouth.setOpaque(true);
		if (labelCenter != null){
			labelCenter.setBackground(bg);
		}
		if (buttonHour != null){
			buttonHour.setBackground(bg2);
		}if (buttonDay != null){
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
			Dimension dimension = new Dimension(w + marge_w_g+marge_w_d, h);
			super.setPreferredSize(dimension);
			super.setMinimumSize(dimension);
			super.setMaximumSize(dimension);

		}

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			Font newFont = new Font("default", Font.BOLD, 16);
			g.setFont(newFont);
			g.setColor(Color.blue);

			g.fillRect(0, 0, w + marge_w_g + marge_w_d, h);
			g.setColor(Color.black);
			
			paintStatistique(g);
		}

		private void paintStatistique(Graphics g) {
			if (StatistiquePanel.this.statistique == null) {
				g.setColor(Color.black);
				g.drawString( titre+"  " , 20, 20);
				g.drawString("No Stat", 20, h_2);
			} else {
				g.setColor(Color.black);
				g.drawLine(marge_w_g, h_2, marge_w_g+w, h_2);
				List<StatistiquesItem> list = statistique.getListAfterDate(StatistiquePanel.this.date_0);
				int nb_succes =0;
				int nb_failures = 0;
				for (StatistiquesItem statistiquesItem : list) {
					long deltaTime = statistiquesItem.getDate().getTime() - StatistiquePanel.this.date_0.getTime();
					int timeW = (int) ((deltaTime * w) / duree);
					int hStat;
					if(statistiquesItem.isSucces()){
						hStat=0;
						nb_succes++;
					}else {
						hStat=h;
						nb_failures++;
					}
					int x = marge_w_g+timeW;
					g.drawLine(x, h_2, x, hStat);
				}
				
				
				g.drawString( titre+"  " , 20, 20);
				g.drawString("Succes : "+ nb_succes+" / " + list.size(), 20, h_2 - 10);
				g.drawString("Failures : "+ nb_failures+" / " + list.size(), 20, h_2 + 20 );
			}
		}
	}

}

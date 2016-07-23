package inter.sigale.model.swing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class StatistiquePanel extends JPanel{
    int w  = 300;
    int h = 100;
	public StatistiquePanel() {
		super();
		Dimension dimension = new Dimension(w, h);
		super.setPreferredSize(dimension);
		super.setMinimumSize(dimension);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.blue);
		g.fillRect(0,0, w, h);
	}
	
	
	

}

package de.tud.gis.teachlets.spatialreferencesystems;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JPanel;

/** 
 * This class displays map image and all graphical elements (lines, ...) in a panel 
 */ 
public class JMapPaintPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private Dimension _preferredSize ;
	Image img = null;
	Metric _metric;	
	
	/**
	 * Class constructor
	 */
	public JMapPaintPanel(Metric metric)
	{
		_metric = metric;
		
		//img = ImageIO.read(new File("karte_newyork.gif"));
		URL myurl = this.getClass().getResource("karte_newyork.gif");
			
		//only try to load image if image path has been found to prevent crash
		if (myurl != null) {
			img = Toolkit.getDefaultToolkit().getImage(myurl);
			prepareImage(img, this);
		}
		
		this.setFont(new Font("Arial", Font.BOLD, 14 ));		
	}
	
	/**
	 * sets width and height of panel
	 * 
	 * @param dimension including width and height
	 */
	public void setPreferredSize(Dimension dim)
	{
		_preferredSize = dim;		
	}
	
	/**
	 * returns width and height of panel
	 * 
	 * @return dimension including width and height
	 */
	public Dimension getPreferredSize()
	{
		return _preferredSize;		
	}
	
	/**
	 * paints graphical elements into panel
	 * 
	 * @param graphics object
	 */
	@SuppressWarnings("unused")
	public void paintComponent(Graphics g)
	{
		//define different line types
		final Stroke mediumline = new BasicStroke( 2 );
		final Stroke fatline = new BasicStroke( 3 );
		final float dash1[] = {1.0f}; 
	    final BasicStroke dashedline = new BasicStroke(1.0f, 
	                                          BasicStroke.CAP_BUTT, 
	                                          BasicStroke.JOIN_MITER, 
	                                          10.0f, dash1, 0.0f);

		Point p1 = _metric.getPoint1();
		Point p2 = _metric.getPoint2();
		int gap = 10; //raster distance
		
		Graphics2D g2 = (Graphics2D) g; 
		
		//draw border of panel
		g2.setColor(Color.BLACK);
		g2.drawRect(0, 0, _preferredSize.width, _preferredSize.height);
		
		//draw map image
		g2.drawImage(img, 2, 1, null);
		
		g2.setColor(Color.BLACK);
		g2.setStroke(dashedline);
		
		//draw points and labels
		g2.fillOval((p1.getX()-4), (p1.getY()-4), 8, 8);		
		g2.drawString("P1", (p1.getX()-5), (p1.getY()-7));
		g2.fillOval((p2.getX()-4), (p2.getY()-4), 8, 8);		
		g2.drawString("P2", (p2.getX()-8), (p2.getY()+18));
				 
		//draw euclidian distance if status is true
		if (_metric.getEuclidStatus()) 
		{
			double dist = _metric.getEuclDistance()/gap;
			g2.setStroke(mediumline);
			g2.setColor(Color.magenta);
			//draw distance line
			g2.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
			//draw label
			g2.drawString(String.valueOf(dist).replace(".", ","), 266, 243);
		}
		
		//draw city block distance if status is true
		if (_metric.getCityBStatus()) 
		{
			int dist = (int) _metric.getCityBDistance()/gap;
			g2.setStroke(mediumline);
			//line 1
			g2.setColor(new Color(255, 130, 0)); //orange
			g2.drawLine(p1.getX()-1, p1.getY(), p2.getX()+1, p1.getY());
			g2.drawLine(p2.getX(), p1.getY(), p2.getX(), p2.getY());
			g2.drawString(String.valueOf(dist), p2.getX()-5,  p1.getY()-5);
			//line 2
			g2.setColor(new Color(0, 180, 0)); //green
			int x3 = 220, x4 = 120;
			int y3 = 317, y4 = 365;
			g2.drawLine(p1.getX()+1, p1.getY()+2, p1.getX()+1, y3);
			g2.drawLine(p1.getX()+1, y3, x3, y3);
			g2.drawLine(x3, y3, x3, y4);
			g2.drawLine(x3, y4, x4, y4);
			g2.drawLine(x4, y4, x4, p2.getY());
			g2.drawLine(x4, p2.getY(), p2.getX()+2, p2.getY());
			g2.drawString(String.valueOf(dist), 215,  379);
			//line 3
			g2.setColor(new Color(0, 130, 255)); //blue
			int x5=345, x6=310, x7=272, x8=210, x9=166, x10=155, x11=110, x12=70, x13=45;
			int y5=210, y6=228, y7=249, y8=268, y9=292, y10=317, y11=340, y12=365;
			g2.drawLine(p1.getX()-2, p1.getY()+2, p1.getX()-2, y5);
			g2.drawLine(p1.getX()-2, y5, x5, y5);
			g2.drawLine(x5, y5, x5, y6);
			g2.drawLine(x5, y6, x6, y6);
			g2.drawLine(x6, y6, x6, y7);
			g2.drawLine(x6, y7, x7, y7);
			g2.drawLine(x7, y7, x7, y8);
			g2.drawLine(x7, y8, x8, y8);
			g2.drawLine(x8, y8, x8, y9);
			g2.drawLine(x8, y9, x9, y9);
			g2.drawLine(x9, y9, x9, y10);
			g2.drawLine(x9, y10, x10, y10);
			g2.drawLine(x10, y10, x10, y11);
			g2.drawLine(x10, y11, x11, y11);
			g2.drawLine(x11, y11, x11, y12);
			g2.drawLine(x11, y12, x12, y12);
			g2.drawLine(x12, y12, x12, p2.getY());
			g2.drawLine(x12, p2.getY()-2, x13, p2.getY()-2);
			g2.drawString(String.valueOf(dist), 190, 282);			
		}
		
		//draw corner edge distance if status is true
		if (_metric.getCornStatus()) 
		{
			int dist = (int) _metric.getCornDistance()/gap;
			//line 1
			g2.setColor(new Color(205, 38, 38)); //brown
			g2.setStroke(mediumline);
			int x14 = 220, x15 = 166;
			int y13 = 293, y14 = 342;
			g2.drawLine(p1.getX()-1, p1.getY()+1, p1.getX()-1, y13);
			g2.drawLine(p1.getX()-1, y13, x14, y13);
			g2.drawLine(x14, y13, x15, y14);
			g2.drawLine(x15+1, y14, p2.getX()+1, y14);
			g2.drawLine(p2.getX()+1, y14, p2.getX()+1, p2.getY());
			g2.drawString(String.valueOf(dist), 302,  290);
		}
	}
	
	
}



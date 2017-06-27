package de.tud.gis.teachlets.voronoi;
import java.awt.*;
import java.util.Enumeration;
import java.util.Vector;
/**
 * 
 * @author danielkadner
 *
 */
@SuppressWarnings("rawtypes")
class PointSet extends Vector {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6103257859909073597L;

	PointSet() {
	}

	public void draw(Graphics g) {
		g.setColor(Color.black);
		GraphPoint graphpoint;
		for (Enumeration enumeration = elements(); enumeration.hasMoreElements(); graphpoint.draw(g,((Point) (graphpoint)).x, ((Point) (graphpoint)).y, graphpoint.getValue()))
			graphpoint = (GraphPoint) enumeration.nextElement();

	}
}
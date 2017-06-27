package de.tud.gis.teachlets.spatialreferencesystems;
/** 
 * This class models a corner edge metric 
 */ 
public class CornerEdgeMetric 
{
	/** 
	 * returns distance between 2 points
	 * 
	 * @return distance
	 */
	public double getDistance(Point p1, Point p2)
	{
		return Math.max(Math.abs(p1.getX() - p2.getX()), Math.abs(p1.getY() - p2.getY()));
	}
	
	/**
	 * returns name of metric
	 * 
	 * @return name
	 */
	public String getName()
	{
		return "Ecken-Kanten-Metrik";
	}
	
	/** 
	 * returns declaration text for corner edge metric
	 * 
	 * @return declaration text
	 */
	public String getDeclarationText()
	{
		String str = "\n\nDie Ecken-Kanten-Metrik (auch Schachbrett-Metrik) ist der " +
		"City-Block-Metrik ähnlich, außer dass zusätzlich zu den horizontalen " +
		"und vertikalen Bewegungen auch Wege entlang der Diagonalen möglich sind. " +
		"Eine solche Diagonale stellt in der Beispielkarte die Abkürzung des Weges " +
		"durch den Park dar.\n\n";
		return str;
	}
}
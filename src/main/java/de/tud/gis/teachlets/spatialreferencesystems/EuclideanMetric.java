package de.tud.gis.teachlets.spatialreferencesystems;
/** 
 * This class models a euclidean metric 
 */
public class EuclideanMetric
{
	/** 
	 * returns distance between 2 points
	 * 
	 * @return distance
	 */
	public double getDistance(Point p1, Point p2)
	{
		double dist = 0;
		dist = Math.sqrt(Math.pow((p1.getX() - p2.getX()), 2) + Math.pow((p1.getY() - p2.getY()), 2));
		dist = Math.round(dist);
		return dist;
	}
	
	/**
	 * returns name of metric
	 * 
	 * @return name
	 */
	public String getName()
	{
		return "Euklidische Metrik";
	}
	
	/** 
	 * returns declaration text for euclidean metric
	 * 
	 * @return declaration text
	 */
	public String getDeclarationText()
	{
		String str = "\n\nDie Distanz zwischen zwei Punkten ist der direkte Abstand zwischen diesen Punkten. " +
				"In der Beispielkarte entspricht sie somit der Luftlinie zwischen P1 und P2.\n\n";
		return str;
	}
}

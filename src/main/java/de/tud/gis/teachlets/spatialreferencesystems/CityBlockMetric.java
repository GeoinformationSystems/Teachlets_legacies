package de.tud.gis.teachlets.spatialreferencesystems;
/** 
 * This class models a city block metric 
 */ 
public class CityBlockMetric 
{
	/** 
	 * returns distance between 2 points
	 * 
	 * @return distance
	 */
	public double getDistance(Point p1, Point p2)
	{
		return Math.abs(p1.getX() - p2.getX()) + Math.abs(p1.getY() - p2.getY());
	}
	
	/**
	 * returns name of metric
	 * 
	 * @return name
	 */
	public String getName()
	{
		return "City-Block-Metrik";
	}
	
	/** 
	 * returns declaration text for city block metric
	 * 
	 * @return declaration text
	 */
	public String getDeclarationText()
	{
		String str = "\n\nBei der City-Block-Metrik (auch Manhattan- oder Kantenmetrik) entspricht " +
				"die Distanz zweier Punkte der Summe der absoluten Differenzen ihrer " +
				"Einzelkoordinaten.\nAm Beispiel der Karte kann man erkennen, dass die Luftlinie " +
				"oft nicht geeignet ist, physikalisch wirklich überwindbare Distanzen anzugeben. " +
				"So sind aufgrund des Straßennetzes nur Wege entlang der vier Haupthimmelsrichtungen " +
				"möglich. Mit der City-Block-Metrik wird also der kürzeste Weg zwischen zwei Punkten " +
				"entlang der Haupthimmelsrichtungen berechnet. Für diesen kürzesten Weg gibt es oft " +
				"mehrere Möglichkeiten, wie das Beispiel zeigt.\n\n";
		return str;
	}
}

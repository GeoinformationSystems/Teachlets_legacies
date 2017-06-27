package de.tud.gis.teachlets.spatialreferencesystems;

/** 
 * This class models a geometric point 
 */ 
public class Point
{
	private int x;
	private int y;
	
	/** 
	 * Class constructor.
	 */
	public Point(int x, int y)  
	{
		this.x = x;
		this.y = y;
	}
	
	/** 
	 * returns x-coordinate of point
	 * 
	 * @return x-coordinate
	 */
	public int getX()
	{
		return x;
	}
	
	/** 
	 * returns y-coordinate of point
	 * 
	 * @return y-coordinate
	 */
	public int getY()
	{
		return y;
	}
	
	/** 
	 * sets x-coordinate of point
	 * 
	 * @param x-coordinate
	 */
	public void setX(int x)
	{
		this.x = x;
	}
	
	/** 
	 * sets y-coordinate of point
	 * 
	 * @param y-coordinate
	 */
	public void setY(int y)
	{
		this.y = y;
	}
}//public class Point

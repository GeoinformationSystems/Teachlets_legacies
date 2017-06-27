package de.tud.gis.teachlets.networkanalysis.shortestpath;
import java.awt.Polygon;


/** 
 * This class models a node consisting of id, coordinates x/y, diameter and name
 */ 
public class Node
{
	private int _ID;
	private int _x;
	private int _y;
	private int _diameter;
	private String _name;
	
	/** 
	 * Class constructor.
	 */
	public Node(int x, int y, int d, String name)  
	{
		_ID = 0;
		_x = x;
		_y = y;
		_name = name;
		_diameter = d;		
	}
	
	/** 
	 * returns ID of node
	 * 
	 * @return ID
	 */
	public int getID()
	{
		return _ID;
	}
	
	/** 
	 * sets ID of node
	 * 
	 * @param ID
	 */
	protected void setID(int id)
	{
		if (_ID == 0)
			this._ID = id;
		else 
			System.out.println("This node has already an ID!");
	}
		
	/** 
	 * returns x-coordinate of node
	 * 
	 * @return x-coordinate
	 */
	public int getX()
	{
		return _x;
	}
	
	/** 
	 * sets x-coordinate of node
	 * 
	 * @param x-coordinate
	 */
	public void setX(int x)
	{
		this._x = x;
	}
	
	/** 
	 * returns y-coordinate of node
	 * 
	 * @return y-coordinate
	 */
	public int getY()
	{
		return _y;
	}	
	
	/** 
	 * sets y-coordinate of node
	 * 
	 * @param y-coordinate
	 */
	public void setY(int y)
	{
		this._y = y;
	}
	
	/** 
	 * returns y-coordinate of node
	 * 
	 * @return y-coordinate
	 */
	public int getDiameter()
	{
		return _diameter;
	}	
	
	/** 
	 * sets y-coordinate of node
	 * 
	 * @param y-coordinate
	 */
	public void setDiameter(int d)
	{
		this._diameter = d;
	}
	
	/** 
	 * returns name of node
	 * 
	 * @return name
	 */
	public String getName()
	{
		return _name;
	}
	
	/** 
	 * sets name of node
	 * 
	 * @param name
	 */
	public void setName(String name)
	{
		this._name = name;
	}
	
	/**
	 * return polygon of this node for drawing
	 * 
	 * @return polygon
	 */
	public Polygon getPolygon()
	{
		Polygon p = new Polygon();
	    int m = 360;
	    double r = _diameter/2;
		for ( int j = 0; j < m; j++ ) 
		      p.addPoint( (int) (_x + r + r * Math.cos( j * 2 * Math.PI / m )), 
		                  (int) (_y + r + r * Math.sin( j * 2 * Math.PI / m )) ); 
	    return p;
	}
	
	/**
	 * return polygon with bigger size of this node for drawing
	 * 
	 * @return bigger polygon
	 */
	public Polygon getBigPolygon()
	{
		Polygon p = new Polygon();
	    int m = 360;
	    double r = _diameter;
		for ( int j = 0; j < m; j++ ) 
		      p.addPoint( (int) (_x + r/2 + (int) r/1.5 * Math.cos( j * 2 * Math.PI / m )), 
		                  (int) (_y + r/2 + (int) r/1.5 * Math.sin( j * 2 * Math.PI / m )) ); 
	    
	    return p;
	}
	
	/**
	 * return String with information about node
	 * 
	 * @return information string
	 */
	public String toString()
	{
		return "n" + _ID + "(" + _x + "," + _y + ") name: " + _name ;
	}
	
	/**
	   * return how close the node is to another node
	   *
	   * @param other node
	   * @return distance
	   */
	public int proximity(Node cother) 
	{
		return proximity(cother.getX(),cother.getY());
	}

	/**
	 * return how far this node is away from a specific point;
	 * uses the pythagorean theorum to calculate the distance
	 *
	 * @param x coordinate
	 * @param y coordinate
	 * @return distance
	 */
	public int proximity(double d, double e) 
	{
		double xdiff = _x - d;
		double ydiff = _y - e;
		return(int)Math.sqrt( xdiff*xdiff + ydiff*ydiff );
	}
}

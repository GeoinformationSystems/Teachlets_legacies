package de.tud.gis.teachlets.networkanalysis.locatallocation;
/**
 * This class manages a point consinsting of coordinates x, y
 */

public class MyPoint  
{
    private double _x, _y;

    /**
     * Class constructur.
     */
    public MyPoint( double iX, double iY )
    {
		_x = iX;
		_y = iY;
		return;
    }

    /**
     * Class constructur.
     */
    public MyPoint( int iX, int iY )
    {
		_x = iX;
		_y = iY;
		return;
    }

    /**
     * Class constructur.
     */
    public MyPoint( MyPoint p )
    {
		_x = p._x;
		_y = p._y;
		return;
    }  
    
    /**
     * return x coordinate of point
     * 
     * @return x coordinate
     */
    public double getX()
    {
    	return _x;
    }
    
    /**
     * return y coordinate of point
     * 
     * @return y coordinate
     */
    public double getY()
    {
    	return _y;
    }
}

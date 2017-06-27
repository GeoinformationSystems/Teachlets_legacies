package de.tud.gis.teachlets.networkanalysis.locatallocation;
/**
 * This class manages a circle consisting of middle point and radius 
 */

public class MyCircle  
{
    private MyPoint _p;
    private double _r;

    /**
     * Class constructur.
     */
    public MyCircle()
    {
		_p = new MyPoint(0,0);
		_r = 0;
		return;
    }
    
    /**
     * Class constructur.
     */
    public MyCircle( MyCircle circ )
    {
		_p = new MyPoint( circ._p );
		_r = circ._r;
		return;
    }

    /**
     * Class constructur.
     */
    public MyCircle( MyPoint center, double radius )
    {
		_p = new MyPoint( center );
		_r = radius;
		return;
    }
    
    /**
     * return middle point of circle
     * 
     * @return middle point
     */
    public MyPoint getMiddlePoint()
    {
    	return _p;
    }
    
    /**
     * return radius of circle
     * 
     * @return radius
     */
    public double getRadius()
    {
    	return _r;
    }
}

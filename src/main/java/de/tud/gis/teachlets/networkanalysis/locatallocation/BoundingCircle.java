package de.tud.gis.teachlets.networkanalysis.locatallocation;
/*
 *  Shripad Thite (thite@uiuc.edu)
 *  November 26, 2000
 *
 */

/**
 * This contains computes the smallest bounding circle for a set of points
 */
public class BoundingCircle 
{
    /**
     * returns distance between two points
     * 
     * @param point 1
     * @param point 2
     * @return distance 
     */
	private static double distance( MyPoint p1, MyPoint p2 )
	{
		return Math.sqrt( (p1.getX()-p2.getX())*(p1.getX()-p2.getX()) + (p1.getY()-p2.getY())*(p1.getY()-p2.getY()) );
	}

    /**
     * Given three points defining a circle,
     * compute the center and radius of the circle.
     * 
     * @param point 1
     * @param point 2
     * @param point 3
     * return circle
     */
	private static MyCircle findCenterRadius( MyPoint p1, MyPoint p2, MyPoint p3 )
    {
		double x = (p3.getX()*p3.getX() * (p1.getY() - p2.getY()) 
			    + (p1.getX()*p1.getX() + (p1.getY() - p2.getY())*(p1.getY() - p3.getY())) 
			    * (p2.getY() - p3.getY()) + p2.getX()*p2.getX() * (-p1.getY() + p3.getY())) 
		    / (2 * (p3.getX() * (p1.getY() - p2.getY()) + p1.getX() * (p2.getY() - p3.getY()) + p2.getX() 
			    * (-p1.getY() + p3.getY())));
	
		double y = (p2.getY() + p3.getY())/2 - (p3.getX() - p2.getX())/(p3.getY() - p2.getY()) 
		    * (x - (p2.getX() + p3.getX())/2);
	
		MyPoint c = new MyPoint( x, y );
		double r = distance(c, p1);
	
		return new MyCircle( c, r );
    }

   /**
     * Compute the center and radius of the smallest circle enclosing the n points in 
     * P, such that the m points in B lie on the boundary of the circle.
     */
	public static MyCircle minCircle(int n, MyPoint[] p, int m, MyPoint[] b)
    {
		MyPoint c = new MyPoint(-1,-1);
		double r = 0;
	
		//... Compute the smallest circle defined by B.
		if( m == 1 )
		    {
			c = new MyPoint( b[0] );
			r = 0;
		    }
		else if( m == 2 )
		    {
			c = new MyPoint( (b[0].getX()+b[1].getX())/2, (b[0].getY()+b[1].getY())/2 );
		        r = distance( b[0], c );
		    }
		else if( m == 3 )
			return findCenterRadius( b[0], b[1], b[2] );
	
		MyCircle minC = new MyCircle( c, r );
	
		//... Now see if all the points in P are enclosed.
		for( int i = 0;  i < n;  i++  )
		    if( distance(p[i], minC.getMiddlePoint()) > minC.getRadius() )
			{
			    //... Compute B <--- B union P[i].
			    b[m] = new MyPoint( p[i] );
	
			    //... Recurse
			    minC = minCircle( i, p, m+1, b );
			}	
		return minC;
    }
}

package de.tud.gis.teachlets.networkanalysis.locatallocation;
/**
 * Class manages edge between two nodes
 */
public class Edge implements Cloneable
{
	protected Node _start;
	protected Node _end;
	protected int _weight;
	protected Edge _reverse; //same edge with reverse start and end node
	
	/**
	 * Class constructor.
	 */
	public Edge(Node s, Node e, int w)
	{
		_start = s;
		_end = e;
		_weight = w;
		_reverse = new Edge(w, e, s);
	}
	
	/**
	  * Class constructor that creates duplicate of edge
	  */
	public Edge(Edge e, int w)
	{
		this._start = e.getStartNode();
		this._end = e.getEndNode();
		this._weight = w;
		this._reverse = e.getReverse();
	}
	
	/**
	 * Class constructur that inverted edge constructs reverse edge 
	 */
	public Edge(int w, Node s, Node e)
	{
		this._start = s;
		this._end = e;
		this._weight = w;
		this._reverse = null;
	}
	
	/**
	 * return first node of edge
	 * 
	 * @return first node
	 */
	public Node getStartNode()
	{
		return _start;
	}
	
	/**
	 * return second node of edge
	 * 
	 * @return second node
	 */
	public Node getEndNode()
	{
		return _end;
	}
	
	/**
	 * return weight of edge
	 * 
	 * @return weight of edge
	 */
	public int getWeight()
	{
		return _weight;
	}
	
	/**
	 * return edge with reverse start and end of edge
	 * 
	 * @return reverse edge
	 */
	public Edge getReverse()
	{
		return _reverse;
	}
	
	/**
	 * return String with information about edge
	 * 
	 * @return information string
	 */
	public String toString()
	{
		return "{"+_start.getID()+"-"+_end.getID()+":"+_weight+"}";		
	}
}

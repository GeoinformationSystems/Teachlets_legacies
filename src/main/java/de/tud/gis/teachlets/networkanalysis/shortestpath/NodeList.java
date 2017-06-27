package de.tud.gis.teachlets.networkanalysis.shortestpath;
import java.util.Iterator;
import java.util.Vector;

/** 
 * This class manages all nodes in a Vector 
 */ 
public class NodeList
{
	private Vector<Node> _nodes;
	private int iCntNodeID;
		
	/** 
	 * Class constructor.
	 */
	NodeList() 
	{
		_nodes = new Vector<Node>();	
		iCntNodeID = 1;
	}
	
	/** 
	 * saves a node in list
	 * 
	 * @param node object
	 */
	public boolean add(Node n)
	{
		if (_nodes.add(n)) {
			if (n.getID() <= 0)
				n.setID(iCntNodeID++);
			return true;
		}
		return false;
	}
	
	/**
	 * return all nodes of vector
	 * 
	 * @return node vector
	 */
	public Vector<Node> get_nodelist()
	{
		return _nodes;
	}
	
	/** 
	 * remove all nodes
	 */
	public void clear()
	{
		_nodes.clear();
		iCntNodeID = 1;
	}
	
	/**
	 * get node at specific position in Vector
	 * 
	 * @param position
	 * @return node object
	 */
	public Node get(int arg)
	{
		return _nodes.get(arg);
	}
	
	/** 
	 * return first node in list
	 * 
	 * @return first node
	 */
	public Node first()
	{
		return _nodes.get(0);
	}
	
	/** 
	 * return returns node iterator of list
	 * 
	 * @return node iterator
	 */
	public Iterator<Node> iterator()
	{
		return _nodes.iterator();
	}
	
	/** 
	 * return last node in list
	 * 
	 * @return last node
	 */
	public Node last()
	{
		return _nodes.get(_nodes.size()-1);
	}
	
	/** 
	 * return amount of all saved nodes
	 * 
	 * @return number of all nodes
	 */
	public int size()
	{
		return _nodes.size();
	}
	
	/**
	 * check if Vector contains specific node
	 * 
	 * @param searched node 
	 * @return true, if node is contained in Vector; otherwise false
	 */
	public boolean contains(Node n)
	{
		return _nodes.contains(n);
	}
}

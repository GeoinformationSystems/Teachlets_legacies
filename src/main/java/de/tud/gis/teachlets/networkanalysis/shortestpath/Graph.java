package de.tud.gis.teachlets.networkanalysis.shortestpath;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Vector;

/**
 * This Class models a graph consisting of a list of nodes that are linked by
 * a weighted edge 
*/
public class Graph
{
	/// list of nodes
	protected Vector<Node> vertices;
	/// list of edges
	protected Vector<Edge> edges;
	/// distance matrix of edges
	protected HashMap<Node,Vector<Edge>> adjList;
	
	/**
	 * Class constructur. 
	 */
	public Graph(Node[] Vertices, Edge[] Edges)
	{
		//save nodes
		this.vertices = new Vector<Node>(Vertices.length);
		for(int i=0; i < Vertices.length; i++)
			this.vertices.add(Vertices[i]);
		 
		//save edges
		this.edges = new Vector<Edge>(Edges.length);
		for(int i=0; i < Edges.length; i++)
			this.edges.add(Edges[i]);
		// build distance matrix
		this.createAdjacencyList();
	}
	
	/**
	 * Class constructur. 
	 */
	public Graph(Vector<Node> Vertices, Vector<Edge> Edges)
	{
		this.vertices = Vertices;
		this.edges = Edges;		
		this.createAdjacencyList();		
	}
	
	/**
	  * build distance matrix
	  */
	protected void createAdjacencyList()
	{
		this.adjList = new HashMap<Node,Vector<Edge>>(this.vertices.size());
		
		for(Enumeration<Node> e = this.vertices.elements(); e.hasMoreElements();)
		{
			this.adjList.put(e.nextElement(), new Vector<Edge>());
		}
		
		for(Enumeration<Edge> e=this.edges.elements(); e.hasMoreElements();)
		{
			Edge t = e.nextElement();
			this.adjList.get(t.getStartNode()).add(t);			
		}		
	}
	
	/**
	  * return distance matrix of graph
	  * 
	  * @return distance matrix of graph
	  */
	public HashMap<Node,Vector<Edge>> getAdjacencyList()
	{
		return this.adjList;
	}
	
	/**
	  * return nodes of graph
	  * 
	  * @return nodes of graph
	  */
	public Vector<Node> getVertices()
	{
		return this.vertices;
	}
	
	/**
	  * return edges of graph
	  * 
	  * @return edges of graph
	  */
	public Vector<Edge> getEdges()
	{
		return this.edges;
	}
	
	/**
	  * return String with information about graph
	  * 
	  * @return information string
	  */
	public String toString()
	{
		return "V: "+this.vertices+"\nE: "+this.edges+"\nA: "+this.adjList+"\n";
	}
}
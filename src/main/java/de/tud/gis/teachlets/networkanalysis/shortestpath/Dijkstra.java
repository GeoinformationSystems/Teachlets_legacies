package de.tud.gis.teachlets.networkanalysis.shortestpath;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Vector;

/**
 * Calculates shortest path from a node to all other nodes in a weighted graph
 * using the algothm of Dijkstra 
*/
public class Dijkstra
{
	/**
	  * Class contains information for a node 
	  */
	private static class DijkstraInfo
	{
		public int distance;
		public Node predecessor;
		public Edge edge;
		
		public DijkstraInfo()
		{
			this.distance = Integer.MAX_VALUE;
			this.predecessor = null;
			this.edge = null;
		}
		
		public String toString()
		{
			return "["+this.distance+","+this.predecessor+","+this.edge+"]";
		}
	}
	
	/**
	 * Class compares distance of two nodes 
	 */
	private static class DijkstraComp implements Comparator<Node>
	{
		private HashMap<Node, DijkstraInfo> info;
		
		public DijkstraComp(HashMap<Node,DijkstraInfo> Info)
		{
			this.info = Info;
		}
		
		public int compare(Node a, Node b)
		{
			int ad = this.info.get(a).distance;
			int bd = this.info.get(b).distance;
			if(ad == bd) return 0;
			if(ad < bd) return -1;
			return 1;
		}
	}
	
	/**
	  * computes shortest path from one node to all other nodes
	  * 
	  * @param weighted graph containing all nodes and edges
	  * @param start node
	  * @return map containing for each node shortest path to start node
	  */
	public static HashMap<Node,Vector<Edge>> compute(Graph G, Node s)
	{
		//nodes
		Vector<Node> vertices = G.getVertices();
		//edges
		Vector<Edge> edges = G.getEdges();
		//distance matrix
		HashMap<Node, Vector<Edge>> adjList = G.getAdjacencyList();
		
		//information
		HashMap<Node, DijkstraInfo> info = new HashMap<Node,DijkstraInfo>();
		
		//list of actual nodes
		PriorityQueue<Node> Q = new PriorityQueue<Node>(edges.size(), new DijkstraComp(info));
		
		//set information
		for(Enumeration<Node> e=vertices.elements(); e.hasMoreElements(); )
			info.put(e.nextElement(), new DijkstraInfo());
		
		//set start node
		info.get(s).distance = 0;
		
		//add nodes
		Q.addAll(vertices);
		
		//execute query
		while(!Q.isEmpty())
		{
			//first element - choose shortest path
			Node v = Q.poll();
			//go through outbound nodes
			for(Enumeration<Edge> e = adjList.get(v).elements(); e.hasMoreElements();)
			{
				Edge t = e.nextElement();
				//if it is shorter to take this path then the previous chosen 
				if(info.get(v).distance + t.getWeight() < info.get(t.getEndNode()).distance)
				{
					//refresh nodes
					info.get(t.getEndNode()).distance = info.get(v).distance + t.getWeight();
					info.get(t.getEndNode()).predecessor = v;
					info.get(t.getEndNode()).edge = t;
					
					//refresh Q
					Q.remove(t.getEndNode());
					Q.add(t.getEndNode());
				}
			}
		}
		
		//calculate output
		HashMap<Node,Vector<Edge>> retval = new HashMap<Node,Vector<Edge>>();
		for(Enumeration<Node>e= vertices.elements(); e.hasMoreElements();)
			retval.put(e.nextElement(), new Vector<Edge>());
		
		//build shortest path for each node
		for(Enumeration<Node>e = vertices.elements(); e.hasMoreElements();)
		{
			Node v = e.nextElement();
			
			//go through tree bottom-up and save edges
			Node predec = v;
			
			while(info.get(predec).predecessor != null)
			{
				retval.get(v).add(info.get(predec).edge);
				predec = info.get(predec).predecessor;
			}
		}
		
		return retval;
	}
}
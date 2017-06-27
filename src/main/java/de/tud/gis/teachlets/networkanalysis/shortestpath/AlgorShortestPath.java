package de.tud.gis.teachlets.networkanalysis.shortestpath;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * This class models the Shortest-Path-Problem
 */
public class AlgorShortestPath
{
	private final int MAXSTARTNODES = 1; 
	private final int MAXENDNODES = 1;
	private NodeList _nodeList;
	private Vector<Edge> _edgeList;
	
	/** 
	 * Class constructor
	 */
	public AlgorShortestPath()
	{
		_nodeList  = new NodeList();
		_edgeList = new Vector<Edge>();
	}

	/**
	 * returns description for this algorithm
	 * 
	 * @return algorithm description
	 */
	public String getDescription()
	{
		String str = "\n\nBeim Shortest-Path-Problem wird der geometrisch kürzeste Weg zwischen einem Start- und Zielknoten gesucht.\n\nNach dem Starten des Beispiels ist in der Karte rechts das Eisenbahnnetz für die größten Städte Deutschlands schematisch dargestellt. " +
				"Die Zahlen geben die Entfernung (Luftlinie) zwischen zwei Städten in 10 km an." +
				"\n\nDie kürzeste Entfernung zwischen zwei beliebigen Punkten wird hier mit dem Dijkstra-Algorithmus berechnet, bei dem " +
				"vom Startknoten aus iterativ die jeweiligen Nachbarknoten mit minimaler Distanz zum Startknoten besucht werden bis " +
				"der Zielknoten bzw. alle Knoten besucht sind." +
				"\n\nDieser Algorithmus findet z.B. bei Routenplanern und Navigationssystemen Anwendung." +
				"\n\nBeispiel starten:\n\n";
		return str;		
	}

	/**
	 * returns maximal allowed number of end nodes
	 * 
	 * @return maximal number of end nodes
	 */	
	public int getMaxEndNodes()
	{
		return MAXENDNODES;
	}

	/**
	 * returns maximal allowed number of start nodes
	 * 
	 * @return maximal number of start nodes
	 */
	public int getMaxStartNodes()
	{
		return MAXSTARTNODES;
	}

	/**
	 * returns instruction text for user handling shown in text panel according 
	 * to actual algorithm phase
	 * 
	 * @param actual algorithm phase
	 * @return instruction text
	 */
	public String getInstructionText(DataModel.AlgorPhase phase)
	{
		if (phase == DataModel.AlgorPhase.SETSTARTNODE) {
			return "Bitte markieren Sie die Startstadt.";			
		} else if (phase == DataModel.AlgorPhase.SETENDNODE) {
			return "Bitte markieren Sie die Zielstadt.";			
		} else if (phase == DataModel.AlgorPhase.SHOWRESULT) {
			return "Durch Klick auf den unteren Button kann der Algorithmus\ngestartet werden.\nUm eine neue Start- und Zielstadt festzulegen, den oberen\nButton klicken.";			
		}
		return "";
	}

	/**
	 * add new edge to specific edge list of this algorithm
	 * 
	 * @param edge 
	 */
	public void addEdge(Edge e)
	{
		_edgeList.add(e);		
	}

	/**
	 * returns specific edge list of this algorithm
	 * 
	 * @return edge list
	 */
	public Vector<Edge> getEdges()
	{
		return _edgeList;
	}

	/**
	 * add new node to specific node list of this algorithm
	 * 
	 * @param node
	 */
	public void addNode(Node n)
	{
		_nodeList.add(n);		
	}

	/**
	 * returns specific node list of this algorithm
	 * 
	 * @return node list
	 */
	public NodeList getNodeList()
	{
		return _nodeList;
	}

	/**
	 * calculates algorithm to solve problem and returns solution
	 * 
	 * @param data model
	 * @return vector containing solution nodes
	 */
	public Vector<Edge> calculateAlgo(DataModel data)
	{
		Vector<Node> n = data.getNodeListAll().get_nodelist();
	    Vector<Edge> e = data.get_edgeList();
	    Vector<Edge> eAll = new Vector<Edge>();
	    
	    //to make graph bidirectional, add all reverse edges of existing edges
	    for (Edge edge : e) {
	    	eAll.add(edge.getReverse());
		}
	    eAll.addAll(e);
	    
	    Graph g = new Graph(n, eAll);
	    
	    //executes dijkstra algorithm for solving shortest path problem and
	    //gets a map which contains for each node the shortest path to start node
	    HashMap<Node,Vector<Edge>> retval = Dijkstra.compute(g, n.get(data.getStartNodes().first().getID()-1));
	    Vector<Edge> edgeResultList = null;
	    
	    //add only shortest path from desired end to start node
	    for(Map.Entry<Node, Vector<Edge>> entry : retval.entrySet())
	    {
	    	Node node = entry.getKey();
	    	if (node == data.getEndNodes().first())
	    	{
	    		edgeResultList = new Vector<Edge>();
	    		for (Edge edge : entry.getValue()) {
		    		edgeResultList.add(edge);		    		
		    	}
		    	return edgeResultList;		    	
	    	}	    	
		}
	    return null;
	}
}

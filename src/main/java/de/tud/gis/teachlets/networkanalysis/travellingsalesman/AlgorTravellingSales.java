package de.tud.gis.teachlets.networkanalysis.travellingsalesman;
import java.util.Vector;

/**
 * This class models the Travelling-Salesman-Problem
 */
public class AlgorTravellingSales
{
	private final int MAXSTARTNODES = 1; 
	private final int MAXENDNODES = 49;
	private NodeList _nodeList;
	private Vector<Edge> _edgeList;
	private Vector<Edge> edgeResultList = new Vector<Edge>();
	static int[][] dist;
	private TSPSimulateAnnealing worker;
	
	/** 
	 * Class constructor
	 */
	public AlgorTravellingSales()
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
		String str = "\n\nBeim Travelling-Salesman-Problem (auch Problem des Handlungsreisenden) geht man davon aus, dass ein Handlungsreisender n viele Städte anfahren und anschließend wieder zur Startstadt " +
				"zurückkehren möchte. Gesucht ist dann eine Route, welche die Reiseentferungen minimiert." +
		"\n\nDa es für dieses Problem noch keine exakten, effizienten Algorithmen gibt, wurde die kürzeste Route hier mit einem heuristischen Optimierungsverfahren, dem so genannten " +
		"Simulated Annealing, ermittelt. Dabei wird versucht, ausgehend von einer beliebigen Route, durch geeignete Abänderung zu einer kürzeren Lösung zu gelangen, wobei allerdings " +
		"auch Verschlechterungen um bis zu einem bestimmten Toleranzwert akzeptiert werden. Dies wird solange durchgeführt, wobei der Toleranzwert " +
		"kontinuierlich verringert wird, bis keine Abänderung mehr zu einer Verbesserung führt und somit ein Optimum gefunden ist." +
		"\n\nDas Travelling-Salesman-Problem findet z.B. Anwendung in der Tourenplanung und Logistik." +
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
			return "Bitte setzen Sie die Startstadt durch Klick an eine beliebige\nStelle in der Karte.";			
		} else if (phase == DataModel.AlgorPhase.SETENDNODE) {
			return "Bitte setzen Sie max. " + MAXENDNODES + " weitere Städte für die Rundreise.\nDurch Klick auf den unteren Button kann der Algorithmus\ngestartet werden.";		
		} else if (phase == DataModel.AlgorPhase.SHOWRESULT) {
			return "Gibt es mehrere Möglichkeiten einer Rundreise, können diese\ndurch" +
					" mehrmaliges Klicken des unteren Buttons angezeigt werden." +
					"\nUm neue Routenpunkte festzulegen, den oberen Button klicken.";				
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
	 * builds matrix which contains distances from each node to others
	 * @param data model
	 * @return array containing distances
	 */
	private int[][] buildDistanceMatrix(DataModel data)
	{
		int[][] intarr = new int[_nodeList.size()][_nodeList.size()];
		int i, j;
		Node n1, n2;
		
		System.out.println("buildmatrix: _nodeList.size: " + _nodeList.size());
		for (i = 0; i < _nodeList.size(); i++)
		{
			n1 = _nodeList.get(i);
			for (j = n1.getID(); j < _nodeList.size(); j++)
			{
				n2 = _nodeList.get(j);
				
				int dist = 0;
				dist = (int) Math.sqrt(Math.pow((n1.getX() - n2.getX()), 2) + Math.pow((n1.getY() - n2.getY()), 2));
				intarr[i][j] = (int) dist;
				intarr[j][i] = (int) dist;				
			}
		}
		return intarr; 
	}
	
	/**
	 * calculates algorithm to solve problem and returns solution
	 * 
	 * @param data model
	 * @return vector containing solution nodes
	 */
	public Vector<Edge> calculateAlgo(DataModel data)
	{
		//get matrix with all distances from one node to others
		dist = buildDistanceMatrix(data);
		
		//execute simulate anneling algorithm to retrieve shortest tour
		if (worker != null)
	      worker = null;
	    worker = new TSPSimulateAnnealing(this);
	    worker.run();
	    update();	    
	    
		return edgeResultList;
	}
	
	/**
     * Called to get the number of cities.
     *
     * @return The number of cities.
     */
	public int getCount()
	{
		return _nodeList.size();
	}

	/**
	 * Returns the distance between two cities.
	 *
	 * @param i The first city.
	 * @param j The second city.
	 * @return The distance between the two cities.
	 */
	public double getError(int i, int j)
	{
		int c1 = worker.order[i % _nodeList.size()];
	    int c2 = worker.order[j % _nodeList.size()];
	    return _nodeList.get(c1).proximity(_nodeList.get(c2));
	}

	/**
	 * Called to determine if annealing should take place.
	 *
	 * @param d The distance.
	 * @return True if annealing should take place.
	 */
	public void update()
	{
		//delete old result
		edgeResultList.clear();
		
		if (_nodeList != null)
		{
		  Node firstn = null, lastn = null, n1 = null, n2 = null;
		  
		  for (int i = 0; i < _nodeList.size(); i++)
		  {
		    int icity = worker.minimalorder[i];
		    if (i != 0)
		    {
		      int last = worker.minimalorder[i - 1];
		      n1 = _nodeList.get(icity);
		      n2 = _nodeList.get(last);
		      Edge edge = new Edge(n1, n2, dist[n1.getID()-1][n2.getID()-1]);
		      edgeResultList.add(edge);
		    
		      lastn = _nodeList.get(icity);	          
		    } else {
		    	firstn = _nodeList.get(icity);
		    }	        
		  }
		  Edge edge = new Edge(firstn, lastn, dist[firstn.getID()-1][lastn.getID()-1]);
		  edgeResultList.add(edge);		  
		}		
	}
}

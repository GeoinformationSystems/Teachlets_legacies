package de.tud.gis.teachlets.networkanalysis.locatallocation;
import java.util.Vector;

/**
 * This class models the Location-Allocation-Problem
 */
public class AlgorLocatAllocat
{
	private final int MAXSTARTNODES = 0; 
	private final int MAXENDNODES = 30;
	private NodeList _nodeList;
	private Vector<Edge> _edgeList;	
	
	/** 
	 * Class constructor
	 */
	public AlgorLocatAllocat()
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
		String str = "\n\nBei der Standortplanung wird der optimale Standort gesucht, von dem aus n viele Städte mit minimalen Wegekosten erreicht werden können." +
			"\n\nDafür wird hier die Methode des kleinsten umschließenden Kreises angewendet, bei der ein Kreis mit minimalem Radius ermittelt wird, der alle n Städte einschließt. " +
			"Der Mittelpunkt dieses Kreises bildet dann den optimalen Standort, da von diesem Punkt aus die Distanz zu den am weitesten entfernten Städten minimal ist." +
			"\n\nStandortplanung bzw. die Suche nach dem optimalen Standort findet z.B. Anwendung bei der Planung von Feuerwehren, Schulen usw." +
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
		if (phase == DataModel.AlgorPhase.SETENDNODE) {
			return "Bitte setzen Sie max. " + MAXENDNODES + " Städte in die Karte.\nDurch Klick auf den unteren Button kann der Algorithmus\ngestartet werden.";			
		} else if (phase == DataModel.AlgorPhase.SHOWRESULT) {
			return "Der optimale Standort und der umschließende Kreis\nwerden angezeigt.\nUm neue Städte zu setzen, den oberen Button klicken!";				
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
	public Vector<Node> calculateAlgo(DataModel data)
	{
		Vector<Node> v = new Vector<Node>();
		MyPoint[]  bndry = new MyPoint[3];
		MyPoint[] p = new MyPoint[_nodeList.size()];
		MyPoint minCenter = new MyPoint(0,0);
		double minRadius;
		
		//save coordinates of all nodes in point array
		for (int i = 0; i < _nodeList.size(); i++) {
			int dia = _nodeList.get(i).getDiameter();
			p[i] = new MyPoint(_nodeList.get(i).getX()+dia/2, _nodeList.get(i).getY()+dia/2);
		}
		
	    //execute algorithm
	    MyCircle minC = BoundingCircle.minCircle( _nodeList.size(), p, 0, bndry );
		minCenter = minC.getMiddlePoint();
		minRadius = minC.getRadius();
		double x = minCenter.getX();
		double y = minCenter.getY();
		
		//save found optimal location as node into Vector	
		Node nOpt = new Node((int) x, (int) y, data.getDiameter(), "optimaler Standort");
		v.add(nOpt);
		//save found smallest bounding circle as node into Vector
		Node n2 = new Node((int) x, (int) y, (int) minRadius*2, "Kreis");
		v.add(n2);
		
		return v;
	}
}

package de.tud.gis.teachlets.networkanalysis.locatallocation;
import java.util.Iterator;
import java.util.Vector;

/** 
 * This class contains data and algorithm handling
 */ 
public class DataModel 
{
	private int _diameter;
	private NodeList _nodeListAll; //general node list (list of nodes valid for all algorithms)
	private NodeList _startNodes;
	private NodeList _endNodes;
	private Vector<Edge> _edgeResultList;
	private Vector<Node> _nodeResultList;
	private AlgorLocatAllocat _algorithm;
	public enum AlgorPhase { SETSTARTNODE, SETENDNODE, SHOWRESULT };
	private AlgorPhase _actualphase;
	
	/** 
	 * Class constructor.
	 */
	DataModel(int d)
	{
		_diameter = d;
		_nodeListAll = new NodeList();
		_startNodes = new NodeList();
		_endNodes = new NodeList();
		_edgeResultList = new Vector<Edge>();
		_nodeResultList = new Vector<Node>();		
		setAlgorithm(new AlgorLocatAllocat());
	}
	
	/** 
	 * return diameter of nodes
	 * 
	 * @return diameter
	 */
	public int getDiameter()
	{
		return _diameter;
	}
	
	/** 
	 * set diameter of nodes
	 * 
	 * @param diameter
	 */
	public void setDiameter(int d)
	{
		this._diameter = d;
	}
	
	/** 
	 * save node general node list
	 * 
	 * @param Node object
	 */
	public void add(Node n)
	{
		_nodeListAll.add(n);		
	}
	
	/** 
	 * save node in list for specific algorithm
	 * 
	 * @param Node object
	 */
	public void addNodeAlgo(Node n)
	{
		if (getActualPhase() == AlgorPhase.SETSTARTNODE ||
			getActualPhase() == AlgorPhase.SETENDNODE	) 
		{
			if (getAlgorithm() != null)
				getAlgorithm().addNode(n);
			else
				System.out.println("There is not set an algorithm!");
		}
	}
	
	/** 
	 * return general node list
	 * 
	 * @return Node list
	 */
	public NodeList getNodeListAll()
	{
		return _nodeListAll;
	}
	
	/** 
	 * return list containing specific nodes for an algorithm
	 * 
	 * @return Node list
	 */
	public NodeList getNodeListAlgo()
	{
		return _algorithm.getNodeList();
	}
	
	/** 
	 * returns iterator for general node list
	 * 
	 * @return Iterator object of Node list
	 */
	public Iterator<Node> nodeIterator()
	{
		return _nodeListAll.iterator();		
	}
	
	/** 
	 * clear general node list, start node list and end node list 
	 */
	public void clearNodeListAll()
	{
		_nodeListAll.clear();
		_startNodes.clear();
		_endNodes.clear();
	}
	
	/** 
	 * clear start node list and end node list
	 */
	public void clearNodeListStartEnd()
	{
		_startNodes.clear();
		_endNodes.clear();
	}
	
	/** 
	 * save edge in list
	 * 
	 * @param Edge object
	 */
	public void add(Edge e)
	{
		if (_algorithm != null)
			_algorithm.addEdge(e);
		else
			System.out.println("There is set no algorithm!");
	}
	
	/**
	 * return specific edge list of algorithm
	 * 
	 * @return edge list
	 */
	public Vector<Edge> get_edgeList()
	{
		return _algorithm.getEdges();
	}
	
	/** 
	 * returns iterator of specific edge list of algorithm
	 * 
	 * @return Iterator object of Edge list
	 */
	public Iterator<Edge> edgeIterator()
	{
		return _algorithm.getEdges().iterator();		
	}
	
	/** 
	 * clear specific edge list of algorithm
	 */
	public void removeEdges()
	{
		_algorithm.getEdges().clear();
	}
	
	/**
	 * add start nodes to start node list
	 * 
	 * @param start node
	 */
	public void addStartNode(Node n)
	{
		if (getActualPhase() == AlgorPhase.SETSTARTNODE) 
		{
			_startNodes.add(n);
			//if maximum number of allowed start nodes has been reached change phase
			if (_startNodes.size() == _algorithm.getMaxStartNodes())
				if (_algorithm.getMaxEndNodes() == 0 )
					setActualPhase(AlgorPhase.SHOWRESULT);
				else
					setActualPhase(AlgorPhase.SETENDNODE);
		}	
	}
	
	/**
	 * return start node list
	 * 
	 * @return start node list
	 */
	public NodeList getStartNodes()
	{
		return _startNodes;
	}
	
	/**
	 * add end nodes to end node list
	 * 
	 * @param end node
	 */
	public void addEndNode(Node n)
	{
		if (getActualPhase() == AlgorPhase.SETENDNODE) 
		{
			_endNodes.add(n);
			//if maximum number of allowed end nodes has been reached change phase
			if (_endNodes.size() == _algorithm.getMaxEndNodes())
				setActualPhase(AlgorPhase.SHOWRESULT);
		}
	}
	

	/**
	 * return end node list
	 * 
	 * @return end node list
	 */
	public NodeList getEndNodes()
	{
		return _endNodes;
	}
	
	/**
	 * set edge list containing result edges after computing algorithm
	 * 
	 * @param result edge list
	 */
	public void setEdgeResultList(Vector<Edge> resultList)
	{
		_edgeResultList = resultList;
	}
	
	/**
	 * set node list containing result nodes after computing algorithm
	 * 
	 * @param result node list
	 */
	public void setNodeResultList(Vector<Node> resultList)
	{
		_nodeResultList = resultList;
	}
	
	/**
	 * return edge list containing result edges after computing algorithm
	 * 
	 * @param result edge list
	 */
	public Vector<Edge> getEdgeResultList()
	{
		return _edgeResultList;
	}
	
	/**
	 * return node list containing result nodes after computing algorithm
	 * 
	 * @param result node list
	 */
	public Vector<Node> getNodeResultList()
	{
		return _nodeResultList;
	}
	
	/**
	 * clear edge and node result lists
	 */
	public void clearResultLists()
	{
		_edgeResultList.clear();
		_nodeResultList.clear();
	}
	
	/**
	 * return actual phase of algorithm
	 * 
	 * @return algorithm phase
	 */
	public AlgorPhase getActualPhase()
	{
		return _actualphase;
	}

	/**
	 * set actual phase of algorithm
	 * 
	 * @param algorithm phase
	 */
	public void setActualPhase(AlgorPhase phase)
	{
		_actualphase = phase;		
	}

	public void setAlgorithm(AlgorLocatAllocat _algoLocat)
	{
		this._algorithm = _algoLocat;
	}

	public AlgorLocatAllocat getAlgorithm()
	{
		return _algorithm;
	}
}
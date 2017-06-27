package de.tud.gis.teachlets.networkanalysis.travellingsalesman;
import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputAdapter;

/** 
 * This class handles mouse events 
 */ 
public class MyMouseAdapter extends MouseInputAdapter 
{
	JPaintPanel _viewer; 
	DataModel _data;
	
	/** 
	 * Class constructor.
	 */
	public MyMouseAdapter(JPaintPanel v, DataModel data)
	{
		_viewer = v;
		_data = data;
	}
	
	/** 
	 * overwrites mousePressed method
	 * according to actual algorithm select given node or set own node into map
	 * 
	 * @param MouseEvent
	 */
	public void mousePressed(MouseEvent e) 
	{
		Node n = null;
		
		if (_data.getAlgorithm() == null)
			return;
		
		//user can set own nodes into map which will be saved
		//set own start nodes
		if (_data.getActualPhase() == DataModel.AlgorPhase.SETSTARTNODE)
		{
			n = new Node(e.getX()-_data.getDiameter()/2, e.getY()-_data.getDiameter()/2, _data.getDiameter(), "1");
			_data.addNodeAlgo(n);
			_data.addStartNode(n);				
		} else { //set own end nodes
			int id = 0;
			if (_data.getAlgorithm().getNodeList().size() > 0)
				id = _data.getAlgorithm().getNodeList().last().getID() + 1;
			else
				id = 1;
			n = new Node(e.getX()-_data.getDiameter()/2, e.getY()-_data.getDiameter()/2, _data.getDiameter(), Integer.toString(id));
			_data.addNodeAlgo(n);
			_data.addEndNode(n);
		}
		_viewer.repaint();			
	}
	
	/** 
	 * overwrites mouseMoved method
	 * 
	 * 
	 * @param MouseEvent
	 */
	public void mouseMoved(MouseEvent e) 
	{
		/*Node n = null;
		
		if (_data.getAlgorithm() == null)
			return;
		
		java.util.Iterator<Node> nIter = _data.nodeIterator();
		
		//only interpret mouse movement in phases in which node has to be set
		if (_data.getActualPhase() != DataModel.AlgorPhase.SETSTARTNODE &&
				_data.getActualPhase() != DataModel.AlgorPhase.SETENDNODE)
			return;
		
		//if given node is hit by mouse cursor, save its id
		while (nIter.hasNext())
		{
			n = nIter.next();
			if (n.getPolygon().contains(e.getX(), e.getY()))
			{
				_viewer.setMouseoverNode(n.getID());
				_viewer.repaint();
				break;
			} else {
				_viewer.setMouseoverNode(0);
				_viewer.repaint();
			}	
		}*/		
	}
}
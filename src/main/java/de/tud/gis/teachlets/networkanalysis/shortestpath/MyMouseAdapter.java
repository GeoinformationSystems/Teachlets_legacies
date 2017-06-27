package de.tud.gis.teachlets.networkanalysis.shortestpath;
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
		
		//user can select one of given nodes		
		java.util.Iterator<Node> nIter = _data.nodeIterator();
		
		while (nIter.hasNext())
		{
			n = nIter.next();
			
			//if mouse cursor is over given node, select this node depending on actual phase
			if (n.getPolygon().contains(e.getX(), e.getY()))
			{
				if (_data.getActualPhase() == DataModel.AlgorPhase.SETSTARTNODE)
				{
					_data.addStartNode(n);
				} 
				else if (_data.getActualPhase() == DataModel.AlgorPhase.SETENDNODE)
				{
					if (!_data.getStartNodes().contains(n))
						_data.addEndNode(n);
				}					
				_viewer.setMouseoverNode(0);
				_viewer.repaint();
				return;
			} 
		}		
	}
	
	/** 
	 * overwrites mouseMoved method
	 * 
	 * 
	 * @param MouseEvent
	 */
	public void mouseMoved(MouseEvent e) 
	{
		Node n = null;
		
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
		}		
	}
}
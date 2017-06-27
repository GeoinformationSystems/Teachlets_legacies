package de.tud.gis.teachlets.networkanalysis.shortestpath;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/** 
 * This class displays map image and all graphical elements (lines, ...) in a panel 
 */ 
public class JPaintPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private Dimension _preferredSize ;
	private DataModel _data;
	private JTextArea _taInstruc;
	private JButton _btnAlgo;
	private int _mouseovernodeID;
	final Stroke THINLINE = new BasicStroke( 1 );
	final Stroke MEDLINE = new BasicStroke( 2 );
	final Stroke FATLINE = new BasicStroke( 3 );
	private Color cResult = new Color(127, 255,	212); //aquamarine
	private Image _imgMap;	
	
	/**
	 * Class constructor
	 */
	public JPaintPanel(DataModel data)
	{
		_data = data;
		_taInstruc = null;
		_btnAlgo = null;
		
		URL myurl = this.getClass().getResource("../Karte_Deutschland.jpg");
		//only try to load image if image path has been found to prevent crash
		if (myurl != null) {
			_imgMap = Toolkit.getDefaultToolkit().getImage(myurl);
			prepareImage(_imgMap, this); //starts processing of image
		}
		
		//set font for text panels
		this.setFont(new Font("Arial", Font.BOLD, 11 ));			
	}
	
	/**
	 * sets width and height of panel
	 * 
	 * @param dimension including width and height
	 */
	public void setPreferredSize(Dimension dim)
	{
		_preferredSize = dim;		
	}
	
	/**
	 * returns width and height of panel
	 * 
	 * @return dimension including width and height
	 */
	public Dimension getPreferredSize()
	{
		return _preferredSize;		
	}
	
	/**
	 * paints graphical elements into panel
	 * 
	 * @param graphics object
	 */
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D) g; 
		
		//draw panel background
		g2.setColor(Color.white);
		g2.fillRect(0, 0, _preferredSize.width, _preferredSize.height);
		
	    //draw map image
		g2.drawImage(_imgMap, 1, 1, null);
		
		//draw panel border
		g2.setColor(Color.black);
		g2.drawRect(0, 0, _preferredSize.width, _preferredSize.height);		
		
		//set text and text color of label displaying specific instruction text for algorithm 
		if (_taInstruc != null) {
			if (_data.getActualPhase() == DataModel.AlgorPhase.SETSTARTNODE)
				_taInstruc.setForeground(Color.blue);
			else if (_data.getActualPhase() == DataModel.AlgorPhase.SETENDNODE)
				_taInstruc.setForeground(Color.red);
			else if (_data.getActualPhase() == DataModel.AlgorPhase.SHOWRESULT)
				_taInstruc.setForeground(new Color(0, 180, 0)); //green
			_taInstruc.setText(_data.getAlgorithm().getInstructionText(_data.getActualPhase()));
		}
		
		//enable/disable "start algorithm" button according to actual phase
		if (_btnAlgo != null) 
		{
			if (_data.getActualPhase() == DataModel.AlgorPhase.SETENDNODE)
			{
				_btnAlgo.setVisible(true);
				if (_data.getEndNodes().size() > 0) {
					_btnAlgo.setEnabled(true);
				} else {
					_btnAlgo.setEnabled(false);
				}				
			} 
			else if (_data.getActualPhase() == DataModel.AlgorPhase.SHOWRESULT)
			{
				_btnAlgo.setEnabled(true);
			}
		}
		
		//draw edges and distance if necessary
		if (_data.getAlgorithm() != null) {
			drawEdges(g2);
			drawWeights(g2);
		}
		//draw nodes
		drawNodes(g2);
	}
	
	/**
	 * draw general nodes for all algorithm and specific nodes per algorithm
	 * 
	 * @param graphics object
	 */
	public void drawNodes(Graphics2D g2)
	{
		Node n = null;
		Color clrInside = null;
		Color clrOutside = Color.white;
		boolean drawBig = false;
		
		//draw general nodes valid for all algorithms
		java.util.Iterator<Node> nIter = _data.nodeIterator();
		while (nIter.hasNext())
		{
			n = nIter.next();
			
			//set color and size of node according to algorithm, phase and type of node
			if (_data.getAlgorithm() != null)
			{
				if (_data.getStartNodes().get_nodelist().contains(n)) //node = start node
				{
					clrInside = Color.blue;
					drawBig = true;				
				} 
				else if (_data.getEndNodes().get_nodelist().contains(n)) //node = end node
				{
					clrInside = Color.red;
					drawBig = true;
				}
				else if (n.getID() == _mouseovernodeID) //mouse cursor touches node
				{
					clrInside = Color.black;
					drawBig = false;
					
					if (_data.getActualPhase() == DataModel.AlgorPhase.SETSTARTNODE) { //start node has to be chosen
						clrInside = Color.blue;
						drawBig = true;
					} else if (_data.getActualPhase() == DataModel.AlgorPhase.SETENDNODE) { //end node has to be chosen
						clrInside = Color.red;
						drawBig = true;
					}
				} 
				else //normal node (not start or end node)
				{
					clrInside = Color.black;
					drawBig = false;
				}
			} 
			else //normal node
			{
				clrInside = Color.black;
				drawBig = false;
			}
			
			//draw small or big variant of node 
			g2.setColor(clrInside);
			if (drawBig)
				g2.fillPolygon(n.getBigPolygon());
			else
				g2.fillPolygon(n.getPolygon());
			g2.setColor(clrOutside);
			g2.setStroke(THINLINE);
			if (drawBig)
				g2.drawPolygon(n.getBigPolygon());
			else
				g2.drawPolygon(n.getPolygon());	
			
			//label node
			g2.setColor(new Color(255, 255, 100));
			g2.drawString(n.getName(), n.getX()+_data.getDiameter()-(n.getName().length()*3), n.getY()-(_data.getDiameter()/2));
		}
		
		//draw specific nodes and result nodes of algorithm if available
		if (_data.getAlgorithm() != null)
		{
			nIter = _data.getNodeListAlgo().iterator();
			while (nIter.hasNext())
			{
				n = nIter.next();
				if (_data.getStartNodes().get_nodelist().contains(n)) {
					clrInside = Color.blue;
				} else if (_data.getEndNodes().get_nodelist().contains(n)){
					clrInside = Color.red;
				} else {
					clrInside = Color.black;
				}
				
				//draw big variant of node 
				g2.setStroke(THINLINE);
				g2.setColor(clrInside);
				g2.fillPolygon(n.getBigPolygon());
				g2.setColor(clrOutside);
				g2.drawPolygon(n.getBigPolygon());
				
				//label node
				g2.setColor(Color.white);
				if (n.getID() < 10)
					g2.drawString(Integer.toString(n.getID()), n.getX()+_data.getDiameter()/2-2, n.getY()+_data.getDiameter()/2+4);
				else
					g2.drawString(Integer.toString(n.getID()), n.getX()+_data.getDiameter()/2-5, n.getY()+_data.getDiameter()/2+4);				
			}
			
			//draw result nodes for algorithm (only in case of location-allocation-problem)
			nIter = _data.getNodeResultList().iterator();
			int i = 0;
			while (nIter.hasNext())
			{
				n = nIter.next();
				int dia = n.getDiameter();
				if (i == 0) { //optimal location
					g2.setStroke(THINLINE);
					g2.setColor(cResult);
					g2.fillOval((n.getX()-dia/2), (n.getY()-dia/2), dia, dia);
					g2.setColor(Color.black);
					g2.drawOval((n.getX()-dia/2), (n.getY()-dia/2), dia, dia);
					g2.setColor(cResult);
					g2.drawString(n.getName(), n.getX()+_data.getDiameter()-(n.getName().length()*3), n.getY()-(_data.getDiameter()/2)-5);
				} else if (i == 1) { //smallest bounding circle
					g2.setColor(Color.white);
					g2.drawOval((n.getX()-dia/2), (n.getY()-dia/2), dia, dia);
				}
				i++;
			}
		}
	}
	
	/**
	 * draw edges 
	 * 
	 * @param graphics object
	 */
	public void drawEdges(Graphics2D g2)
	{
		Edge e = null;
		int x1, x2, y1, y2;
		java.util.Iterator<Edge> iter = null;
		
		boolean isResultEdge = false;
		
		//in case of TSP only draw result edges
		iter = _data.edgeIterator();
			
		//draw specific edges or result edges
		while (iter.hasNext())
		{
			e = iter.next();
			if (_data.getEdgeResultList().contains(e) || _data.getEdgeResultList().contains(e.getReverse()))
				isResultEdge = true;
			else
				isResultEdge = false;
		
			x1 = e.getStartNode().getX() + _data.getDiameter()/2;
			y1 = e.getStartNode().getY() + _data.getDiameter()/2;
			x2 = e.getEndNode().getX() + _data.getDiameter()/2;
			y2 = e.getEndNode().getY() + _data.getDiameter()/2;
			
			g2.setStroke(THINLINE);
			if (isResultEdge) {
				g2.setColor(cResult);
				g2.setStroke(MEDLINE);
			} else {
				g2.setColor(Color.white);
				g2.setStroke(THINLINE);
			}
			g2.drawLine(x1, y1, x2, y2);						
		}		
	}
	
	/**
	 * draw weight of weighted edges
	 * 
	 * @param graphics object
	 */
	public void drawWeights(Graphics2D g2)
	{
		Edge e = null;
		int x1, x2, y1, y2;
		java.util.Iterator<Edge> iter = null;
		boolean isResultEdge = false;
		
		iter = _data.edgeIterator();
		
		//draw weight depending on if edge is normal or result edge
		while (iter.hasNext())
		{
			e = iter.next();
			x1 = e.getStartNode().getX() + _data.getDiameter()/2;
			y1 = e.getStartNode().getY() + _data.getDiameter()/2;
			x2 = e.getEndNode().getX() + _data.getDiameter()/2;
			y2 = e.getEndNode().getY() + _data.getDiameter()/2;
			
			if (_data.getEdgeResultList().contains(e) || _data.getEdgeResultList().contains(e.getReverse()))
				isResultEdge = true;
			else
				isResultEdge = false;
			double dist = 0;
			dist = (int) Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
			dist = Math.round(dist);
			
			//draw rect
			g2.setStroke(THINLINE);
			if (isResultEdge) {
				g2.setColor(Color.black);
			} else {
				g2.setColor(Color.white);
			}
			if (e.getWeight() < 10)
				g2.fillRect(x1+(x2-x1)/2-5, y1+(y2-y1)/2-6, 10, 12);
			else if (e.getWeight() <= 99)
				g2.fillRect(x1+(x2-x1)/2-10, y1+(y2-y1)/2-6, 18, 12);
			else 
				g2.fillRect(x1+(x2-x1)/2-15, y1+(y2-y1)/2-6, 26, 12);
			
			//draw outline of rect
			if (isResultEdge) {
				g2.setColor(cResult);								
			} else {
				g2.setColor(Color.black);
			}			
			if (e.getWeight() < 10)
				g2.drawRect(x1+(x2-x1)/2-5, y1+(y2-y1)/2-6, 10, 12);
			else if (e.getWeight() <= 99)
				g2.drawRect(x1+(x2-x1)/2-10, y1+(y2-y1)/2-6, 18, 12);
			else 
				g2.drawRect(x1+(x2-x1)/2-15, y1+(y2-y1)/2-6, 26, 12);
			
			//draw weight string
			if (isResultEdge) {
				g2.setColor(Color.white);
			} else {
				g2.setColor(Color.black);
			}			
			if (e.getWeight() < 10)
				g2.drawString(String.valueOf(e.getWeight()), x1+(x2-x1)/2-2, y1+(y2-y1)/2+5);
			else if (e.getWeight() <= 99)
				g2.drawString(String.valueOf(e.getWeight()), x1+(x2-x1)/2-7, y1+(y2-y1)/2+5);
			else
				g2.drawString(String.valueOf(e.getWeight()), x1+(x2-x1)/2-12, y1+(y2-y1)/2+5);
		}
	}
	
	/**
	 * set id of node mouse cursor is over at the moment
	 * 
	 * @param id of node
	 */
	public void setMouseoverNode(int id)
	{
		_mouseovernodeID = id;		
	}
	
	/**
	 * set text area object depending on actual algorithm
	 *  
	 * @param text area object
	 */
	public void setActualInstructionTextArea(JTextArea ta)
	{
		_taInstruc = ta; 
	}
	
	/**
	 * set button object depending on actual algorithm
	 * 
	 * @param button object
	 */
	public void setActualContinueButton(JButton button)
	{
		_btnAlgo = button; 
	}
}



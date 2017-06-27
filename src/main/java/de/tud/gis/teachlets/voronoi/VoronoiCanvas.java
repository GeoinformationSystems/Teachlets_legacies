package de.tud.gis.teachlets.voronoi;
import java.awt.*;
import java.awt.event.*;
import java.util.Enumeration;
import java.util.Random;

import javax.swing.JPanel;

/**
 * 
 * @author danielkadner
 *
 */
class VoronoiCanvas extends Canvas implements MouseListener, MouseMotionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8394775979235944621L;
	Triangulation graph;
	PointSet set;
	static final int RIGHT = 1;
	static final int LEFT = 2;
	static final int CENTER = 3;
	int button;
	GraphPoint prev;
	public boolean drawDelaunay;
	public boolean drawVoronoi;
	boolean isOnPoint;

	public VoronoiCanvas(JPanel panel) {
		isOnPoint = false;
		System.out.println(panel.getWidth());
		this.setBackground(new Color(232,232,232));
		initialize();
		System.out.println(this.getWidth());
		System.out.println(this.getHeight());
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	void initialize() {
		if (graph == null) {
			graph = new Triangulation();
		}
		else {
			graph.removeAllElements();
			graph.init();
		}
		if (set == null)
			set = new PointSet();
		else
			set.removeAllElements();
	}

	public void mouseClicked(MouseEvent mouseevent) {
	}

	@SuppressWarnings("rawtypes")
	public void mouseDragged(MouseEvent mouseevent) {
		int i = mouseevent.getX();
		int j = mouseevent.getY();
		if (button == 3 || button == 1) return;
		int k = Math.max(Math.min(i, getSize().width - 3), 3);
		int l = Math.max(Math.min(j, getSize().height - 3), 3);
		if (prev != null && k == ((Point) (prev)).x && l == ((Point) (prev)).y) return;
		if (isOnPoint) {
			GraphPoint graphpoint1 = new GraphPoint(k, l);
			addPoint(graphpoint1);
			prev = graphpoint1;
			isOnPoint = false;
			return;
		}
		for (Enumeration enumeration = set.elements(); enumeration.hasMoreElements();) {
			GraphPoint graphpoint = (GraphPoint) enumeration.nextElement();
			if (((Point) (graphpoint)).x == k && ((Point) (graphpoint)).y == l) {
				isOnPoint = true;
				break;
			}
		}

		if (prev != null) deletePoint(prev);
		if (!isOnPoint) {
			GraphPoint graphpoint2 = new GraphPoint(k, l);
			addPoint(graphpoint2);
			prev = graphpoint2;
		}
		repaint();
	}

	public void mouseMoved(MouseEvent mouseevent) {
	}

	public void mouseExited(MouseEvent mouseevent) {
	}

	@SuppressWarnings("rawtypes")
	public void mousePressed(MouseEvent mouseevent) {
		int i = mouseevent.getX();
		int j = mouseevent.getY();
		System.out.println(i +" "+j);
		boolean flag = false;
		GraphPoint graphpoint = null;
		isOnPoint = false;
		for (Enumeration enumeration = set.elements(); enumeration.hasMoreElements();) {
			graphpoint = (GraphPoint) enumeration.nextElement();
			if (Math.abs(((Point) (graphpoint)).x - i) < 3 && Math.abs(((Point) (graphpoint)).y - j) < 3) {
				flag = true;
				break;
			}
		}

		if (mouseevent.isMetaDown()) {
			button = 1;
			if (flag && graphpoint != null) deletePoint(graphpoint);
		}
		else if (mouseevent.isAltDown()) {
			button = 3;
		}
		else {
			button = 2;
			if (!flag) {
				GraphPoint graphpoint1 = new GraphPoint(i, j);
				addPoint(graphpoint1);
				prev = graphpoint1;
			}
			else {
				prev = graphpoint;
			}
		}
	}

	public void mouseReleased(MouseEvent mouseevent) {
	}

	public void mouseEntered(MouseEvent mouseevent) {
	}

	@SuppressWarnings("unchecked")
	void addPoint(GraphPoint graphpoint) {
		Random r = new Random();
		graphpoint.setValue(r.nextDouble()*10);
		set.addElement(graphpoint);
		graph.InsertSite(new GraphPoint(((Point) (graphpoint)).x, ((Point) (graphpoint)).y));
		repaint();
	}

	@SuppressWarnings("rawtypes")
	void deletePoint(GraphPoint graphpoint) {
		set.removeElementAt(set.indexOf(graphpoint));
		if (graph == null) {
			graph = new Triangulation();
		}
		else {
			graph.removeAllElements();
			graph.init();
		}
		GraphPoint graphpoint1;
		for (Enumeration enumeration = set.elements(); enumeration.hasMoreElements(); graph.InsertSite(graphpoint1))
			graphpoint1 = new GraphPoint((GraphPoint) enumeration.nextElement());

		repaint();
	}
	
	void setChoosen(boolean delaTri, boolean voroDia){
		this.drawDelaunay = delaTri;
		this.drawVoronoi = voroDia;
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void paint(Graphics g) {
		g.setColor(getBackground());
		g.fillRect(0, 0, getSize().width, getSize().height);
		graph.draw(g, drawDelaunay, drawVoronoi);
		set.draw(g);
	}

}
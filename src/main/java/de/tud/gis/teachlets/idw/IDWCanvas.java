package de.tud.gis.teachlets.idw;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @author danielkadner
 * 
 */
class IDWCanvas extends Canvas implements MouseListener, MouseMotionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8394775979235944621L;
	// Triangulation graph;
	// PointSet set;
	static final int RIGHT = 1;
	static final int LEFT = 2;
	static final int CENTER = 3;
	int button;
	// GraphPoint prev;
	// public boolean drawDelaunay;
	// public boolean drawVoronoi;
	private boolean isOnPoint;
	private Point prev;
	private ArrayList<Point> pointList = null;
	private int amountBorder = 10;
	@SuppressWarnings("unused")
	private double min, max;
	private int matrixwidth;

	private int[][] matrix = null;

	private Start_App_IDW idw;

	public IDWCanvas(Start_App_IDW idw) {
		this.idw = idw;
		this.matrixwidth = idw.getMatrixwidth();
		isOnPoint = false;
		pointList = new ArrayList<Point>();
		// this.setBounds(5, 5, 90, 90);
		this.setBackground(new Color(232, 232, 232));
		initialize();
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	void initialize() {
		if (pointList == null)
			pointList = new ArrayList<Point>();
		else
			pointList.clear();
		matrix = null;
	}

	public void mouseClicked(MouseEvent mouseevent) {
	}

	public void mouseDragged(MouseEvent mouseevent) {
		int i = mouseevent.getX();
		int j = mouseevent.getY();
		// if (button == 3 || button == 1) return;
		int k = Math.max(Math.min(i, getSize().width - 4), 4);
		int l = Math.max(Math.min(j, getSize().height - 4), 4);
		if (prev != null && k == ((Point) (prev)).getX() && l == ((Point) (prev)).getY()) return;
		if (isOnPoint) {
			Point graphpoint1 = new Point(k, l);
//			idw.setValue(prev.getValue());
			graphpoint1.setValue(prev.getValue());
			addPoint(graphpoint1);
			prev = graphpoint1;
			isOnPoint = false;
			return;
		}

		for (Point p : pointList) {
			if (p.getX() == k && p.getY() == l) {
				isOnPoint = true;
				break;
			}
		}

		if (prev != null) deletePoint(prev);
		if (!isOnPoint) {
			Point graphpoint2 = new Point(k, l);
//			idw.setValue(prev.getValue());
			graphpoint2.setValue(prev.getValue());

			addPoint(graphpoint2);
			prev = graphpoint2;
		}
		repaint();
	}

	public void mouseMoved(MouseEvent mouseevent) {
	}

	public void mouseExited(MouseEvent mouseevent) {
	}

	public void mousePressed(MouseEvent mouseevent) {
		int i = mouseevent.getX();
		int j = mouseevent.getY();
		boolean flag = false;
		Point point = null;
		System.out.println("Point added " + i + " " + j);

		isOnPoint = false;

		for (Point p : pointList) {
			if (Math.abs(p.getX() - i) < 4 && Math.abs(p.getY() - j) < 4) {
				point = p;
				flag = true;
				break;
			}
		}

		if (mouseevent.isMetaDown()) {
			button = 1;
			if (flag && point != null) deletePoint(point);
		}
		else if (mouseevent.isAltDown()) {
			button = 3;
		}
		else {
			button = 2;
			if (!flag) {
				Point graphpoint1 = new Point(i, j);
				if (idw.getValue() == null){
					Random r = new Random();
					graphpoint1.setValue(r.nextInt(100));
				}
				else {
					graphpoint1.setValue(idw.getValue());
					idw.setValue(null);
				}
				addPoint(graphpoint1);
				prev = graphpoint1;
			}
			else {
				prev = point;
//				idw.setValue(prev.getValue());

			}
		}
		repaint();
	}

	public void mouseReleased(MouseEvent mouseevent) {
	}

	public void mouseEntered(MouseEvent mouseevent) {
	}

	void addPoint(Point point) {
		if (pointList.size() < amountBorder-1) {
			pointList.add(point);
			matrix = null;
			idw.updateComboBox(pointList.size());
		}
		// Random r = new Random();
		// graphpoint.setValue(r.nextDouble()*10);
		// set.addElement(graphpoint);
		// graph.InsertSite(new GraphPoint(((Point) (graphpoint)).x, ((Point)
		// (graphpoint)).y));
		repaint();
	}

	void deletePoint(Point point) {
		pointList.remove(point);
		matrix = null;
		idw.updateComboBox(pointList.size());
		// set.removeElementAt(set.indexOf(graphpoint));
		// if (graph == null) {
		// graph = new Triangulation();
		// }
		// else {
		// graph.removeAllElements();
		// graph.init();
		// }
		// GraphPoint graphpoint1;
		// for (Enumeration enumeration = set.elements();
		// enumeration.hasMoreElements(); graph.InsertSite(graphpoint1))
		// graphpoint1 = new GraphPoint((GraphPoint) enumeration.nextElement());
		//
		repaint();
	}

	void setChoosen(boolean delaTri, boolean voroDia) {
		// this.drawDelaunay = delaTri;
		// this.drawVoronoi = voroDia;
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void paint(Graphics g) {
		
		if (matrix != null){
			System.out.println("Höhe Matrix "+matrix.length);
			System.out.println("Breite Matrix "+matrix[0].length);
			System.out.println("matrix ist nicht null");
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix[0].length; j++) {
					int d =  matrix[i][j];
//					System.out.println(d);
					
					//black/white color ramp
					//g.setColor(new Color(d,d,d));
					
					//red/green color ramp
					g.setColor(new Color(255 - d, d, 0));
					g.fillRect(j*matrixwidth, i*matrixwidth, matrixwidth, matrixwidth);
//					g.setColor(Color.BLACK);
//					g.drawString(""+i, i*10, j*10);
				}
			}
			
		}
		else {
			g.setColor(Color.GRAY);
			g.fillRect(0, 0, getSize().width, getSize().height);
		}
		
		g.setColor(getBackground());
		g.setColor(Color.DARK_GRAY);
		for (Point p : pointList) {
			g.setColor(Color.DARK_GRAY);
			g.fillOval(p.getX() - 3, p.getY() - 3, 6, 6);
			g.setColor(Color.BLACK);
			g.drawOval(p.getX() - 4, p.getY() - 4, 8, 8);
			if (matrix != null) {
				//nachsacheuen welchen wert der pixel in matrix hätte
				//nd daraus den farbwert für schrift festlegen
				g.setColor(Color.white);
			}
			 
			
			g.drawString("" + p.getValue(), p.getX() + 5, p.getY() + 5);
		}
		// g.setColor(getBackground());
		// g.fillRect(0, 0, getSize().width, getSize().height);
		// graph.draw(g, drawDelaunay, drawVoronoi);
		// set.draw(g);
	}

	public boolean isEmpty() {
		if (pointList.isEmpty())
			return true;
		else
			return false;
	}

	public int amountOfEntrys() {
		return pointList.size();
	}

	public ArrayList<Point> getPointList() {
		return pointList;
	}
	
	public void setMatrix(int[][] matrix){
		this.matrix = matrix;
	}
	
	public void setMinMax(double min, double max){
		this.min = min;
		this.max = max;
	}

}
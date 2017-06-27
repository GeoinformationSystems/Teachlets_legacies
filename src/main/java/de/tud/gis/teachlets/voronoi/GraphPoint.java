package de.tud.gis.teachlets.voronoi;
import java.awt.*;
/**
 * 
 * @author danielkadner
 *
 */
class GraphPoint extends Point {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2155616262651773024L;
	public boolean isInfinite;
	private double value;

	public GraphPoint(int i, int j) {
		super(i, j);
		isInfinite = false;
	}

	public GraphPoint(GraphPoint graphpoint) {
		super(((Point) (graphpoint)).x, ((Point) (graphpoint)).y);
		isInfinite = false;
	}

	public GraphPoint() {
		isInfinite = false;
	}

	public boolean isOn(Edge edge) {
		GraphPoint graphpoint = edge.Org();
		GraphPoint graphpoint1 = edge.Dest();
		return TriArea(graphpoint, this, graphpoint1) == 0L;
	}

	public boolean isRightOf(Edge edge) {
		GraphPoint graphpoint = edge.Org();
		GraphPoint graphpoint1 = edge.Dest();
		return TriArea(this, graphpoint, graphpoint1) > 0L;
	}

	public boolean isLeftOf(Edge edge) {
		GraphPoint graphpoint = edge.Org();
		GraphPoint graphpoint1 = edge.Dest();
		return TriArea(this, graphpoint, graphpoint1) < 0L;
	}

	long TriArea(GraphPoint graphpoint, GraphPoint graphpoint1, GraphPoint graphpoint2) {
		return (long) ((((Point) (graphpoint1)).x - ((Point) (graphpoint)).x)
				* (((Point) (graphpoint2)).y - ((Point) (graphpoint)).y) - (((Point) (graphpoint2)).x - ((Point) (graphpoint)).x)
				* (((Point) (graphpoint1)).y - ((Point) (graphpoint)).y));
	}

	public boolean isInCircle(GraphPoint graphpoint, GraphPoint graphpoint1, GraphPoint graphpoint2) {
		long l = super.x;
		long l1 = super.y;
		long l2 = ((Point) (graphpoint)).x;
		long l3 = ((Point) (graphpoint)).y;
		long l4 = ((Point) (graphpoint1)).x;
		long l5 = ((Point) (graphpoint1)).y;
		long l6 = ((Point) (graphpoint2)).x;
		long l7 = ((Point) (graphpoint2)).y;
		long l8 = ((l4 - l2) * (l7 - l3) * ((l * l + l1 * l1) - l2 * l2 - l3 * l3) + (l6 - l2) * (l1 - l3)
				* ((l4 * l4 + l5 * l5) - l2 * l2 - l3 * l3) + (l - l2) * (l5 - l3)
				* ((l6 * l6 + l7 * l7) - l2 * l2 - l3 * l3))
				- (l4 - l2)
				* (l1 - l3)
				* ((l6 * l6 + l7 * l7) - l2 * l2 - l3 * l3)
				- (l6 - l2)
				* (l5 - l3)
				* ((l * l + l1 * l1) - l2 * l2 - l3 * l3)
				- (l - l2)
				* (l7 - l3)
				* ((l4 * l4 + l5 * l5) - l2 * l2 - l3 * l3);
		return l8 > 0L;
	}
	
	public void setValue(double d){
		this.value = d;
	}
	
	public double getValue(){
		return value;
	}

	public void draw(Graphics g, int i, int j, double value) {
		g.setColor(Color.DARK_GRAY);
		g.fillOval(i - 3, j - 3, 6, 6);
//		DecimalFormat df = new DecimalFormat("0.00");
//		String zahl = df.format(value);
//		g.drawString(zahl, i+5, j+5);
	}

}
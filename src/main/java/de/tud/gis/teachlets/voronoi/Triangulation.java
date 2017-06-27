package de.tud.gis.teachlets.voronoi;
import java.awt.*;

/**
 * 
 * @author danielkadner
 *
 */
class Triangulation extends QuadEdge {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6998202612272249404L;

	public Triangulation() {
		init();
	}

	public void init() {
		Edge edge = MakeQuadEdge();
		Edge edge1 = MakeQuadEdge();
		Edge edge2 = MakeQuadEdge();
		edge.SetOrg(new GraphPoint(0, -8192));
		edge1.SetOrg(new GraphPoint(-16384, 8192));
		edge2.SetOrg(new GraphPoint(16384, 8192));
		edge.SetDest(edge1.Org());
		edge.Org().isInfinite = true;
		edge1.SetDest(edge2.Org());
		edge1.Org().isInfinite = true;
		edge2.SetDest(edge.Org());
		edge2.Org().isInfinite = true;
		edge.SetOnext(edge2.Sym());
		edge.Rot().SetOnext(edge1.Rot());
		edge.Sym().SetOnext(edge1);
		edge.invRot().SetOnext(edge1.invRot());
		edge1.SetOnext(edge.Sym());
		edge1.Rot().SetOnext(edge2.Rot());
		edge1.Sym().SetOnext(edge2);
		edge1.invRot().SetOnext(edge2.invRot());
		edge2.SetOnext(edge1.Sym());
		edge2.Rot().SetOnext(edge.Rot());
		edge2.Sym().SetOnext(edge);
		edge2.invRot().SetOnext(edge.invRot());
	}

	public Edge Connect(Edge edge, Edge edge1) {
		Edge edge2 = MakeQuadEdge();
		edge2.SetOrg(edge.Dest());
		edge2.SetDest(edge1.Org());
		Splice(edge2, edge.Lnext());
		Splice(edge2.Sym(), edge1);
		return edge2;
	}

	public void DeleteEdge(Edge edge) {
		Splice(edge, edge.Oprev());
		Splice(edge.Sym(), edge.Sym().Oprev());
		for (int i = 0; i < 3; i++) {
			Edge edge1 = edge.Rot();
			removeElement(edge);
			edge = edge1;
		}

		removeElement(edge);
	}

	public void Swap(Edge edge) {
		Edge edge1 = edge.Oprev();
		Edge edge2 = edge.Sym().Oprev();
		Splice(edge, edge1);
		Splice(edge.Sym(), edge2);
		Splice(edge, edge1.Lnext());
		Splice(edge.Sym(), edge2.Lnext());
		edge.SetOrg(edge1.Dest());
		edge.SetDest(edge2.Dest());
	}

	public void InsertSite(GraphPoint graphpoint) {
		Edge edge = Locate(graphpoint);
		if (graphpoint.isOn(edge)) {
			Edge edge1 = edge.Oprev();
			DeleteEdge(edge);
			edge = edge1;
		}
		Edge edge2 = MakeQuadEdge();
		GraphPoint graphpoint1 = edge.Org();
		edge2.SetOrg(graphpoint1);
		edge2.SetDest(graphpoint);
		Splice(edge2, edge);
		do {
			edge2 = Connect(edge, edge2.Sym());
			edge = edge2.Oprev();
		}
		while (edge.Dest() != graphpoint1);
		do {
			Edge edge3 = edge.Oprev();
			boolean flag = graphpoint.isInCircle(edge.Org(), edge3.Dest(), edge.Dest());
			if (edge3.Dest().isRightOf(edge) && flag) {
				Swap(edge);
				edge = edge.Oprev();
			}
			else {
				if (edge.Org() == graphpoint1) return;
				edge = edge.Onext().Lprev();
			}
		}
		while (true);
	}

	public Edge Locate(GraphPoint graphpoint) {
		Edge edge = (Edge) firstElement();
		do {
			if (graphpoint == edge.Org() || graphpoint == edge.Dest()) return edge;
			if (graphpoint.isRightOf(edge))
				edge = edge.Sym();
			else if (!graphpoint.isRightOf(edge.next))
				edge = edge.next;
			else if (!graphpoint.isRightOf(edge.Dprev()))
				edge = edge.Dprev();
			else
				return edge;
		}
		while (true);
	}

	void GetVoronoiDiagram() {
		for (int i = 12; i < size(); i += 4) {
			Edge edge = (Edge) elementAt(i);
			if (edge.isInfinite()) {
				GraphPoint graphpoint = getVoronoiVertex(edge, edge.Onext());
				GraphPoint graphpoint1 = getVoronoiVertex(edge, edge.Oprev());
				edge.dual.SetOrg(graphpoint1);
				edge.dual.SetDest(graphpoint);
			}
		}

	}

	GraphPoint getVoronoiVertex(Edge edge, Edge edge1) {
		GraphPoint graphpoint = new GraphPoint(0, 0);
		long l = ((Point) (edge.Org())).x;
		long l1 = ((Point) (edge.Org())).y;
		long l2 = ((Point) (edge.Dest())).x;
		long l3 = ((Point) (edge.Dest())).y;
		long l4 = ((Point) (edge1.Dest())).x;
		long l5 = ((Point) (edge1.Dest())).y;
		long l6 = (l1 - l5) * (l - l2) - (l1 - l3) * (l - l4);
		long l7 = ((l2 + l) * (l - l2)) / 2L + ((l1 - l3) * (l3 + l1)) / 2L;
		long l8 = ((l + l4) * (l - l4)) / 2L + ((l1 - l5) * (l1 + l5)) / 2L;
		graphpoint.x = (int) ((l7 * (l1 - l5) - l8 * (l1 - l3)) / l6);
		graphpoint.y = (int) ((l8 * (l - l2) - l7 * (l - l4)) / l6);
		graphpoint.isInfinite = false;
		return graphpoint;
	}

	public void draw(Graphics g, boolean flag, boolean flag1) {
		if (flag) {
			for (int i = 0; i < size(); i += 4) {
				Edge edge = (Edge) elementAt(i);
				g.setColor(new Color(200,40,80));
				if (edge.isInfinite())
					if (edge.Lnext().isInfinite() && edge.Rnext().isInfinite()) {
						if (flag)
							g.drawLine(((Point) (edge.Org())).x, ((Point) (edge.Org())).y, ((Point) (edge.Dest())).x,
									((Point) (edge.Dest())).y);
					}
					else {
						g.drawLine(((Point) (edge.Org())).x, ((Point) (edge.Org())).y, ((Point) (edge.Dest())).x,
								((Point) (edge.Dest())).y);
					}
			}

		}
		if (flag1) {
			GetVoronoiDiagram();
			g.setColor(new Color(90,10,230));
			for (int j = 1; j < size(); j += 4) {
				Edge edge1 = (Edge) elementAt(j);
				if (edge1.isInfinite())
					g.drawLine(((Point) (edge1.Org())).x, ((Point) (edge1.Org())).y, ((Point) (edge1.Dest())).x,
							((Point) (edge1.Dest())).y);
			}

		}
	}
}
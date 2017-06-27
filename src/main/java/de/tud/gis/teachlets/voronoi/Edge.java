package de.tud.gis.teachlets.voronoi;
/**
 * 
 * @author danielkadner
 *
 */
public class Edge {
	GraphPoint org;
	Edge next;
	Edge dual;

	public Edge() {
		org = null;
		next = null;
		dual = null;
	}

	public void SetOrg(GraphPoint graphpoint) {
		org = graphpoint;
	}

	public void SetDest(GraphPoint graphpoint) {
		Sym().org = graphpoint;
	}

	public void SetRot(Edge edge) {
		dual = edge;
	}

	public void SetSym(Edge edge) {
		dual.dual = edge;
	}

	public void setInvRot(Edge edge) {
		dual.dual.dual = edge;
	}

	public void SetOnext(Edge edge) {
		next = edge;
	}

	public void SetLnext(Edge edge) {
		invRot().next.dual = edge;
	}

	public void SetOprev(Edge edge) {
		dual.next.dual = edge;
	}

	public void SetRnext(Edge edge) {
		dual.next.dual.dual.dual = edge;
	}

	public GraphPoint Org() {
		return org;
	}

	public GraphPoint Dest() {
		return Sym().org;
	}

	public Edge Rot() {
		return dual;
	}

	public Edge Sym() {
		return dual.dual;
	}

	public Edge invRot() {
		return dual.dual.dual;
	}

	public Edge Onext() {
		return next;
	}

	public Edge Lnext() {
		return invRot().next.dual;
	}

	public Edge Oprev() {
		return dual.next.dual;
	}

	public Edge Rnext() {
		return dual.next.invRot();
	}

	public Edge Dprev() {
		return invRot().next.invRot();
	}

	public Edge Lprev() {
		return next.Sym();
	}

	public boolean isInfinite() {
		return Org() != null && !Org().isInfinite && Dest() != null && !Dest().isInfinite;
	}
}
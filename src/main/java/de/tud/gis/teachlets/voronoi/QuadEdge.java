package de.tud.gis.teachlets.voronoi;
import java.util.Vector;

/**
 * 
 * @author danielkadner
 *
 */
@SuppressWarnings("rawtypes")
class QuadEdge extends Vector {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6126604399177904951L;

	QuadEdge() {
	}

	@SuppressWarnings("unchecked")
	public Edge MakeQuadEdge() {
		Edge aedge[] = new Edge[4];
		for (int i = 0; i < 4; i++)
			aedge[i] = new Edge();

		aedge[0].SetRot(aedge[1]);
		aedge[1].SetRot(aedge[2]);
		aedge[2].SetRot(aedge[3]);
		aedge[3].SetRot(aedge[0]);
		aedge[0].SetOnext(aedge[0]);
		aedge[1].SetOnext(aedge[3]);
		aedge[2].SetOnext(aedge[2]);
		aedge[3].SetOnext(aedge[1]);
		for (int j = 0; j < 4; j++)
			addElement(aedge[j]);

		return aedge[0];
	}

	public void Splice(Edge edge, Edge edge1) {
		Edge edge2 = edge.Onext().Rot();
		Edge edge3 = edge1.Onext().Rot();
		Edge edge4 = edge.Onext();
		edge.SetOnext(edge1.Onext());
		edge1.SetOnext(edge4);
		edge4 = edge2.Onext();
		edge2.SetOnext(edge3.Onext());
		edge3.SetOnext(edge4);
	}
}
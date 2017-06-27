package de.tud.gis.teachlets.quadtree;
import java.awt.Graphics;
import java.util.LinkedList;
import javax.swing.JPanel;

/*
 * hier wird der Graph gezeichnet
 */
public class QuadPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -58687245574559173L;
	LinkedList<QuadNode> ll = new LinkedList<QuadNode>();

	public QuadPanel(LinkedList<QuadNode> ll) {
		this.ll = ll;
		this.setBounds(5, 380, 1000, 350);
	}
	
	public QuadPanel(){}
	
	public void setLL(LinkedList<QuadNode> ll){
		this.setBounds(5, 380, 1000, 350);
		this.ll = ll;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < ll.size(); i++) {
			//Hier sind die ganzen Koordinaten gespeichert, wo ein Knoten sich befindet
			QuadNodeCoordinates qnc = HelpMethods.getCoordinates(i);
			
			/*
			 * sobald eine Anfrage ankommt, bei deren Elternteil schon gesagt wurde, dass es das letzte Element sei,
			 * wird das Zeichnen umgangen
			 */
			if (!ll.get(i).isDepricated()){
				/*
				 * Entscheidung ob das Element noch "schmutzig" ist, dh nicht nur noch aus wei�en bzw. schwarzen
				 * Pixeln besteht
				 */
				if (ll.get(i).isDirty()){
					//Wenn noch mal geteilt werden muss, dann zeichne gef�lltes Rechteck
					//beim fillRect +1 in der Ausdehnung, damit es genauso groß ist wie drawRect
					g.fillRect(qnc.getX(), qnc.getY(), 11, 11);
					//das +5 und +10 bewirkt, dass die linie mittig unterhalb des elternrechtecks endet
					g.drawLine(qnc.getX()+5, qnc.getY(), qnc.getToX()+5, qnc.getToY()+10);
				}
				else{
					//ansonsten zeichne ein "leeres" Rechteck
					g.drawRect(qnc.getX(), qnc.getY(), 10, 10);
					g.drawLine(qnc.getX()+5, qnc.getY(), qnc.getToX()+5, qnc.getToY()+10);
				}
			}
		}
	}
	
	void cleanPanel(){
//		this.setForeground(Color.WHITE);
//		gr.drawRect(5, 380, 1000, 350);
	}
}

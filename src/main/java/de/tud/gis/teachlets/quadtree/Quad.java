package de.tud.gis.teachlets.quadtree;
import java.util.LinkedList;

public class Quad {
	
	private CanvasPanel cp;
	private QuadPanel qp;
	private boolean[][] matrix;
	private LinkedList<QuadNode> finalLL;
	
	public Quad(CanvasPanel cp, QuadPanel qp){
		this.cp = cp;
		this.matrix = cp.getMatrix();
		this.qp = qp;
		calcQuad();
		newWindow();
	}
	
	/*
	 * wenn es nicht leer ist, dann teile es weiter
	 * 
	 * Struktur Quadelement: parent, children 4x, dirty, final
	 */
	
	/*
	 * berechnet den quad
	 */
	@SuppressWarnings("rawtypes")
	private void calcQuad(){
		finalLL = new LinkedList<QuadNode>();	
		LinkedList<QuadNode> level = new LinkedList<QuadNode>();
		LinkedList<QuadNode> levelnew = new LinkedList<QuadNode>();
		LinkedList<QuadNode> levelget = new LinkedList<QuadNode>();
		LinkedList<LinkedList> levelSeparated = new LinkedList<LinkedList>();
		
		//Wie oft geteilt werden muss maximal
		int times = (int) (Math.log( cp.getMatrix().length ) / Math.log( 2.0 )); 
		
		//ersten Knoten hinzu fügen
		finalLL.add( (countPoints(matrix) >= 1) ? new QuadNode(matrix, true, false, false) : new QuadNode(matrix, false, true, false) );
		level.add( (countPoints(matrix) >= 1) ? new QuadNode(matrix, true, false, false) : new QuadNode(matrix, false, true, false) );
		
		//so viele unterteilung gibt es max
		for (int i = 1; i<=times; i++){
			//LL aus diesem Level erzeugen (Level sind die einzelnen Ebenen)
			for (int l = 0; l < level.size(); l++){
				levelget = divide(level.get(l));
				for (int q = 0; q < levelget.size(); q++){
					levelnew.add(levelget.get(q));
				}
			}
			
			//LL aus diesem Level in die Finale rein hauen
			for (int q = 0; q < levelnew.size(); q++){
				finalLL.add(levelnew.get(q));
			}
			
			level = new LinkedList<QuadNode>();
			for (int s = 0; s < levelnew.size(); s++){
				level.add(levelnew.get(s));
			}
			
			levelSeparated.add(level);
			levelnew = new LinkedList<QuadNode>();		
		}
	}
	
	//vorbereiten zum teilen der matrix
	private LinkedList<QuadNode> divide(QuadNode qn){
		LinkedList<boolean[][]> quads;
		LinkedList<QuadNode> ll = new LinkedList<QuadNode>();
	
		//wenn ne null ankommt
		if (qn.isLast()){
			for ( int r = 0; r <= 3; r++){
				ll.add(new QuadNode(qn.getmatrix(), qn.isDirty(), qn.isLast(), true));
			}
		}else {
			//wenn bildinfos in dem Quadnode vorhanden sind, dann teile ihn wieder
			if (countPoints(qn.getmatrix()) >= 1 && !qn.isDepricated()){ 
				//wenn noch bildinfos vorhanden, dann muss nochmal geteilt werden
				quads = split(qn.getmatrix()); 
					//wenn noch mindestens eine bildinfo in der matrix von quad1 ist
					if (countPoints(quads.get(0)) >= 1) {
						//schau nach ob alle punkte in der matrix auf true gesetzt sind
						if (allPoints(quads.get(0))){
							// wenn ja, dann ist es der letzte (dirty = true, last = true -> QuadNode)
							ll.add(new QuadNode(quads.get(0), true, true, false));
						}else {
							// wenn nein, dann gibt es noch nachfolger (dirty = true, last = false)
							ll.add(new QuadNode(quads.get(0), true, false, false));
						}
					}
					//wenn keine Bildinfo mehr vorhanden ist
					else {
						// dann setze (dirty = false und last=true)
						ll.add(new QuadNode(quads.get(0), false, true, false));
					}
					
					if (countPoints(quads.get(1)) >= 1) {
						if (allPoints(quads.get(1))){
							ll.add(new QuadNode(quads.get(1), true, true, false));
						}
						else {
							ll.add(new QuadNode(quads.get(1), true, false, false));
						}
					}
					else {
						ll.add(new QuadNode(quads.get(1), false, true, false));
					}
										
					if (countPoints(quads.get(2)) >= 1) {
						if (allPoints(quads.get(2))){
							ll.add(new QuadNode(quads.get(2), true, true, false));
						}
						else {
							ll.add(new QuadNode(quads.get(2), true, false, false));
						}
					}
					else {
						ll.add(new QuadNode(quads.get(2), false, true, false));
					}
					
					
					if (countPoints(quads.get(3)) >= 1) {
						if (allPoints(quads.get(3))){
							ll.add(new QuadNode(quads.get(3), true, true, false));
						}
						else {
							ll.add(new QuadNode(quads.get(3), true, false, false));
						}
					}
					else {
						ll.add(new QuadNode(quads.get(3), false, true, false));
					}
			}
			//wenn keine vorhanden sind, dann bracuhste auch nicht teilen
			else {
				for ( int r = 0; r <= 3; r++){
//					ll.add(new QuadNode(null, null, null));
					ll.add(new QuadNode(qn.getmatrix(), false, true, false));
				}
			}
		}
		return ll;
	}

	//z�hlt punkte in matrix
	private int countPoints(boolean[][] matrix){
		int matrixHeight = matrix.length;
		int matrixLength = matrix[0].length;
		int numberofPoints = 0;
		for(int i=0;i<matrixHeight;i++){
			for(int k=0;k<matrixLength;k++){
				if(matrix[i][k]==true){
					numberofPoints++;
				}
			}
		}
		return numberofPoints;
	}
	
	//schaut ob alle punkte der matrix true sind
	private boolean allPoints(boolean[][] matrix){
		boolean all = true;
		for(int i=0;i<matrix.length;i++){
			for(int k=0;k<matrix.length;k++){
				if(matrix[i][k]==false){
					all = false;
				}
			}
		}
		return all;
	}
	
	//splittet eine matrix in 4 quad-matrizen
	private LinkedList<boolean[][]> split(boolean[][] matrix){
		int matrixHeight = matrix.length;

		int quadHeight = matrixHeight/2;
		boolean[][] quad1 = new boolean[quadHeight][quadHeight];
		boolean[][] quad2 = new boolean[quadHeight][quadHeight];
		boolean[][] quad3 = new boolean[quadHeight][quadHeight];
		boolean[][] quad4 = new boolean[quadHeight][quadHeight];
		LinkedList<boolean[][]> quads = new LinkedList<boolean[][]>();
		
		//Quads separieren
		for(int i=0;i<quadHeight;i++){
			for(int k=0;k<quadHeight;k++){
				//ersten Quad separieren (links oben)
				quad1[i][k] = matrix[i][k];
				//zweiten Quad separieren (rechts oben)
				quad2[i][k] = matrix[i][k+quadHeight];
				//dritten Quad separieren (rechts unten)
				quad3[i][k] = matrix[i+quadHeight][k+quadHeight];
				//vierten Quad separieren (links unten)
				quad4[i][k] = matrix[i+quadHeight][k];
			}
		}
		/*
		 * die matrix und Ihre Teilmatrizen
		 * ---------
		 * | 1 | 2 |
		 * ---------
		 * | 4 | 3 |
		 * ---------
		 */
		
		// in der Reihenfolge 1,2,3,4 hinzugef�gt
		quads.add(quad1);
		quads.add(quad2);
		quads.add(quad3);
		quads.add(quad4);
		return quads;
	}
	
	/**
	 * create Window to show the graph
	 */
	private void newWindow(){
		qp.setLL(finalLL);
		cp.setLL(finalLL);
	}//newWindow()
}

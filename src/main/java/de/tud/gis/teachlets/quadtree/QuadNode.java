package de.tud.gis.teachlets.quadtree;

/*
 * Der eigentliche Quadknoten
 * matrix = die matrix die der Knoten noch hat
 * dirty = ob die matrix nur noch aus den gleichen Werten besteht, oder ob noch "verschmutzungen" vorhanden sind
 * last = ob es das letzte element des baumes ist
 * depricated = wenn der elternteil last=true ist, dann muss man diese Elemente nicht mehr beachten/zeichnen
 */
public class QuadNode {
	
	private boolean dirty, last, depricated;
	private boolean[][] matrix;
	
	public QuadNode(boolean[][] matrix, boolean dirty, boolean last, boolean depricated){
		this.matrix = matrix;
		this.dirty = dirty;
		this.last = last;
		this.depricated = depricated;
	}
	
//		
//	public QuadNode(Object matrix2, Object object, Object object2) {
//		// TODO Auto-generated constructor stub
//	}


	public boolean isDirty() {
		return dirty;
	}

	public boolean[][] getmatrix() {
		return matrix;
	}
	
	public boolean isLast(){
		return last;
	}
	
	public void setLast(boolean last){
		this.last = last;
	}
	
	public boolean isDepricated(){
		return depricated;
	}
	
	

}

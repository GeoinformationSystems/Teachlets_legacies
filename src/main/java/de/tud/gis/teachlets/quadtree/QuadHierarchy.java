package de.tud.gis.teachlets.quadtree;

public class QuadHierarchy {
	boolean[][] quad1;
	boolean[][] quad2;
	boolean[][] quad3;
	boolean[][] quad4;
	
	public QuadHierarchy(boolean[][] quad1,boolean[][] quad2,boolean[][] quad3, boolean[][] quad4){
		this.quad1=quad1;
		this.quad2=quad2;
		this.quad3=quad3;
		this.quad4=quad4;
	}

	public boolean[][] getQuad1() {
		return quad1;
	}

	public void setQuad1(boolean[][] quad1) {
		this.quad1 = quad1;
	}

	public boolean[][] getQuad2() {
		return quad2;
	}

	public void setQuad2(boolean[][] quad2) {
		this.quad2 = quad2;
	}

	public boolean[][] getQuad3() {
		return quad3;
	}

	public void setQuad3(boolean[][] quad3) {
		this.quad3 = quad3;
	}

	public boolean[][] getQuad4() {
		return quad4;
	}

	public void setQuad4(boolean[][] quad4) {
		this.quad4 = quad4;
	}	
	
}

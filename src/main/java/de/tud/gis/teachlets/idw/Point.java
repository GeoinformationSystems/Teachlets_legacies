package de.tud.gis.teachlets.idw;

public class Point {
	
	private int x,y;
	private int val;
	
	public Point (int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setValue(int d){
		this.val = d;
	}
	
	public int getValue(){
		return val;
	}

}

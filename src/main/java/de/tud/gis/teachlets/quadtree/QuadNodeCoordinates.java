package de.tud.gis.teachlets.quadtree;

/*
 * ist mehr eine Hilfsklasse
 * gibt die Koordinaten f�r den Graphen an, wo ein bestimmter Knoten beginnt (x,y)
 *  und wo sein parent beginnt (toX, toY) zu dem die Linie gezeichnet werden muss
 */

public class QuadNodeCoordinates {
	
	int x,y, toX, toY;
	
	
	
	public QuadNodeCoordinates(int x, int y, int toX, int toY){
		this.x = x;
		this.y = y;
		this.toX = toX;
		this.toY = toY;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public int getToX(){
		return toX;
	}
	
	public int getToY(){
		return toY;
	}

}

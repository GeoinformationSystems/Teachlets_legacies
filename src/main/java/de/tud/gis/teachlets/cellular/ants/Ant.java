package de.tud.gis.teachlets.cellular.ants;

public class Ant {

	private int x,y;
	//north = 0
	//east = 1
	//south = 2
	//west = 3
	private int direction;
	private int max;
	
	public void setCoor(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setMax(int max){
		this.max = max-1;
	}
	
	public void setDirection(int i){
		this.direction = i;
	}
	
	public int getDirection(){
		return direction;
	}
	
	public void turnLeft(){
		if (direction == 0){
			direction = 3;
		}
		else{
			direction = direction - 1;
		}
		goForward();
	}
	
	public void turnRight(){
		direction = (direction + 1) % 4;
		goForward();
	}
	
	private void goForward(){
		switch (direction){
			case 0: {
				if (x!=0) x--;
				else x = max;
				break;
			}
			case 1: {
				if (y!=max) y++;
				else y = 0;
				break;
			}
			case 2: {
				if (x!=max) x++;
				else x = 0;
				break;
			}
			case 3: {
				if (y!=0) y--; 
				else y = max;
				break;
			}
			default: System.out.println("Fehler: "+direction);break;
		}
	}
	
}

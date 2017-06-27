package de.tud.gis.teachlets.cellular.forestfire;

import java.awt.Color;

public class Cell {

	private boolean street = false;
	private boolean ash = false;
	private boolean growntree = false;
	private boolean finishedtree = false;
	private boolean firefinished = false;
	private boolean fireburns = false;
	private boolean firestarted = false;
	
	public Cell(){
		this.ash = true;
	}
	
	public void setStreet(){
		this.street = true;
		this.ash = false;
		this.growntree = false;
		this.finishedtree = false;
		this.firefinished = false;
		this.fireburns = false;
		this.firestarted = false;
	}
	
	public void setAsh(){
		this.street = false;
		this.ash = true;
		this.growntree = false;
		this.finishedtree = false;
		this.firefinished = false;
		this.fireburns = false;
		this.firestarted = false;
	}
	
	public void setGrowingTree(){
			this.street = false;
			this.ash = false;
			this.growntree = true;
			this.finishedtree = false;
			this.firefinished = false;
			this.fireburns = false;
			this.firestarted = false;
	}
	
	public void setFinishedTree(){
		this.street = false;
		this.ash = false;
		this.growntree = false;
		this.finishedtree = true;
		this.firefinished = false;
		this.fireburns = false;
		this.firestarted = false;
	}
	
	public void setFinishedFire(){
		this.street = false;
		this.ash = false;
		this.growntree = false;
		this.finishedtree = false;
		this.firefinished = true;
		this.fireburns = false;
		this.firestarted = false;
	}
	
	public void setFireBurns(){
		this.street = false;
		this.ash = false;
		this.growntree = false;
		this.finishedtree = false;
		this.firefinished = false;
		this.fireburns = true;
		this.firestarted = false;
	}
	
	public void setFireStarted(){
		this.street = false;
		this.ash = false;
		this.growntree = false;
		this.finishedtree = false;
		this.firefinished = false;
		this.fireburns = false;
		this.firestarted = true;
	}
	
	public boolean isStreet(){
		return street;
	}
	
	public boolean isAsh(){
		return ash;
	}
	
	public boolean isGrownTree(){
		return growntree;
	}
	
	public boolean isFinishedTree(){
		return finishedtree;
	}
	
	public boolean isFireFinished(){
		return firefinished;
	}
	
	public boolean isFireBurns(){
		return fireburns;
	}
	
	public boolean isFireStarted(){
		return firestarted;
	}
	
	public boolean isOnFire(){
		if (isFireFinished() || isFireBurns() || isFireStarted())
			return true;
		else return false;
	}
	
	public Color getColor(){
		if (street)
			return new Color(179, 179, 179);
		if (ash)
			return new Color(31, 81, 9);
		if (growntree)
			return new Color(47, 130, 8);
		if (finishedtree)
			return new Color(60, 169, 6);
		if (firefinished)
			return new Color(252, 219, 29);
		if (fireburns)
			return new Color(246, 119, 31);
		if (firestarted)
			return new Color(244, 14, 31);
		return null;
	}
	
}

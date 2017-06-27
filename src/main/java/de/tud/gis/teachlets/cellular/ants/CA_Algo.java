package de.tud.gis.teachlets.cellular.ants;
import java.util.LinkedList;


/**
 * class representing the rlc algorithm 
 * @author danielkadner
 *
 */
public class CA_Algo {

	private CanvasPanel cp;
	private boolean [][] oldMatrix;
	private boolean [][] newMatrix;
	private LinkedList<Ant> antFarm;
	
	public CA_Algo(CanvasPanel cp){
		this.cp = cp;
		this.antFarm = cp.getAntFarm();
		calcNewLife();
		cp.setMatrix(newMatrix);
		cp.repaint();
	}
	
	public void calcNewLife(){
		this.oldMatrix = cp.getMatrix();
		newMatrix = new boolean[oldMatrix.length][oldMatrix[0].length];
		for (int i=0; i<oldMatrix.length; i++){
        	for (int j=0; j<oldMatrix[0].length; j++){
        		newMatrix[i][j] = oldMatrix[i][j];
        	}
		}
		
		for (int i = 0; i<antFarm.size(); i++){
			int x,y;
			x = antFarm.get(i).getY();
			y = antFarm.get(i).getX();
			if (oldMatrix[x][y]){
				newMatrix[x][y] = false;
				antFarm.get(i).turnRight();
			}
			else {
				newMatrix[x][y] = true;
				antFarm.get(i).turnLeft();
			}
		}
//		
//		int amountOfLife;
//		
//		 for (int i=0; i<oldMatrix.length; i++){
//	        	for (int j=0; j<oldMatrix[0].length; j++){
//	        		amountOfLife = amountOfLivingNeighbours(i, j);
//	        		if ( oldMatrix[i][j] &&  (amountOfLife ==2 || amountOfLife ==3)){
//	        			newMatrix[i][j] = true;
//	        			continue;
//	        		}
//	        		if ( !oldMatrix[i][j] && amountOfLife == 3){
//	        			newMatrix[i][j] = true;
//	        			continue;
//	        		}
//	        		newMatrix[i][j] = false;
//	        	}//for
//	        }//for
		
	}
	
//	public int amountOfLivingNeighbours(int i, int j){
//		int z = 0;
//		
//		//links oben
//		if (i>=1 && j>=1) if (oldMatrix[i-1][j-1]) 	z++;
//		//oben
//		if (i>=1) if (oldMatrix[i-1][j]) z++;
//		//rechts oben
//		if (i>=1 && j<oldMatrix[0].length-1) if (oldMatrix[i-1][j+1]) z++;
//		//rechts
//		if (j<oldMatrix[0].length-1) if(oldMatrix[i][j+1]) z++;
//		//rechts unten
//		if (i<oldMatrix.length-1 && j<oldMatrix[0].length-1) if(oldMatrix[i+1][j+1]) z++;
//		//unten
//		if (i<oldMatrix.length-1) if(oldMatrix[i+1][j]) z++;
//		//links unten
//		if (i<oldMatrix.length-1 && j>=1) if(oldMatrix[i+1][j-1]) z++;
//		//links
//		if (j>=1) if (oldMatrix[i][j-1]) z++;
//		
//		return z;
//		
//	}
	
}//class RLC

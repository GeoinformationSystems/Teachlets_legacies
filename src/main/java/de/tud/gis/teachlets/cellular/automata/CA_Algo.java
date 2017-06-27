package de.tud.gis.teachlets.cellular.automata;

/**
 * class representing the rlc algorithm 
 * @author danielkadner
 *
 */
public class CA_Algo {

	private CanvasPanel cp;
	private boolean [][] oldMatrix;
	private boolean [][] newMatrix;
	
	public CA_Algo(CanvasPanel cp){
		this.cp = cp;
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
		
		int amountOfLife;
		
		 for (int i=0; i<oldMatrix.length; i++){
	        	for (int j=0; j<oldMatrix[0].length; j++){
	        		amountOfLife = amountOfLivingNeighbours(i, j);
	        		if ( oldMatrix[i][j] &&  (amountOfLife ==2 || amountOfLife ==3)){
	        			newMatrix[i][j] = true;
	        			continue;
	        		}
	        		if ( !oldMatrix[i][j] && amountOfLife == 3){
	        			newMatrix[i][j] = true;
	        			continue;
	        		}
	        		newMatrix[i][j] = false;
	        	}//for
	        }//for
		
	}
	
	public int amountOfLivingNeighbours(int i, int j){
		int z = 0;
		
		//links oben
		if (i>=1 && j>=1) if (oldMatrix[i-1][j-1]) 	z++;
		//oben
		if (i>=1) if (oldMatrix[i-1][j]) z++;
		//rechts oben
		if (i>=1 && j<oldMatrix[0].length-1) if (oldMatrix[i-1][j+1]) z++;
		//rechts
		if (j<oldMatrix[0].length-1) if(oldMatrix[i][j+1]) z++;
		//rechts unten
		if (i<oldMatrix.length-1 && j<oldMatrix[0].length-1) if(oldMatrix[i+1][j+1]) z++;
		//unten
		if (i<oldMatrix.length-1) if(oldMatrix[i+1][j]) z++;
		//links unten
		if (i<oldMatrix.length-1 && j>=1) if(oldMatrix[i+1][j-1]) z++;
		//links
		if (j>=1) if (oldMatrix[i][j-1]) z++;
		
		return z;
		
	}
	
}//class RLC

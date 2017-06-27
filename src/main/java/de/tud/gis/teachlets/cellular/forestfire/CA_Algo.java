package de.tud.gis.teachlets.cellular.forestfire;

/**
 * class representing the rlc algorithm
 * 
 * @author danielkadner
 *
 */
public class CA_Algo {

	private CanvasPanel	cp;
	private Cell[][]	oldMatrix;
	private Cell[][]	newMatrix;
	private double		alpha		= 0.01;
	private double		beta		= 0.3;
	private double		gamma		= 0.000005;
	private int			neighbours	= 4;		 //9

	public CA_Algo(CanvasPanel cp) {
		this.cp = cp;
		calcNewLife();
		cp.setMatrix(newMatrix);
		cp.repaint();
	}

	public void calcNewLife() {
		this.oldMatrix = cp.getMatrix();
		newMatrix = new Cell[oldMatrix.length][oldMatrix[0].length];
		for (int i = 0; i < oldMatrix.length; i++) {
			for (int j = 0; j < oldMatrix[0].length; j++) {
				newMatrix[i][j] = oldMatrix[i][j];
			}
		}

		for (int i = 0; i < oldMatrix.length; i++) {
			for (int j = 0; j < oldMatrix[0].length; j++) {
				if (oldMatrix[i][j].isStreet()) {
					newMatrix[i][j].setStreet();
					continue;
				}
				if (oldMatrix[i][j].isAsh()) {
					if (Math.random() < alpha) {
						newMatrix[i][j].setGrowingTree();
						continue;
					}
				}
				if (oldMatrix[i][j].isGrownTree()) {
					if (Math.random() < alpha) {
						newMatrix[i][j].setFinishedTree();
						continue;
					} else {
						if ((isFireAround(i, j) && Math.random() < beta) || Math.random() < gamma) {
							newMatrix[i][j].setFireStarted();
							continue;
						} else {
							newMatrix[i][j].setGrowingTree();
							continue;
						}
					}
				}
				if (oldMatrix[i][j].isFinishedTree()) {
					if ((isFireAround(i, j) && Math.random() < beta) || Math.random() < gamma) {
						newMatrix[i][j].setFireStarted();
						continue;
					} else {
						newMatrix[i][j].setFinishedTree();
						continue;
					}
				}
				if (oldMatrix[i][j].isFireFinished()) {
					newMatrix[i][j].setAsh();
					continue;
				}
				if (oldMatrix[i][j].isFireBurns()) {
					newMatrix[i][j].setFinishedFire();
					continue;
				}
				if (oldMatrix[i][j].isFireStarted()) {
					newMatrix[i][j].setFireBurns();;
					continue;
				}
				

			}//for
		}//for	
	}

	public boolean isFireAround(int i, int j) {

		if (neighbours == 9) {
			//links oben
			if (i >= 1 && j >= 1)
				if (oldMatrix[i - 1][j - 1].isOnFire())
					return true;
			//rechts oben
			if (i >= 1 && j < oldMatrix[0].length - 1)
				if (oldMatrix[i - 1][j + 1].isOnFire())
					return true;
			//rechts unten
			if (i < oldMatrix.length - 1 && j < oldMatrix[0].length - 1)
				if (oldMatrix[i + 1][j + 1].isOnFire())
					return true;
			//links unten
			if (i < oldMatrix.length - 1 && j >= 1)
				if (oldMatrix[i + 1][j - 1].isOnFire())
					return true;
		}

		//oben
		if (i >= 1)
			if (oldMatrix[i - 1][j].isOnFire())
				return true;

		//rechts
		if (j < oldMatrix[0].length - 1)
			if (oldMatrix[i][j + 1].isOnFire())
				return true;

		//unten
		if (i < oldMatrix.length - 1)
			if (oldMatrix[i + 1][j].isOnFire())
				return true;

		//links
		if (j >= 1)
			if (oldMatrix[i][j - 1].isOnFire())
				return true;

		return false;

	}
}//class RLC

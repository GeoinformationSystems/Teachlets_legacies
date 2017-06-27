package de.tud.gis.teachlets.cellular.ants;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 * class representing the painting panel
 * -ist das feld weiß, färbe schwarz und drehe 90 nach rechts
 * -ist das feld schwarz, färbe weiß und drehe 90 nach links
 * -und laufe weiter ein feld in richtung blickrichtung
 * @author danielkadner
 *
 */
public class CanvasPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 643772305126131551L;
	private Start_App_CA gui;
	private int tempX, tempY, amountBit;
	private boolean [][] matrix;
	private LinkedList<Ant> antFarm;
	
	public CanvasPanel(Start_App_CA gui){
		this.gui = gui;
		createJPanel();
		antFarm = new LinkedList<Ant>();
	}//CanvasPanel(RLC_Gui gui)
	
	/**
	 * calculate bounds for the rect to be painted while mouse move
	 */
	private void calcPaintRec(MouseEvent e){
    	tempX = Math.abs(e.getX()/gui.getMatrixwidth());
    	tempY = Math.abs(e.getY()/gui.getMatrixwidth());
    	if (matrix[tempY][tempX] == false) {
			matrix[tempY][tempX] = true;
			//add Ant
			Random r = new Random();
			Ant ant = new Ant();
			ant.setCoor(tempX, tempY);
			ant.setDirection(r.nextInt(4));
			ant.setMax(matrix.length);
			antFarm.add(ant);
		}// if
		else {
			matrix[tempY][tempX] = false;
			for (int i=0; i<antFarm.size(); i++ ){
				if (antFarm.get(i).getX() == tempX && antFarm.get(i).getY() == tempY){
					antFarm.remove(i);
				}
			}
		}
    	System.out.print("in der antFarm : "+antFarm.size());
    	for (Ant a: antFarm){
    		System.out.print(" / "+a.getX()+ " "+a.getY());
    	}
    	System.out.println();
	}//calcPaintRec(MouseEvent e)
	
	/**
	 * update dimension of a pixel (->update matrix)
	 */
	 public void updateFH(int matrixwidth){
	    	matrix = new boolean[Math.abs(this.getHeight()/matrixwidth)][Math.abs(this.getWidth()/matrixwidth)];
	        for (int i=0; i<matrix.length; i++){
	        	for (int j=0; j<matrix[0].length; j++){
	        		matrix[i][j]=false;
	        	}//for
	        }//for
	 }//updateFH(int matrixwidth)
	 
	 /**
	  * clear the canvas
	  */
	 public void cleanPanel(){
		 for (int i = 0; i < matrix.length; i++ ){
			 for (int j = 0; j < matrix[0].length; j++ ){
					 matrix[i][j] = false;
			 }//for
		 }//for
		 antFarm = new LinkedList<Ant>();
		 repaint();
	 }//cleanPanel()
	 
	 /**
	  * return the JPanel
	  */
	 public JPanel getPanel(){
		 return this;
	 }//getPanel()
	 
	 /**
	  * return the matrix behind the canvas
	  */
	 public boolean [][] getMatrix(){
		 return matrix;
	 }//getMatrix()
	 
	 public void setMatrix(boolean[][] matrix){
		 this.matrix = matrix;
	 }
	 
	 /**
	  * return the amount of Bits without compression 
	  */
	 public int getAmountBit(){
		 return amountBit;
	 }
	 
	 public LinkedList<Ant> getAntFarm(){
		 return antFarm;
	 }
	 
	 /**
	  * create panel to paint on
	  */
	 private void createJPanel(){
			this.setBackground(new Color(232, 232, 232));
			this.setBounds(5, 5, 400, 400);
			this.setOpaque(true);
			Border empty = BorderFactory.createEmptyBorder();
			this.setBorder(new TitledBorder(empty, "Zeichenfläche", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
			this.addMouseListener( new CanvasMouseListener(this));
			this.setVisible(true);
			
			matrix = new boolean[Math.abs(this.getHeight()/gui.getMatrixwidth())][Math.abs(this.getWidth()/gui.getMatrixwidth())];
	        
			for (int i=0; i<matrix.length; i++){
	        	for (int j=0; j<matrix[0].length; j++){
	        		matrix[i][j]=false;
	        	}//for
	        }//for
			gui.getFrame().add(this);
	 }//createJPanel()
	 
	 public void paintComponent(Graphics g){
		 super.paintComponent(g);
		 
		 for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix[0].length; j++) {

					if (matrix[i][j] == false) {
						g.setColor(new Color(232, 232, 232));
						g.fillRect(j * gui.getMatrixwidth(), i * gui.getMatrixwidth(), gui.getMatrixwidth(), gui
								.getMatrixwidth());
					}
					if (matrix[i][j] == true) {
						g.setColor(Color.BLACK);
						g.fillRect(j * gui.getMatrixwidth(), i * gui.getMatrixwidth(), gui.getMatrixwidth(), gui
								.getMatrixwidth());
					}
				}// for
			}// for
		 
		 if (gui.getRaster()){
			 g.setColor(Color.BLACK);
			 for (int i=0; i<this.getHeight(); i=i+gui.getMatrixwidth()){
				 for (int j=0; j<this.getWidth(); j = j+gui.getMatrixwidth()){
					g.drawLine(0, i, this.getWidth(), i);
					g.drawLine(j, 0, j, this.getHeight()); 
				 }
			 }
		 }
	 }
	
	/**
	 * MouseListener for the Canvas 
	 */
	class CanvasMouseListener implements MouseListener{
		
		CanvasPanel cp;
	
		CanvasMouseListener(CanvasPanel cp){
			this.cp = cp;
		}
		
		public void mouseClicked(MouseEvent e) {}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {
	    	calcPaintRec(e);
	    	cp.repaint();
		}
		public void mouseReleased(MouseEvent e) {}
	}//class CanvasMouseListsner
}//class CanvasPanel

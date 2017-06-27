package de.tud.gis.teachlets.cellular.automata;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

/**
 * class representing the painting panel
 * jede zelle kann entweder lebendig oder tot sein
 * - zelle bleibt am leben, wenn in der Umgebung 2 oder 3 lebendige Zellen sind
 * - zelle wird lebendig wenn genau 3 in Umgebung lebendig sind
 * - in allen anderen Fällen stirbt die Zelle oder bleibt tot
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
	private boolean clickedOne;
	
	public CanvasPanel(Start_App_CA gui){
		this.gui = gui;
		createJPanel();
	}//CanvasPanel(RLC_Gui gui)
	
	/**
	 * calculate bounds for the rect to be painted while mouse move
	 */
	private void calcPaintRec(MouseEvent e){
    	tempX = Math.abs(e.getX()/gui.getMatrixwidth());
    	tempY = Math.abs(e.getY()/gui.getMatrixwidth());
    	if (matrix[tempY][tempX] == false) {
			matrix[tempY][tempX] = true;
			clickedOne = true;
		}// if
		else {
			matrix[tempY][tempX] = false;
			clickedOne = false;
		}
	}//calcPaintRec(MouseEvent e)
	
	/**
	 * calculate bounds for the rect to be painted while mouse move
	 */
	private void calcPaintRecDrag(MouseEvent e) {
		tempX = Math.abs(e.getX() / gui.getMatrixwidth());
		tempY = Math.abs(e.getY() / gui.getMatrixwidth());
		if (clickedOne == false) {
			matrix[tempY][tempX] = false;
		}// if
		else {
			matrix[tempY][tempX] = true;
		}
	}// calcPaintRec(MouseEvent e)
	
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
			this.addMouseMotionListener(new CanvasMouseMotionListener(this));
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
	
	/**
	 * MouseMotionListener for the Canvas 
	 */
	class CanvasMouseMotionListener implements MouseMotionListener{
		
		CanvasPanel cp;
		
		CanvasMouseMotionListener(CanvasPanel cp){
			this.cp = cp;
		}
	
		public void mouseDragged(MouseEvent e) {
			if (e.getX() < cp.getWidth() && e.getY() < cp.getHeight()){
	        	calcPaintRecDrag(e);
	        	cp.repaint();
	    	}//if
		}
		public void mouseMoved(MouseEvent e) {}
		
	}//class CanvasMouseMotionListener
}//class CanvasPanel

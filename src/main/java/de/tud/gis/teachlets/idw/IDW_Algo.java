package de.tud.gis.teachlets.idw;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;


public class IDW_Algo {

	private Start_App_IDW idw;
	private IDWCanvas ic;
	private int n;
	private int[][] matrix;
	private double[][] dmatrix;
	private ArrayList<Point> pl;
	@SuppressWarnings("unused")
	private LinkedList<Object[]> helpL;
	private int matrixwidth;
	private Map<Double, Point> finmap;
	private double conversion, min, max;
	ArrayList<Double> allDoubles;

	public IDW_Algo(IDWCanvas ic, Start_App_IDW idw, int amount) {
		this.ic = ic;
		this.idw = idw;
		this.n = amount;
		this.matrixwidth = idw.getMatrixwidth();
		helpL = null;
		matrix = null;
		pl = ic.getPointList();
		runAlgo();
	}

	private void runAlgo() {
		matrix = new int[idw.getCanvasPanel().getHeight() / matrixwidth][idw.getCanvasPanel().getWidth() / matrixwidth];
//		System.out.println("Breite Matrix "+matrix[0].length);
//		System.out.println("Höhe Matrix "+matrix.length);
		dmatrix = new double[idw.getCanvasPanel().getHeight() / matrixwidth][idw.getCanvasPanel().getWidth() / matrixwidth];
//		System.out.println("Breite DMatrix "+dmatrix[0].length);
//		System.out.println("Höhe DMatrix "+dmatrix.length);
		fillDmatrix();
		calcConversion();
		convertDoubleToIntMatrix();
		ic.setMatrix(matrix);
		ic.setMinMax(min, max);
		ic.repaint();
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				System.out.print(matrix[i][j]+" ");
			}
			System.out.println();
		}
		
	}
	
	private void fillDmatrix(){
		allDoubles = new ArrayList<Double>();
		for (int i = 0; i < dmatrix.length; i++) {
			for (int j = 0; j < dmatrix[0].length; j++) {
				 dmatrix[i][j] = calcDistance(new Point(j,i));
				 allDoubles.add(dmatrix[i][j]);
//				 System.out.println("Pos dmatrix "+i+ " " +j);
					
			}
		}
		
		for (int i = 0; i < dmatrix.length; i++) {
			for (int j = 0; j < dmatrix[0].length; j++) {
				 System.out.print((Double.valueOf(dmatrix[i][j])).intValue()+" ");
			} 
			System.out.println();
		}
	}
	
	private void calcConversion(){
		min = allDoubles.get(0);
		max = allDoubles.get(0);
		
		for(Double d : allDoubles){
			if (d < min) min = d;
			if (d > max) max = d;
		}
		
		System.out.println("min "+min + "  max "+max);
		
		conversion = 250 / Math.abs(max-min);
		System.out.println("Conversion "+conversion);
	}
	
	private void convertDoubleToIntMatrix(){
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				 matrix[i][j] = (int)((dmatrix[i][j] - min) * conversion);
//				 System.out.println("Pos dmatrix "+i+ " " +j);

			}
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private double calcDistance(Point p) {
		finmap = new TreeMap();
		System.out.println("Abstand von Punkt p "+p.getX()+ " "+p.getY());

		SortedMap<Double, Point> map = new TreeMap();
		
		for (Point point : pl) {
			System.out.println("     zu Punkt "+point.getX()/matrixwidth+ " " +point.getY()/matrixwidth + " = "+ distanceBetween(p, new Point(point.getX()/matrixwidth, point.getY()/matrixwidth)));
			if (map.containsKey(distanceBetween(p,new Point(point.getX()/matrixwidth, point.getY()/matrixwidth)))){
				map.put(distanceBetween(p, new Point(point.getX()/matrixwidth, point.getY()/matrixwidth))+0.00000000001, point);
			}
			else map.put(distanceBetween(p, new Point(point.getX()/matrixwidth, point.getY()/matrixwidth)), point);
		}

		int l = 1;
		for (Iterator iter = map.keySet().iterator(); iter.hasNext();) {
			Double key = (Double) iter.next();
			if (l <= n) {
				finmap.put(key, map.get(key));
				System.out.println(map.get(key) + " length "+ key +" value "+map.get(key).getValue() +" added") ;
				
			}
			l++;
//			System.out.println("Value/key:" + map.get(key) + "/" + key);
		}

		// Wert = Summe ( 1/Abstand * Wert) / Summe 1/Abstand

		double value = 0;

		double oben = 0, unten = 0;
		
		double key = 0;
//		Iterator it;
		boolean canceled = false;
		
		System.out.println("Im Finmap "+finmap.size());
		for (Iterator it = finmap.keySet().iterator(); it.hasNext();) {
			key = (Double) it.next();
			if (key == 0.0) {
				canceled = true;
				break;
			}
//			System.out.println("KEY "+key);
			oben += finmap.get(key).getValue() / key;
			unten += 1 / key;
		}
		
		System.out.println();

		if (canceled) value = finmap.get(key).getValue();
		else value = oben / unten;

//		System.out.println("value "+value);

		return value;
	}

	private double distanceBetween(Point p, Point q) {
		return Math.sqrt(Math.pow(q.getX() - p.getX(), 2) + Math.pow(q.getY() - p.getY(), 2));
	}

}

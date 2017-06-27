package de.tud.gis.teachlets.runlengthcoding;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
/**
 * class for reading example files
 * @author danielkadner
 *
 */
public class ReadData {

	private BufferedReader br, br2;
	private int countLines;
	private Start_App_RLC gui;
	private CanvasPanel cp;
	private boolean [][] matrix;
	private String Beispiel1 =  "dimension(50)   |\n"+
								"xxxx    \n"+
								"xxxx    \n"+
								"xxxx    \n"+
								"xxxx    \n"+
								"        \n"+
								"        \n"+
								"        \n"+
								"        ";
	
	private String Beispiel2 =  "dimension(50)   |\n"+
								"xxxx    \n"+
								"xxx xx x\n"+
								"   x   x\n"+
								"xxx   xx\n"+
								" xx   xx\n"+
								"   xx   \n"+
								"  xxx   \n"+
								" xxxxxx ";
	
	private String Beispiel3 =  "dimension(4)   |\n"+
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                         xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx                         \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    \n" +
	"                                                                                                    ";
		

	/**
	 * @param string name of example file
	 * @param cp CanvasPanel
	 */
	public ReadData(String string, CanvasPanel cp, Start_App_RLC gui) {
		this.gui = gui;
		this.cp = cp;
		readfile(string);
		interpretFile();
		
	}

	/**
	 * initial readfile
	 * @param string
	 */
	public void readfile(String string) {
		
		if (string.equals("Beispiel1")){
			string = Beispiel1;
		}
		else if(string.equals("Beispiel2")){
			string = Beispiel2;
		}
		else if(string.equals("Beispiel3")){
			string = Beispiel3;
		}
		br = new BufferedReader(new StringReader(string));
		br2 = new BufferedReader(new StringReader(string));
		
	}//readfile
	
	/**
	 * method to interpret the file
	 */
	public void interpretFile(){
		countLines = 0;
		//anzahl Zeilen in Datei
		try {
			for(@SuppressWarnings("unused")
			int j=0; br2.readLine()!=null; j++){	
				countLines++;
			}//for
			br2.close();
			
			readOutMatrix(readOutHeader());
			cp.setMatrix(matrix);
			cp.repaint();

		} catch (IOException e) {
			e.printStackTrace();		
		}//try-catch
	}//interpretFile()
	
	/**
	 * Read out the dimension (header) of the datafile 
	 * @return
	 */
	public int readOutHeader(){
		String helpString;
		String matrixwidth = "";
		boolean found = false;
		int dimension = -1;
		
		//read out the string between "(" and ")"
		try {
			helpString = br.readLine();
			for (int i = 0; i<helpString.length();i++){
				if (found){
					if (helpString.charAt(i) == ')'){
						found = false;
					}//if
					else {
						matrixwidth += helpString.charAt(i);
					}//else
				}//if
				else{
					if (helpString.charAt(i) == '(') {
						found = true;
					}//if
				}//else
			}//for
		}
		catch (IOException e) {
			System.err.println("Reading of header failed");
		}//try-catch
		
		//parse string to integer
		try {
			dimension = Integer.parseInt (matrixwidth);
		} 
		catch (Exception E){

		}//try-catch
		gui.setMatrixwidth(dimension);
		return dimension;
	}//readOutHeader()
	
	
	/**
	 * generate matrix out of the file
	 * @param dimension
	 */
	public void readOutMatrix(int dimension){
		
		matrix = new boolean[Math.abs(cp.getPanel().getHeight()/dimension)][Math.abs(cp.getPanel().getWidth()/dimension)];
		String helpString = "";

		for (int i=0 ; i < countLines-1; i++){
			try {
				helpString = br.readLine();

				for (int j = 0; j<cp.getPanel().getWidth()/dimension; j++){
//					System.out.print(helpString.charAt(j));
					if (helpString.charAt(j) == 'x') {
						matrix[i][j] = true;
					}//if
					else {
						matrix[i][j] = false;
					}//else
				}//for
//				System.out.println();
			} catch (IOException e) {
				e.printStackTrace();
			}//try-catch
		}//for
	}//readOutMatrix(int dimension)
}//class ReadData

package de.tud.gis.teachlets.runlengthcoding;
import java.text.DecimalFormat;
import java.util.LinkedList;


/**
 * class representing the rlc algorithm 
 * @author danielkadner
 *
 */
public class RLC_Algo {

	private CanvasPanel cp;
	private Start_App_RLC gui;
	private int colorDepth;
	private DecimalFormat df = new DecimalFormat("0.00");

	
	public RLC_Algo(CanvasPanel cp, Start_App_RLC gui){
		this.cp = cp;
		this.gui = gui;
		calcConstantRLC();
		calcVariableRLC();
	}
	
	private void calcConstantRLC(){
		
		boolean falsetrue = false;
		boolean[][] matrix = cp.getMatrix();
		boolean current;
		int count = 1;
		int diskspace = 0;
		int eightBitDiskspace = 0;
		@SuppressWarnings("unused")
		String bitString = "";
		LinkedList <Integer> results = new LinkedList<Integer>();
		LinkedList<String> bitResult = new LinkedList<String>();
		colorDepth = cp.getColorDepth();
				
		// count the false and trues starting with false
		for (int i=0; i<matrix.length; i++){
			falsetrue = matrix[i][0];
        	for (int j=1; j<matrix[0].length; j++){
        		current = matrix[i][j];
        		if (current == falsetrue) count ++;
        		else {
        			results.add(count);
        			falsetrue = !falsetrue;
        			count = 1;
        		}
        	}//for
        	results.add(count);
			count = 1;
        }//for
		
		//find biggest value
		int max = HelpMethods.bubble(results).getLast();
		
		//find out the needed amount of bits
		int bit = HelpMethods.amountBit(max);
		
		//convert intStream (results) in BitStream (bitResult)		
		for ( int u = 0; u < results.size(); u++){
			bitResult.add(HelpMethods.IntToBit(results.get(u), bit));
		}//for
		
		for ( int u = 0; u < results.size(); u++){
			bitString += bitResult.get(u);
			diskspace += bit+colorDepth;
			eightBitDiskspace += (8+colorDepth);
		}//for
		
		//output
		String rlcOutput = "";
		
		rlcOutput += "Bei einer Bildgröße von ("+matrix.length +"x"+matrix[0].length+") sind insgesamt "+results.size()+" Zeichenfolgen vorhanden";
		rlcOutput += "\n\n";
		rlcOutput += "minimale feste Anzahl bit / Anzahl der Zeichen: "+ bit;
		rlcOutput += "\n\n";
		rlcOutput += "benötigter Speicherplatz bei "+bit+" bit / Anzahl der Zeichen und "+colorDepth+" bit Farbtiefe:";
		rlcOutput += "\n";
		rlcOutput += bitResult.size()+"*("+bit+"+"+colorDepth+") =";
		rlcOutput += "\t "+diskspace+" bit\n";
		rlcOutput += "= \t"+df.format((double)diskspace/8)+" Byte\n";
		rlcOutput += "= \t"+df.format((double)(diskspace/8)/1024)+" KB";
		rlcOutput += "\n\n";
		
		if (bit%8!=0){
			rlcOutput += "------------------------------------------\n\n";
			rlcOutput += "Worst Case: 1 Byte (=8bit) für Codierung der Anzahl der Zeichen";
			rlcOutput += "\n\n";
			rlcOutput += "benötigter Speicherplatz bei 8 bit / Anzahl der Zeichen und "+colorDepth+" bit Farbtiefe:";
			rlcOutput += "\n";
			rlcOutput += bitResult.size()+"*(8+"+colorDepth+") =";
			rlcOutput += "\t "+eightBitDiskspace+" bit\n";
			rlcOutput += "= \t"+df.format((double)eightBitDiskspace/8)+" Byte\n";
			rlcOutput += "= \t"+df.format((double)(eightBitDiskspace/8)/1024)+" KB\n";
		}
		gui.getRlcPane().setText(rlcOutput);
	}//calcConstantRLC()
	
	public void calcVariableRLC(){
		boolean falsetrue = false;
		boolean[][] matrix = cp.getMatrix();
		boolean current;
		int count = 0;
		LinkedList <Integer> results = new LinkedList<Integer>();
		LinkedList <String> bitResult = new LinkedList<String>();
				
		// count the false and trues starting with false and write it LinkedList
		for (int i=0; i<matrix.length; i++){
        	for (int j=0; j<matrix[0].length; j++){
        		        		
           		current = matrix[i][j];
           		
           		if(j == 0){
        			falsetrue = current;
        			count = 0;
        		}
           		
           		if (current == falsetrue) {
        			count ++;
        		}//if
        		else {
        			results.add(count);
        			falsetrue = !falsetrue;
        			count = 1;
        		}//else
        	}//for
        	results.add(count);
        }//for
		
		//convert intStream (results) in BitStream (bitResult)
		for ( int u = 0; u < results.size(); u++){
			bitResult.add(HelpMethods.IntToBit(results.get(u), HelpMethods.amountBit(results.get(u))));
		}
		
		//convert in String
		@SuppressWarnings("unused")
		String bitString = "";
		
		for ( int u = 0; u < results.size(); u++){
			bitString += bitResult.get(u);
		}//for
		
		//output
		String rlcOutput = "";
		
		rlcOutput += "Bei einer Bildgröße von ("+matrix.length +"x"+matrix[0].length+") sind insgesamt "+results.size()+" Zeichenfolgen vorhanden.";
		rlcOutput += "\n\n";
		rlcOutput += "benötigter Speicherplatz bei variabler Anzahl bit / Anzahl der Zeichen und "+colorDepth+" bit Farbtiefe:";
		rlcOutput += "\n\n";
		rlcOutput += "[nach dem Schema (minimale Anzahl bit für Anzahl der Zeichen + Farbtiefe)]\n\n";
		
		int diskspace = 0;
		
		for (int z = 0 ; z < results.size(); z++){
//			System.out.println(results.get(z) + " >>  " + HelpMethods.amountBit(results.get(z)));
			rlcOutput += "("+HelpMethods.amountBit(results.get(z))+"+"+colorDepth+")+";
			diskspace += HelpMethods.amountBit(results.get(z))+colorDepth;
		}//for
		
//		delete last plus-sign
		rlcOutput = rlcOutput.substring(0, rlcOutput.length()-1);
		rlcOutput += "\n\n";

		rlcOutput += "= \t"+diskspace+" bit\n";
		rlcOutput += "= \t"+df.format((double)diskspace/8)+" Byte\n";
		rlcOutput += "= \t"+df.format((double)(diskspace/8)/1024)+" KB\n";
		rlcOutput += "\n\n";
		
		gui.getrlcVPane().setText(rlcOutput);	
	}//calcVariableRLC()
}//class RLC

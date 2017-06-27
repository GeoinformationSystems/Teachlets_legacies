package de.tud.gis.teachlets.runlengthcoding;
import java.util.LinkedList;

public class HelpMethods {

	/**
	 * convert Integer to Bit (as String)
	 * 
	 * @param value
	 * @return
	 */
	public static String IntToBit(int value, int bit) {
		int displayMask = 1 << bit - 1;
		StringBuffer buf = new StringBuffer(100);

		for (int c = 1; c <= bit; c++) {
			buf.append((value & displayMask) == 0 ? '0' : '1');
			value <<= 1;

			if (c % 8 == 0)
				buf.append(' ');
		}

		return buf.toString();
	}

	/**
	 * Bubblesort algorithm
	 * 
	 * @param x
	 * @return sorted LinkedList
	 */
	@SuppressWarnings("unchecked")
	public static LinkedList<Integer> bubble(LinkedList<Integer> get) {
		LinkedList<Integer> x = (LinkedList<Integer>) get.clone();
		int length = x.size();
		boolean changed = true;
		while (changed) {
			int count = 0;
			for (int j = 0; j <= length - 1; j++, length--)
				for (int i = 0; i < length - 1; i++) {
					if (x.get(i) > x.get(i + 1)) {
						int mid = x.get(i);
						x.set(i, x.get(i + 1));
						x.set(i + 1, mid);
						count++;
					}
				}
			if (count == 0) {
				changed = false;
			}
		}
		return x;
	}
	
	//returns the amount of bits for coding the given value (maximum amount is 10)
	public static int amountBit(int max){
		
		for (int i = 1; i <= 10; i++){
			if (max < Math.pow(2, i)) return i;
		};
		return 0;
	}
}

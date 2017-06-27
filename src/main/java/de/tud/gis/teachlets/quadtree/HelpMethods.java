package de.tud.gis.teachlets.quadtree;
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
	
	public static int amountBit(int max){
		int bit = 0;
		
		//1bit
		if (0 <= max && max < 1) bit = 1;
		//2bit
		if (2 <= max && max < 3) bit = 2;
		//3bit
		if (4 <= max && max < 7) bit = 3;
		//4bit
		if (8 <= max && max < 15) bit = 4;
		//5bit
		if (16 <= max && max < 31) bit = 5;
		//6bit
		if (32 <= max && max < 63) bit = 6;
		//7bit
		if (64 <= max && max < 127) bit = 7;
		//8bit
		if (128 <= max && max < 255) bit = 8;
		//9bit
		if (256 <= max && max < 511) bit = 9;
		//10bit
		if (512 <= max && max < 1023) bit = 10;
		//11bit
		if (1024 <= max && max < 2047) bit = 11;
		//12bit
		if (2048 <= max && max < 4095) bit = 12;
		//13bit
		if (4096 <= max && max < 8191) bit = 13;
		
		return bit;
	}
	
	
	public static QuadNodeCoordinates getCoordinates(int position) {

		QuadNodeCoordinates qnc;

		switch (position) {
		//erste Ebene
		case 0:	qnc = new QuadNodeCoordinates(495, 20, 490, 10); break;
		//zweite Ebene	
		case 1:	qnc = new QuadNodeCoordinates(132, 120, 495, 20); break;
		case 2: qnc = new QuadNodeCoordinates(372, 120, 495, 20); break;
		case 3: qnc = new QuadNodeCoordinates(612, 120, 495, 20); break;
		case 4: qnc = new QuadNodeCoordinates(852, 120, 495, 20); break;
		//dritte Ebene
		case 5:	qnc = new QuadNodeCoordinates(42, 220, 132, 120); break;
		case 6:	qnc = new QuadNodeCoordinates(102, 220, 132, 120); break;
		case 7:	qnc = new QuadNodeCoordinates(162, 220, 132, 120);	break;
		case 8:	qnc = new QuadNodeCoordinates(222, 220, 132, 120); break;
		case 9:	qnc = new QuadNodeCoordinates(282, 220, 372, 120); break;
		case 10:qnc = new QuadNodeCoordinates(342, 220, 372, 120); break;
		case 11:qnc = new QuadNodeCoordinates(402, 220, 372, 120); break;
		case 12:qnc = new QuadNodeCoordinates(462, 220, 372, 120); break;
		case 13:qnc = new QuadNodeCoordinates(522, 220, 612, 120); break;
		case 14: qnc = new QuadNodeCoordinates(582, 220, 612, 120); break;
		case 15: qnc = new QuadNodeCoordinates(642, 220, 612, 120); break;
		case 16: qnc = new QuadNodeCoordinates(702, 220, 612, 120); break;
		case 17: qnc = new QuadNodeCoordinates(762, 220, 852, 120); break;
		case 18: qnc = new QuadNodeCoordinates(822, 220, 852, 120); break;
		case 19: qnc = new QuadNodeCoordinates(882, 220, 852, 120); break;
		case 20: qnc = new QuadNodeCoordinates(942, 220, 852, 120); break;
		//vierte Ebene
		case 21: qnc = new QuadNodeCoordinates(20, 320, 42, 220); break;
		case 22: qnc = new QuadNodeCoordinates(35, 320, 42, 220); break;
		case 23: qnc = new QuadNodeCoordinates(50, 320, 42, 220); break;
		case 24: qnc = new QuadNodeCoordinates(65, 320, 42, 220); break;
		case 25: qnc = new QuadNodeCoordinates(80, 320, 102, 220); break;
		case 26: qnc = new QuadNodeCoordinates(95, 320, 102, 220); break;
		case 27: qnc = new QuadNodeCoordinates(110, 320, 102, 220); break;
		case 28: qnc = new QuadNodeCoordinates(125, 320, 102, 220); break;
		case 29: qnc = new QuadNodeCoordinates(140, 320, 162, 220); break;
		case 30: qnc = new QuadNodeCoordinates(155, 320, 162, 220); break;
		case 31: qnc = new QuadNodeCoordinates(170, 320, 162, 220); break;
		case 32: qnc = new QuadNodeCoordinates(185, 320, 162, 220); break;
		case 33: qnc = new QuadNodeCoordinates(200, 320, 222, 220); break;
		case 34: qnc = new QuadNodeCoordinates(215, 320, 222, 220); break;
		case 35: qnc = new QuadNodeCoordinates(230, 320, 222, 220); break;
		case 36: qnc = new QuadNodeCoordinates(245, 320, 222, 220); break;
		case 37: qnc = new QuadNodeCoordinates(260, 320, 282, 220); break;
		case 38: qnc = new QuadNodeCoordinates(275, 320, 282, 220); break;
		case 39: qnc = new QuadNodeCoordinates(290, 320, 282, 220); break;
		case 40: qnc = new QuadNodeCoordinates(305, 320, 282, 220); break;
		case 41: qnc = new QuadNodeCoordinates(320, 320, 342, 220); break;
		case 42: qnc = new QuadNodeCoordinates(335, 320, 342, 220); break;
		case 43: qnc = new QuadNodeCoordinates(350, 320, 342, 220); break;
		case 44: qnc = new QuadNodeCoordinates(365, 320, 342, 220); break;
		case 45: qnc = new QuadNodeCoordinates(380, 320, 402, 220); break;
		case 46: qnc = new QuadNodeCoordinates(395, 320, 402, 220); break;
		case 47: qnc = new QuadNodeCoordinates(410, 320, 402, 220); break;
		case 48: qnc = new QuadNodeCoordinates(425, 320, 402, 220); break;
		case 49: qnc = new QuadNodeCoordinates(440, 320, 462, 220); break;
		case 50: qnc = new QuadNodeCoordinates(455, 320, 462, 220); break;
		case 51: qnc = new QuadNodeCoordinates(470, 320, 462, 220); break;
		case 52: qnc = new QuadNodeCoordinates(485, 320, 462, 220); break;
		case 53: qnc = new QuadNodeCoordinates(500, 320, 522, 220); break;
		case 54: qnc = new QuadNodeCoordinates(515, 320, 522, 220); break;
		case 55: qnc = new QuadNodeCoordinates(530, 320, 522, 220); break;
		case 56: qnc = new QuadNodeCoordinates(545, 320, 522, 220); break;
		case 57: qnc = new QuadNodeCoordinates(560, 320, 582, 220); break;
		case 58: qnc = new QuadNodeCoordinates(575, 320, 582, 220); break;
		case 59: qnc = new QuadNodeCoordinates(590, 320, 582, 220); break;
		case 60: qnc = new QuadNodeCoordinates(605, 320, 582, 220); break;
		case 61: qnc = new QuadNodeCoordinates(620, 320, 642, 220); break;
		case 62: qnc = new QuadNodeCoordinates(635, 320, 642, 220); break;
		case 63: qnc = new QuadNodeCoordinates(650, 320, 642, 220); break;
		case 64: qnc = new QuadNodeCoordinates(665, 320, 642, 220); break;
		case 65: qnc = new QuadNodeCoordinates(680, 320, 702, 220); break;
		case 66: qnc = new QuadNodeCoordinates(695, 320, 702, 220); break;
		case 67: qnc = new QuadNodeCoordinates(710, 320, 702, 220); break;
		case 68: qnc = new QuadNodeCoordinates(725, 320, 702, 220); break;
		case 69: qnc = new QuadNodeCoordinates(740, 320, 762, 220); break;
		case 70: qnc = new QuadNodeCoordinates(755, 320, 762, 220); break;
		case 71: qnc = new QuadNodeCoordinates(770, 320, 762, 220); break;
		case 72: qnc = new QuadNodeCoordinates(785, 320, 762, 220); break;
		case 73: qnc = new QuadNodeCoordinates(800, 320, 822, 220); break;
		case 74: qnc = new QuadNodeCoordinates(815, 320, 822, 220); break;
		case 75: qnc = new QuadNodeCoordinates(830, 320, 822, 220); break;
		case 76: qnc = new QuadNodeCoordinates(845, 320, 822, 220); break;
		case 77: qnc = new QuadNodeCoordinates(860, 320, 882, 220); break;
		case 78: qnc = new QuadNodeCoordinates(875, 320, 882, 220); break;
		case 79: qnc = new QuadNodeCoordinates(890, 320, 882, 220); break;
		case 80: qnc = new QuadNodeCoordinates(905, 320, 882, 220); break;
		case 81: qnc = new QuadNodeCoordinates(920, 320, 942, 220); break;
		case 82: qnc = new QuadNodeCoordinates(935, 320, 942, 220); break;
		case 83: qnc = new QuadNodeCoordinates(950, 320, 942, 220); break;
		case 84: qnc = new QuadNodeCoordinates(965, 320, 942, 220); break;
//		case 80: qnc = new QuadNodeCoordinates(300, 350, 400, 50); break;

		default:
			qnc = new QuadNodeCoordinates(-1, -1, -1, -1);
		}

		return qnc;

	}
	
	public static int[] getCrossCoordinates(int i){
		//xa1, ya1, xe1, ye1, xa2, ya2, xe2, ye2 "|" , "-" 
		switch (i){
			case 0: return new int[]{200,0,200,400, 0,200,400,200};
			
			case 1: return new int[]{100,0,100,200, 0,100,200,100};
			case 2: return new int[]{300,0,300,200, 200,100,400,100};
			case 3: return new int[]{300,200,300,400, 200,300,400,300};
			case 4: return new int[]{100,200,100,400, 0,300,200,300};
			
			case 5: return new int[]{50,0,50,100, 0,50,100,50};
			case 6: return new int[]{150,0,150,100, 100,50,200,50};
			case 7: return new int[]{150,100,150,200, 100,150,200,150};
			case 8: return new int[]{50,100,50,200, 0,150,100,150};
			case 9: return new int[]{250,0,250,100, 200,50,300,50};
			case 10: return new int[]{350,0,350,100, 300,50,400,50};
			case 11: return new int[]{350,100,350,200, 300,150,400,150};
			case 12: return new int[]{250,100,250,200, 200,150,300,150};
			case 13: return new int[]{250,200,250,300, 200,250,300,250};
			case 14: return new int[]{350,200,350,300, 300,250,400,250};
			case 15: return new int[]{350,300,350,400, 300,350,400,350};
			case 16: return new int[]{250,300,250,400, 200,350,300,350};
			case 17: return new int[]{50,200,50,300, 0,250,100,250};
			case 18: return new int[]{150,200,150,300, 100,250,200,250};
			case 19: return new int[]{150,300,150,400, 100,350,200,350};
			case 20: return new int[]{50,300,50,400, 0,350,100,350};
			
			default: return new int[]{-1,-1,-1,-1,-1,-1,-1,-1};
		}
	}
}

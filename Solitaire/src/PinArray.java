
public class PinArray {
	
	public static int[][] pinArray(int size) {
		int[][] pinValues = new int[size][size];
		for (int x = 0; x < size; x++) {
			for (int y = 0; y < size; y++) {
				if (x == size/2 && y == size/2) {
					pinValues[x][y] = 0;
				} else if ((x > 1 || (y > 1 && y < 5)) && (x < 5 || (y > 1 && y < 5))) {
					pinValues[x][y] = 1;
				} else {
					pinValues[x][y] = -1;
				}
			}
		}
		return pinValues;
	}
}

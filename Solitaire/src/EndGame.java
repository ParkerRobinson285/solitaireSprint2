
public class EndGame {
	public static String winOrLose(int[][] pinValues) {
		String state = "none";
		int numPins = 0;
		for (int i = 0; i < pinValues.length; i++) {
			for (int j = 0; j < pinValues.length; j++) {
				if (pinValues[i][j] == 1) {
					numPins += 1;
				}
				if ((pinValues[i][j] == 1 && j < pinValues.length - 2) && (pinValues[i][j+1] == 1 && pinValues[i][j+2] == 0)) {
					return state;
				} else if ((pinValues[i][j] == 0 && j < pinValues.length - 2) && (pinValues[i][j+1] == 1 && pinValues[i][j+2] == 1)) {
					return state;
				} else if ((pinValues[i][j] == 1 && i < pinValues.length - 2) && (pinValues[i+1][j] == 1 && pinValues[i+2][j] == 0)) {
					return state;
				} else if ((pinValues[i][j] == 0 && i < pinValues.length - 2) && (pinValues[i+1][j] == 1 && pinValues[i+2][j] == 1)) {
					return state;
				}
			}
		}
		if (numPins == 1) {
			state = "win";
			return state;
		} else {
			state = "lose";
			return state;
		}
	}
}

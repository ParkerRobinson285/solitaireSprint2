import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JToggleButton;

public class PinButtons {

	public static void addButtons(int[][] pinArray, JFrame frame, List<JToggleButton> buttons) {
		
		int xOffset = (frame.getWidth()/2) + (-51 * ((pinArray.length/2))) - 25;
		for (int x = 0; x < pinArray.length; x++) {
			int yOffset = (frame.getHeight()/2) + (51 * ((pinArray.length/2) -1));
			for (int y = 0; y < pinArray.length; y++) {
				JToggleButton newButton = new JToggleButton();
				if (pinArray[x][y] == 1) {
					newButton.setIcon(new ImageIcon(Class.class.getResource("/filled.png")));
				} else {
					newButton.setIcon(new ImageIcon(Class.class.getResource("/empty.png")));
				}
				frame.add(newButton);
				newButton.setBounds(xOffset, yOffset, 50, 50);
				if (pinArray[x][y] == -1) {
					newButton.setVisible(false);
				}
				buttons.add(newButton);
				yOffset -= 51;
			}
			xOffset += 51;
		}
		JToggleButton newButton = new JToggleButton("extra");
		newButton.setVisible(false);
		frame.add(newButton);

	}
}

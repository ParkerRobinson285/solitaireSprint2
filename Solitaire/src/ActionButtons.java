import javax.swing.JButton;
import javax.swing.JFrame;

public class ActionButtons {
	
	public static void addButtons(JFrame frame) {
		int xCenter = frame.getWidth() / 2;
		
		JButton newGameButton = new JButton("New Game");
		newGameButton.setBounds(xCenter + 100, frame.getHeight() - 100, 100, 25);
		newGameButton.setActionCommand("new game");
		frame.add(newGameButton);
		
		JButton makeMoveButton = new JButton("Make Move");
		makeMoveButton.setBounds(xCenter - 200, frame.getHeight() - 100, 100, 25);
		makeMoveButton.setActionCommand("make move");
		frame.add(makeMoveButton);
		
		JButton extra = new JButton("extra");
		extra.setVisible(false);
		frame.add(extra);
	}
}

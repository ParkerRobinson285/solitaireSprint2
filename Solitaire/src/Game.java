import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToggleButton;

public class Game {
	
	public static void main(String[] args) {
		new Game();
	}
	
	public Game() {
		int size = 7;
		//int[][] pinValues = PinArray.pinArray(size);
		/*
		int[][] pinValues = {
			    {-1, -1,  0,  0,  0, -1, -1},
			    {-1, -1,  0,  0,  0, -1, -1},
			    { 0,  0,  0,  1,  0,  0,  0},
			    { 0,  1,  0,  0,  0,  1,  0},
			    { 0,  0,  0,  1,  0,  0,  0},
			    {-1, -1,  0,  0,  0, -1, -1},
			    {-1, -1,  1,  0,  1, -1, -1}
			}; 
		*/
			int[][] pinValues = {
			    {-1, -1,  0,  0,  0, -1, -1},
			    {-1, -1,  0,  0,  0, -1, -1},
			    { 0,  0,  0,  0,  0,  0,  0},
			    { 0,  0,  0,  1,  0,  0,  0},
			    { 0,  0,  0,  0,  0,  0,  0},
			    {-1, -1,  0,  0,  0, -1, -1},
			    {-1, -1,  0,  0,  0, -1, -1}
			};
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		JFrame frame = new JFrame("Solitaire");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(screenSize);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//frame.add(new English());
		List<JToggleButton> buttons = new ArrayList<>();
		PinButtons.addButtons(pinValues, frame, buttons);
		
		int xCenter = frame.getWidth() / 2;
		
		JLabel alertLabel = new JLabel();
		alertLabel.setBounds(xCenter, 50, 250, 25);
		alertLabel.setVisible(false);
		frame.add(alertLabel);

		
		JButton makeMoveButton = new JButton("Make Move");
		makeMoveButton.setBounds(xCenter - 200, frame.getHeight() - 100, 100, 25);
		makeMoveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alertLabel.setVisible(false);
				List<Integer> buttonPos = new ArrayList<>();
				int number = 0;
				List<JToggleButton> pressed = new ArrayList<>();
				for (JToggleButton button : buttons) {
					if (button.isSelected()) {
						buttonPos.add(number);
						System.out.println(number);
						pressed.add(button);
						System.out.println(button.getIcon());
					}
					if (buttonPos.size() > 2) {
						alertLabel.setText("Please Select 2 Pins");
						alertLabel.setBounds(xCenter - 50, 50, 500, 25);
						alertLabel.setVisible(true);
						break;
					}
					number += 1;
				}
				if (buttonPos.size() == 2) {
					int x1 = buttonPos.get(0).intValue() / size;
					//System.out.println(x1);
					int y1 = buttonPos.get(0).intValue() % size;
					//System.out.println(y1);
					int x2 = buttonPos.get(1).intValue() / size;
					//System.out.println(x2);
					int y2 = buttonPos.get(1).intValue() % size;
					//System.out.println(y2);
					if ((pinValues[x1][y1] == 0 && pinValues[x2][y2] == 0) || (pinValues[x1][y1] == 1 && pinValues[x2][y2] == 1)) {
						alertLabel.setText("Select One Empty Space");
						alertLabel.setBounds(xCenter - 50, 50, 500, 25);
						alertLabel.setVisible(true);
					} else {
						if (x1 != x2 && y1 != y2) {
							alertLabel.setText("Pins Are Not Across From Each Other");
							alertLabel.setBounds(xCenter - 50, 50, 500, 25);
							alertLabel.setVisible(true);
						//moving down
						} else if ((x1 == x2 && y1 < y2) && (pinValues[x2][y2] == 0 && pinValues[x1][y1+1] == 1)) {
							pinValues[x1][y1] = 0;
							buttons.get(buttonPos.get(0)).setIcon(new ImageIcon(Class.class.getResource("/empty.png")));
							pinValues[x1][y1+1] = 0;
							buttons.get(buttonPos.get(0)+1).setIcon(new ImageIcon(Class.class.getResource("/empty.png")));
							pinValues[x2][y2] = 1;
							buttons.get(buttonPos.get(1)).setIcon(new ImageIcon(Class.class.getResource("/filled.png")));
						//moving right
						} else if ((y1 == y2 && x1 < x2) && (pinValues[x2][y2] == 0 && pinValues[x1+1][y1] == 1)) {
							pinValues[x1][y1] = 0;
							buttons.get(buttonPos.get(0)).setIcon(new ImageIcon(Class.class.getResource("/empty.png")));
							pinValues[x1+1][y1] = 0;
							buttons.get(buttonPos.get(0)+size).setIcon(new ImageIcon(Class.class.getResource("/empty.png")));
							pinValues[x2][y2] = 1;
							buttons.get(buttonPos.get(1)).setIcon(new ImageIcon(Class.class.getResource("/filled.png")));
						//moving up
						} else if ((x1 == x2 && y1 < y2) && (pinValues[x1][y1] == 0 && pinValues[x1][y1+1] == 1)) {
							pinValues[x1][y1] = 1;
							buttons.get(buttonPos.get(0)).setIcon(new ImageIcon(Class.class.getResource("/filled.png")));
							pinValues[x1][y1+1] = 0;
							buttons.get(buttonPos.get(0)+1).setIcon(new ImageIcon(Class.class.getResource("/empty.png")));
							pinValues[x2][y2] = 1;
							buttons.get(buttonPos.get(1)).setIcon(new ImageIcon(Class.class.getResource("/empty.png")));
						//moving left
						} else if ((y1 == y2 && x1 < x2) && (pinValues[x1][y1] == 0 && pinValues[x1+1][y1] == 1)) {
							pinValues[x1][y1] = 1;
							buttons.get(buttonPos.get(0)).setIcon(new ImageIcon(Class.class.getResource("/filled.png")));
							pinValues[x1+1][y1] = 0;
							buttons.get(buttonPos.get(0)+size).setIcon(new ImageIcon(Class.class.getResource("/empty.png")));
							pinValues[x2][y2] = 0;
							buttons.get(buttonPos.get(1)).setIcon(new ImageIcon(Class.class.getResource("/empty.png")));
						} else {
							alertLabel.setText("There Is No Pin To Hop");
							alertLabel.setBounds(xCenter - 50, 50, 500, 25);
							alertLabel.setVisible(true);
						}
					}
				} else {
					alertLabel.setText("Please Select 2 Pins");
					alertLabel.setBounds(xCenter - 50, 50, 500, 25);
					alertLabel.setVisible(true);
				}
				String state = EndGame.winOrLose(pinValues);
				if (state == "win") {
					alertLabel.setText("You Win");
					alertLabel.setBounds(xCenter - 50, 50, 500, 25);
					alertLabel.setVisible(true);
				} else if (state == "lose") {
					alertLabel.setText("You Lose");
					alertLabel.setBounds(xCenter - 50, 50, 500, 25);
					alertLabel.setVisible(true);
				} else {
					frame.pack();
					frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
					frame.setVisible(true);
				}
			}
		});
		frame.add(makeMoveButton);
		
		
		JButton newGameButton = new JButton("New Game");
		newGameButton.setBounds(xCenter + 100, frame.getHeight() - 100, 100, 25);
		newGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Game();
				frame.dispose();
			}
		});
		frame.add(newGameButton);
		
		JButton extra = new JButton("extra");
		extra.setVisible(false);
		frame.add(extra);
		
		//ActionButtons.addButtons(frame);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		
	}
}

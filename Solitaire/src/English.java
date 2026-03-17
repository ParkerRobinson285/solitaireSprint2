import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class English extends JPanel {
	
	public English() {
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(200, 200);
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();
		//int size = Math.min(getWidth() - 4, getHeight() - 4) / 10;
		int size = 51;
		int width = getWidth() - (size * 2);
		int height = getHeight() - (size * 2);
		
		int centerHeight = (getHeight() / 2) - size;
		int centerWidth = (getWidth() / 2) - size;
		for (int horz = -3; horz < 4; horz++) {
			int x = centerWidth + (horz * size);
			for (int vert = -3; vert < 4; vert++) {
				int y = centerHeight + (vert * size);
				if ((horz > -2 || (vert > -2 && vert < 2)) && (horz < 2 || (vert > -2 && vert < 2))) {
					g.drawRect(x, y, size, size);
				}
			}
		}
		g2d.dispose();
	}
	
}

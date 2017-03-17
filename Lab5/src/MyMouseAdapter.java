import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JFrame;

public class MyMouseAdapter extends MouseAdapter {

	private Color existingColor;
	private Color[] existingColorArray = new Color[Math.max(MyPanel.TOTAL_COLUMNS, MyPanel.TOTAL_ROWS - 1)];

	private Random generator = new Random();
	public void mousePressed(MouseEvent e) {
		switch (e.getButton()) {
		case 1:		//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			JFrame myFrame = (JFrame) c;
			MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			myPanel.mouseDownGridX = myPanel.getGridX(x, y);
			myPanel.mouseDownGridY = myPanel.getGridY(x, y);
			myPanel.repaint();
			break;
		case 3:		//Right mouse button
			//Do nothing
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
	public void mouseReleased(MouseEvent e) {
		switch (e.getButton()) {
		case 1:		//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			JFrame myFrame = (JFrame)c;
			MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			int gridX = myPanel.getGridX(x, y);
			int gridY = myPanel.getGridY(x, y);
			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
				//Had pressed outside
				//Do nothing
			} else {
				if ((gridX == -1) || (gridY == -1)) {
					//Is releasing outside
					//Do nothing
				} else {
					if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
						//Released the mouse button on a different cell where it was pressed
						//Do nothing
					} else {
						//Released the mouse button on the same cell where it was pressed
						if (gridX == 0 && gridY == 0) {

							//On the top-left cell

							Color newColor = null;
							for (int i = 0; i < (Math.max(MyPanel.TOTAL_COLUMNS - 1, MyPanel.TOTAL_ROWS - 2)) - 1; i++) {
								do {
									switch (generator.nextInt(5)) 
									{
									case 0:
										newColor = Color.YELLOW;
										break;
									case 1:
										newColor = Color.MAGENTA;
										break;
									case 2:
										newColor = Color.BLACK;
										break;
									case 3:
										newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
										break;
									case 4:
										newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
										break;
									}
								} while (newColor.equals(existingColorArray[i]));
								existingColorArray[i] = newColor;
								MyPanel.colorArray[i+1][i+1] = newColor;
								myPanel.repaint();
							}
						}

						else if (gridX == 0 && gridY == MyPanel.TOTAL_ROWS - 1) {

							//On the bottom-left cell

							for (int i = 0; i < 3; i++) {
								for (int j = 0; j<3; j++) {


									Color newColor = null;
									
										do {
											switch (generator.nextInt(5)) 
											{
											case 0:
												newColor = Color.YELLOW;
												break;
											case 1:
												newColor = Color.MAGENTA;
												break;
											case 2:
												newColor = Color.BLACK;
												break;
											case 3:
												newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
												break;
											case 4:
												newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
												break;
											}
										} while (newColor.equals(existingColorArray[i]));
										existingColorArray[i] = newColor;
										myPanel.colorArray[(MyPanel.TOTAL_COLUMNS - 2)/2 + i][(MyPanel.TOTAL_ROWS - 3)/2 + j] = newColor;
										myPanel.repaint();
									}
									myPanel.repaint();
								}
							}
						

						else if (gridX == 0) {

							//On the left column

							Color newColor = null;
							for (int i = 1; i < MyPanel.TOTAL_COLUMNS; i++) {
								do {
									switch (generator.nextInt(5)) 
									{
									case 0:
										newColor = Color.YELLOW;
										break;
									case 1:
										newColor = Color.MAGENTA;
										break;
									case 2:
										newColor = Color.BLACK;
										break;
									case 3:
										newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
										break;
									case 4:
										newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
										break;
									}
								} while (newColor.equals(existingColorArray[i]));
								existingColorArray[i] = newColor;
								myPanel.colorArray[i][gridY] = newColor;
								myPanel.repaint();
							}
						}

						else if (gridY == 0) {

							//On the top row

							Color newColor = null;
							for (int i = 1; i < MyPanel.TOTAL_ROWS - 1; i++) {
								do {
									switch (generator.nextInt(5)) 
									{
									case 0:
										newColor = Color.YELLOW;
										break;
									case 1:
										newColor = Color.MAGENTA;
										break;
									case 2:
										newColor = Color.BLACK;
										break;
									case 3:
										newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
										break;
									case 4:
										newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
										break;
									}
								} while (newColor.equals(existingColorArray[i]));
								existingColorArray[i] = newColor;
								myPanel.colorArray[gridX][i] = newColor;
								myPanel.repaint();
							}
						}

						else {

							//On the grid other than on the left column and on the top row

							Color newColor = null;
							do {
								switch (generator.nextInt(5)) 
								{
								case 0:
									newColor = Color.YELLOW;
									break;
								case 1:
									newColor = Color.MAGENTA;
									break;
								case 2:
									newColor = Color.BLACK;
									break;
								case 3:
									newColor = new Color(0x964B00);   //Brown (from http://simple.wikipedia.org/wiki/List_of_colors)
									break;
								case 4:
									newColor = new Color(0xB57EDC);   //Lavender (from http://simple.wikipedia.org/wiki/List_of_colors)
									break;
								}
							} while (newColor.equals(existingColor));
							existingColor = newColor;
							myPanel.colorArray[gridX][gridY] = newColor;
							myPanel.repaint();
						}
					}
				}
				myPanel.repaint();
			}
				break;
			
			
		case 3:		//Right mouse button
			
			c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			myFrame = (JFrame)c;
			myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
			myInsets = myFrame.getInsets();
			x1 = myInsets.left;
			y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			x = e.getX();
			y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			gridX = myPanel.getGridX(x, y);
			gridY = myPanel.getGridY(x, y);
			if (gridX == -1 || gridY == -1) {

				for (int i=1; i < MyPanel.TOTAL_COLUMNS; i++) {
					for (int j=1; j < MyPanel.TOTAL_ROWS; j++) {
						if(!MyPanel.colorArray[i][j].equals(Color.WHITE)) {
							float r = generator.nextFloat();
							float g = generator.nextFloat();
							float b = generator.nextFloat();
							Color newColor = new Color(r, g, b);
							MyPanel.colorArray[i][j] = newColor;
							myPanel.repaint();
						}
					}
				}

				break;
			}

		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
}

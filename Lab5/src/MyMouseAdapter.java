import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JFrame;

public class MyMouseAdapter extends MouseAdapter {
	private int existingCaseNum;
	private int[] existingCaseNumbers = new int[Math.max(MyPanel.TOTAL_COLUMNS, MyPanel.TOTAL_ROWS)];
	private int[][] existingCaseNumbers2 = new int[3][3];

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
							for (int i = 1; i < MyPanel.TOTAL_COLUMNS; i++) {
								Color newColor = null;
								int caseNum = generator.nextInt(5);
								if (!myPanel.colorArray[i][i].equals(Color.WHITE)) {
									while (caseNum == existingCaseNumbers[i-1]) {
										caseNum = generator.nextInt(5);
									}
								}
								switch (caseNum) 
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
								existingCaseNumbers[i-1] = caseNum;
								myPanel.colorArray[i][i] = newColor;
							}
							myPanel.repaint();
						}
						
						else if (gridX == 0 && gridY == MyPanel.TOTAL_ROWS - 1) {
							//On the bottom-left cell
							for (int i = 0; i < 3; i++) {
								for (int j = 0; j<3; j++) {
									
								
								Color newColor = null;
								int caseNum = generator.nextInt(5);
								if (!myPanel.colorArray[4+i][4+j].equals(Color.WHITE)) {
									while (caseNum == existingCaseNumbers2[i][j]) {
										caseNum = generator.nextInt(5);
									}
								}
								switch (caseNum) 
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
								existingCaseNumbers2[i][j] = caseNum;
								myPanel.colorArray[4+i][4+j] = newColor;
							}
							myPanel.repaint();
						}
						}
						
						else if (gridX == 0) {
							//On the left column
							for (int i = 1; i < MyPanel.TOTAL_COLUMNS; i++) {
								Color newColor = null;
								int caseNum = generator.nextInt(5);
								if (!myPanel.colorArray[i][gridY].equals(Color.WHITE)) {
									while (caseNum == existingCaseNumbers[i-1]) {
										caseNum = generator.nextInt(5);
									}
								}
								switch (caseNum) 
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
								existingCaseNumbers[i-1] = caseNum;
								myPanel.colorArray[i][gridY] = newColor;
							}
							myPanel.repaint();
						}
						
						else if (gridY == 0) {
							//On the top row
							for (int j = 1; j < MyPanel.TOTAL_ROWS; j++) {
								Color newColor = null;
								int caseNum = generator.nextInt(5);
								if (!myPanel.colorArray[gridX][j].equals(Color.WHITE)) {
									while (caseNum == existingCaseNumbers[j-1]) {
										caseNum = generator.nextInt(5);
									}
								}
								switch (caseNum) 
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
								existingCaseNumbers[j-1] = caseNum;
								myPanel.colorArray[gridX][j] = newColor;
							}
							myPanel.repaint();
						}
						
						else {
							//On the grid other than on the left column and on the top row:
							Color newColor = null;
							int caseNum = generator.nextInt(5);
							if (!myPanel.colorArray[gridX][gridY].equals(Color.WHITE)) {
								while (caseNum == existingCaseNum) {
									caseNum = generator.nextInt(5);
								}
							}
							switch (caseNum) 
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
							existingCaseNum = caseNum;
							myPanel.colorArray[gridX][gridY] = newColor;
							myPanel.repaint();
						}
					}
				}
			}
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
}
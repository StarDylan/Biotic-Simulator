package gameBoard;

import javax.swing.JPanel;

public class Board extends JPanel {
	
	static int DimentionX = 1024;
	static int DimentionY = 1024;
	

	

	
	static public int[] getDimentions() {
		int[] Dimentions = new int[3];
		Dimentions[0] = DimentionX;
		Dimentions[0] = DimentionY;
		return Dimentions;
	}



	public Board() {}
	
	

}

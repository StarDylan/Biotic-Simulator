package gameBoard;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.json.simple.JSONObject;

import gameMechanics.Biotic;
import gameMechanics.BioticActions;
import gameMechanics.GameController;
import gameMechanics.JsonImport;



public class Application extends JPanel {
	
	
	
	public static void main(String[] args) throws InterruptedException {
	    
		Board.setScreenSize();
		
		JFrame frame = new JFrame("Biotics");
		 Board gc = new Board();
		frame.add(gc);
		Board.addEscapeListener(frame);
		frame.setSize(Board.DimentionX, Board.DimentionY);
		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setVisible(true);
		
		

		
		
		JSONObject jsonArray = JsonImport.getJsonObjectFromRaw("{\r\n" + 
				"\"PROGRAM\":\r\n" + 
				"[\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"{\"IF\":[\"ALWAYS\"],\r\n" + 
				"\"THAN\":[\"NETWORK\"]\r\n" + 
				"},\r\n" + 
				"{\"IF\":[\"STRANGER\"],\r\n" + 
				"\"THAN\":[\"HACK\"]\r\n" + 
				"},\r\n" + 
				"\r\n" + 
				"{\"IF\":[\"ALWAYS\",\"STRANGER\"],\r\n" + 
				"\"THAN\":[\"FOLLOW\"]\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"]\r\n" + 
				"\r\n" + 
				",\"Location\":[102,102]\r\n" + 
				"}");
		
		JSONObject jsonArray2 = JsonImport.getJsonObjectFromRaw("{\r\n" + 
				"\"PROGRAM\":\r\n" + 
				"[\r\n" + 
				"{\"IF\":[\"ALWAYS\"],\r\n" + 
				"\"THAN\":[\"REPLICATE\"]\r\n" + 
				"}\r\n" + 
				"\r\n" + 
				"]\r\n" + 
				"\r\n" + 
				",\"Location\":[102,102]}");

		Biotic cel1 = new Biotic("123e4567-e89b-42d3-a456-556642440000",jsonArray,190,100,Color.black);
		Biotic cel2 = new Biotic("223e4567-e89b-42d3-a456-556642440000",jsonArray2,0,0,Color.RED);
		
		//Biotic cel3 = new Biotic("123e4567-e89b-42d3-a456-556642440000",jsonArray,5,0);
		//Biotic cel4 = new Biotic("123e4567-e89b-42d3-a456-556642440000",jsonArray,1,0);
		while (true) {
			GameController.UpdateAllBiotics();
			gc.repaint();
			Thread.sleep(10);
	    }
	

}
}

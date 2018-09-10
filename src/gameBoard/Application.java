package gameBoard;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

import networkServer.JavaHTTPServer;
import networkServer.serverThread;
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
		
		

		
		
		JSONObject redJson = JsonImport.getJsonObjectFromRaw("{\r\n" +
				"\"PROGRAM\":\r\n" + 
				"[\r\n" + 
				"{\"IF\":[\"DIFFERENT_COLOR\"],\r\n" + 
				"\"THAN\":[\"HACK\"]\r\n" + 
				"},\r\n" + 
				"{\"IF\":[\"ALWAYS\"],\r\n" + 
				"\"THAN\":[\"REPLICATE\"]\r\n" + 
				"},\r\n" + 
				"{\"IF\":[\"ALWAYS\"],\r\n" +
				"\"THAN\":[\"NETWORK\"]\r\n" + 
				"},\r\n" + 
				"{\"IF\":[\"SAME_COLOR\"],\r\n" + 
				"\"THAN\":[\"FOLLOW\"]\r\n" + 
				"},\r\n" + 
				"{\"IF\":[\"ALWAYS\"],\r\n" + 
				"\"THAN\":[\"RUN_AWAY\"]\r\n" + 
				"},\r\n" + 
				"\r\n" + 
				"]\r\n" + 
				"\r\n" + 
				",\"Location\":[102,102]\r\n" + 
				"}");
		
		JSONObject blackJson = JsonImport.getJsonObjectFromRaw("{\r\n" +
				"	\"PROGRAM\": [{\r\n" + 
				"				\"IF\": [\"DIFFERENT_COLOR\"],\r\n" +
				"				\"THAN\": [\"HACK\"]\r\n" +
				"			},\r\n" + 
				"			{\r\n" + 
				"				\"IF\": [\"ALWAYS\"],\r\n" + 
				"				\"THAN\": [\"REPLICATE\"]\r\n" + 
				"			},\r\n" + 
				"\r\n" + 
				"			{\r\n" + 
				"				\"IF\": [\"ALWAYS\"],\r\n" +
				"				\"THAN\": [\"NETWORK\"]\r\n" + 
				"			},\r\n" + 
				"\r\n" + 
				"			{\r\n" + 
				"				\"IF\": [\"DIFFERENT_COLOR\"],\r\n" + 
				"				\"THAN\": [\"FOLLOW\"]\r\n" + 
				"			}\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"		]\r\n" + 
				"\r\n" + 
				"		,\r\n" +
				"}");
		(new serverThread()).start();

		
		//Biotic cel3 = new Biotic("123e4567-e89b-42d3-a456-556642440000",jsonArray,5,0);
		//Biotic cel4 = new Biotic("123e4567-e89b-42d3-a456-556642440000",jsonArray,1,0);
		while (true) {
			GameController.UpdateAllBiotics();
			gc.repaint();
			Thread.sleep(40); //40 for 1x Speed
	    }
	

}
}

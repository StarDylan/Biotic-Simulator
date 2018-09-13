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
				"\"program\":\r\n" +
				"[\r\n" + 
				"{\"if\":[\"always\"],\r\n" +
				"\"than\":[\"replicate\"]\r\n" +
				"},\r\n" + 
				"{\"if\":[\"different_color\"],\r\n" +
				"\"than\":[\"hack\"]\r\n" +
				"},\r\n" + 
				"{\"if\":[\"same_colorX																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																																az\"],\r\n" +
				"\"than\":[\"network\"]\r\n" +
				"},\r\n" +
				"{\"if\":[\"same_color\"],\r\n" +
				"\"than\":[\"follow\"]\r\n" +
				"},\r\n" + 
				"{\"if\":[\"always\"],\r\n" +
				"\"than\":[\"run_away\"]\r\n" +
				"},\r\n" + 
				"\r\n" + 
				"]\r\n" + 
				"\r\n" + 
				",\"Location\":[102,102]\r\n" + 
				"}");
		
		JSONObject blackJson = JsonImport.getJsonObjectFromRaw("{\r\n" +
				"	\"program\": [{\r\n" +
				"				\"if\": [\"different_color\"],\r\n" +
				"				\"than\": [\"hack\"]\r\n" +
				"			},\r\n" + 
				"			{\r\n" + 
				"				\"if\": [\"always\"],\r\n" +
				"				\"than\": [\"replicate\"]\r\n" +
				"			},\r\n" + 
				"\r\n" + 
				"			{\r\n" + 
				"				\"if\": [\"always\"],\r\n" +
				"				\"than\": [\"network\"]\r\n" +
				"			},\r\n" + 
				"\r\n" + 
				"			{\r\n" + 
				"				\"if\": [\"different_color\"],\r\n" +
				"				\"than\": [\"follow\"]\r\n" +
				"			}\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"		]\r\n" + 
				"\r\n" + 
				"		,\r\n" +
				"}");
		(new serverThread()).start();

		
		Biotic cel3 = new Biotic("123e4567-e89b-42d3-a456-556642440000",blackJson,5,0,Color.black);
		new Biotic("123e4567-e89b-42d3-a456-556642440000",redJson,100,80,Color.red);
		while (true) {
			GameController.UpdateAllBiotics();
			gc.repaint();
			Thread.sleep(3); //40 for 1x Speed
	    }
	

}
}

package gameMechanics;

import gameMechanics.JsonImport;
import gameBoard.Board;

import java.util.HashMap;

import org.json.simple.*;

import java.util.ArrayList;
import java.util.Arrays;


public class Cell {
	
	private static HashMap<Cell,Integer[]> CELLS_INGAME = new HashMap<Cell,Integer[]>();
	
	private final String Owner_UUID;
	
	private static Cell[][] grid = new Cell[1024][1024];
	
	private Integer[] currentCords = new Integer[2];

	private JSONObject program;
	
	enum actions{
		ATTACK,
		RUN_AWAY
	}

	

	 Cell(String Owner_UUID, JSONObject program,int locX, int locY){
		
		this.Owner_UUID = Owner_UUID;
		
		this.program = program;
		
		//Fill Grid with null values
		if (CELLS_INGAME.size() == 0) {
			Arrays.fill(grid,null);
		}
		
		grid[locX][locY] = this;
		
		Integer[] cords = {locX,locY};
		
		CELLS_INGAME.put(this,cords);
	}
	 
	//Getters And Setters
	public String getOwner_UUID () {
		return this.Owner_UUID;
		
	}

	public Integer[] getCurrentCords() {
		return currentCords;
	}
	static public int getNum_CELLS_INGAME() {
		return CELLS_INGAME.size();
	}

	
	
	//Call to Update the Cell
	public void Update() {
		
		JSONObject json = this.program;
		
		/**
		 * JSON Syntax
		 * [{"WHEN":["DETECT_RED","DETECT BLUE"],"THAN":["EAT"]},"IF":["DETECT BLUE"],"THAN":["RUN_AWAY"]{}]
		 */

		
		//Loop Through All - WHEN, THAN Pairs
		for(int x = 0; x<json.size(); x++) {
			HashMap<String,Object> dictionary = (HashMap<String,Object>) json.get(x);

				//Show the WHEN Commands
				JSONObject WhenArray = (JSONObject) dictionary.get("WHEN");
				
				//Show the THAN Commands
				JSONObject ThanArray = (JSONObject) dictionary.get("THAN");
			
				//Comparing Current State to Programmed State
				if(WhenArray.sort().equals(NearCell)) {
					
					//If True, Set Next Action to programmed action 
					switch(ThanArray) {
					case "attack":
						actions NextAction = actions.ATTACK;
					case "run_away":
					
					}
					
					switch(NextAction) {
					case ATTACK:
					case RUN_AWAY:
						this.run_away;
					}
				}
				
		}
	
	}
	
	
	
	
	
	
	public static void main(String[] args) {
	
		Cell cel1 = new Cell("UUID",new JSONObject(),23,42);
		System.out.println(Cell.getNum_CELLS_INGAME());
		
	}
	
}

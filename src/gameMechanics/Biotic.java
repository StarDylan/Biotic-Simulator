package gameMechanics;

import java.util.UUID;

import gameMechanics.JsonImport;
import gameBoard.Board;

import java.util.HashMap;

import org.json.simple.*;

import java.util.ArrayList;
import java.util.Arrays;


public class Biotic {
	
	private static HashMap<Biotic,int[]> BIOTICS_INGAME = new HashMap<Biotic,int[]>();
	
	private final UUID Owner_UUID;
	
	public final UUID BioticUUID;
	
	static Biotic[][] grid = new Biotic[Board.getDimentionX()][Board.getDimentionY()];
	
	private int[] currentCords;

	private JSONObject[] program;
	
	private actions NextAction;
	
	enum actions{
		DELETE,
		RUN_AWAY,
		REPLICATE,
		FOLLOW,
		NETWORK,
		INFECT,
		HACK,
		WANDER
	}

	

	 Biotic(String Owner_UUID, JSONObject[] program,int locX, int locY){
		
		this.Owner_UUID = UUID.fromString(Owner_UUID);
		
		this.program = program;
		
		BioticUUID = UUID.randomUUID();
		
		//Fill Grid with null values
		if (BIOTICS_INGAME.size() == 0) {
			Arrays.fill(grid,null);
		}
		
		grid[locX][locY] = this;
		
		int[] currentCords = {locX,locY};
		
		BIOTICS_INGAME.put(this,currentCords);
	}
	 
	 Biotic(String Owner_UUID, JSONObject[] program,int locX, int locY,UUID BioticUUID){
			
		this.Owner_UUID = Owner_UUID;
		
		BioticUUID = this.BioticUUID;
		
		this.program = program;
		
		BioticUUID = UUID.randomUUID();
		
		//Fill Grid with null values
		if (BIOTICS_INGAME.size() == 0) {
			Arrays.fill(grid,null);
		}
		
		grid[locX][locY] = this;
		
		Integer[] cords = {locX,locY};
		
		BIOTICS_INGAME.put(this,cords);
	}
	 
	//Getters And Setters
	public UUID getOwner_UUID () {
		return this.Owner_UUID;
	}

	public int[] getCurrentCords() {
		return currentCords;
	}
	static public int getNum_BIOTICS_INGAME() {
		return BIOTICS_INGAME.size();
	}
	static public Biotic[][] getGrid(){
		return grid;
	}

	
	
	//Call to Update the Biotic
	public void Update() {
		
		String NearBy = "Insert Nearby String";
	
		/**
		 * JSON Syntax
		 * {"WHEN":["DETECT_RED","DETECT BLUE"],"THAN":["EAT"]},"IF":["DETECT BLUE"],"THAN":["RUN_AWAY"]}
		 */
		
		for(int a = 0; a < this.program.length; a++) {
			JSONObject json = this.program[a];
			
			actions NextAction = null;
			
			//Get the WHEN Commands
			JSONArray WhenArray = (JSONArray) json.get("WHEN");
			String IfConditions = "";
			for (int n = 0; n<WhenArray.size(); n++) {
				
				IfConditions = IfConditions + "," + WhenArray.get(n).toString();
			}
			
			//Get the THAN Commands
			JSONArray ThanArray = (JSONArray) json.get("THAN");
			
			for(int z = 0; z<ThanArray.size();z++) {
				ThanArray.get(z).toString();
				
				//Comparing Current State to Programmed State
				if(IfConditions.equals(NearBy)) {
					
					//If True, Set Next Action to programmed action 
					switch((String)ThanArray.get(z)) {
					case "ATTACK":
						NextAction = actions.ATTACK;
					case "RUN_AWAY":
						NextAction = actions.RUN_AWAY;
						
					
					}	
				}
			}
		}
		
		//Detecting Functions
		public String detectAll() {
			
			switch (typeBiotic) {
			
			//	Near/NextTo Young
			case 
			//	Near/NextTo Old
			
			//	Near/NextTo Network
			
			//	Near/NextTo Past Creation
			
			//	Near/NextTo Stranger
			
			//	Near/NextTo Replicate
			}
		}
		
	}
	
	
	
	
	
	
	public static void main(String[] args) {
	
		JSONObject[] json = {new JSONObject()};
		Biotic cel1 = new Biotic("UUID",json,23,42);
		System.out.println(Biotic.getNum_BIOTICS_INGAME());
		
	}
	
	public 
	
}

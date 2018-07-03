package gameMechanics;

import java.util.UUID;

import gameBoard.Board;

import java.util.HashMap;

import org.json.simple.*;

public class Biotic {
	
	private static HashMap<Biotic,int[]> BIOTICS_INGAME = new HashMap<Biotic,int[]>();
	
	private UUID Owner_UUID;
	
	public UUID BioticUUID;
	
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
		
		grid[locX][locY] = this;
		
		int[] currentCords = {locX,locY};
		
		BIOTICS_INGAME.put(this,currentCords);
	}
	 
	 Biotic(String Owner_UUID, JSONObject[] program,int locX, int locY,UUID BioticUUID){
			
		this.Owner_UUID = UUID.fromString(Owner_UUID);
		
		BioticUUID = this.BioticUUID;
		
		this.program = program;
		
		BioticUUID = UUID.randomUUID();
		
		grid[locX][locY] = this;
		
		int[] cords = {locX,locY};
		
		BIOTICS_INGAME.put(this,cords);
	}
	 
	//Getters And Setters
	public UUID getOwner_UUID () {
		return this.Owner_UUID;
	}
	public UUID getBioticUUID() {
		return this.BioticUUID;
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
					case "DELETE":
						this.NextAction = actions.DELETE;
					case "RUN_AWAY":
						this.NextAction = actions.RUN_AWAY;
					case "REPLICATE":
						this.NextAction = actions.REPLICATE;
					case "NETWORK":
						this.NextAction = actions.NETWORK;
					case "HACK":
						this.NextAction = actions.HACK;
					case "INFECT":
						this.NextAction = actions.INFECT;
					case "WANDER":
						this.NextAction = actions.WANDER;
					case "FOLLOW":
						this.NextAction = actions.FOLLOW;
						
					
					}	
				}
			}
		}
	}
		
		//Detecting Functions
		public String detectAll() {
			return null;
			
			//	Near/NextTo Young
		 
			//	Near/NextTo Old
			
			//	Near/NextTo Network
			
			//	Near/NextTo Past Creation
			
			//	Near/NextTo Stranger
			
			//	Near/NextTo Replicate
		
		}
		
	
	
	
	
	
	
	
	public static void main(String[] args) {
	
		JSONObject[] json = {new JSONObject()};
		Biotic cel1 = new Biotic("123e4567-e89b-42d3-a456-556642440000",json,0,0);
		System.out.print(cel1.getBioticUUID());
		System.out.println(Biotic.getNum_BIOTICS_INGAME());
		
	}
	
	 
	
}

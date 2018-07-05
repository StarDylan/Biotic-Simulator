package gameMechanics;

import java.util.UUID;

import gameBoard.Board;

import gameMechanics.JsonImport;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.*;

public class Biotic {
	
	private static HashMap<Biotic,int[]> BIOTICS_INGAME = new HashMap<Biotic,int[]>();
	
	private UUID Owner_UUID;
	
	public UUID BioticUUID;
	
	private double Information = 0;
	
	static Biotic[][] grid = new Biotic[Board.getDimentionX()][Board.getDimentionY()];
	
	private int[] CurrentCords;

	private JSONObject[] Program;
	
	private actions NextAction;
	
	private ArrayList<Biotic> Network;
	
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
	
	SearchAlgorithm Biotic_func = new SearchAlgorithm();
	//biotic actions
	public static void DELETE() {
		
		
		/**I want to get these
		Biotic_func.Xcord_Attack ;
		Biotic_func.Xcord;					IMPORTANT, SOS, PLEASE HELP
		Biotic_func.Ycord_Attack;
		Biotic_func.Ycord;
		*/
		
		//if Xcord less than Xtarget, +=
		if(Xcord < Xcord_Attack) {
			for( Xcord = Xcord; Xcord <= Xcord_Attack; Xcord += 1) {}
			
		}
		//if Xcord more than Xtarget, -=
		if(Xcord > Xcord_Attack) {
			for( Xcord = Xcord; Xcord >= Xcord_Attack; Xcord -= 1) {}
					
		}
		//if Ycord less than Ytarget +=
		if(Ycord < Ycord_Attack) {
			for( Ycord = Ycord; Ycord <= Ycord_Attack; Ycord += 1) {}
			
		}
		//if Ycord more than Xtarget, -=
		if(Ycord > Ycord_Attack) {
			for( Ycord = Ycord; Ycord >= Ycord_Attack; Ycord -= 1) {}
					
		}
		// tell me how to delete the targeted biotic when the target cord it reached, please call
		
	}
	

	 Biotic(String Owner_UUID, JSONObject[] Program,int locX, int locY){
		
		this.Owner_UUID = UUID.fromString(Owner_UUID);
		
		this.Program = Program;
		
		grid[locX][locY] = this;
		
		int[] CurrentCords = {locX,locY};
		
		BIOTICS_INGAME.put(this,CurrentCords);
		
		BioticUUID = UUID.randomUUID();
	}
	 
	 //Biotic Constructor with Specified Biotic UUID
	 Biotic(String Owner_UUID, JSONObject[] Program,int locX, int locY, UUID BioticUUID){
			
		this.Owner_UUID = UUID.fromString(Owner_UUID);
		
		this.Program = Program;
		
		grid[locX][locY] = this;
		
		int[] CurrentCords = {locX,locY};
		
		BIOTICS_INGAME.put(this,CurrentCords);
		
		this.BioticUUID = BioticUUID;
	}
	 
	//Getters And Setters
	public UUID getOwner_UUID () {
		return this.Owner_UUID;
	}
	public UUID getBioticUUID() {
		return this.BioticUUID;
	}

	public int[] getCurrentCords() {
		return CurrentCords;
	}
	static public int getNum_BIOTICS_INGAME() {
		return BIOTICS_INGAME.size();
	}
	static public Biotic[][] getGrid(){
		return grid;
	}
	public double getInformation() {
		return this.Information;
	}
	public ArrayList<Biotic> getNetwork() {
		return Network;
	}

	//Call to Update the Biotic
	public void Update() {
		
		String NearBy = "Insert Nearby String";
	
		/**
		 * JSON Syntax
		 * {"WHEN":["DETECT_RED","DETECT BLUE"],"THAN":["EAT"]},"IF":["DETECT BLUE"],"THAN":["RUN_AWAY"]}
		 */
		
		for(int a = 0; a < this.Program.length; a++) {
			JSONObject json = this.Program[a];
			
			//Get the WHEN Commands
			JSONArray WhenArray = (JSONArray) json.get("WHEN");
			ArrayList<String> IfConditions = new ArrayList<String>();
			for (int n = 0; n<WhenArray.size(); n++) {
				
				IfConditions.add((String) WhenArray.get(n));
			}
			
			//Get the THAN Commands
			JSONArray ThanArray = (JSONArray) json.get("THAN");
			
			for(int z = 0; z<ThanArray.size();z++) {
				ThanArray.get(z).toString();
				for(int k = 0; k < IfConditions.size(); k++)
				//Comparing Current State to Programmed State
				if(IfConditions.equals(NearBy)) {
					
					//If True, Set Next Action to Programmed action 
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

	
	
	
	
	
	public static void main(String[] args) {
	
		JSONObject[] jsonArray = {JsonImport.getJsonObjectFromRaw("{\"WHEN\":[\"DETECT_RED\",\"DETECT BLUE\"],\"THAN\":[\"EAT\"]}")};
		Biotic cel1 = new Biotic("123e4567-e89b-42d3-a456-556642440000",jsonArray,0,0);
		System.out.print(cel1.getBioticUUID());
		System.out.println(Biotic.getNum_BIOTICS_INGAME());
		cel1.Update();
		
	}
	
}


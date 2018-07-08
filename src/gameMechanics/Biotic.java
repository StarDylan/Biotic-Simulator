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
	
	private Network_Status Networkable;
	
	private double Information = 0;
	
	private static Biotic[][] grid = new Biotic[Board.getDimentionX()][Board.getDimentionY()];
	
	private int[] CurrentCords;

	private JSONObject[] Program;
	
	private actions NextAction;
	
	private ArrayList<Biotic> Network;
	
	private Timer Timer = new Timer();
	
	enum Network_Status{
		ABLE_TO,
		NOT_TESTED
	}
	
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
	
	

	 Biotic(String Owner_UUID, JSONObject[] Program,int locX, int locY){
		
		this.Owner_UUID = UUID.fromString(Owner_UUID);
		
		this.Program = Program;
		
		grid[locX][locY] = this;
		
		int[] CurrentCords = {locX,locY};
		
		this.CurrentCords = CurrentCords;
		
		BIOTICS_INGAME.put(this,CurrentCords);
		
		BioticUUID = UUID.randomUUID();
	}
	 
	 //Biotic Constructor with Specified Biotic UUID
	 Biotic(String Owner_UUID, JSONObject[] Program,int locX, int locY, UUID BioticUUID){
			
		this.Owner_UUID = UUID.fromString(Owner_UUID);
		
		this.Program = Program;
		
		grid[locX][locY] = this;
		
		int[] CurrentCords = {locX,locY};
		
		this.CurrentCords = CurrentCords;
		
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
	static public void setGrid(Biotic[][] newGrid) {
		grid = newGrid;
	}
	public double getInformation() {
		return this.Information;
	}
	public ArrayList<Biotic> getNetwork() {
		return Network;
	}
	public JSONObject[] getProgram() {
		return this.Program;
	}
	public void setProgram(JSONObject[] program) {
		this.Program = program;
	}
	public Timer getTimer() {
		return this.Timer;
	}
	public Network_Status getNetworkable(){
		return this.Networkable;
	}
	public void addNetwork(Biotic TargetBiotic) {
		this.Network.add(TargetBiotic);
	}
	public void setNetworkable(Network_Status newNetworkStatus) {
		this.Networkable = newNetworkStatus;
	}
	public void setCurrentCords(int[] cords) {
		this.CurrentCords = cords;
	}
	
	public void clone(int xcord,int ycord) {
		
		new Biotic(this.getOwner_UUID().toString(),this.getProgram(),xcord,ycord, this.getBioticUUID());
		
	}

	//Call to Update the Biotic
	public void Update() {
		
		this.Timer.Update();
		
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
		int[] cords = {0,1};
		BioticActions.Move(cel1,cords);
		
		System.out.println();
		
	}
	
}


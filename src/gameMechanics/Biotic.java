package gameMechanics;

import java.util.UUID;

import gameBoard.Board;

import gameMechanics.JsonImport;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import gameMechanics.BioticActions;

import org.json.simple.*;

public class Biotic {

	private static ArrayList<Biotic> BIOTICS_INGAME = new ArrayList<Biotic>();

	private UUID Owner_UUID;

	public UUID BioticUUID;

	private Network_Status Networkable;

	private double Information = 0;

	private static Biotic[][] grid = new Biotic[Board.DimentionX/10][Board.DimentionY/10];

	private int[] CurrentCords;

	private JSONObject Data;

	private ArrayList<Biotic> Network = new ArrayList<Biotic>();

	private Timer Timer = new Timer();

	private int InformationDeath = 100000;

	private Color color = Color.BLACK;

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



	public Biotic(String Owner_UUID, JSONObject Data,int locX, int locY,Color color){

		this.Owner_UUID = UUID.fromString(Owner_UUID);

		this.Data = Data;

		this.color = color;

		grid[locX][locY] = this;

		int[] CurrentCords = {locX,locY};

		this.CurrentCords = CurrentCords;

		BIOTICS_INGAME.add(this);

		BioticUUID = UUID.randomUUID();
	}

	//Biotic Constructor with Specified Biotic UUID
	public Biotic(String Owner_UUID, JSONObject Data,int locX, int locY, UUID BioticUUID, Color color){

		this.Owner_UUID = UUID.fromString(Owner_UUID);

		this.Data = Data;

		this.color = color;

		grid[locX][locY] = this;

		int[] CurrentCords = {locX,locY};

		this.CurrentCords = CurrentCords;

		BIOTICS_INGAME.add(this);

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
	public static ArrayList<Biotic> getBIOTICS_INGAME() {
		return Biotic.BIOTICS_INGAME;
	}
	public static void setBIOTICS_INGAME(ArrayList<Biotic> newBIOTICS_INGAME) {
		Biotic.BIOTICS_INGAME = newBIOTICS_INGAME;
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
	public JSONObject getProgram() {
		return this.Data;
	}
	public void setProgram(JSONObject program) {
		this.Data = program;
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
	public void setNetwork(ArrayList<Biotic> newNetwork) {
		this.Network = newNetwork;
	}
	public void setCurrentCords(int[] cords) {
		this.CurrentCords = cords;
	}
	public void addInformation(int addInfo) {
		this.Information += addInfo;
	}
	public int getInformationDeath() {
		return this.InformationDeath;
	}
	public void addInformationDeath(int add) {
		this.InformationDeath += add;
	}
	public Color getColor() {
		return this.color;
	}
	public void setColor(Color color) {
		this.color = color;
	}

	public void delete() {
		Biotic.getBIOTICS_INGAME().remove(this);

		grid[this.getCurrentCords()[0]][this.getCurrentCords()[1]] = null;

	}

	public Biotic clone(int xcord,int ycord, Color color) {

		return new Biotic(this.getOwner_UUID().toString(),this.getProgram(),xcord,ycord, this.getBioticUUID(),color);

	}

	//Call to Update the Biotic
	public void Update() {

		if (getInformation() <= this.getInformationDeath()) {
			this.addInformation(1);
		}
		else {
			this.delete();
		}

		int SearchRadius = 5;

		this.Timer.Update();

		Biotic targetBiotic = null;

		ArrayList<String> NearBy;

		HashMap<Biotic,Integer> FoundBiotics = SearchAlgorithm.search(SearchRadius, this);

		Iterator<Biotic> FoundBioticsIterator = FoundBiotics.keySet().iterator();
		if(FoundBioticsIterator.hasNext()) {
			targetBiotic = FoundBioticsIterator.next();

			NearBy = 
					SearchAlgorithm.findAttributes(this, targetBiotic, FoundBiotics.get(targetBiotic));
		}
		else {
			NearBy = new ArrayList<String>();
		}
		NearBy.add("ALWAYS");
		/**
		 * JSON Syntax
		 * {"WHEN":["DETECT_RED","DETECT BLUE"],"THAN":["EAT"]},"IF":["DETECT BLUE"],"THAN":["RUN_AWAY"]}
		 */


		JSONArray ProgramArray = (JSONArray) Data.get("PROGRAM");
		outerloop:
			for(int p = 0; p < ProgramArray.size(); p++) {

				//Convert to JSON Object
				JSONObject Program = (JSONObject) ProgramArray.get(p);

				//Get the If Commands
				JSONArray IfArray = (JSONArray) Program.get("IF");
				ArrayList<String> IfConditions = new ArrayList<String>();

				//Transfer from JSONArray to ArrayList
				for (int n = 0; n<IfArray.size(); n++) {

					IfConditions.add((String) IfArray.get(n));
				}

				//Get the THAN Commands
				JSONArray ThanArray = (JSONArray) Program.get("THAN");


				for(int k = 0; k < IfConditions.size(); k++) {
					//Comparing Current State to Programmed State
					if(!(NearBy.contains(IfConditions.get(k)))) {
						break outerloop;
					}
				}

				Collections.shuffle(ThanArray);

				for (int k = 0; k<ThanArray.size(); k++) {
					String Command = (String) ThanArray.get(k);
					//If True, Set Next Action to Programmed action 
					boolean CommandOutput = false;
					//Requries Close Proximety Commands
					if (NearBy.contains("NEXT_TO")) {
						switch(Command) {
						case "DELETE":
							CommandOutput = BioticActions.Delete(this, targetBiotic);
							break;
						case "NETWORK":
							CommandOutput = BioticActions.Network(this, targetBiotic);
							break;
						case "HACK":
							CommandOutput = BioticActions.Hack(this, targetBiotic);
							break;
						case "INFECT":
							CommandOutput = BioticActions.Infect(this, targetBiotic);
							break;
						}
					}
					switch(Command) {

					case "RUN_AWAY":
						CommandOutput = BioticActions.RunAway(this);
						break;
					case "REPLICATE":
						CommandOutput = BioticActions.Replicate(this);
						break;
					case "WANDER":
						CommandOutput = BioticActions.Wander(this);
						break;
					case "FOLLOW":
						CommandOutput = BioticActions.Follow(this);
						break;
					}
					if (CommandOutput == true) {
						break outerloop;
					}

				}
			}
		//If Nothing works, Wander
		BioticActions.Wander(this);
	}









	public static void main(String[] args) {

	}

}


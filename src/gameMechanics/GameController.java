package gameMechanics;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.json.simple.JSONObject;

import gameMechanics.*;



public class GameController {

	public static void UpdateAllBiotics() {
		
		Random rand = new Random();
				
		ArrayList<Biotic> BioticList = new ArrayList<Biotic>();
		
		for (Biotic bio: Biotic.getBIOTICS_INGAME()) {
			BioticList.add(bio);
		}
		
		
		
		
		while (BioticList.size() != 0) {
		
			Biotic currentBiotic = (Biotic) BioticList.get(rand.nextInt(BioticList.size()));
			currentBiotic.Update();
			BioticList.remove(currentBiotic);
			}
		
		
	}

	
	
	
	

	public static void main(String[] args) {
		
		
	}

}

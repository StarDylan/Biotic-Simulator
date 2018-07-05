package gameMechanics;

import java.util.ArrayList;
import java.util.HashMap;

import gameMechanics.JsonImport;

public class SearchAlgorithm {
	
	private static int radius = 4;
	static Biotic selectedBiotic;

	static public HashMap<Biotic, Integer> search(int radius, Biotic biotic) {
		int[] currentCords = biotic.getCurrentCords();
		int Xcord = currentCords[0];
		int Ycord = currentCords[1];
		int Xatk;
		int Yatk;

		HashMap<Biotic,Integer> BioticsFound = new HashMap<Biotic,Integer>();
		
		
		boolean Found = false;
		// runs a scan of layers around
		//biotic (z) until the height of the layers is equal to the radius
		for (int z=1; z<=radius; z++) {
			
			//starting point
			Xcord += z;
			Ycord += z;
			
			
			for (int xOffset=1; xOffset<= z*2; xOffset++) {
				selectedBiotic = Biotic.getGrid()[Xcord-=xOffset][Ycord];
				if (selectedBiotic != null) {
					
					BioticsFound.put(selectedBiotic,z);
					Found = true;
					}
				}
			
			for (int yOffset=1; yOffset<= z*2; yOffset++) {
				selectedBiotic = Biotic.getGrid()[Xcord][Ycord-=yOffset];
				if (selectedBiotic != null) {
					
					BioticsFound.put(selectedBiotic,z);
					AttackCoordinates = z;
					Found = true;
					}
				}
			
			for (int xOffset=1; xOffset<= z*2; xOffset++) {
				selectedBiotic = Biotic.getGrid()[Xcord+=xOffset][Ycord];
				if (selectedBiotic != null) {
					
					BioticsFound.put(selectedBiotic,z);
					Found = true;
					}
				}
			
			for (int yOffset=1; yOffset<= z*2; yOffset++) {
				selectedBiotic = Biotic.getGrid()[Xcord][Ycord+=yOffset];
				if (selectedBiotic != null) {
					
					BioticsFound.put(selectedBiotic,z);
					Found = true;
					
				}
			}
			// finds the first scanned biotic in radius
			for (int xOffset=1; xOffset<= z*2; xOffset++) {
				selectedBiotic = Biotic.getGrid()[Xcord-=xOffset][Ycord];
				if (selectedBiotic != null) {
					
					Xcord_Attack = (Integer) Xcord;
					Ycord_Attack = (Integer) Ycord;
					
					break;
					}
				}
			
			for (int yOffset=1; yOffset<= z*2; yOffset++) {
				selectedBiotic = Biotic.getGrid()[Xcord][Ycord-=yOffset];
				if (selectedBiotic != null) {
					
					Xcord_Attack = (Integer) Xcord;
					Ycord_Attack = (Integer) Ycord;
					
					break;
					}
				}
			
			for (int xOffset=1; xOffset<= z*2; xOffset++) {
				selectedBiotic = Biotic.getGrid()[Xcord+=xOffset][Ycord];
				if (selectedBiotic != null) {
					
					Xcord_Attack = (Integer) Xcord;
					Ycord_Attack = (Integer) Ycord;
					
					break;
					}
				}
			
			for (int yOffset=1; yOffset<= z*2; yOffset++) {
				selectedBiotic = Biotic.getGrid()[Xcord][Ycord+=yOffset];
				if (selectedBiotic != null) {
					
					Xcord_Attack = (Integer) Xcord;
					Ycord_Attack = (Integer) Ycord;
					
					break;
					
				}
			}
		}
		
		
		return BioticsFound;
	}
	

	static public ArrayList<String> findAttributes
	(Biotic currentBiotic, Biotic selectedBiotic, int radiusFoundIn) {
		
		boolean NextTo = false;
		
		ArrayList<String> near = new ArrayList<String>();
		
		if (radiusFoundIn == 1) {
			NextTo=true;
			near.add("NEXT_TO");
		}
		else {
			near.add("NEAR_BY");
		}
		//	Near/NextTo Young 
			if (selectedBiotic.getInformation() <= currentBiotic.getInformation()) {
				near.add("YOUNG");
			}
		//	Near/NextTo Old
			else {
				near.add("OLD");
			}
		//	Near/NextTo Network
			if(currentBiotic.getNetwork().contains(selectedBiotic)) {
				near.add("NETWORKED");
			}
		//	Near/NextTo Past Creation
			if(currentBiotic.getOwner_UUID().equals(selectedBiotic.getOwner_UUID())) {
				near.add("PAST_CREATION");
			}
		//	Near/NextTo Stranger
			if (!(currentBiotic.getOwner_UUID().equals(selectedBiotic.getOwner_UUID())) 
			&& !(currentBiotic.getNetwork().contains(selectedBiotic))) {
				
				near.add("STRANGER");
			}
		//	Near/NextTo Replicate
			if (currentBiotic.getBioticUUID().equals(selectedBiotic.getBioticUUID())) {
				near.add("REPLICATE");
			}
		
		return near;
	}
}

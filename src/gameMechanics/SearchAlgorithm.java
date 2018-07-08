package gameMechanics;

import java.util.ArrayList;
import java.util.HashMap;


public class SearchAlgorithm {
	
	enum Attribute {
		NEXT_TO,
		NEAR_BY,
		YOUNG,
		OLD,
		NETWORKED,
		PAST_CREATION,
		STRANGER,
		REPLICATE
		
	}
	

	//Searches around biotic in radius
	static public HashMap<Biotic, Integer> search(int radius, Biotic biotic) {

		Biotic selectedBiotic;
		
		int[] currentCords = biotic.getCurrentCords();
		int Xcord = currentCords[0];
		int Ycord = currentCords[1];

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
			
			if (Found) {
				break;
			}
		}
		
		//Returns a HashMap
		//{BIOTIC_OBJECT : RADIUS_WHERE_OBJ_WAS_FOUND}
		return BioticsFound;
	}
	
	static public ArrayList <Integer[]> searchForEmpty(int radius, Biotic biotic) {

		Biotic selectedBiotic;
		
		int[] currentCords = biotic.getCurrentCords();
		int Xcord = currentCords[0];
		int Ycord = currentCords[1];

		ArrayList<Integer[]> SpacesFound = new ArrayList<Integer[]>();

		boolean Found = false;
		// runs a scan of layers around
		//biotic (z) until the height of the layers is equal to the radius
		for (int z=1; z<=radius; z++) {

			//starting point
			Xcord += z;
			Ycord += z;


			for (int xOffset=1; xOffset<= z*2; xOffset++) {
				selectedBiotic = Biotic.getGrid()[Xcord-xOffset][Ycord];
				if (selectedBiotic == null) {

					Integer[] cord = {Xcord - xOffset,Ycord};

					SpacesFound.add(cord);
					Found = true;
				}
			}

			for (int yOffset=1; yOffset<= z*2; yOffset++) {
				selectedBiotic = Biotic.getGrid()[Xcord][Ycord-yOffset];
				if (selectedBiotic != null) {

					Integer[] cord = {Xcord,Ycord-yOffset};

					SpacesFound.add(cord);
					Found = true;
				}
			}

			for (int xOffset=1; xOffset<= z*2; xOffset++) {
				selectedBiotic = Biotic.getGrid()[Xcord+xOffset][Ycord];
				if (selectedBiotic != null) {

					Integer[] cord = {Xcord + xOffset,Ycord};

					SpacesFound.add(cord);
					Found = true;
				}
			}

			for (int yOffset=1; yOffset<= z*2; yOffset++) {
				selectedBiotic = Biotic.getGrid()[Xcord][Ycord+yOffset];
				if (selectedBiotic != null) {

					Integer[] cord = {Xcord,Ycord + yOffset};

					SpacesFound.add(cord);
					Found = true;

				}
			}

			if (Found) {
				break;
			}
		}
		
		//Returns a ArrayList of Arrays, with cords of empty spaces.
		//{BIOTIC_OBJECT : RADIUS_WHERE_OBJ_WAS_FOUND}
		return SpacesFound;
	}
	
	

	static public ArrayList<Attribute> findAttributes
	(Biotic currentBiotic, Biotic selectedBiotic, int radiusFoundIn) {
		
		ArrayList<Attribute> near = new ArrayList<Attribute>();
		
		if (radiusFoundIn == 1) {
			near.add(Attribute.NEXT_TO);
		}
		else {
			near.add(Attribute.NEAR_BY);
		}
		//	Near/NextTo Young 
			if (selectedBiotic.getInformation() <= currentBiotic.getInformation()) {
				near.add(Attribute.YOUNG);
			}
		//	Near/NextTo Old
			else {
				near.add(Attribute.OLD);
			}
		//	Near/NextTo Network
			if(currentBiotic.getNetwork().contains(selectedBiotic)) {
				near.add(Attribute.NETWORKED);
			}
		//	Near/NextTo Past Creation
			if(currentBiotic.getOwner_UUID().equals(selectedBiotic.getOwner_UUID())) {
				near.add(Attribute.PAST_CREATION);
			}
		//	Near/NextTo Stranger
			if (!(currentBiotic.getOwner_UUID().equals(selectedBiotic.getOwner_UUID())) 
			&& !(currentBiotic.getNetwork().contains(selectedBiotic))) {
				
				near.add(Attribute.STRANGER);
			}
		//	Near/NextTo Replicate
			if (currentBiotic.getBioticUUID().equals(selectedBiotic.getBioticUUID())) {
				near.add(Attribute.REPLICATE);
			}
		
		return near;
	}
}

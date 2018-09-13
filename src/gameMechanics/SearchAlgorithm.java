package gameMechanics;

import java.util.ArrayList;
import java.util.HashMap;

import gameBoard.Board;


public class SearchAlgorithm {


	//Searches around biotic in radius
	static public HashMap<Biotic, Integer> search(int radius, Biotic biotic) {

		int[] currentCords = biotic.getCurrentCords();
		int Xoriginal = currentCords[0];
		int Yoriginal = currentCords[1];

		HashMap<Biotic,Integer> SpacesFound = new HashMap<Biotic,Integer>();

		boolean Found = false;
		// runs a scan of layers around
		//biotic (z) until the height of the layers is equal to the radius
		for (int z=1; z<=radius; z++) {




			int[] signs = {-1,1};
			for (int sign: signs ) {
				//starting point
				int Xcord = Xoriginal + (z * -sign);
				int Ycord = Yoriginal + (z * -sign);

				for(int xOffset = 0; xOffset <= z*2; xOffset++) {

					if (Xcord-xOffset >= 0 && Xcord+(xOffset*sign) < Board.getDimentionX() &&
							Ycord >= 0 && Ycord < Board.getDimentionY()) {

						if (Biotic.getGrid()[Xcord + (xOffset * sign)][Ycord] != null) {

							SpacesFound.put(Biotic.getGrid()[Xcord + (xOffset * sign)][Ycord],z);
							Found = true;
						}
					}
				}
				//starting point
				Xcord = Xoriginal + (z * sign);
				Ycord = Yoriginal + (z * -sign);

				for(int yOffset = 1; yOffset <= (z*2)-1; yOffset++) {

					if (Xcord >= 0 && Xcord < Board.getDimentionX() &&
							Ycord+(yOffset*sign) >= 0 && Ycord+(yOffset*sign) < Board.getDimentionY()) {

						if (Biotic.getGrid()[Xcord ][Ycord+ (yOffset * sign)] != null) {

							SpacesFound.put(Biotic.getGrid()[Xcord][Ycord + (yOffset * sign)],z);
							Found = true;
						}
					}
				}
			}

			if (Found) {
				break;
			}

		}

		return SpacesFound;
		//Returns a HashMap
		//{BIOTIC_OBJECT : RADIUS_WHERE_OBJ_WAS_FOUND}

	}

	static public ArrayList <int[]> searchForEmpty(int radius, Biotic biotic) {


		int[] currentCords = biotic.getCurrentCords();
		int Xoriginal = currentCords[0];
		int Yoriginal = currentCords[1];

		ArrayList<int[]> SpacesFound = new ArrayList<int[]>();

		boolean Found = false;
		// runs a scan of layers around
		//biotic (z) until the height of the layers is equal to the radius
		for (int z=1; z<=radius; z++) {




			int[] signs = {-1,1};
			for (int sign: signs ) {
				//starting point
				int Xcord = Xoriginal + (z * -sign);
				int Ycord = Yoriginal + (z * -sign);

				int xOffset = 0;
				for(xOffset = 0; xOffset <= z*2; xOffset++) {
					if (Xcord+(xOffset*sign) >= 0 && Xcord+(xOffset*sign) < Board.getDimentionX() &&
							Ycord >= 0 && Ycord < Board.getDimentionY()) {

						if (Biotic.getGrid()[Xcord + (xOffset * sign)][Ycord] == null) {

							int[] cord = {Xcord + (xOffset*sign),Ycord};

							SpacesFound.add(cord);
							Found = true;
						}
					}
				}
				//starting point
				Xcord = Xoriginal + (z * sign);
				Ycord = Yoriginal + (z * -sign);

				int yOffset = 0;

				for(yOffset = 1; yOffset <= (z*2)-1; yOffset++) {

					if (Xcord >= 0 && Xcord <= Board.getDimentionX() &&
							Ycord+(yOffset*sign) >= 0 && Ycord+(yOffset*sign) < Board.getDimentionY()) {

						if (Biotic.getGrid()[Xcord ][Ycord+ (yOffset * sign)] == null) {

							int[] cord = {Xcord, Ycord+ (yOffset*sign)};

							SpacesFound.add(cord);
							Found = true;
						}
					}
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



	static public ArrayList<String> findAttributes
	(Biotic currentBiotic, Biotic selectedBiotic, int radiusFoundIn) {

		ArrayList<String> near = new ArrayList<String>();

		if (radiusFoundIn <= 2) {
			near.add("next_to");
		}
		else {
			near.add("near_by");
		}
		//	Near/NextTo Young 
		if (selectedBiotic.getInformation() <= currentBiotic.getInformation()) {
			near.add("young");
		}
		//	Near/NextTo Old
		else {
			near.add("old");
		}
		//	Near/NextTo Network
		if(currentBiotic.getNetwork().contains(selectedBiotic)) {
			near.add("networked");
		}
		//	Near/NextTo Past Creation
		if(currentBiotic.getOwner_UUID().equals(selectedBiotic.getOwner_UUID())) {
			near.add("past_creation");
		}
		//	Near/NextTo Stranger
		if (!(currentBiotic.getOwner_UUID().equals(selectedBiotic.getOwner_UUID())) 
				&& !(currentBiotic.getNetwork().contains(selectedBiotic)
						&& selectedBiotic.getNetwork().contains(currentBiotic))) {
			near.add("stranger");
		}
		//	Near/NextTo Replicate
		if (currentBiotic.getBioticUUID().equals(selectedBiotic.getBioticUUID())) {
			near.add("replicate");
		}
		//	Near/NextTo SAME_COLOR
		if(selectedBiotic.getColor() == currentBiotic.getColor()) {
			near.add("same_color");
		}
		//	Near/NextTO DIFFERENT_COLOR
		else{
			near.add("different_color");
		}
		return near;

	}
}

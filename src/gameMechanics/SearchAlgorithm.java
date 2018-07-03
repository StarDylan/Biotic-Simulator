package gameMechanics;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchAlgorithm {
	
	private static int radius = 4;
	static Biotic selectedBiotic;

	public HashMap<Biotic, Integer> search(int radius, Biotic biotic) {
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
		
		
		return BioticsFound;
	}

	public ArrayList<String> findAttributes(Biotic currentBiotic, Biotic selectedBiotic, int radiusFoundIn) {
		
		boolean NextTo = false;
		
		ArrayList<String> near = new ArrayList<String>();
		
		if (radiusFoundIn == 1) {
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
		
		//	Near/NextTo Past Creation
		
		//	Near/NextTo Stranger
		
		//	Near/NextTo Replicate
		
		
		return null;
	}
}

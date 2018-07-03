package gameMechanics;

import java.util.HashMap;

public class SearchAlgorithm {
	
	private static int radius = 4;
	static Biotic selectedSpace;
	

	public HashMap<Biotic,int[]> search(int radius, Biotic biotic) {
		int[] currentCords = biotic.getCurrentCords();
		int Xcord = currentCords[0];
		int Ycord = currentCords[1];

		HashMap<Biotic,int[]> BioticsFound = new HashMap<Biotic,int[]>();
		
		boolean Found = false;
		// runs a scan of layers around
		//biotic (z) until the height of the layers is equal to the radius
		for (int z=1; z<=radius; z++) {
			
			//starting point
			Xcord += z;
			Ycord += z;
			
			
			for (int xOffset=1; xOffset<= z*2; xOffset++) {
				selectedSpace = Biotic.getGrid()[Xcord-=xOffset][Ycord];
				if (selectedSpace != null) {
					
					BioticsFound.put(selectedSpace, currentCords);
					Found = true;
					}
				}
			
			for (int yOffset=1; yOffset<= z*2; yOffset++) {
				selectedSpace = Biotic.getGrid()[Xcord][Ycord-=yOffset];
				if (selectedSpace != null) {
					
					BioticsFound.put(selectedSpace, currentCords);
					Found = true;
					}
				}
			
			for (int xOffset=1; xOffset<= z*2; xOffset++) {
				selectedSpace = Biotic.getGrid()[Xcord+=xOffset][Ycord];
				if (selectedSpace != null) {
					
					BioticsFound.put(selectedSpace, currentCords);
					Found = true;
					}
				}
			
			for (int yOffset=1; yOffset<= z*2; yOffset++) {
				selectedSpace = Biotic.getGrid()[Xcord][Ycord+=yOffset];
				if (selectedSpace != null) {
					
					BioticsFound.put(selectedSpace, currentCords);
					Found = true;
					
				}
			}
			
			if (Found) {
				break;
			}
		}
		
		
		return BioticsFound;
	}
}

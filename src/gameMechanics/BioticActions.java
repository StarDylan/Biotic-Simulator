package gameMechanics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import gameMechanics.Biotic.Network_Status;
import gameMechanics.SearchAlgorithm;

public class BioticActions {
	
	public static void Move (Biotic targetBiotic, int[] newCords) {
		
		
		int xCord = targetBiotic.getCurrentCords()[0];
		int yCord = targetBiotic.getCurrentCords()[1];
		
		Biotic[][] tempGrid = Biotic.getGrid();
		
		if ( tempGrid[newCords[0]][newCords[1]] != null) {
			
			System.out.println("Invalid Move, Target has Biotic in it");
		
		}
		else {
		
			tempGrid[xCord][yCord] = null;
			
			tempGrid[newCords[0]][newCords[1]] = targetBiotic;
			
			Biotic.setGrid(tempGrid);
			
			targetBiotic.setCurrentCords(newCords);
		}
		
	}
	
	public static void Replicate (Biotic targetBiotic) {
		
		int radius_of_placement = 2;
		
		//Check if Timer is at 0
		if(targetBiotic.getTimer().getReplicateTimer() == 0) {
			ArrayList<Integer[]> availableSpaces = 
				SearchAlgorithm.searchForEmpty(radius_of_placement, targetBiotic);
			
			//Chooses a Random Space
			int index = ((int) (Math.random() * ((availableSpaces.size()) + 1)));
			
			int xCord = availableSpaces.get(index)[0];
			int yCord = availableSpaces.get(index)[1];
			
			targetBiotic.clone(xCord, yCord);
		}
	}
	
	public static void Network (Biotic currentBiotic, Biotic targetBiotic) {
		
		//Tests if target can network
		if (targetBiotic.getNetworkable().equals(Network_Status.ABLE_TO)){
			currentBiotic.addNetwork(targetBiotic);
			currentBiotic.setNetworkable(Network_Status.ABLE_TO);
			targetBiotic.Update();
			}
		//Tests if you were prev identified as Networkable
		else if (currentBiotic.getNetworkable().equals(Network_Status.ABLE_TO)){
			currentBiotic.addNetwork(targetBiotic);
		}
		
		//If not, update other biotic
		else {
			currentBiotic.addNetwork(targetBiotic);
			currentBiotic.setNetworkable(Network_Status.ABLE_TO);
			targetBiotic.Update();
		}
		
	}
	
	public static void RunAway (Biotic currentBiotic) {
		
		int detectRadius = 20;
		
		HashMap<Biotic, Integer> Found = SearchAlgorithm.search(detectRadius, currentBiotic);
		
		ArrayList Keys = (ArrayList)Found.keySet();
		
		Biotic selectedBiotic = (Biotic) Keys.get(0);
		
		int[] cords1 = currentBiotic.getCurrentCords();
		int[] cords2 = selectedBiotic.getCurrentCords();
		
		int xCord1 = cords1[0];
		int yCord1 = cords1[1];
		
		int xCord2 = cords2[0];
		int yCord2 = cords2[1];
		
		int deltaX = xCord2 - xCord1;
		int deltaY = yCord2 - yCord2;
		
		int[] newCords = {};
		
		if (deltaY == 0) {
			if (deltaX < 0) {
				newCords[0] = xCord1 + 1;
				newCords[1] = yCord1;
			}
			else {
				newCords[0] = xCord1 - 1;
				newCords[1] = yCord1;
			}
		}
		if (deltaX == 0) {
			if (deltaY < 0) {
				newCords[0] = xCord1;
				newCords[1] = yCord1 + 1;
			}
			else {
				newCords[0] = xCord1;
				newCords[1] = yCord1 - 1;
			}
		}
		
		//Move Randomly if selected spot is filled
		if (Biotic.getGrid()[newCords[0]][newCords[1]] == null) {
			ArrayList<Integer[]> emptySpaces = SearchAlgorithm.searchForEmpty(1, currentBiotic);
			
			//Chooses a Random Space
			int index = ((int) (Math.random() * ((emptySpaces.size()) + 1)));
			
			newCords[0] = emptySpaces.get(index)[0];
			newCords[1] = emptySpaces.get(index)[1];
			
		}
		
		BioticActions.Move(currentBiotic, newCords);
	}
}

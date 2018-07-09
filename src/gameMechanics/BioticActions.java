package gameMechanics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import gameBoard.Board;
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

			//Moves Pointer from and to grid and sets the current cords to new Cordinates
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
			ArrayList<int[]> availableSpaces = 
					SearchAlgorithm.searchForEmpty(radius_of_placement, targetBiotic);

			System.out.println(availableSpaces.size());
			//Chooses a Random Space
			for (int index = 0; index < availableSpaces.size(); index++) {

				System.out.println("Index:  " + index);

				int xCord = availableSpaces.get(index)[0];
				int yCord = availableSpaces.get(index)[1];

				if (xCord >= 0 && xCord <= Board.getDimentionX()
						&& yCord >=0 && yCord <=Board.getDimentionY()) {
					if (Biotic.getGrid()[xCord][yCord] == null) {

						System.out.println("Cloned at " + xCord + ","+yCord);

						targetBiotic.clone(xCord, yCord);

						break;

					}
				}
			}
		}
	}

	public static void Network (Biotic currentBiotic, Biotic targetBiotic) {

		if (targetBiotic.getNetwork().contains(currentBiotic) && 
				currentBiotic.getNetwork().contains(targetBiotic)) {
			//Already Networked
		}
		else {

			//Step 2
			//Tests if target can network
			//If so, set self as Networkable and Update Target
			if (targetBiotic.getNetworkable().equals(Network_Status.ABLE_TO)){
				currentBiotic.addNetwork(targetBiotic);
				currentBiotic.setNetworkable(Network_Status.ABLE_TO);
				targetBiotic.Update();
			}
			//Step 3 - Finsihed
			//Tests if you were prev identified as Networkable
			else if (currentBiotic.getNetworkable().equals(Network_Status.ABLE_TO)){

				//If so add target as networked
				currentBiotic.addNetwork(targetBiotic);

				//Reset yourself and other for next Network Attempt
				currentBiotic.setNetworkable(Network_Status.NOT_TESTED);
				targetBiotic.setNetworkable(Network_Status.NOT_TESTED);
			}

			//Step 1
			//If other is not networkable and self is not networkable,
			else {
				//Update other biotic and set self as networkable

				currentBiotic.setNetworkable(Network_Status.ABLE_TO);
				targetBiotic.Update();
			}
		}

	}

	public static void RunAway (Biotic currentBiotic) {

		//
		int detectRadius = 20;

		//Gets HashMap of nearest Biotics
		HashMap<Biotic, Integer> Found = SearchAlgorithm.search(detectRadius, currentBiotic);

		//Sets cord2 to First Biotic Found
		Set<Biotic> Keys = Found.keySet();

		Iterator<Biotic> KeysIterator = Keys.iterator();

		int[] cords1 = currentBiotic.getCurrentCords();

		int xCord1 = cords1[0];
		int yCord1 = cords1[1];

		//Finds Farthest Away Tile
		int[] newCords = {xCord1, yCord1};

		if (KeysIterator.hasNext() == true) {

			Biotic selectedBiotic = KeysIterator.next();

			int[] cords2 = selectedBiotic.getCurrentCords();

			int xCord2 = cords2[0];
			int yCord2 = cords2[1];

			int deltaX = xCord2 - xCord1;
			int deltaY = yCord2 - yCord2;


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
		}

		//Move Randomly if selected spot is filled
		if (Biotic.getGrid()[newCords[0]][newCords[1]] != null) {
			ArrayList<int[]> emptySpaces = SearchAlgorithm.searchForEmpty(1, currentBiotic);
			if (emptySpaces.size() != 0) {
				//Chooses a Random Space
				int index = ((int) (Math.random() * ((emptySpaces.size()) + 1)));

				newCords[0] = emptySpaces.get(index)[0];
				newCords[1] = emptySpaces.get(index)[1];
			}
		}

		BioticActions.Move(currentBiotic, newCords);
	}

	public static void Follow(Biotic currentBiotic) {

		int detectRadius = 20;

		//Gets HashMap of nearest Biotics
		HashMap<Biotic, Integer> Found = SearchAlgorithm.search(detectRadius, currentBiotic);

		//Sets cord2 to First Biotic Found
		Set<Biotic> Keys = Found.keySet();

		Iterator<Biotic> KeysIterator = Keys.iterator();

		int[] cords1 = currentBiotic.getCurrentCords();

		int xCord1 = cords1[0];
		int yCord1 = cords1[1];


		//Finds Closest Tile
		int[] newCords = {xCord1, yCord1};

		if (KeysIterator.hasNext() == true) {

			Biotic selectedBiotic = KeysIterator.next();

			int[] cords2 = selectedBiotic.getCurrentCords();

			int xCord2 = cords2[0];
			int yCord2 = cords2[1];

			int deltaX = xCord2 - xCord1;
			int deltaY = yCord2 - yCord2;


			if (deltaY == 0) {
				if (deltaX < 0) {
					newCords[0] = xCord1 - 1;
					newCords[1] = yCord1;
				}
				else {
					newCords[0] = xCord1 + 1;
					newCords[1] = yCord1;
				}
			}
			if (deltaX == 0) {
				if (deltaY < 0) {
					newCords[0] = xCord1;
					newCords[1] = yCord1 - 1;
				}
				else {
					newCords[0] = xCord1;
					newCords[1] = yCord1 + 1;
				}
			}
		}


		//Move Randomly if selected spot is filled
		if (Biotic.getGrid()[newCords[0]][newCords[1]] != null) {
			ArrayList<int[]> emptySpaces = SearchAlgorithm.searchForEmpty(1, currentBiotic);
			if (emptySpaces.size() != 0) {
				//Chooses a Random Space
				int index = ((int) (Math.random() * ((emptySpaces.size()) + 1)));

				newCords[0] = emptySpaces.get(index)[0];
				newCords[1] = emptySpaces.get(index)[1];
			}
		}

		BioticActions.Move(currentBiotic, newCords);
	}

	public static void Hack(Biotic currentBiotic, Biotic targetBiotic) {
		targetBiotic.setProgram(currentBiotic.getProgram());

		currentBiotic.delete();	
	}

	public static void Infect(Biotic currentBiotic, Biotic targetBiotic) {
		ArrayList<Biotic> empty = new ArrayList<Biotic>();
		targetBiotic.setNetwork(empty);

		currentBiotic.delete();
	}

	public static void Wander(Biotic currentBiotic) {

		int[] newCords = 
			{currentBiotic.getCurrentCords()[0],
					currentBiotic.getCurrentCords()[1]};

		ArrayList<int[]> emptySpaces = SearchAlgorithm.searchForEmpty(1, currentBiotic);
		if (emptySpaces.size() != 0) {
			//Chooses a Random Space
			int index = ((int) (Math.random() * ((emptySpaces.size()) + 1)));

			newCords[0] = emptySpaces.get(index)[0];
			newCords[1] = emptySpaces.get(index)[1];

		}

		Move(currentBiotic,newCords);
	}
}



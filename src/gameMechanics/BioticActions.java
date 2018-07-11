package gameMechanics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import gameBoard.Board;
import gameMechanics.Biotic.Network_Status;
import gameMechanics.SearchAlgorithm;

public class BioticActions {

	public static boolean Move (Biotic targetBiotic, int[] newCords) {

		int xCord = targetBiotic.getCurrentCords()[0];
		int yCord = targetBiotic.getCurrentCords()[1];

		Biotic[][] tempGrid = Biotic.getGrid();
		if (newCords[0] >= 0 && newCords[0] <= Board.getDimentionX() &&
				newCords[1] >= 0 && newCords[1] < Board.getDimentionY()) {
			if ( tempGrid[newCords[0]][newCords[1]] != null) {

				//Target has BIotic already in it
				return false;

			}

			else {

				//Moves Pointer from and to grid and sets the current cords to new Cordinates
				tempGrid[xCord][yCord] = null;

				tempGrid[newCords[0]][newCords[1]] = targetBiotic;

				Biotic.setGrid(tempGrid);

				targetBiotic.setCurrentCords(newCords);
				return true;
			}

		}
		else {
			return false;
		}
	}

	public static boolean Replicate (Biotic targetBiotic) {

		int radius_of_placement = 1;

		//Check if Timer is at 0
		if(targetBiotic.getTimer().getReplicateTimer() == 0) {
			ArrayList<int[]> availableSpaces = 
					SearchAlgorithm.searchForEmpty(radius_of_placement, targetBiotic);
			//Chooses a Random Space
			for (int index = 0; index < availableSpaces.size(); index++) {

				int xCord = availableSpaces.get(index)[0];
				int yCord = availableSpaces.get(index)[1];

				if (xCord >= 0 && xCord <= Board.getDimentionX()
						&& yCord >=0 && yCord <=Board.getDimentionY()) {
					if (Biotic.getGrid()[xCord][yCord] == null) {
						targetBiotic.addInformationDeath(500); 
						targetBiotic.clone(xCord, yCord,targetBiotic.getColor());
						//.getTimer().addReplicateTimer(targetBiotic.getTimer().getResetValueReplicateTimer());
						targetBiotic.getTimer().addReplicateTimer(targetBiotic.getTimer().getResetValueReplicateTimer()*9999999);
						return true;

					}
				}
			}
			return false;
		}
		else {
			return false;
		}
	}

	public static boolean Network (Biotic currentBiotic, Biotic targetBiotic) {

		if (currentBiotic.getNetwork().contains(targetBiotic)) {
			//Already Networked
			return false;
		}
		else {

			currentBiotic.addNetwork(targetBiotic);
			return true;

		}
	}

	public static boolean RunAway (Biotic currentBiotic) {

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
		if (newCords[0] >= 0 && newCords[0] < Board.getDimentionX() &&
				newCords[1] >= 0 && newCords[1] < Board.getDimentionY()) {
			if (Biotic.getGrid()[newCords[0]][newCords[1]] != null) {
				ArrayList<int[]> emptySpaces = SearchAlgorithm.searchForEmpty(1, currentBiotic);
				if (emptySpaces.size() != 0) {
					//Chooses a Random Space
					Random rand = new Random();
					int index = rand.nextInt(emptySpaces.size());

					newCords[0] = emptySpaces.get(index)[0];
					newCords[1] = emptySpaces.get(index)[1];

				}
			}
		}


		if(BioticActions.Move(currentBiotic, newCords)) {
			return true;
		}
		else {
			return false;
		}
	}

	public static boolean Follow(Biotic currentBiotic) {

		int detectRadius = 10;


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
		if (newCords[0] >= 0 && newCords[0] <= Board.getDimentionX() &&
				newCords[1] >= 0 && newCords[1] < Board.getDimentionY()) {
			if (Biotic.getGrid()[newCords[0]][newCords[1]] != null) {
				ArrayList<int[]> emptySpaces = SearchAlgorithm.searchForEmpty(1, currentBiotic);
				if (emptySpaces.size() != 0) {
					//Chooses a Random Space
					Random rand = new Random();
					int index = rand.nextInt(emptySpaces.size());

					newCords[0] = emptySpaces.get(index)[0];
					newCords[1] = emptySpaces.get(index)[1];
				}
			}
		}

		if(BioticActions.Move(currentBiotic, newCords)) {
			return true;
		}
		else {
			return false;
		}
	}

	public static boolean Hack(Biotic currentBiotic, Biotic targetBiotic) {
		if (Biotic.getBIOTICS_INGAME().contains(targetBiotic)) {
			
			targetBiotic.setProgram(currentBiotic.getProgram());
			targetBiotic.setColor(currentBiotic.getColor());

			return true;
		}
		else {
			return false;
		}
	}

	public static boolean Infect(Biotic currentBiotic, Biotic targetBiotic) {
		ArrayList<Biotic> empty = new ArrayList<Biotic>();
		targetBiotic.setNetwork(empty);

		currentBiotic.delete();
		return true;
	}

	public static boolean Wander(Biotic currentBiotic) {

		int[] newCords = 
			{currentBiotic.getCurrentCords()[0],
					currentBiotic.getCurrentCords()[1]};

		//Move Randomly if selected spot is filled
		if (Biotic.getGrid()[newCords[0]][newCords[1]] != null) {
			ArrayList<int[]> emptySpaces = SearchAlgorithm.searchForEmpty(1, currentBiotic);
			if (emptySpaces.size() != 0) {
				//Chooses a Random Space
				Random rand = new Random();
				int index = rand.nextInt(emptySpaces.size());
				newCords[0] = emptySpaces.get(index)[0];
				newCords[1] = emptySpaces.get(index)[1];
			}
		}

		if(Move(currentBiotic,newCords)) {
			return true;
		}
		else {
			return false;
		}
	}

	public static boolean Delete(Biotic currentBiotic,Biotic targetBiotic) {
		if(currentBiotic.getTimer().getDeleteTimer() == 0) {
			targetBiotic.delete();
			return true;
		}
		else {
			return false;
		}
	}
}



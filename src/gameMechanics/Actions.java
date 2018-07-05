package gameMechanics;

public class Actions {

	int[] currentCords = biotic.getCurrentCords();
	int Xcord = currentCords[0];
	int Ycord = currentCords[1];
	
	int SearchAlgorithm.getXcord_Attack()	{
		return NextTo;
	}
	
	int SearchAlgorithm.getYcord_Attack()	{
		return NextTo;
	}
	
	boolean SearchAlgorithm.getNextTo()	{
		return NextTo;
	}
	
	
	// remember in order to execute commands, they must meet a requirement like the ones shown below:
	/** 
	 * Delete - 50 info
	 * Hack - more info than target ()
	 * Infect - death to owner cell, but not target (removes target biotic from network)
	 * Follow - none (until next to)
	 * Run_Away - none
	 * Self destruct - none
	 * Reproduce - none (self destructs, but makes two biotics, each with 0 information) (specified with a "when" statement)
	 * Network - none (gains 1 info every 10 seconds for every biotic connected)
	 * Wander - none
	 */
	
	public static void DELETE() {

			
			//if Xcord less than Xtarget, +=
			if(Xcord < Xcord_Attack) {
				for( Xcord = Xcord; Xcord <= Xcord_Attack; Xcord += 1) {}
				
			}
			//if Xcord more than Xtarget, -=
			else if (Xcord > Xcord_Attack) {
				for( Xcord = Xcord; Xcord >= Xcord_Attack; Xcord -= 1) {}
						
			}
			//if Ycord less than Ytarget +=
			if(Ycord < Ycord_Attack) {
				for( Ycord = Ycord; Ycord <= Ycord_Attack; Ycord += 1) {}
				
			}
			//if Ycord more than Xtarget, -=
			else if(Ycord > Ycord_Attack) {
				for( Ycord = Ycord; Ycord >= Ycord_Attack; Ycord -= 1) {}
						
			}
			if (NexTo=true) {
				Biotic.remove.getGrid()[Xcord_Attack][Ycord_Attack];
			}
				
					
		}
	

}

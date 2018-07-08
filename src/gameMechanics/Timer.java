package gameMechanics;

public class Timer {
	
	//Minimum Number of Ticks between Replicates
	private int ReplicateTimer = 140;
	private int NetworkTimer = 140;
	private int DeleteTimer = 140;
	
	private int ResetValueReplicateTimer;
	private int ResetValueNetworkTimer;
	private int ResetValueDeleteTimer;
	
	Timer(){
		ResetValueReplicateTimer = ReplicateTimer;
		ResetValueNetworkTimer = NetworkTimer;
		ResetValueDeleteTimer = DeleteTimer;
	}
	
	public int getReplicateTimer() {
		return this.ReplicateTimer;
	}
	
	public int getNetworkTimer() {
		return this.NetworkTimer;
	}
	
	public int DeleteTimer() {
		return this.DeleteTimer;
	}
	
	public void Update() {
		this.ReplicateTimer -= 1;
		this.NetworkTimer -= 1;
		this.DeleteTimer -= 1;
		
		if (ReplicateTimer == -1) {
			this.ReplicateTimer = ResetValueReplicateTimer;
		}
		
		if (NetworkTimer == -1) {
			this.NetworkTimer = ResetValueNetworkTimer;
		}
		
		if (DeleteTimer == -1) {
			this.DeleteTimer = ResetValueDeleteTimer;
		}
		
		
	}
}

package gameMechanics;

public class Timer {
	
	//Minimum Number of Ticks between Replicates
	private int ReplicateTimer = 200;
	private int NetworkTimer = 400;
	private int DeleteTimer = 0;
	
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
	public void addReplicateTimer(int time) {
		this.ResetValueReplicateTimer += time;
	}
	
	public int getNetworkTimer() {
		return this.NetworkTimer;
	}
	
	public int getDeleteTimer() {
		return this.DeleteTimer;
	}
	public int getResetValueReplicateTimer() {
		return ResetValueReplicateTimer;
	}
	
	public void Update() {
		this.ReplicateTimer -= 1;
		this.NetworkTimer -= 1;
		this.DeleteTimer -= 0;
		
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

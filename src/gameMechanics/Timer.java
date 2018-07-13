package gameMechanics;

public class Timer {
	
	//Minimum Number of Ticks between Replicates
	private int ReplicateTimer = 200;
	private int DeleteTimer = 3;
	private int HackTimer = 4;
	private Biotic Owner;
	
	private int ResetValueReplicateTimer;
	private int ResetValueDeleteTimer;
	private int ResetValueHackTimer;
	
	Timer(Biotic biotic){
		ResetValueReplicateTimer = ReplicateTimer;
		ResetValueDeleteTimer = DeleteTimer;
		ResetValueHackTimer = HackTimer;
		Owner = biotic;
	}
	
	public int getReplicateTimer() {
		return this.ReplicateTimer;
	}
	public void addReplicateTimer(int time) {
		this.ResetValueReplicateTimer += time;
	}
	
	public int getHackTimer() {
		return this.HackTimer;
	}
	
	public int getDeleteTimer() {
		return this.DeleteTimer;
	}
	public int getResetValueReplicateTimer() {
		return ResetValueReplicateTimer;
	}
	public void setResetValueReplicateTimer(int newTime) {
		ResetValueReplicateTimer = newTime;
	}
	
	public void Update() {
		//Network Buff; Increases Replicating by 2 fold
		if (Owner.getNetwork().size() > 3) {
			this.ReplicateTimer -= 3;
		}
		else {
			this.ReplicateTimer -= 1;
		}
		if (Owner.getNetwork().size() > 10) {
			this.DeleteTimer -= 2;
			this.HackTimer -= 2;
		}
		else {
			this.DeleteTimer -= 1;
			this.HackTimer -= 1;
		}

		
		if (ReplicateTimer <= -1) {
			
			this.ReplicateTimer = ResetValueReplicateTimer;
		}
		
		if (DeleteTimer <= -1) {
			this.DeleteTimer = ResetValueDeleteTimer;
		}
		if(HackTimer <= -1) {
			this.HackTimer = this.ResetValueHackTimer;
		}
		
		
	}
}

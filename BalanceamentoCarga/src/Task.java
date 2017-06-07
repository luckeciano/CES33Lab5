
public class Task {
	private static int taskID = 0;
	private int cpuID;
	private int creationTime;
	private int remainingTime;
	private int myID;
	private int taskRate;
	
	public Task (int ID, int cTime, int eTime, int rate) {
		myID = taskID++;
		cpuID = ID;
		creationTime = cTime;
		remainingTime = eTime;
		taskRate = rate;
	}
	public void execute() {
		remainingTime--;
	}
	public int getRemainingTime() {
		return remainingTime;
	}
	public int getTaskRate() {
		return taskRate;
	}

}


public class Task {
	public static int taskID = 0;
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
		System.out.println("New Task - ID: " + myID + "; cpuID: " + cpuID + 
				"; creationTime: " + creationTime + "; executionTime: " + remainingTime +
				"; cpu use: " + taskRate);
	}
	public void execute(CPU cpu) {
		System.out.println("Executing " + this.getID()+ " in CPU " + cpu.getID() );
		System.out.println("Remaining Time: " + remainingTime);
		remainingTime--;
	}
	public int getRemainingTime() {
		return remainingTime;
	}
	public int getTaskRate() {
		return taskRate;
	}
	public int getID() {
		// TODO Auto-generated method stub
		return myID;
	}

}

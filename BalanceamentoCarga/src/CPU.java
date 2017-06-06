import java.util.ArrayList;

public class CPU {
	private int nProcess;
	private float rateCPU;
	private float processRatio;
	private ArrayList<Task> runningTasks;
	private TaskGenerator taskGenerator;
	public CPU(int nProc, float cpuUse, float processRate) {
		nProcess = nProc;
		rateCPU = cpuUse;
		processRatio = processRate;
	}
	
	public void addTaskGenerator (int TMT, int Amount, int nProc, String type) {
		TaskFactory taskFactory = new TaskFactory();
		taskGenerator = TaskFactory.createTaskGenerator("intense", TMT, Amount, nProc);
	}

}

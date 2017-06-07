import java.util.ArrayList;

public class CPU {
	private static int ID = 0;
	private int nProcess;
	private int cpuID;
	private int timeForNewTask = 0;
	private int rateCPU;
	private float processRatio;
	private ArrayList<Task> runningTasks;
	private TaskGenerator taskGenerator;
	private SurveyStrategy strategy;
	public CPU(int nProc, int cpuUse, float processRate) {
		cpuID = ID++;
		nProcess = nProc;
		rateCPU = cpuUse;
		processRatio = processRate;
		runningTasks = new ArrayList<Task>();
	}
	
	public void addSurveyStrategy (String strategyType) {
		if (strategyType == "emissor")
			strategy = new EmissorStrategy();
		else if (strategyType == "receptor")
			strategy = new ReceptorStrategy();
	}
	public void addTaskGenerator (int TMT, int Amount, int nProc, String generatorType) {
		taskGenerator = TaskFactory.createTaskGenerator(generatorType, TMT, Amount, nProc);
	}
	
	public void generateTask(int realTime) { 
		Task task = taskGenerator.generateTask(cpuID, realTime, timeForNewTask);
		if (task == null) {
			timeForNewTask ++;
			update();
			return;
		} else {
			timeForNewTask = 0;
			if (!isBusy()) {
				runningTasks.add(task);
			} else strategy.doSurvey();
			
		}
		
	}

	private void survey() {
	
		
	}

	private boolean isBusy() {
		
		return false;
	}

	public void update() {
		
		ArrayList<Integer> killProcess = new ArrayList<Integer>();
		int rateNow = 0;
		for (int i = 0; i < runningTasks.size() && rateNow < rateCPU; i++) {
			rateNow += runningTasks.get(i).getTaskRate();
			runningTasks.get(i).execute();
			if (runningTasks.get(i).getRemainingTime() == 0) {
				killProcess.add(i);
			}
		}
		for (int process : killProcess) {
			runningTasks.remove(process);
		}
		timeForNewTask++;
		
	}

}

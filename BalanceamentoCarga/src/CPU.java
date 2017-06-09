import java.util.ArrayList;
/***
 * 
 * Classe CPU - Abstração que simula uma CPU
 *
 */
public class CPU {
	private static final int CPU_RATE_THRESHOLD = 80;
	private static final int PROCESS_RATIO_THRESHOLD = 40;
	private static final int CPU_N_PROCESS_THRESHOLD = 20;
	private int receivedRequest = 0;
	private int transmitedRequest = 0;
	private static int ID = 0;
	private int nProcess = 0;
	private int cpuID;
	private int timeForNewTask = 0;
	private int rateCPU;
	private int processRatio;
	private int surveyRate = 20;
	private ArrayList<Task> runningTasks;
	private TaskGenerator taskGenerator;
	private SurveyStrategy strategy;
	public CPU(int nProc, int cpuUse, int processRate) {
		cpuID = ID++;
		nProcess = nProc;
		rateCPU = cpuUse;
		processRatio = processRate;
		runningTasks = new ArrayList<Task>();
	}
	//Escolher qual heurística escolher para balanceamento
	public void addSurveyStrategy (String strategyType) {
		if (strategyType == "emissor")
			strategy = new EmissorStrategy();
		else if (strategyType == "receptor")
			strategy = new ReceptorStrategy();
	}
	// Criação do gerador de tarefas do tipo especificado
	public void addTaskGenerator (int TMT, int Amount, int nProc, String generatorType) {
		taskGenerator = TaskFactory.createTaskGenerator(generatorType, TMT, Amount, nProc);
	}
	
	public void generateTask(MultiCPUSystem system, int realTime) { 
		Task task = taskGenerator.generateTask(cpuID, realTime, timeForNewTask);
		boolean inSurvey = false;
		if (task == null) {
			return;
		} else {
			timeForNewTask = 0;
			if (!isBusy(this)) {
				pullTask(task);
			} else {
				System.out.println("CPU " + this.getID() + " is busy. Starting survey...");
				strategy.doSurvey(this, system, task);
				inSurvey = true;
			}
			
		}
		update(inSurvey);
		
	}

	public boolean isBusy(CPU requestFromCPU) {
		if (requestFromCPU != this) {
			requestFromCPU.incrementRequests();
			receivedRequest++;
		}
		int rateNow = 0;
		for (Task task : runningTasks) {
			rateNow += task.getTaskRate();
		}
		
		if (rateNow > CPU_RATE_THRESHOLD) return true;
		
		if (processRatio > PROCESS_RATIO_THRESHOLD) return true;
		
		if (runningTasks.size() > CPU_N_PROCESS_THRESHOLD) return true;
		
		return false;
	}

	private void incrementRequests() {
		transmitedRequest++;
		
	}

	public void update(boolean inSurvey) {
		
		ArrayList<Integer> killProcess = new ArrayList<Integer>();
		int rateNow = 0;
		if (inSurvey) rateNow = surveyRate;
		for (int i = 0; i < runningTasks.size() && rateNow < rateCPU; i++) {
			rateNow += runningTasks.get(i).getTaskRate();
			runningTasks.get(i).execute(this);
			if (runningTasks.get(i).getRemainingTime() <= 0) {
				System.out.println("Process " + runningTasks.get(i).getID() + " from CPU "
						+ this.getID() + " is done. Killing process...");
				runningTasks.remove(runningTasks.get(i));
			}
		}
		timeForNewTask++;
		nProcess = runningTasks.size();
		
	}
	
	public void updateProcessRatio(int totalProcess) {
		if (totalProcess != 0)
			processRatio = (100*runningTasks.size())/totalProcess;
	}

	public int getNumberOfTasks() {
		return nProcess;
	}

	public int getID() {
		// TODO Auto-generated method stub
		return cpuID;
	}

	public void pullTask(Task task) {
		System.out.println("Pulling task " + task.getID() + " on CPU " + this.getID());
		runningTasks.add(task);
		
	}

	public int getReceivedRequests() {
		return receivedRequest;
	}
	public int getTransmitedRequests() {
		return transmitedRequest;
	}

}

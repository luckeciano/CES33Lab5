package Extra;

import java.util.Random;

public class CentralCPU extends CPU {

	public CentralCPU(int nProc, int cpuUse, int processRate) {
		super(nProc, cpuUse, processRate);
	}
	
	public void manageTasks(MultiCPUSystem system) {
		
		for (int i = 0; i < system.getSystem().size(); i++) {
			if (runningTasks.size() <= 0) break;
			CPU thisCPU = system.getCPU(i);
			if (!thisCPU.isBusy(system.getCentralCPU())) {
				Random random = new Random();
				int randomTaskID = random.nextInt(runningTasks.size());
				Task randomTask = runningTasks.get(randomTaskID);
				runningTasks.remove(randomTask);
				thisCPU.pullTask(randomTask);
				incrementTransmitedRequests();
				thisCPU.incrementReceivedRequests();
				System.out.println("Task " + randomTask.getID() + " delegated to " + thisCPU.getID());
			}
		}
		
	}

}

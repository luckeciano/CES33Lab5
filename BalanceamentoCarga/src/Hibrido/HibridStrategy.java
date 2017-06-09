package Hibrido;

import java.util.Random;

import Hibrido.CPU;
import Hibrido.Task;

public class HibridStrategy {


	private static final int RETRY = 5;

	public void doEmissorSurvey(CPU thisCPU, MultiCPUSystem system, Task task) {
		for (int i = 0; i < RETRY; i++ ) {
			Random random = new Random();
			int randomCPU = random.nextInt(system.getSystem().size());
			CPU newCPU = system.getCPU(randomCPU);
			if (newCPU.getID() != thisCPU.getID()) {
				System.out.println("Request from CPU " + thisCPU.getID() + 
						" to CPU " + newCPU.getID());
							
				if (!newCPU.isBusy(thisCPU)) {
					System.out.println("Task" + task.getID() + "was transfered from CPU "
							+ thisCPU.getID() + "to CPU " + newCPU.getID());
					newCPU.pullTask(task);
					return;
				}
				
			}
		}
		thisCPU.pullTask(task);
		
	}

	public void doReceptorSurvey(CPU thisCPU, MultiCPUSystem system) {
		for (int i = 0; i < RETRY; i++ ) {
			Random random = new Random();
			int randomCPU = random.nextInt(system.getSystem().size());
			CPU newCPU = system.getCPU(randomCPU);
			if (newCPU.getID() != thisCPU.getID()) {
				System.out.println("Request from CPU " + thisCPU.getID() + 
						" to CPU " + newCPU.getID());
							
				if (!newCPU.isFree(thisCPU)) {
					Task pullTask = newCPU.takeRandomTask();
					System.out.println("Task" + pullTask.getID() + "was transfered from CPU "
							+ newCPU.getID() + "to CPU " + thisCPU.getID());
					thisCPU.pullTask(pullTask);
					return;
				}
				
			}
		}
		
	}

}

package Receptor;

import java.util.Random;

public class ReceptorStrategy implements SurveyStrategy {


	private static final int RETRY = 5;

	@Override
	public void doSurvey(CPU thisCPU, MultiCPUSystem system, Task task) {
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

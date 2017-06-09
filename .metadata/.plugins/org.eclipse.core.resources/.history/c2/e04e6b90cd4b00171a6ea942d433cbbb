import java.util.ArrayList;

public class Main {
	private static int realTime = 0;
	private static int N_CPUS = 10;
	private static int TMT = 1;
	private static int Amount = 1;
	private static int nProc = 1;
	
	public static void main (String [] args) {
		
		MultiCPUSystem system = new MultiCPUSystem();
		for (int i = 0; i < N_CPUS; i++) {
			CPU cpu = new CPU(0, 100, 0);
			cpu.addTaskGenerator(TMT, Amount, nProc, "intense");
			system.addCPU(cpu);
		}
		
		while(true) {
			
			//update the state of old tasks
			for (int i = 0; i < N_CPUS; i++) {
				system.getCPU(i).update();
				
			}
			realTime++;
			//generate new tasks
			for (int i = 0; i < N_CPUS; i++) {
				system.getCPU(i).generateTask(realTime);
			}
			realTime++;
			
		}
	}
}

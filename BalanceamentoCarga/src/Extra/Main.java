package Extra;

public class Main {
	private static final int N_CYCLES = 1000;
	private static int realTime = 0;
	private static int N_CPUS = 10;
	private static int TMT = 5000;
	private static int Amount = 1000;
	private static int nProc = 1;
	
	public static void main (String [] args) {
		
		MultiCPUSystem system = new MultiCPUSystem();
		for (int i = 0; i < N_CPUS; i++) {
			CPU cpu = new CPU(0, 100, 0);
			cpu.addTaskGenerator(TMT, Amount, nProc, "intense");
			system.addCPU(cpu);
		}
		system.addCentralCPU(new CentralCPU(0, 100, 0));
		int cycles = 0;
		while(cycles < N_CYCLES) {
			cycles++;
			//update the state of old tasks
			int totalTasks = 0;
			for (int i = 0; i < N_CPUS; i++) {
				system.getCPU(i).update(false, system);
				totalTasks += system.getCPU(i).getNumberOfTasks();
				
			}
			
			for (int i = 0; i < N_CPUS; i++) {
				system.getCPU(i).updateProcessRatio(totalTasks);
			}
			realTime++;
			//generate new tasks
			for (int i = 0; i < N_CPUS; i++) {
				system.getCPU(i).generateTask(system, realTime);
			}
			
			//distribute tasks
			system.getCentralCPU().manageTasks(system);
			System.out.println("Number of tasks on Central CPU: " + system.getCentralCPU().getNumberOfTasks());
			realTime++;
			
		}
		System.out.println("TOTAL NUMBER OF REQUESTS FROM SURVEYS: ");
		for (int i = 0; i < N_CPUS; i++) {
			System.out.println("RECEIVED: " + system.getCPU(i).getReceivedRequests());
			System.out.println("TRANSMITED: " + system.getCPU(i).getTransmitedRequests());
			
		}
	}
}

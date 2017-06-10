package Hibrido;

public class Main {
	private static final int N_CYCLES = 10000;
	private static int realTime = 0;
	private static int N_CPUS = 10;
	private static int TMT = 5000;
	private static int Amount = 1000;
	private static int nProc = 1;
	
	public static void main (String [] args) {
		double cpuAvgUse[] = new double[N_CPUS];
		MultiCPUSystem system = new MultiCPUSystem();
		for (int i = 0; i < N_CPUS; i++) {
			CPU cpu = new CPU(0, 100, 0);
			cpu.addTaskGenerator(TMT, Amount, nProc, "intense");
			cpu.addSurveyStrategy("receptor");
			system.addCPU(cpu);
		}
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
			realTime++;
			for (int i = 0; i < N_CPUS; i++) {
				cpuAvgUse[i] = (cpuAvgUse[i]*(cycles-1)+system.getCPU(i).getRate())/cycles;
			}
			System.out.println(cycles+" CICLOS <<<--------");
		}
		System.out.println("AVG CPU USAGE:");
		double total = 0;
		for (int i = 0; i < N_CPUS; i++) {
			System.out.println("CPU #"+i+": "+ cpuAvgUse[i]);
			total = total + cpuAvgUse[i];
		}
		System.out.println("AVERAGE: "+total/N_CPUS);
		System.out.println("TASKS EXECUTED: "+Task.taskID);
		System.out.println("TOTAL NUMBER OF REQUESTS FROM SURVEYS: \nRECEIVED\tTRANSMITED");
		for (int i = 0; i < N_CPUS; i++) {
			System.out.println(system.getCPU(i).getReceivedRequests()+"\t"+ system.getCPU(i).getTransmitedRequests());	
		}
	}
}


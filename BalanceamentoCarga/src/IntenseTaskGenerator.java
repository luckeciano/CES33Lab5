import java.util.Random;

public class IntenseTaskGenerator implements TaskGenerator {
	private int TMT;
	private int AMOUNT;
	private int nProcessors;
	private static int time = 0;
	private static int meanTime = 5000;
	private static int stdTime = 500;
	private static int upperBoundRate = 25;
	private static int lowerBoundRate = 5;
	
	public IntenseTaskGenerator (int tmt, int amount, int nProc) {
		TMT = tmt;
		meanTime = tmt;
		stdTime = (int)0.1*tmt;
		AMOUNT = amount;
		nProcessors = nProc;
	}
	public Task generateTask(int cpuID, int startTime, int timeForNewTask) {
		Random random = new Random();
		int randomTime = (int) (random.nextGaussian()*stdTime + meanTime);
		int randomRate = random.nextInt(upperBoundRate - lowerBoundRate) + lowerBoundRate;
		if (timeForNewTask >= TMT/AMOUNT) {
			Task task = new Task(cpuID, startTime, randomTime, randomRate);
			return task;
		} return null;
		
	}

}


public class IntenseTaskGenerator implements TaskGenerator {
	private int TMT;
	private int AMOUNT;
	private int nProcessors;
	private static int time = 0;
	
	public IntenseTaskGenerator (int tmt, int amount, int nProc) {
		TMT = tmt;
		AMOUNT = amount;
		nProcessors = nProc;
	}
	public Task generateTask(int cpuID, int startTime, int eTime) {
		
	}

}

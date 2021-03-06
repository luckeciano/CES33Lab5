package Extra;

public class TaskFactory {
	public static TaskGenerator createTaskGenerator (String type, int TMT, int AMOUNT, int N) {
		if (type == "intense") {
			return new IntenseTaskGenerator(TMT, AMOUNT, N);
		}
		else if (type == "light") {
			return new LightTaskGenerator(TMT, AMOUNT, N);
		}
		return null;
	}
}

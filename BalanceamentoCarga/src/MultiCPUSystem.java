import java.util.ArrayList;

public class MultiCPUSystem {
	private static ArrayList<CPU> system = new ArrayList<CPU>();
	
	public void addCPU (CPU cpu) {
		system.add(cpu);
	}

	public CPU getCPU(int i) {
		// TODO Auto-generated method stub
		return system.get(i);
	}
	public ArrayList<CPU> getSystem() {
		return system;
	}

}

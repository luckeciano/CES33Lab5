package Extra;
import java.util.ArrayList;

public class MultiCPUSystem {
	private CentralCPU centralCPU = null;
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

	public void addCentralCPU(CentralCPU cCPU) {
		centralCPU = cCPU;
		
	}

	public CentralCPU getCentralCPU() {
		// TODO Auto-generated method stub
		return centralCPU;
	}

}

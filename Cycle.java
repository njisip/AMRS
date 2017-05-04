import java.util.*;

public class Cycle{
	
	private HashMap<Integer,ArrayList<String>> instructions = new HashMap<Integer,ArrayList<String>>();
	private int clockCycle;
	private int[] stages = new int[5];
	private int currInstr;

	public Cycle(HashMap<Integer,ArrayList<String>> instructions){
		this.instructions = instructions;
		this.currInstr = 1;
		this.clockCycle = 0;
	}
	public void updateClock() {
		this.clockCycle++;
	}
	public int getClockCycles() {
		return this.clockCycle;
	}
	public void updateStages() {
		
	}
}
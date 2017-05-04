import java.util.*;

public class Cycle{
	
	private HashMap<Integer,ArrayList<String>> instructions = new HashMap<Integer,ArrayList<String>>();
	private int clockCycle;
	private ArrayList<Integer> stages = new ArrayList<Integer>();
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
	//check if instr exists in list
	public boolean checkInstr() {

	}
	public void updateStages() {
		 	
	}
}
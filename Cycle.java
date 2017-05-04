import java.util.*;

public class Cycle{
	
	private HashMap<Integer,ArrayList<String>> instructions = new HashMap<Integer,ArrayList<String>>();
	private ArrayList<Integer> stages = new ArrayList<Integer>();
	private int clockCycle;
	private int currInstr;

	public Cycle(HashMap<Integer,ArrayList<String>> instructions){
		this.instructions = instructions;
		this.clockCycle = 0;
		this.currInstr = 1;
		
		for (int i = 0; i < 5; i++) {
		    this.stages.add(-1);
		}
	}
	public void updateClock() {
		this.clockCycle++;
	}
	public int getClockCycles() {
		return this.clockCycle;
	}
	
	public boolean checkInstr(int instr) {
        if (this.stages.contains(instr)) {
            return true;
        }
        return false;
	}
	public boolean isEmpty() {
	    for (int i = 0; i < 5; i++) {
	        if (this.stages.get(i) != 0) {
	            return false;
	        }
	    }
	    
	    return true;
	}
	//without dependencies
	public void updateStages() {
		while (this.isEmpty() == false) {
    		this.stages.remove(4);
    		if (this.currInstr <= instructions.size()){
    		    this.stages.add(0, this.currInstr);
    		    this.currInstr++;
    		}
    		else {
    		    this.stages.add(0, 0);
    		}
    		if (this.isEmpty() == true) {
    		    break;
    		}
    		updateClock();
            System.out.println("Clock Cycles: " + getClockCycles());
            System.out.println(this.stages);
		}
	}
}
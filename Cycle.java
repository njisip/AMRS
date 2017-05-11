import java.util.*;

public class Cycle{
	
	private HashMap<Integer,ArrayList<String>> instructions = new HashMap<Integer,ArrayList<String>>();
	private ArrayList<Integer> stages = new ArrayList<Integer>();
	private InstructionSet set = new InstructionSet();
	private int[][] depMatrix;
	private int clockCycle;
	private int currInstr;

	public Cycle(Parser parser, HazardChecker hchecker){
		this.instructions = parser.getInstructions();
		this.depMatrix = hchecker.getDepMatrix();
		this.clockCycle = 0;
		this.currInstr = 1;
		
		for (int i = 0; i < 5; i++) {
		    this.stages.add(-1);
		}
		updateStages();
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
	public boolean isInPipeline(int instNum) {
		for (int i=0; i<5; i++) {
			if (instNum == this.stages.get(i))
				return true;
		}
		return false;
	}

	//without dependencies
	public void updateStages() {
		int instrInWB; 		
		
		while (this.isEmpty() == false) {
    		this.stages.remove(4);
    		if (this.currInstr <= instructions.size()){
    		    this.stages.add(0, this.currInstr);
    		    this.currInstr++;
    		}
    		else {
    		    this.stages.add(0, 0);
    		}
    		if (this.isEmpty()) {
    		    break;
    		}
    		
    		//writeback stage
    		updateClock();
            System.out.println("Clock Cycles: " + getClockCycles());
            System.out.println(this.stages);
            if (this.stages.get(4) != -1) {
    			instrInWB = this.stages.get(4);
    			updateRegisters(instrInWB);
    		}
		}
	}

	public void updateRegisters(int tempInstr) {
		ArrayList<String> instruction = this.instructions.get(tempInstr);
		System.out.println(instruction.get(0));
		String inst;

		inst = instruction.get(0);

		switch(inst) {
			case("LOAD"):
						set.load(instruction.get(1),instruction.get(2));
						for (int k = 0; k < 32; k++) {
							System.out.print(set.getRegisters()[k] + " ");
						}
						System.out.println("\n\n");
						break;
			case("ADD"):
						set.add(instruction.get(1),instruction.get(2));
						for (int k = 0; k < 32; k++) {
							System.out.print(set.getRegisters()[k] + " ");
						}
						System.out.println("\n\n");
						break;
			case("SUB"):
						set.sub(instruction.get(1),instruction.get(2));
						for (int k = 0; k < 32; k++) {
							System.out.print(set.getRegisters()[k] + " ");
						}
						System.out.println("\n\n");
						break;
			case("CMP"):
						set.compare(instruction.get(1),instruction.get(2));
						for (int k = 0; k < 32; k++) {
							System.out.print(set.getRegisters()[k] + " ");
						}
						System.out.println("\n\n");
						break;
		}
	}
}
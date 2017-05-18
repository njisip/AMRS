import java.lang.String;
import java.util.*;

public class HazardChecker {

	private HashMap<Integer,ArrayList<String>> instructions;
	private int[][] depMatrix;
	private int noOfInst;
	
	//constants
	private static final int NO_HAZARD = 0;
	private static final int RAW = -1;
	private static final int WAR = -2;
	private static final int WAW = -3;
	private static final int OP1 = 1;
	private static final int OP2 = 2;

	//CONSTRUCTOR
	public HazardChecker(HashMap<Integer,ArrayList<String>> instructions) {
		this.noOfInst = instructions.size();
		this.instructions = instructions;
		this.depMatrix = new int[noOfInst][noOfInst];
		buildDepMatrix();
	}

	//BUILDING MATRIX WITH THE VALUES RAW, WAW, WAR
	public void buildDepMatrix() {
		for (int i=1; i<=this.noOfInst; i++) {	//iterates over all the instruction
			for (int j=i+1; j<=noOfInst; j++) {			//iterates over all the instructions below the current instr
				//WAW case
				if(this.instructions.get(i).get(OP1).equals(instructions.get(j).get(OP1)))
					this.depMatrix[i-1][j-1] = this.depMatrix[j-1][i-1] = WAW;
				//RAW case
				else if(this.instructions.get(i).get(OP1).equals(instructions.get(j).get(OP2)))
					this.depMatrix[i-1][j-1] = this.depMatrix[j-1][i-1] = RAW;
				//WAR case
				else if (this.instructions.get(i).get(OP2).equals(instructions.get(j).get(OP1)))
					this.depMatrix[i-1][j-1] = this.depMatrix[j-1][i-1] = WAR;
			}
		}
	}

	public int checkTwoInsts(int inst1, int inst2) {
		//isnt1 < inst2
		return this.depMatrix[inst1][inst2];
	}

	public int[][] getDepMatrix() {
		return this.depMatrix;
	}

}
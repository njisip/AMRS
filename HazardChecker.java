import java.lang.String;
import java.util.*;

public class HazardChecker {

	private HashMap<Integer,ArrayList<String>> instructions;
	private int[][] depMatrix;
	private int size;
	private static final int NO_HAZARD = 0;
	private static final int RAW = -1;
	private static final int WAR = -2;
	private static final int WAW = -3;
	private static final int OP1 = 1;
	private static final int OP2 = 2;


	public HazardChecker(HashMap<Integer,ArrayList<String>> instructions) {
		this.size = instructions.size();
		this.instructions = instructions;
		this.depMatrix = new int[size][size];
		buildDepArray();
	}

	public void buildDepArray() {
		for (int i=1; i<=this.size; i++) {	//iterates over all the instruction
			for (int j=i+1; j<=size; j++) {			//iterates over all the instructions below the current instr
				if(this.instructions.get(i).get(OP1).equals(instructions.get(j).get(OP1)))
					this.depMatrix[i-1][j-1] = WAW;
				else if(this.instructions.get(i).get(OP1).equals(instructions.get(j).get(OP2)))
					this.depMatrix[i-1][j-1] = RAW;
				else if (this.instructions.get(i).get(OP2).equals(instructions.get(j).get(OP1)))
					this.depMatrix[i-1][j-1] = WAR;
			}
		}

		for (int k = 0; k < this.size; k++) {
			for (int l = 0; l < this.size; l++) {
				System.out.print(depMatrix[k][l] + "\t");
			}
			System.out.println("");
		}
	}

	public int checkTwoInstructions(int inst1, int inst2) {
		//isnt1 < inst2
		return this.depMatrix[inst1][inst2];
	}

	public int[][] getDepMatrix() {
		return this.depMatrix;
	}

}
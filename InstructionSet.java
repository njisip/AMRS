import java.lang.String;
import java.lang.Integer;

public class InstructionSet{
	private int[] registers; 
	private SyntaxChecker syntaxChecker;

	public InstructionSet(int[] registers, SyntaxChecker syntaxChecker){
		this.registers = registers;
		this.syntaxChecker = syntaxChecker;
	}

	public void load(String regDest, int immed){
		int indexDest;

		//Parse registers to get index
		indexDest = Integer.parseInt(regDest.substring(1));

		//Change OF or not AND put to the Dest Register
		if (immed > 99){
			this.syntaxChecker.setOF(1);
			this.syntaxChecker.setRegister(indexDest, 99);
		} else if (immed < -99) {
			this.syntaxChecker.setOF(1);
			this.syntaxChecker.setRegister(indexDest, -99);
		} else {
			this.syntaxChecker.setOF(0);
			this.syntaxChecker.setRegister(indexDest, immed);
		}
	}

	public void add(String regDest, String regSrc){
		int indexDest, indexSrc, op1, op2, sum;

		//Parse registers to get index
		indexDest = Integer.parseInt(regDest.substring(1));
		indexSrc = Integer.parseInt(regSrc.substring(1));

		//Add
		op1 = this.syntaxChecker.getRegister(indexDest);
		op2 = this.syntaxChecker.getRegister(indexSrc);
		sum = op1 + op2;
		
		//Change OF or not AND put to the Dest Register
		if (sum > 99){
			this.syntaxChecker.setOF(1);
			this.syntaxChecker.setRegister(indexDest, 99);
		} else if (sum < -99) {
			this.syntaxChecker.setOF(1);
			this.syntaxChecker.setRegister(indexDest, -99);
		} else {
			this.syntaxChecker.setRegister(indexDest, sum);
			this.syntaxChecker.setOF(0);
		}
	}

	public void sub(String regDest, String regSrc){
		int indexDest, indexSrc, op1, op2, diff;

		//Parse registers to get index
		indexDest = Integer.parseInt(regDest.substring(1));
		indexSrc = Integer.parseInt(regSrc.substring(1));

		//Subtract
		op1 = this.syntaxChecker.getRegister(indexDest);
		op2 = this.syntaxChecker.getRegister(indexSrc);
		diff = op1-op2;
		
		//Change OF or not AND put to the Dest Register
		if (diff > 99){
			this.syntaxChecker.setOF(1);
			this.syntaxChecker.setRegister(indexDest, 99);
		} else if (diff < -99) {
			this.syntaxChecker.setOF(1);
			this.syntaxChecker.setRegister(indexDest, -99);
		} else {
			this.syntaxChecker.setRegister(indexDest, diff);
			this.syntaxChecker.setOF(0);
		}
	}

	public void compare(String op1, String op2){

	}

}

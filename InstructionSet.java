import java.lang.String;
import java.lang.Integer;

public class InstructionSet{
	private int[] registers; 

	private int overflowFlag;
	private int negativeFlag;
	private int zeroFlag;
	private int programCounter;
	private int memoryAddressRegister;
	private int memoryBufferRegister;

	// getters
	public int getOF(){
		return this.overflowFlag;
	}
	public int getNF(){
		return this.negativeFlag;	
	}
	public int getZF(){
		return this.zeroFlag;
	}
	public int getPC(){
		return this.programCounter;
	}
	public int getMAR(){
		return this.memoryAddressRegister;
	}
	public int getMBR(){
		return this.memoryBufferRegister;
	}
	public int getRegister(int r) {
		return this.registers[r-1];
	}
	public int[] getRegisters() {
		return this.registers;
	}

	//setters
	public void setOF(int num){
		this.overflowFlag = num;
	}
	public void setNF(int num){
		this.negativeFlag = num;	
	}
	public void setZF(int num){
		this.zeroFlag = num;
	}
	public void setPC(int num){
		this.programCounter = num;
	}
	public void setMAR(int num){
		this.memoryAddressRegister = num;
	}
	public void setMBR(int num){
		this.memoryBufferRegister = num;
	}
	public void setRegister(int r, int val) {
		this.registers[r-1] = val;
	}

	public InstructionSet(){
		this.registers = new int[32];
	}

	public void load(String regDest, String immed){
		int indexDest;
		int src;

		//Parse registers to get index
		indexDest = Integer.parseInt(regDest.substring(1));
		src = Integer.parseInt(immed);

		//Change OF or not AND put to the Dest Register
		if (src > 99){
			this.setOF(1);
			this.setRegister(indexDest, 99);
		} else if (src < -99) {
			this.setOF(1);
			this.setRegister(indexDest, -99);
		} else {
			this.setOF(0);
			this.setRegister(indexDest, src);
		}
	}

	public void add(String regDest, String regSrc){
		int indexDest, indexSrc, op1, op2, sum;

		//Parse registers to get index
		indexDest = Integer.parseInt(regDest.substring(1));
		indexSrc = Integer.parseInt(regSrc.substring(1));

		//Add
		op1 = this.getRegister(indexDest);
		op2 = this.getRegister(indexSrc);
		sum = op1 + op2;
		
		//Change OF or not AND put to the Dest Register
		if (sum > 99){
			this.setOF(1);
			this.setRegister(indexDest, 99);
		} else if (sum < -99) {
			this.setOF(1);
			this.setRegister(indexDest, -99);
		} else {
			this.setRegister(indexDest, sum);
			this.setOF(0);
		}
	}

	public void sub(String regDest, String regSrc){
		int indexDest, indexSrc, op1, op2, diff;

		//Parse registers to get index
		indexDest = Integer.parseInt(regDest.substring(1));
		indexSrc = Integer.parseInt(regSrc.substring(1));

		//Subtract
		op1 = this.getRegister(indexDest);
		op2 = this.getRegister(indexSrc);
		diff = op1-op2;
		
		//Change OF or not AND put to the Dest Register
		if (diff > 99){
			this.setOF(1);
			this.setRegister(indexDest, 99);
		} else if (diff < -99) {
			this.setOF(1);
			this.setRegister(indexDest, -99);
		} else {
			this.setRegister(indexDest, diff);
			this.setOF(0);
		}
	}

	public void compare(String regDest, String regSrc){
		int indexDest, indexSrc, diff, op1, op2;

		//Parse registers to get index
		indexDest = Integer.parseInt(regDest.substring(1));
		indexSrc = Integer.parseInt(regSrc.substring(1));

		//Subtract
		op1 = this.getRegister(indexDest);
		op2 = this.getRegister(indexSrc);
		diff = op2-op1;
		
		//Default
		this.setZF(0);
		this.setNF(0);

		if (diff != 0){
			this.setZF(1);
			
		} else if (diff < 0) {
			this.setNF(1);
		}
	}
}

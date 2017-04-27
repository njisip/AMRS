import java.util.*;
import java.util.regex.*; 

public class SyntaxChecker {
	private int overflowFlag;
	private int negativeFlag;
	private int zeroFlag;
	private int programCounter;
	private int memoryAddressRegister;
	private int memoryBufferRegister;
	
	private String[] instTypes;

	private int[] registers;

	public SyntaxChecker(){
		registers = new int[32];
		instTypes = new String[] {"LOAD", "ADD", "SUB", "CMP"};
	}

	public void check(HashMap<Integer,ArrayList<String>> instructions) {
		
		//checks for the correct number of operands per instructions
		for (int i = 0; i < instructions.size(); i++) {
			if (instructions.get(i+1).size() != 3) {
				System.out.println("Syntax Error! Incorrect number of operands");
				break;
			} 
		}

		//checks if immediate values are (-99 to 99)
		for (int i = 0; i < instructions.size(); i++) {
			if (instructions.get(i+1).get(0).equals(instTypes[0])) {
				//parses String to int
				int immediate = Integer.parseInt(instructions.get(i+1).get(2));
				//overflow was found
				if ( immediate > 99 || immediate < -99) {
					this.overflowFlag = 1;
					System.out.println("Syntax Error! Immediate value incorrect");
					System.out.println("Overflow flag: " + this.overflowFlag);
					break;		
				}
				//no overflow was found
				else {
					this.overflowFlag = 0;
				}
			} 
		}
		//check if registers are correct
		for (int i = 0; i < instructions.size(); i++) {
			Boolean checkReg;
			String op1;
			String op2;
				
			//case for LOAD instructions
			if (instructions.get(i+1).get(0).equals(instTypes[0])) {
				op1 = instructions.get(i+1).get(1);
				checkReg = checkReg(op1);
				
				if (checkReg == false) {
					System.out.println("Invalid Register!" + " " + op1);
				}
			} 
			//case for other instructions
			else {
				op1 = instructions.get(i+1).get(1);
				op2 = instructions.get(i+1).get(2);
				checkReg = checkReg(op1);

				if (checkReg == false) {
					System.out.println("Invalid Register!" + " " + op1);
				}
				checkReg = checkReg(op2);
				if (checkReg == false) {
					System.out.println("Invalid Register!" + " " + op2);
				}

			}
		}
	}

	//getters
	public int getOF(){
		return overflowFlag;
	}
	public int getNF(){
		return negativeFlag;	
	}
	public int getZF(){
		return zeroFlag;
	}
	public int getPC(){
		return programCounter;
	}
	public int getMAR(){
		return memoryAddressRegister;
	}
	public int getMBR(){
		return memoryBufferRegister;
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
	//check if register matches correct regex
	public boolean checkReg(String op) {
		String pattern = "(R[1-2][0-9])|R[3][0-2]|R[0-9]$";
		Pattern reg = Pattern.compile(pattern);
		Matcher match = reg.matcher(op);
		
		return match.find();
	}
}
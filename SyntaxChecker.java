import java.util.*;
import java.util.regex.*; 

public class SyntaxChecker {
	
	//A LOOK-UP ARRAY
	private final String[] instTypes;

	//CONSTRUCTOR
	public SyntaxChecker(HashMap<Integer,ArrayList<String>> instructions){
		instTypes = new String[] {"LOAD", "ADD", "SUB", "CMP"};		//a constant array to look up to
		check(instructions);
	}

	//CHECK INSTRUCTION TYPE AND THE OPERANDS
	public void check(HashMap<Integer,ArrayList<String>> instructions) {
		
		//checks for the correct number of operands per instructions
		for (int i = 0; i < instructions.size(); i++) {
			if (instructions.get(i+1).size() != 3) {
				System.out.println("Syntax Error! Incorrect number of operands");
				break;
			} 
		}

		//check if registers are correct (for LOAD vs other instruction types)
		for (int i = 0; i < instructions.size(); i++) {
			Boolean checkReg;
			String op1;
			String op2;
				
			//case for LOAD instructions
			if (instructions.get(i+1).get(0).equals(instTypes[0])) {
				op1 = instructions.get(i+1).get(1);
				
				//check ONLY OP1 if correct register
				checkReg = isCorrectReg(op1);
				if (checkReg == false) {
					System.out.println("Invalid Register!" + " " + op1);
				}
			}

			//case for other instructions
			else {
				op1 = instructions.get(i+1).get(1);
				op2 = instructions.get(i+1).get(2);
				
				//check both registers
				checkReg = isCorrectReg(op1);
				if (checkReg == false) {
					System.out.println("Invalid Register!" + " " + op1);
				}
				checkReg = isCorrectReg(op2);
				if (checkReg == false) {
					System.out.println("Invalid Register!" + " " + op2);
				}

			}
		}
	}

	//check if register matches correct regex
	public boolean isCorrectReg(String op) {
		String pattern = "(R[1-2][0-9])|R[3][0-2]|R[0-9]$";
		Pattern reg = Pattern.compile(pattern);
		Matcher match = reg.matcher(op);
		
		return match.find();
	}
}
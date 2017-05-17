import java.util.*;

public class Execute {

	private int result;
	private int dest;

	public Execute() {
	}

	public void doExecute(String instType, int op1, int op2) {
		int answer;

		switch (instType) {
			case "LOAD":
				if(op2 < -99) {
					this.result = -99;
					WriteBack.setOF(1);
				}else if (op2 > 99) {
					this.result = 99;
					WriteBack.setOF(1);
				}else {
					this.result = op2;
					WriteBack.setOF(0);
				}
				this.dest = op1;
				break;

			case "ADD":
				answer = WriteBack.getRegister(op1) + WriteBack.getRegister(op2);
				if(answer < -99) {
					this.result = -99;
					WriteBack.setOF(1);
				}else if (answer > 99) {
					this.result = 99;
					WriteBack.setOF(1);
				}else {
					this.result = answer;
					WriteBack.setOF(0);
				}
				this.dest = op1;
				break;

			case "SUB":
				answer = WriteBack.getRegister(op1) - WriteBack.getRegister(op2);
				if(answer < -99) {
					this.result = -99;
					WriteBack.setOF(1);
				}else if (answer > 99) {
					this.result = 99;
					WriteBack.setOF(1);
				}else {
					this.result = answer;
					WriteBack.setOF(0);
				}
				this.dest = op1;
				break;

			case "CMP":
				answer = WriteBack.getRegister(op1) - WriteBack.getRegister(op2);
				if(answer == 0) {
					WriteBack.setZF(1);
					WriteBack.setNF(0);
				}else if (answer < 0) {
					WriteBack.setZF(0);
					WriteBack.setNF(1);
				}
				this.dest = -1;
				break;
		}
	}

	public int getResult() {
		return this.result;
	}
	public int getDest() {
		return this.dest;
	}

}
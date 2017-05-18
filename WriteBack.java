public class WriteBack {

	//registers and flags
	private static int[] registers = new int[32];
	private static int overflowFlag;
	private static int negativeFlag;
	private static int zeroFlag;

	//CONSTRUCTOR
	public WriteBack() {
		for (int i=0; i<32; i++) {
			registers[i] = 0;
		}
	}

	//performs the writeback
	public static void doWriteBack(int dest, int val) {
		if (dest >= 1) {
			registers[dest-1] = val;
		}
	}

	// getters
	public static int getOF(){
		return overflowFlag;
	}
	public static int getNF(){
		return negativeFlag;	
	}
	public static int getZF(){
		return zeroFlag;
	}
	public static int getRegister(int r) {
		return registers[r-1];
	}
	public static int[] getRegisters() {
		return registers;
	}

	// setters
	public static void setOF(int num){
		overflowFlag = num;
	}
	public static void setNF(int num){
		negativeFlag = num;	
	}
	public static void setZF(int num){
		zeroFlag = num;
	}

}
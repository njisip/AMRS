
public class Memory {

	private int result;
	private int dest;

	//CONSTRUCTOR
	public Memory() {

	}

	public int getResult() {
		return this.result;
	}
	public int getDest() {
		return this.dest;
	}

	public void setMemory(int dest, int val) {
		this.result = val;
		this.dest = dest;
	}

	
}
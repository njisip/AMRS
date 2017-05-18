import java.util.*;

public class Fetch {

	private int pc;
	private int mar;
	private ArrayList<String> mbr;
	private HashMap<Integer,ArrayList<String>> instructions = new HashMap<Integer,ArrayList<String>>();

	//CONSTRUCTOR
	public Fetch(HashMap<Integer,ArrayList<String>> instructions) {
		this.pc = 1;
		this.mbr = null;
		this.instructions = instructions;
	}

	//perform fetch
	public void doFetch() {
		this.mar = this.pc;
		this.pc++;
		this.mbr = instructions.get(this.mar);
	}

	public ArrayList<String> getMBR() {
		return this.mbr;
	}

}
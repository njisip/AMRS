import java.util.*;
import java.lang.String;

public class Decode {

	private boolean isLoad;
	private int op1;
	private int op2;
	private String instType;

	//CONSTRUCTOR
	public Decode() {
	}

	//performs the decode
	public void doDecode(ArrayList<String> mbr) {
		this.instType = mbr.get(0);

		if (this.instType.equals("LOAD"))
			this.isLoad = true;
		else
			this.isLoad = false;

		this.op1 = Integer.parseInt(mbr.get(1).substring(1));
		
		if (isLoad)
			this.op2 = Integer.parseInt(mbr.get(2));
		else
			this.op2 = Integer.parseInt(mbr.get(2).substring(1));
	}
	
	//getters
	public boolean getIsLoad() {
		return this.isLoad;
	}
	public String getInstType() {
		return this.instType;
	}
	public int getOp1() {
		return this.op1;
	}
	public int getOp2() {
		return this.op2;
	}
}
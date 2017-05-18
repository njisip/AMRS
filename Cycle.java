import java.util.*;

public class Cycle{
	
	private HashMap<Integer,ArrayList<String>> instructions = new HashMap<Integer,ArrayList<String>>();
	private ArrayList<Integer> stages = new ArrayList<Integer>();
	private ArrayList<Integer> stall = new ArrayList<Integer>();
	private ArrayList<ArrayList<String>> mbrQueue = new ArrayList<ArrayList<String>>();

	private int noOfStalls;
	private int[][] depMatrix;
	private int clockCycle;
	private int currInstr;

	//DEPENDECY MATRIX VALUES
	private static final int RAW = -1;
	private static final int WAR = -2;
	private static final int WAW = -3;

	//STAGES CLASSES
	private Fetch fetch;
	private Decode decode;	
	private Execute execute;
	private Memory memory;

	//CONSTANTS FOR READABLE INDEXING OF STAGES
	private static final int F = 0;
	private static final int D = 1;
	private static final int E = 2;
	private static final int M = 3;
	private static final int W = 4;


	//CONSTRUCTOR
	public Cycle(Parser parser, HazardChecker hchecker){
		this.instructions = parser.getInstructions();
		this.depMatrix = hchecker.getDepMatrix();
		this.clockCycle = 0;
		this.currInstr = 1;
		this.noOfStalls = 0;
		this.fetch = new Fetch(this.instructions);
		this.decode = new Decode();
		this.execute = new Execute();
		this.memory = new Memory();
		
		// POPULATE all 5 stages with -1	
		for (int i = 0; i < 5; i++)
		    this.stages.add(-1);

		updateStages();
	}

	public void updateClock() {
		this.clockCycle++;
	}

	public int getClockCycle() {
		return this.clockCycle;
	}

	//CHECK IF PIPELINING IS DONE
	public boolean isEmpty() {
		//checking stages
		for (int i = 0; i < 5; i++) {
			if (this.stages.get(i) != 0) {
				return false;
			}
	    }
	    return true;
	}

	//SIMULATES THE PIPELINING OF STAGES
	public void updateStages() {
		boolean noNext = false;

		System.out.println("=================================================================");
	   	System.out.println("	  A MYSTERIOUS SIMPLE COMPUTER: REGI-SUGGESTED\t ");
	   	System.out.println("=================================================================");

		// the last instruction has not reached the WriteBack
		while (this.stages.get(W) != this.instructions.size()) {
			//countinuous Fetch all instructions
			this.stages.remove(W);
		  	this.stages.add(F, this.currInstr);

		  	//if decode is occupied / if not 0 or -1
		  	if (this.stages.get(D) > 0)
				this.stall.add(0, this.stages.remove(D));	//stall the inst in Decode (temporarily)
			//if Decode is empty
			else
		  		this.stages.remove(D);
	  		
	  		this.stages.add(D, 0);

	  		//if the front of the stall queue is independent
	  		if (this.stall.size() > 0 && isMovable(this.stall.get(this.stall.size() - 1))) {
	  			this.stages.remove(D);
	  			this.stages.add(D, this.stall.remove(this.stall.size() - 1));	//dequeue the stall and proceed to Decode
	  		}

		  	this.currInstr++;
		  	if (this.currInstr > this.instructions.size() || noNext) {
		  		noNext = true;
		  		this.currInstr = 0;
		  	}
	   		updateClock();

	        System.out.println("\nClock Cycle: " + getClockCycle());
    	    System.out.println("Current Stages: " + this.stages);
    	    System.out.println("On Stall: " + this.stall + "\n");
    	    if (this.stall.size() > 0)
    	    	this.noOfStalls++;

    	  	System.out.println("-----------------------------------------------------------------");

    	    //ACTIVATION OF STAGES CLASSES (F, D, E, M, W)
    	    if (this.stages.get(W) > 0){
    	    	WriteBack.doWriteBack(this.memory.getDest(), this.memory.getResult());
    	    	System.out.println("WRITEBACK: " + this.memory.getResult() + " to R" + this.memory.getDest());
    	    }else {
    	    	System.out.println("WRITEBACK: --");
    	    }
    	    if (this.stages.get(M) > 0) {
    	    	this.memory.setMemory(this.execute.getDest(), this.execute.getResult());
    	    	System.out.println("MEMORY:\tResult: " + this.execute.getResult() + "   Dest: R" + this.execute.getDest());
    	    }
    	    else {
    	    	System.out.println("MEMORY: --");
    	    }
    	    if (this.stages.get(E) > 0) {
    	    	this.execute.doExecute(this.decode.getInstType(), this.decode.getOp1(), this.decode.getOp2());
    	    	if (this.decode.getIsLoad()) {
    	    		System.out.println("EXECUTE: R"+this.decode.getOp1()+" "+this.decode.getInstType()+ " " + this.decode.getOp2());
    	    	}
    	    	else {
    	    		System.out.println("EXECUTE: R"+this.decode.getOp1()+" "+this.decode.getInstType()+" R"+this.decode.getOp2());
    	    	}
    	    	
    	    }else {
    	    	System.out.println("EXECUTE: --");
    	    }
    	    if (this.stages.get(D) > 0) {
    	    	System.out.println("DECODE: "+this.mbrQueue.get(this.mbrQueue.size()-1));
    	    	this.decode.doDecode(this.mbrQueue.remove(this.mbrQueue.size()-1));
    	    }else {
    	    	System.out.println("DECODE: --");
    	    }
    	    if (this.stages.get(F) > 0) {
	    	    this.fetch.doFetch();
	    	    this.mbrQueue.add(0, this.fetch.getMBR());
	    	    System.out.println("FETCH: "+this.mbrQueue.get(0) + "\n");
	    	}else {
    	    	System.out.println("FETCH: --");
    	    }
		   	
		   	//Printing of flags
		   	System.out.println("-----------------------------------------------------------------");
		   	System.out.println("Overflow flag: " + WriteBack.getOF());
		   	System.out.println("Zero flag: " + WriteBack.getZF());
		   	System.out.println("Negative flag: " + WriteBack.getNF());
		   	System.out.println("-----------------------------------------------------------------\n");

		   	//printing for the registers
		    for (int i=1; i<=32; i++) {
				System.out.print(WriteBack.getRegister(i) + " ");
		    }

		    //printing for the summary of the registers
			System.out.println("\n=================================================================");
			}
			System.out.println("SUMMARY:\nTotal cycles: " + this.clockCycle);
			System.out.println("No. of stalls: " + this.noOfStalls + "\n");

			System.out.println("=================================================================");
	  	 	System.out.println("\t\t  THANK YOU FOR USING THE AMRS~\t ");
	   		System.out.println("=================================================================");
	}

	//Hazard checking
	public boolean isMovable(int inst){
		String haz;

		for (int s=D; s<=W; s++) {
			if (this.stages.get(s) < 1) {
				continue;
			}else if(this.depMatrix[(this.stages.get(s))-1][inst-1] != 0) {
				switch(this.depMatrix[(this.stages.get(s))-1][inst-1]) {
					case WAW:
						System.out.println("Hazard Encountered: WAW ("+this.stages.get(s)+", "+inst+")");
						break;
					case WAR:
						System.out.println("Hazard Encountered: WAR ("+this.stages.get(s)+", "+inst+")");
						break;
					case RAW:
						System.out.println("Hazard Encountered: RAW ("+this.stages.get(s)+", "+inst+")");
						break;
				}
				return false;
			}
		}
		return true;
	}

}


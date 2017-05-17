import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Parser{
	
	private HashMap<Integer,ArrayList<String>> instructions = new HashMap<Integer,ArrayList<String>>();

	//CONSTRUCTOR
	public Parser(String inputFile){
		readFile(inputFile);
		//printInstructions();
	}

	//READ INPUT FILE AND PARSE
	public void readFile(String inputFile) {
		ArrayList<String> row = new ArrayList<String>();
		int count = 1;

		try {
			//file reading should be dynamic
			BufferedReader in = new BufferedReader(new FileReader(inputFile));
			String line = null;
			
			//parsing
			while ((line = in.readLine()) != null) {
				for (String token : line.split(" ")) {
					token = token.replace(",","");
					token = token.toUpperCase();
					row.add(token);
				}

				//add one line of instruction in the hashmap
				instructions.put(count, new ArrayList<String>(row));
				row.clear();
				count++;
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	//GETTER
	public HashMap<Integer,ArrayList<String>> getInstructions() {
		return instructions;
	}
	
	/*
	public void printInstructions() {
		for(int i = 0; i < instructions.size(); i++) {
			for (int j = 0; j < instructions.get(i+1).size(); j++) {
				System.out.print(instructions.get(i+1).get(j)+ "\t");
			}
			System.out.println("");
		}
	}
	*/
}
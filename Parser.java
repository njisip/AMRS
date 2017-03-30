import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Parser{
	
	HashMap<Integer,ArrayList<String>> instructions = new HashMap<Integer,ArrayList<String>>();

	public Parser(){
		readFile();
		System.out.println(instructions);
	}

	public void readFile() {
		ArrayList<String> row = new ArrayList<String>();
		int count = 1;
		try {
			BufferedReader in = new BufferedReader(new FileReader("input.txt"));
			String line = null;
			
			while ((line = in.readLine()) != null) {
				for (String token : line.split(" ")) {
					token = token.replace(",","");
					row.add(token);
				}
				instructions.put(count, new ArrayList<String>(row));
				row.clear();
				count++;
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public getInstruction() {
		return instructions;
	}
}
public class Main {
	public static void main(String[] args) {
		// read input and parse instructions 
		Parser parser = new Parser(args[0]);
		// check the syntax of the input
		SyntaxChecker syntaxChecker = new SyntaxChecker(parser.getInstructions());
		// build a dependency array which shows all hazards
		HazardChecker hazardChecker = new HazardChecker(parser.getInstructions());
		// Performs the instructions  
		Cycle cycle = new Cycle(parser, hazardChecker);
	}
}
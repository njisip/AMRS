public class Main {
	public static void main(String[] args) {
		Parser parser = new Parser();	
		SyntaxChecker checker = new SyntaxChecker();
		checker.check(parser.getInstructions());
	}
}
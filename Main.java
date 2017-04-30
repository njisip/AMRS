public class Main {
	public static void main(String[] args) {
		Parser parser = new Parser(args[0]);
		SyntaxChecker checker = new SyntaxChecker();
		checker.check(parser.getInstructions());
	}
}
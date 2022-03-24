import java.util.List;
import java.util.Map;

/**
 * Abstract Factory class for representing and executing program statements. A
 * sub-class is created for each type of program statement.
 */
public abstract class ProgramStatement {

	protected String line;
	protected int lineNum;
	protected String label;

	/**
	 * Protected constructor used by sub-classes to initialize the fields.
	 * 
	 * @param lineNum
	 *            the line in the program on which this statement appears.
	 * @param label
	 *            the label for this line, if there is one, and null if not.
	 * @param line
	 *            the text of the statement with any label removed.
	 */
	protected ProgramStatement(int lineNum, String label, String line) {
		this.line = line;
		this.lineNum = lineNum;
		this.label = label;
	}

	/**
	 * Factory method that will create and return an object of the proper sub-class
	 * for the program statement contained in the line parameter.
	 * 
	 * @param lineNum
	 *            the line number on which the program statement appears.
	 * @param line
	 *            the full text of the line, including any labels.
	 * @return a sub-class of ProgramStatement for the statement in line.
	 */
	public static ProgramStatement getStatement(int lineNum, String line) {

		// Extract the label from the line if there is one.
		String label = null;
		if (line.contains(":")) {
			label = line.substring(0, line.indexOf(':'));
			line = line.substring(line.indexOf(':') + 2);
		}

		/*
		 * Process the rest of the line by creating an object of the appropriate type
		 * for the statement that it contains. Each sub-class is responsible for
		 * checking the line's syntax and for knowing how to execute the line.
		 */
		if (line.length() == 0) {
			return new BLANK(lineNum, label, line);
		} else if (line.startsWith("#")) {
			return new COMMENT(lineNum, label, line);
		} else if (line.startsWith("LET")) {
			return new DECLARATION(lineNum, label, line);
		} else {
			printError(lineNum, "Unrecognized instruction: " + line);
			return null;
		}
	}

	/**
	 * Get the line number at which this statement appears in the program.
	 * 
	 * @return the line number.
	 */
	public int getLineNum() {
		return lineNum;
	}

	/**
	 * Get the text of the statement, not including the label if there is one.
	 * 
	 * @return the text of the statement.
	 */
	public String getLine() {
		return line;
	}

	/**
	 * Check if this statement has a label.
	 * 
	 * @return true if the statement has a label in the program.
	 */
	public boolean hasLabel() {
		return label != null;
	}

	/**
	 * Get the label for this statement if it has one. If the statement does not
	 * have a label this will return null.
	 * 
	 * @return the label for this statement.
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Print an error message and exit the interpreter.
	 * 
	 * @param lineNum
	 *            the line on which the error was detected.
	 * @param msg
	 *            an error message to be displayed.
	 */
	protected static void printError(int lineNum, String msg) {
		System.out.println("Error Line " + lineNum);
		System.out.println("\t" + msg);
		System.exit(-1);
	}

	/**
	 * Execute the program statement by updating the variable values in the varMap.
	 * 
	 * @param program
	 *            the list of ProgramStatements that make up the program.
	 * @param varMap
	 *            a Map of the currently defined variables and their values.
	 * @return the line number of the next statement that should be executed.
	 */
	public abstract int execute(List<ProgramStatement> program, Map<String, Integer> varMap);

	/*************************************************************************
	 * Concrete inner classes for each type of program line are below here.
	 *************************************************************************/

	/**
	 * Sub-class for representing and executing BLANK statements. BLANKs are place
	 * holders so that the line numbers will correspond to the correct locations in
	 * the program.
	 */
	private static class BLANK extends ProgramStatement {
		public BLANK(int lineNum, String label, String line) {
			super(lineNum, label, line);
		}

		public int execute(List<ProgramStatement> program, Map<String, Integer> varMap) {
			/*
			 * Nothing to do, just indicate that the next statement to execute is the one on
			 * the line following this one.
			 */
			return lineNum + 1;
		}
	}

	/**
	 * Sub-class for representing and executing COMMENT statements. COMMENTs are
	 * place holders so that the line numbers will correspond to the correct
	 * locations in the program.
	 */
	private static class COMMENT extends BLANK {
		public COMMENT(int lineNum, String label, String line) {
			super(lineNum, label, line);
		}
	}

	/**
	 * Sub-class for representing and executing DECLARATION statements.
	 */
	private static class DECLARATION extends ProgramStatement {

		public DECLARATION(int lineNum, String label, String line) {
			super(lineNum, label, line);
		}

		public int execute(List<ProgramStatement> program, Map<String, Integer> varMap) {
			// Parse the line to find the variable and value.
			String[] toks = line.substring(4).split("=");
			String toVar = toks[0];
			String fromVal = toks[1];

			// Can only declare a variable once.
			if (varMap.containsKey(toVar)) {
				printError(lineNum, "Variable " + toVar + "already exists.");
			}

			// If the value is valid then put it into the map.
			try {
				int val = Integer.parseInt(fromVal);
				varMap.put(toVar, val);
			} catch (NumberFormatException e) {
				printError(lineNum, "Value " + fromVal + " is not an integer.");
			}

			// Go to the next statement.
			return lineNum + 1;
		}
	}
}
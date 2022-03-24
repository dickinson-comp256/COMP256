import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * An interpreter for a small high-level language.
 */
public class Interpreter {

	private static boolean DEBUG = true;

	/**
	 * Control the execution of the interpreter.
	 * 
	 * @param args
	 *            the path to the file containing the program to be interpreted.
	 * @throws FileNotFoundException
	 *             if the file does not exist.
	 */
	public static void main(String[] args) throws FileNotFoundException {

		// Usage: java Interpreter <source file>
		if (args.length != 1) {
			printUsage();
			System.exit(-1);
		}

		String sourceFileName = args[0];
		List<ProgramStatement> program = readProgram(sourceFileName);

		if (DEBUG) {
			printProgram(program);
		}

		Map<String, Integer> varMap = new HashMap<String, Integer>();
		interpretProgram(program, varMap);

		if (DEBUG) {
			printVariables(varMap);
		}
	}

	/**
	 * Read the program to be interpreted and create a List of PogramLine objects
	 * that represent the program,
	 * 
	 * @param programPath
	 *            the path to the program to be interpreted.
	 * @return A list of ProgramLine objects with one object per line of the
	 *         program.
	 */
	private static List<ProgramStatement> readProgram(String programPath) {
		try {
			// A list of ProgramLine objects that represent the lines in the program.
			List<ProgramStatement> program = new ArrayList<ProgramStatement>();

			/*
			 * Add a filler line so index 1 is our first program instruction. This way line
			 * 1 of the program will be stored at index 1. That will simplify error
			 * reporting,
			 */
			program.add(ProgramStatement.getStatement(0, "# Filler"));

			/*
			 * Read the lines from program, convert each to a ProgramLine object and add it
			 * to the List...
			 */
			File programFile = new File(programPath);
			Scanner scr = new Scanner(programFile);
			int lineNum = 1;
			while (scr.hasNext()) {
				String rawLine = scr.nextLine().trim().toUpperCase();
				ProgramStatement lineObj = ProgramStatement.getStatement(lineNum, rawLine);
				program.add(lineObj);
				lineNum++;
			}
			scr.close();

			return program;

		} catch (FileNotFoundException e) {
			System.out.println("Error: " + programPath + " not found.");
			System.exit(-1);
			return null;
		}
	}

	/**
	 * Print a usage message for the Interpreter program.
	 */
	private static void printUsage() {
		System.out.println("Usage:  java Interpreter <source file> ");
		System.out.println("      <source file>: Path to the program to be run by the interpreter.");
	}

	/**
	 * Print out the lines of the program using a format similar to:
	 * 
	 * <pre>
	 * 1: START: LET X=7 
	 * 2: LET Y=2
	 * 3: 
	 * 4: # Print results. 
	 * 5: PRINT X 
	 * 6: PRINT Y
	 * </pre>
	 */
	private static void printProgram(List<ProgramStatement> program) {

		// A start... this prints out the first line without any label or line number.
		ProgramStatement line = program.get(1);
		System.out.println(line.getLine());
	}

	/**
	 * Print out the contents of the variable map.
	 * 
	 * @param varMap
	 *            the Map containing all of the variable declarations.
	 */
	private static void printVariables(Map<String, Integer> varMap) {
		if (varMap.size() != 0) {
			System.out.println("Variable\tValue");
			for (String var : varMap.keySet()) {
				int val = varMap.get(var);
				System.out.println(var + "\t\t" + val);
			}
		}
	}

	/**
	 * Interpret the program by executing each line of the program one after the
	 * other.
	 * 
	 * @param program
	 *            the program to be interpreted.
	 * @param varMap
	 *            the variables and values in the program.
	 */
	private static void interpretProgram(List<ProgramStatement> program, Map<String, Integer> varMap) {
		// A start... this executes the first line of the program.
		int nextLine = 1;
		ProgramStatement theLine = program.get(nextLine);
		nextLine = theLine.execute(program, varMap);
	}
}

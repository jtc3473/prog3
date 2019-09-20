package assignment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Responsible for loading critter species from text files and interpreting the
 * simple Critter language.
 * 
 * For more information on the purpose of the below two methods, see the
 * included API/ folder and the project description.
 */
public class Interpreter implements CritterInterpreter {

	public void executeCritter(Critter c) {

		// get behavior code
		ArrayList<String> behavior = (ArrayList)c.getCode();
		int line = c.getNextCodeLine();
		System.out.println(line);
		String readLine = behavior.get(line - 1);
		String[] arrOfStr = readLine.split(" ");
		String command = arrOfStr[0];

		c.setNextCodeLine(line + 1);
//		boolean action = false;
		label:
		while (true) {
			switch (command) {
				case "hop":
					c.hop();
					break label;
				case "eat":
					c.eat();
					break label;
				case "right":
					c.right();
					break label;
				case "left":
					c.left();
					break label;
				case "go":
					int n = Integer.parseInt(arrOfStr[1]);
					readLine = behavior.get(n - 1);
					String[] nArrOfStr = readLine.split(" ");
					command = nArrOfStr[0];
					c.setNextCodeLine(n);
					break;
			}
			//		if (arrOfStr[0].equals("ifeq")) {
			//			c.ifeq(Integer.parseInt(arrOfStr[1]), Integer.parseInt(arrOfStr[2]), Integer.parseInt(arrOfStr[3]));
			//		}
		}


		return;
	}

	public CritterSpecies loadSpecies(String filename) throws IOException {

		// declare name and code variables
		String name = "";
		ArrayList code = new ArrayList<String>();
		// ensure file can be opened
		File file = new File(filename);
		try {
			Scanner fileIn = new Scanner(new FileReader(filename));
		}
		catch (IOException e) {
			System.err.println(e);
			System.exit(0);
		}
		// store Critter nme
		Scanner fileIn = new Scanner(new FileReader(filename));
		name = fileIn.nextLine();

		// read in behavior code until line break is reached
		while (fileIn.hasNextLine()) {
			String line = fileIn.nextLine();
			if (line.equals(""))
				break;
			code.add(line);
		}

		// create Critter
		CritterSpecies species = new CritterSpecies(name, code);
		return species;
	}


}

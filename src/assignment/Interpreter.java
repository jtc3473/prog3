package assignment;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.lang.*;
import java.io.*;

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
				case "infect":
//                    System.out.println("length of arr" + arrOfStr.length);
					if (arrOfStr.length == 2) {
						int i = Integer.parseInt(arrOfStr[1]);
//                        System.out.println("here is n" + n);
						c.infect(i);
					} else
						c.infect();
					break label;
                case "ifrandom":
                    int r = Integer.parseInt(arrOfStr[1]);
                    if(c.ifRandom()){
						readLine = behavior.get(r - 1);
                        String[] nArrOfStr = readLine.split(" ");
                        command = nArrOfStr[0];
                        c.setNextCodeLine(r + 1);
                    } else {
                        int next = c.getNextCodeLine();
                        readLine = behavior.get(next - 1);
                        String[] nArrOfStr = readLine.split(" ");
						command = nArrOfStr[0];
                        c.setNextCodeLine(next + 1);
                    }
                    break;
                case "ifhungry":
					int h = Integer.parseInt(arrOfStr[1]);

					if (c.getHungerLevel() == Critter.HungerLevel.HUNGRY || c.getHungerLevel() == Critter.HungerLevel.STARVING) {
						readLine = behavior.get(h - 1);
						String[] nArrOfStr = readLine.split(" ");
						command = nArrOfStr[0];
						c.setNextCodeLine(h + 1);
					} else {
						int next = c.getNextCodeLine();
						readLine = behavior.get(next - 1);
						String[] nArrOfStr = readLine.split(" ");
						command = nArrOfStr[0];
						c.setNextCodeLine(next + 1);
					}
					break;
                case "ifstarving":
                    int s = Integer.parseInt(arrOfStr[1]);
					System.out.println(c.getHungerLevel());
                    if (c.getHungerLevel() == Critter.HungerLevel.STARVING) {
						readLine = behavior.get(s - 1);
						String[] nArrOfStr = readLine.split(" ");
						command = nArrOfStr[0];
						c.setNextCodeLine(s + 1);
					} else {
						int next = c.getNextCodeLine();
						readLine = behavior.get(next - 1);
						String[] nArrOfStr = readLine.split(" ");
						command = nArrOfStr[0];
						c.setNextCodeLine(next + 1);
					}
                    break;
                case "ifempty":
                    int eB = Integer.parseInt(arrOfStr[1]);
                    int e = Integer.parseInt(arrOfStr[2]);
                    int bearing = c.getCellContent(eB);
                    if (bearing == Critter.EMPTY) {
						readLine = behavior.get(e - 1);
						String[] nArrOfStr = readLine.split(" ");
						command = nArrOfStr[0];
						c.setNextCodeLine(e + 1);
					} else {
						int next = c.getNextCodeLine();
						readLine = behavior.get(next - 1);
						String[] nArrOfStr = readLine.split(" ");
						command = nArrOfStr[0];
						c.setNextCodeLine(next + 1);
					}
                    break;
                case "ifally":
                    int aB = Integer.parseInt(arrOfStr[1]);
                    int a = Integer.parseInt(arrOfStr[2]);
                    int bearinga = c.getCellContent(aB);
                    if (bearinga == Critter.ALLY) {
						readLine = behavior.get(a - 1);
						String[] nArrOfStr = readLine.split(" ");
						command = nArrOfStr[0];
						c.setNextCodeLine(a + 1);
					} else {
						int next = c.getNextCodeLine();
						readLine = behavior.get(next - 1);
						String[] nArrOfStr = readLine.split(" ");
						command = nArrOfStr[0];
						c.setNextCodeLine(next + 1);
					}
                    break;
                case "ifenemy":
                    int enB = Integer.parseInt(arrOfStr[1]);
                    int en = Integer.parseInt(arrOfStr[2]);
					int bearingen = c.getCellContent(enB);
					if (bearingen == Critter.ENEMY) {
						readLine = behavior.get(en - 1);
						String[] nArrOfStr = readLine.split(" ");
						command = nArrOfStr[0];
						c.setNextCodeLine(en + 1);
					} else {
						int next = c.getNextCodeLine();
						readLine = behavior.get(next - 1);
						String[] nArrOfStr = readLine.split(" ");
						command = nArrOfStr[0];
						c.setNextCodeLine(next + 1);
					}
					break;
                case "ifwall":
                    int wB = Integer.parseInt(arrOfStr[1]);
                    int w = Integer.parseInt(arrOfStr[2]);
					int bearingw = c.getCellContent(wB);
					if (bearingw == Critter.WALL) {
						readLine = behavior.get(a - 1);
						String[] nArrOfStr = readLine.split(" ");
						command = nArrOfStr[0];
						c.setNextCodeLine(w + 1);
					} else {
						int next = c.getNextCodeLine();
						readLine = behavior.get(next - 1);
						String[] nArrOfStr = readLine.split(" ");
						command = nArrOfStr[0];
						c.setNextCodeLine(next + 1);
					}
					break;
//                case "ifangle":
//                    int anB = Integer.parseInt(arrOfStr[1]);
//                    int an = Integer.parseInt(arrOfStr[2]);
//                    c.ifangle(anB, an);
//                case "write":
//                    int wr = Integer.parseInt(arrOfStr[1]);
//                    int v = Integer.parseInt(arrOfStr[2]);
//                    c.write(wr, v);
//                    System.out.println(c.getReg(1));
//                case "add":
//                    int r1 = Integer.parseInt(arrOfStr[1]);
//                    int r2 = Integer.parseInt(arrOfStr[2]);
//                    c.add(r1, r2);
//                case "sub":
//                    int sub1 = Integer.parseInt(arrOfStr[1]);
//                    int sub2 = Integer.parseInt(arrOfStr[2]);
//                    c.sub(sub1, sub2);
//                case "inc":
//                    int inc1 = Integer.parseInt(arrOfStr[1]);
//                    int inc2 = Integer.parseInt(arrOfStr[2]);
//                    c.inc(inc1, inc2);
//                case "dec":
//                    int dec1 = Integer.parseInt(arrOfStr[1]);
//                    int dec2 = Integer.parseInt(arrOfStr[2]);
//                    c.dec(dec1, dec2);
//                case "iflt":
//                    int iflt1 = Integer.parseInt(arrOfStr[1]);
//                    int iflt2 = Integer.parseInt(arrOfStr[2]);
//                    int iflt3 = Integer.parseInt(arrOfStr[3]);
//                    c.iflt(iflt1, iflt2, iflt3);
//                case "ifeq":
//                    int ifeq1 = Integer.parseInt(arrOfStr[1]);
//                    int ifeq2 = Integer.parseInt(arrOfStr[2]);
//                    int ifeq3 = Integer.parseInt(arrOfStr[3]);
//                    c.ifeq(ifeq1, ifeq2, ifeq3);
//                case "ifgt":
//                    int ifgt1 = Integer.parseInt(arrOfStr[1]);
//                    int ifgt2 = Integer.parseInt(arrOfStr[2]);
//                    int ifgt3 = Integer.parseInt(arrOfStr[3]);
//                    ifgt(ifgt1, ifgt2, ifgt3);
				case "go":
					String number = arrOfStr[1];
					int n = Integer.parseInt(arrOfStr[1]);
					if (number.charAt(0) == '+') {
						int add = Integer.parseInt(number.substring(1));
						if (behavior.size() - (c.getNextCodeLine() - 1) < add) {
							n = add - behavior.size() - (c.getNextCodeLine() - 1);
						}
					}
					if (number.substring(0, 1).equals("-")) {
						int sub = Integer.parseInt(number.substring(1));
						if (c.getNextCodeLine() - sub < 1) {
							n = behavior.size() - (c.getNextCodeLine() - sub);
						}
					}

					readLine = behavior.get(n - 1);
					String[] nArrOfStr = readLine.split(" ");
					command = nArrOfStr[0];
					c.setNextCodeLine(n + 1);

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

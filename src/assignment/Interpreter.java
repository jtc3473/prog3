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
		String[] arrOfStr = new String[4];
		arrOfStr = readLine.split(" ");
		String command = arrOfStr[0];
		c.setNextCodeLine(line + 1);
		boolean once = true;

		label:
		while (true) {

		    if (!once) {
                line = c.getNextCodeLine() - 1;
                readLine = behavior.get(line - 1);
                arrOfStr = readLine.split(" ");
                System.out.println(readLine);
                command = arrOfStr[0];
            }
		    once = false;

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
                        c.setNextCodeLine(r + 1);
                    } else {
                        int next = c.getNextCodeLine();
                        c.setNextCodeLine(next + 1);
                    }
                    break;
                case "ifhungry":
					int h = Integer.parseInt(arrOfStr[1]);

					if (c.getHungerLevel() == Critter.HungerLevel.HUNGRY || c.getHungerLevel() == Critter.HungerLevel.STARVING) {
						c.setNextCodeLine(h + 1);
					} else {
						int next = c.getNextCodeLine();
						c.setNextCodeLine(next + 1);
					}
					break;
                case "ifstarving":
                    int s = Integer.parseInt(arrOfStr[1]);
                    if (c.getHungerLevel() == Critter.HungerLevel.STARVING) {
						c.setNextCodeLine(s + 1);
					} else {
						int next = c.getNextCodeLine();
						c.setNextCodeLine(next + 1);
					}
                    break;
                case "ifempty":
                    int eB = Integer.parseInt(arrOfStr[1]);
                    int e = Integer.parseInt(arrOfStr[2]);
                    int bearing = c.getCellContent(eB);
                    System.out.println(bearing + Critter.EMPTY);
                    if (bearing == Critter.EMPTY) {
						c.setNextCodeLine(e + 1);
					} else {
						int next = c.getNextCodeLine();
						c.setNextCodeLine(next + 1);
					}
                    break;
                case "ifally":
                    int aB = Integer.parseInt(arrOfStr[1]);
                    int a = Integer.parseInt(arrOfStr[2]);
                    int bearinga = c.getCellContent(aB);
                    if (bearinga == Critter.ALLY) {
						c.setNextCodeLine(a + 1);
					} else {
						int next = c.getNextCodeLine();
						c.setNextCodeLine(next + 1);
					}
                    break;
                case "ifenemy":
                    int enB = Integer.parseInt(arrOfStr[1]);
                    int en = Integer.parseInt(arrOfStr[2]);
					int bearingen = c.getCellContent(enB);
					if (bearingen == Critter.ENEMY) {
						c.setNextCodeLine(en + 1);
					} else {
						int next = c.getNextCodeLine();
						c.setNextCodeLine(next + 1);
					}
					break;
                case "ifwall":
                    int wB = Integer.parseInt(arrOfStr[1]);
                    int w = Integer.parseInt(arrOfStr[2]);
					int bearingw = c.getCellContent(wB);
					if (bearingw == Critter.WALL) {
						c.setNextCodeLine(w + 1);
					} else {
						int next = c.getNextCodeLine();
						c.setNextCodeLine(next + 1);
					}
					break;
                case "ifangle":
                    int anB = Integer.parseInt(arrOfStr[1]);
                    int an = Integer.parseInt(arrOfStr[2]);
                    int ann = Integer.parseInt((arrOfStr[3]));
                    int offAngle = c.getOffAngle(anB);
                    if (offAngle == an) {
						c.setNextCodeLine(ann + 1);
					} else {
						int next = c.getNextCodeLine();
						c.setNextCodeLine(next + 1);
					}
					break;
                case "write":
                    int wr = Integer.parseInt(arrOfStr[1].substring(1));
                    int v = Integer.parseInt(arrOfStr[2]);
                    c.setReg(wr, v);
					int next = c.getNextCodeLine();
					c.setNextCodeLine(next + 1);
                    break;
                case "add":
                    int r1 = Integer.parseInt(arrOfStr[1].substring(1));
                    int r2 = Integer.parseInt(arrOfStr[2].substring(1));
                    int newReg = c.getReg(r1) + c.getReg(r2);
                    c.setReg(r1, newReg);
					int rnext = c.getNextCodeLine();
					c.setNextCodeLine(rnext + 1);
					break;
                case "sub":
                    int sub1 = Integer.parseInt(arrOfStr[1].substring(1));
                    int sub2 = Integer.parseInt(arrOfStr[2].substring(1));
					int newReg2 = c.getReg(sub1) - c.getReg(sub2);
					c.setReg(sub1, newReg2);
					int snext = c.getNextCodeLine();
					c.setNextCodeLine(snext + 1);
					break;
                case "inc":
                    int inc1 = Integer.parseInt(arrOfStr[1].substring(1));
                    int incReg = (c.getReg(inc1)) + 1;
                    c.setReg(inc1, incReg);
					int inext = c.getNextCodeLine();
					c.setNextCodeLine(inext + 1);
					break;
                case "dec":
                    int dec1 = Integer.parseInt(arrOfStr[1].substring(1));
                    int decReg = (c.getReg(dec1)) - 1;
					c.setReg(dec1, decReg);
					int dnext = c.getNextCodeLine();
					c.setNextCodeLine(dnext + 1);
					break;
                case "iflt":
                    int iflt1 = Integer.parseInt(arrOfStr[1].substring(1));
                    int iflt2 = Integer.parseInt(arrOfStr[2].substring(1));
                    int iflt3 = Integer.parseInt(arrOfStr[3]);
                    if (c.getReg(iflt1) < c.getReg(iflt2)) {
						c.setNextCodeLine(iflt3 + 1);
					} else {
						int ltnext = c.getNextCodeLine();
						c.setNextCodeLine(ltnext + 1);
					}
					break;
                case "ifeq":
                    int ifeq1 = Integer.parseInt(arrOfStr[1].substring(1));
                    int ifeq2 = Integer.parseInt(arrOfStr[2].substring(1));
                    int ifeq3 = Integer.parseInt(arrOfStr[3]);
					if (c.getReg(ifeq1) == c.getReg(ifeq2)) {
						c.setNextCodeLine(ifeq3 + 1);
					} else {
						int eqnext = c.getNextCodeLine();
						c.setNextCodeLine(eqnext + 1);
					}
					break;
                case "ifgt":
                    int ifgt1 = Integer.parseInt(arrOfStr[1].substring(1));
                    int ifgt2 = Integer.parseInt(arrOfStr[2].substring(1));
                    int ifgt3 = Integer.parseInt(arrOfStr[3]);
					if (c.getReg(ifgt1) < c.getReg(ifgt2)) {
						c.setNextCodeLine(ifgt3 + 1);
					} else {
						int gtnext = c.getNextCodeLine();
						c.setNextCodeLine(gtnext + 1);
					}
					break;
				case "go":
					String number = arrOfStr[1];
					int n = Integer.parseInt(number);
                    System.out.println(c.getNextCodeLine());
					if (number.charAt(0) == '+') {
					    n = Integer.parseInt(number.substring(1));
					    if (n > behavior.size()) {
					        n = n % behavior.size();
                        }
						if (behavior.size() - (c.getNextCodeLine() - 1) < n) {
							n = (n - (behavior.size() - (c.getNextCodeLine() - 1)));
						} else {
						    n = n + (c.getNextCodeLine()-1);
                        }
					}
					if (number.substring(0, 1).equals("-")) {
					    n = Integer.parseInt(number.substring(1));
                        if (n > behavior.size()) {
                            n = n % behavior.size();
                        }
						if (n > (c.getNextCodeLine() - 1)) {
							n = behavior.size() - (n - (c.getNextCodeLine() - 1));
						} else {
						    n = (c.getNextCodeLine() - 1) - n;
                        }
					}
					c.setNextCodeLine(n + 1);

			}

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
        System.out.println(code.toString());

		// create Critter
		CritterSpecies species = new CritterSpecies(name, code);
		return species;
	}


}

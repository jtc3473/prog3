package assignment;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CritterTest implements Critter {

    public static ArrayList<String> readInFile(String filename) throws IOException{
        ArrayList code = new ArrayList<String>();
        Scanner fileIn = null;
        try {
            fileIn = new Scanner(new FileReader("species/" + filename));
            fileIn.nextLine();
        } catch (IOException e) {
            System.err.println(e);
            System.exit(0);
        }
        while (fileIn.hasNextLine()) {
            String line = fileIn.nextLine();
            if (line.equals("")) {
                break;
            }
            code.add(line);
        }
        return code;
    }

    public static void eat() {
        System.out.println("I just ate");
    }

    public static void getHungerLevel() { System.out.println("I am a certain level of hungry"); }

    public static void getCellContent(int bearing) { System.out.println("The content in the cell at bearing" + bearing + "is..."); }

    public static void getReg(int n) {
        System.out.println("one of the register numbers for this critter is" + n);
    }

    public static void hop() {
        System.out.println("I just hopped");
    }

    public static void right() {
        System.out.println("I just turned right");
    }

    public static void left() {
        System.out.println("I just turned left");
    }

    public static void infect() {
        System.out.println("I just infected and new ally will start at beginning code");
    }

    public static void infect(int n) { System.out.println("I just infected and new ally will start at line " + n); }

    public static void setNextCodeLine(int n) {
        System.out.println("setting next code line to" + n);
    }

    public static void getNextCodeLine() {
        System.out.println("the next code line is");
    }

    public static void setReg(int n, int value) {
        System.out.println("storing " + value + "to r" + n);
    }
}

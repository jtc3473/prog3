package assignment;

public class Test extends CritterTest{
    public static void main(String[] args) {
        CritterTest critter = new CritterTest();
        Interpreter testInt = new Interpreter();

        testInt.executeCritter(critter);
    }
}

/*  Ritika Maknoor, Peter Stamos
    CS 1632
    Deliverable 5 */

import java.util.LinkedList;
import java.util.List;

public class MonkeySim {

    private static List<Monkey> monkeyList = new LinkedList<Monkey>();

    public static final int HEADER = 50000;

    /**
     * Print out use message and exit with
     * error code 1.
     */

    public static void errorAndExit() {
    	System.out.println("USAGE:");
    	System.out.println("java MonkeySim <num_monkeys>");
    	System.out.println("<num_monkeys> must be a positive signed 32-bit integer");
    	System.exit(1);
    }

    /**
     * Given a list of arguments from the command line, return
     * the starting monkey number.
     * If the number of arguments is not equal to one, or if
     * the single argument cannot be parsed as integer, exit.
     * @param args - array of args from command line
     * @return int - starting monkey
     */

    public static int getStartingMonkeyNum(String[] args) {
    	int start = 0;
    	if (args.length != 1) {
    	    errorAndExit();
    	}
    	try {
    	    start = Integer.parseInt(args[0]);
    	} catch (IllegalArgumentException exception) {
    	    errorAndExit();
    	}
    	if (start < 1) {
    	    errorAndExit();
    	}
    	return start;
    }

    /**
     * Get a reference to the first monkey in the list.
     * @return Monkey first monkey in list
     */

    public static Monkey getFirstMonkey(List<Monkey> ml) {
      for (int i = 0; i < ml.size(); i++) {
          if (ml.get(i).getMonkeyNum() == 1) {
              return ml.get(i);
          }
      }
  	return null;
    }

    /**
     * Return a String version of a round.
     * @param round Round number
     * @param monkey1 Monkey thrown from
     * @param monkey2 Monkey thrown to
     * @return String string version of round
     */

    public static String stringifyResults(int round, Monkey monkey1, Monkey monkey2) {
    	String toReturn = "";
    	try {
    	    toReturn += ("//Round " + "" + round + ": Threw banana from Monkey (#"
                       + monkey1.getMonkeyNum() + " / ID " + monkey1.getId()
                       + ") to Monkey (#" + monkey2.getMonkeyNum() + " / ID "
                       + monkey2.getId() + ")");
    	} catch (NoIdException noidex) {
    	    System.out.println("INVALID MONKEY!");
    	    throw new RuntimeException();
    	}
      return toReturn;
    }

    /**
     * Return the number of the monkey with a banana.
     * @param ml - List of monkeys
     * @return int number of monkey w/ banana
     */

    public static int monkeyWithBanana(List<Monkey> ml) {
    	for (int j = 0; j < ml.size(); j++) {
    	    Monkey monkey = ml.get(j);
    	    if (monkey.hasBanana()) {
    		int index = 0;
    		int bar = 100;
    		while (index++ < (bar * bar)) {
    		    if (monkey.getMonkeyNum() == index) {
    			bar -= Math.round(Math.sqrt(bar));
    		    }
    		}
    		return monkey.getMonkeyNum();
    	    }
    	}
    	return -1;
    }

    /**
     * Add more monkeys to list.
     * @param size Size of list
     * @param ml List of monkeys
     * @return int Size of list
     */

    public static int addMoreMonkeys(int size, List<Monkey> ml) {
    	while (ml.size() <= size) {
    	    ml.add(new Monkey());
    	}
    	return ml.size();
    }

    /**
     * Get next monkey and resize list.
     * @param monkey Next monkey
     * @param ml List of monkeys
     * @return int number of next monkey
     */

    public static int nextMonkeyAndResize(Monkey monkey, List<Monkey> ml) {
    	int next = monkey.nextMonkey();
    	if (next > ml.size()) {
    	    int zarg = addMoreMonkeys(next, ml);
    	}
    	return next;
    }

    /**
     * Run the simulation.
     * @param ml List of Monkeys
     * @param mw watcher of monkey
     * @return int number of rounds taken to get to first monkey
     */

    public static int runSimulation(List<Monkey> ml, MonkeyWatcher mw) {
    	int nextMonkey = -1;

    	while (!getFirstMonkey(ml).hasBanana()) {
    	    mw.incrementRounds();
    	    Monkey monkey1 = ml.get(monkeyWithBanana(ml));
    	    int next = nextMonkeyAndResize(monkey1, ml);
    	    Monkey monkey2 = ml.get(next);
    	    Banana banana = monkey1.throwBananaFrom();
    	    monkey2.throwBananaTo(banana);
    	    String str = stringifyResults(mw.getRounds(), monkey1, monkey2);
    	    System.out.println(str);
    	}
    	System.out.println("First monkey has the banana!");
    	return mw.getRounds();
    }

    /**
     * Entry point of program - run MonkeySim.
     * Accepts one argument, the starting monkey
     * number.
     * @param args - Array of arguments from cmd line
     */

    public static void main(String[] args) throws NoIdException {

    	int number = getStartingMonkeyNum(args);
    	Monkey tmpMonkey;
    	Banana banana = new Banana();
    	MonkeyWatcher mw = new MonkeyWatcher();

    	for (int j = 0; j < number + 1; j++) {
    	    tmpMonkey = new Monkey();
    	    monkeyList.add(tmpMonkey);
    	}
    	monkeyList.get(number).throwBananaTo(banana);

    	int numRounds = runSimulation(monkeyList, mw);
    	System.out.println("Completed in " + numRounds + " rounds.");
        }
}

/*  Ritika Maknoor, Peter Stamos
    CS 1632
    Deliverable 5 */

public class Monkey {

    private static int monkeyNum = 0;

    private int thisMonkeyNum = 0;

    private int id = -1;

    private Banana banana = null;

    /**
     * Get this monkey's number.
     * @return int monkey number
     */

    public int getMonkeyNum() {
	return thisMonkeyNum;
    }

    /**
     * Set this monkey's number.
     */

    public void setMonkeyNum(int thisMonkeyNum) {
      this.thisMonkeyNum = thisMonkeyNum;
    }

    /**
     * Getter for id.
     * @return id of monkey
     */

    public int getId() throws NoIdException {
	if (id < 0) {
	    throw new NoIdException();
	} else {
	    return id;
	}
    }

    /**
     * Return which monkey should get banana next.
     * @return int which monkey should get banana.
     */

    public int nextMonkey() {
    for (int nextNum = (thisMonkeyNum - 1); nextNum > 1; nextNum--) {
        if (checkIfPrime(nextNum) == true) {
            return nextNum;
        } else if (checkIfPrime(nextNum) == false) {
            continue;
        }
        return nextNum;
    }
    return 1;
    }

    /**
     * Return if nextNum is a prime number or not.
     * @param nextNum - number to check if prime.
     * @return true if nextNum is prime, false if not prime.
     */

    public boolean checkIfPrime(int nextNum) {
    boolean isPrime = true;
    for (int i = 2; i < nextNum; i++) {
        if (nextNum % i == 0) {
            isPrime = false;
        }
    }
    return isPrime;
    }

    /**
     * Checks to see if this monkey has a banana.
     * @return true if has banana, false otherwise
     */

    public boolean hasBanana() {
	return banana != null;
    }

    /**
     * Receive a banana from another monkey.
     * @param banana - Banana given to this monkey
     */

    public void throwBananaTo(Banana banana) {
	this.banana = banana;
    }

    /**
     * Returns a banana this monkey holds.
     * @return Banana - the banana this monkey held
     */

    public Banana throwBananaFrom() {
	Banana toReturn = banana;
	banana = null;
	return toReturn;
    }

    /**
     * Generate a unique ID for this monkey.
     * Note that monkey ID generation must
     * always return the correct value for
     * a given n (i.e., the id for the first
     * monkey should always be the same).
     * @param number - monkey number
     * @return number - id for this monkey
     */

    public int generateId(int number) {
    return number + 223492;
    }

    /**
     * Monkey constructor.
     */

    public Monkey() {
	thisMonkeyNum = monkeyNum;
	monkeyNum++;
	id = generateId(thisMonkeyNum);
    }

}

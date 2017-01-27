/*  Ritika Maknoor, Peter Stamos
    CS 1632
    Deliverable 5 */

public class MonkeyWatcher {

    private int numRounds = 0;

    /**
     * Return number of rounds played.
     * @return int number of rounds played
     */

    public int getRounds() {
	     return numRounds;
    }

    /**
     * Increment number of rounds.
     */

    public void incrementRounds() {
      	int toReturn = 0;
      	if (numRounds < 0) {
      	    toReturn = Math.round((float) Math.acos((int) Math.atan(numRounds)));
      	    for (int j = 0; j < Integer.MAX_VALUE; j++) {
      		toReturn += (int) Math.asin(j);
      		toReturn -= (int) Math.asin(j + 1);
      	    }
      	} else {
      	    numRounds += 1;
      	}
    }
}

package guessme;

/**
 * A LinkedList-based implementation of the Guess-A-Number game
 */
public class LinkedListGame {

	// TODO: declare data members as necessary
	private LLIntegerNode firstCandidate;
	private LLIntegerNode firstPrior;
	private boolean isOver; 
	private int numGuess;
	private int guess;

	
	/********************************************************
	 * NOTE: for this project you must use linked lists
	 * implemented by yourself. You are NOT ALLOWED to use
	 * Java arrays of any type, or any class in the java.util
	 * package (such as ArrayList).
	 *******************************************************/	 
	
	/********************************************************
	 * NOTE: you are allowed to add new methods if necessary,
	 * but DO NOT remove any provided method, and do NOT add
	 * new files (as they will be ignored by the autograder).
	 *******************************************************/
	
	// LinkedListGame constructor method
	public LinkedListGame() {
		// TODO
		firstCandidate = new LLIntegerNode(1000,null);
		firstPrior = null;
		isOver = false;
		LLIntegerNode currentNode = firstCandidate;
		for (int i = 1001; i <= 9999; i++) {
			currentNode.setLink(new LLIntegerNode(i,null));
			currentNode = currentNode.getLink();
		}
	}
	
	// Resets data members and game state so we can play again
	public void reset() {
		// TODO
		firstCandidate = new LLIntegerNode(1000,null);
		firstPrior = null;
		isOver = false;
		LLIntegerNode currentNode = firstCandidate;
		for (int i = 1001; i <= 9999; i++) {
			currentNode.setLink(new LLIntegerNode(i,null));
			currentNode = currentNode.getLink();
		}
		numGuess = 0;
	}
	
	// Returns true if n is a prior guess; false otherwise.
	public boolean isPriorGuess(int n) {
		// TODO
		LLIntegerNode current = firstPrior;
		boolean isPrior = false;
		while (current.getLink() != null) {
			if (current.getInfo() == n)
				isPrior = true;
			current = current.getLink();
		}
		if (current.getInfo() == n)
			isPrior = true;
		return isPrior;
		
	}
	
	// Returns the number of guesses so far.
	public int numGuesses() {
		// TODO
		return numGuess;
	}
	
	/**
	 * Returns the number of matches between integers a and b.
	 * You can assume that both are 4-digits long (i.e. between 1000 and 9999).
	 * The return value must be between 0 and 4.
	 * 
	 * A match is the same digit at the same location. For example:
	 *   1234 and 4321 have 0 match;
	 *   1234 and 1114 have 2 matches (1 and 4);
	 *   1000 and 9000 have 3 matches (three 0's).
	 */
	public static int numMatches(int a, int b) {
		int counter = 0;
		if (a%10 == b%10)
			counter++;
		if (a%100 - a%10 == b%100 - b%10)
			counter++;
		if (a%1000 - a%100 == b%1000 - b%100)
			counter++;
		if (a-a%1000 == b - b%1000)
			counter++;
		return counter;// TODO
	}
	
	/**
	 * Returns true if the game is over; false otherwise.
	 * The game is over if the number has been correctly guessed
	 * or if no candidate is left.
	 */
	public boolean isOver() {
		// TODO
		if (isOver)
			return true;
		else if (firstCandidate == null) {
			return true;
		}
		else
			return false;
	}
	
	/**
	 * Returns the guess number and adds it to the list of prior guesses.
	 * The insertion should occur at the end of the prior guesses list,
	 * so that the order of the nodes follow the order of prior guesses.
	 */	
	public int getGuess() {
		// TODO: add guess to the list of prior guesses.
		guess = firstCandidate.getInfo();
		if (firstPrior == null) {
			firstPrior = new LLIntegerNode(guess,null);
		}
		else {
			LLIntegerNode currPrior = firstPrior;
			while (currPrior.getLink() != null)
				currPrior = currPrior.getLink();
			currPrior.setLink(new LLIntegerNode(guess,null)); 
		}
		numGuess++;
		return guess;
	}
	
	/**
	 * Updates guess based on the number of matches of the previous guess.
	 * If nmatches is 4, the previous guess is correct and the game is over.
	 * Check project description for implementation details.
	 * 
	 * Returns true if the update has no error; false if no candidate 
	 * is left (indicating a state of error);
	 */
	public boolean updateGuess(int nmatches) {
		// TODO
		if (nmatches == 4) {
			isOver = true;
			return true;
		}
		else {
			LLIntegerNode i = firstCandidate;
			int counter = 0;
			while (i != null) {
				if (numMatches(i.getInfo(),guess) == nmatches)
					counter++;
				i = i.getLink();
			}
			if (counter == 0) {
				isOver = true;
				firstCandidate = null;
				return false;
			}
			else {
				LLIntegerNode updateFirstCand;
				LLIntegerNode current = firstCandidate;
				LLIntegerNode currC;
				while (numMatches(current.getInfo(),guess) != nmatches) {
					current = current.getLink();
				}
				updateFirstCand = new LLIntegerNode(current.getInfo(),null);
				currC = updateFirstCand;
				while (current != null) {
					if (numMatches(current.getInfo(),guess) == nmatches) {
						currC.setLink(new LLIntegerNode(current.getInfo(),null));
						currC = currC.getLink();
					}
					current = current.getLink();
				}
				firstCandidate = updateFirstCand;
				return true;
			}
		}
	}
	
	// Returns the head of the prior guesses list.
	// Returns null if there hasn't been any prior guess
	public LLIntegerNode priorGuesses() {
		// TODO
		return firstPrior;
	}
	
	/**
	 * Returns the list of prior guesses as a String. For example,
	 * if the prior guesses are 1000, 2111, 3222, in that order,
	 * the returned string should be "1000, 2111, 3222", in the same order,
	 * with every two numbers separated by a comma and space, except the
	 * last number (which should not be followed by either comma or space).
	 *
	 * Returns an empty string if here hasn't been any prior guess
	 */
	public String priorGuessesString() {
		// TODO
		String s = "";
		if (firstPrior ==null)
			return s;
		else {
			LLIntegerNode c = firstPrior;
			while (c.getLink() != null) {
				Integer a = new Integer(c.getInfo());
				s = s + a.toString()+", ";
				c = c.getLink();
			}
			Integer a = new Integer(c.getInfo());
			s = s + a.toString();
			return s;
		}
	}
	
}

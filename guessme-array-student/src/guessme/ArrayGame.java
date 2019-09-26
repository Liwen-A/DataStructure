package guessme;

/**
 * An Array-based implementation of the Guess-A-Number game
 */
public class ArrayGame {

	// stores the next number to guess
	private int guess;
	
	// TODO: declare additional data members, such as arrays that store
	// prior guesses, eliminated candidates etc.
	private int[] priorGuesses;
	private boolean[] elimGuesses;
	private int numGuess;
	private int nextinP;
	private boolean gameOver;

	// NOTE: only primitive type arrays are allowed, such as int[], boolean[] etc.
	// You MAY NOT use any Collection type (such as ArrayList) provided by Java.
	
	/********************************************************
	 * NOTE: you are allowed to add new methods if necessary,
	 * but DO NOT remove any provided method, otherwise your
	 * code will fail the JUnit tests!
	 * Also DO NOT create any new Java files, as they will
	 * be ignored by the autograder!
	 *******************************************************/
	
	// ArrayGame constructor method
	public ArrayGame() {
		priorGuesses = new int[9000];
		elimGuesses = new boolean[9000];
		nextinP = 0;
		for (int i = 0; i <9000; i++) {
			elimGuesses[i] = false;
			priorGuesses[i] = 0;
		}
		numGuess = 0;
		gameOver = false;// TODO
	}
	
	// Resets data members and game state so we can play again
	public void reset() {
		priorGuesses = new int[9000];
		elimGuesses = new boolean[9000];
		nextinP = 0;
		for (int i = 0; i <9000; i++) {
			elimGuesses[i] = false;
			priorGuesses[i] = 0;
		}
		numGuess = 0;
		gameOver = false;// TODO
	}
	
	// Returns true if n is a prior guess; false otherwise.
	public boolean isPriorGuess(int n) {
		boolean Guessed = false;
		for (int i = 0; i < nextinP; i++) {
			if(priorGuesses[i] == n)
				Guessed = true;// TODO
		}
		return Guessed;
		
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
	public static int numMatches(int a, int b) { // DO NOT remove the static qualifier
		// TODO
		int counter = 0;
		if (a%10 == b%10)
			counter++;
		if (a%100 - a%10 == b%100 - b%10)
			counter++;
		if (a%1000 - a%100 == b%1000 - b%100)
			counter++;
		if (a-a%1000 == b - b%1000)
			counter++;
		return counter;
	}
	
	/**
	 * Returns true if the game is over; false otherwise.
	 * The game is over if the number has been correctly guessed
	 * or if all candidates have been eliminated.
	 */
	public boolean isOver() {
		if (gameOver)
			return true;
		else {
			gameOver = true;
			for (int i = 0; i < 9000; i++) {
				if (elimGuesses[i] == false)
					gameOver = false;// TODO
			}
			return gameOver;
		}
	}
	
	// Returns the guess number and adds it to the list of prior guesses.
	public int getGuess() {
		if (nextinP == 0) {
			guess = 1000;
			priorGuesses[0] = guess;
			nextinP++;
		}
		else {
			int i = 0;
			while (elimGuesses[i] == true)
				i++;
			guess = i+1000;
			priorGuesses[nextinP] = guess;
			nextinP++;
			// TODO: add guess to the list of prior guesses.
		}
		numGuess++;
		return guess;
	}
	
	/**
	 * Updates guess based on the number of matches of the previous guess.
	 * If nmatches is 4, the previous guess is correct and the game is over.
	 * Check project description for implementation details.
	 * 
	 * Returns true if the update has no error; false if all candidates
	 * have been eliminated (indicating a state of error);
	 */
	public boolean updateGuess(int nmatches) {
		// TODO
		boolean a = false;
		if (nmatches == 4) {
			gameOver = true;
			return true;
		}
		else {
			for (int i = 0; i < 9000; i++) {
				if (nmatches != numMatches(guess,i+1000))
					elimGuesses[i] = true;
			}
			for (int i = 0; i < 9000; i++) {
				if (elimGuesses[i] == false)
					a = true;
		}
			return a;
				
		}
	}
	
	// Returns the list of guesses so far as an integer array.
	// The size of the array must be the number of prior guesses.
	// Returns null if there has been no prior guess
	public int[] priorGuesses() {
		// TODO
		if (nextinP == 0)
			return null;
		else {
			return trim(priorGuesses);
		}
	}
	
	public int[] trim(int[] a) {
		int i = 0;
		while(a[i] != 0) {
			i++;
		}
		int [] result = new int[i];
		for (int j = 0; j < i; j++)
			result[j] = a[j];
		return result;
	}
}

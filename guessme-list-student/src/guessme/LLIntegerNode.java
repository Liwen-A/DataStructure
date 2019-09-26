package guessme;

/**
 * This class defines a linked list node storing an integer.
 * Use primitive type int (do not use wrapper class Integer)
 * You must provide the following methods:
 * - a constructor
 * - a setInfo method and a getInfo method
 * - a setLink method and a getLink method
 */
public class LLIntegerNode {
	// TODO
	private int number;
	private LLIntegerNode next;
	
	public LLIntegerNode(int a,LLIntegerNode b) {
		this.number = a;
		this.next = b;
	}
	
	public int getInfo() {
		return number;
	}
	
	public LLIntegerNode getLink() {
		return next;
	}
	
	public void setInfo(int a) {
		number = a;
	}
	
	public void setLink(LLIntegerNode a) {
		this.next = a;
	}
	
	
}


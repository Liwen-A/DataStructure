package largeinteger;

import largeinteger.LLNode;

/** The LargeInteger class
 *  This class represents a large, non-negative integer using a linked list.
 *  Each node stores a single digit. The nodes represent all digits in *reverse* order:
 *  the least significant digit is the first node, and the most significant the last node.
 *  For example, 135642 is represented as 2->4->6->5->3->1 in that order.
 */
public class LargeInteger {
	private LLNode<Integer> head;	// head of the list
	private int size;				// size (i.e. number of digits)
	
	// Returns size
	public int size() { return size; }
	// Returns the linked list (used only for JUnit test purpose)
	public LLNode<Integer> getList() { return head; }
	
	public LargeInteger() {
		head = null; size = 0;
	}
	
	/** Constructor that takes a String as input and constructs the linked list.
	 *  You can assume that the input is guaranteed to be valid: i.e. every character
	 *  in the string is between '0' and '9', and the first character is never '0'
	 *  (unless '0' is the only character in the string). You can use input.charAt(i)-'0'
	 *  to convert the character at index i to the integer value of that digit.
	 *  Remember: the list nodes must be in reverse order as the characters in the string.
	 */
	public LargeInteger(String input) {
		head = new LLNode<Integer> (input.charAt(input.length()-1)-'0',null);
		if (input.length() > 1) {
			LLNode<Integer> current = head;
			for (int i = input.length()-2; i >= 0; i--) {
				LLNode<Integer> a = new LLNode<Integer> (input.charAt(i)-'0',null);
				current.link = a;
				current = a;
			}
		}
		size = input.length();
		// TODO
	}
	
	/** Divide *this* large integer by 10 and return this.
	 *  Assume integer division: for example, 23/10 = 2, 8/10 = 0 and so on.
	 */
	public LargeInteger divide10() {
		// TODO
		if (head.link == null) {
			this.head.data = 0;
			this.size = 1;
			return this;
		}
		else {	
			LLNode<Integer> result = head.link;
			this.head = result;
			this.size = size - 1;
			return this;
		}
	}
	
	/** Multiply *this* large integer by 10 and return this.
	 *  For example, 23*10 = 230, 0*10 = 0 etc.
	 */
	public LargeInteger multiply10() {
		// TODO
		if (head.data == 0)
			return this;
		else {
			this.head = new LLNode<Integer> (0,head);
			this.size = size + 1;
			return this;
		}
	}
	
	/** Returns a *new* LargeInteger object representing the sum of this large integer
	 *  and another one (given by that). Your code must correctly handle cases such as
	 *  the two input integers have different sizes (e.g. 2+1000=1002), or there is a
	 *  carry over at the highest digit (e.g. 9999+2=10001).
	 */
	public LargeInteger add(LargeInteger that) {
		// TODO
		LargeInteger result = new LargeInteger();
		LLNode<Integer> a = this.head;
		LLNode<Integer> b = that.head;
		result.head = new LLNode<Integer> (a.data + b.data,null);
		LLNode<Integer> current = result.head;
		if (this.size == that.size) {
			for (int i = 0; i < this.size - 1  ; i++) {
				a = a.link;
				b = b.link;
				current.link = new LLNode<Integer>(a.data+b.data,null);
				current = current.link;
			}
			result.size = this.size;
		}
		else if (this.size > that.size) {
			for (int i = 0; i < that.size - 1 ; i++) {
				a = a.link;
				b = b.link;
				current.link = new LLNode<Integer>(a.data+b.data,null);
				current = current.link;
			}
			for (int i = 0; i < this.size - that.size; i++) {
				a = a.link;
				current.link = new LLNode<Integer>(a.data,null);
				current = current.link;
			}
			result.size = this.size;
			
		}
		else if (this.size < that.size) {
			for (int i = 0; i < this.size - 1; i++) {
				a = a.link;
				b = b.link;
				current.link = new LLNode<Integer>(a.data+b.data,null);
				current = current.link;
			}
			for (int i = 0; i < that.size - this.size; i++) {
				b = b.link;
				current.link = new LLNode<Integer>(b.data,null);
				current = current.link;
			}
			result.size = that.size;
		}
		
		LLNode c= result.head;
		while (c.link != null) {
			if ((int)c.data >= 10) {
				c.data = (int)c.data -10;
				c.link.data = (int)c.link.data + 1;
			}
			c = c.link;
		}
		if ((int)c.data < 10) 
			return result;
		else {
			c.data = (int)c.data - 10;
			c.link = new LLNode<Integer> (1,null);
			result.size++;
			return result;
		}
	}
	
	/** Returns a new LargeInteger object representing the result of multiplying
	 *  this large integer with a non-negative integer x. You can assume x is either
	 *  a positive integer or 0. Hint: you can use a loop and call the 'add' method
	 *  above to accomplish the 'multiply'.
	 */
	public LargeInteger multiply(int x) {
		// TODO
		LargeInteger result = new LargeInteger("0");
		if (x == 0)
			return result;
		else {
			for (int i = 0; i < x; i++) {
				result = result.add(this);
			}
			return result;
		}
	}

	/** Recursive method that converts the list referenced by curr_node back to
	 *  a string representing the integer. Think about what's the base case and
	 *  what it should return. Then think about what it should return in non-base case.
	 *  Hint: refer to the 'printing a list backwards' example we covered in lectures.
	 */
	private String toString(LLNode<Integer> curr_node) {
		// TODO
		if (curr_node == null)
			return "";
		else {
			return toString(curr_node.link) + curr_node.data.toString();
		}
	}
	
	/** Convert this list back to a string representing the large integer.
	 *  This is a public method that jump-starts the call to the recursive method above.
	 */
	public String toString() {
		return toString(head);
	}
	
	// Recursive method to compute factorial
	public static LargeInteger factorial(int n) {
		if(n==0) return new LargeInteger("1");
		return factorial(n-1).multiply(n);
	}
	
	// Recursive method to compute power
	public static LargeInteger pow(int x, int y) {
		if(y==0) return new LargeInteger("1");
		return pow(x, y-1).multiply(x);
	}
}

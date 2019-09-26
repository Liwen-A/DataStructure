package stack;

/**
 * A {@link LinkedStack} is a generic stack that is implemented using
 * a Linked List structure to allow for unbounded size.
 */
public class LinkedStack<T> {
	
	// TODO: define class variables here
	LLNode<T> head = null;
	int size;

	/**
	 * Remove and return the top element on this stack.
	 * If stack is empty, return null (instead of throw exception)
	 */
	public T pop() {
		// TODO
		if (isEmpty())
			return null;
		else {
			LLNode<T> temp = head;
			head = head.link;
			size--;
			return temp.info;
		}
	}

	/**
	 * Return the top element of this stack (do not remove the top element).
	 * If stack is empty, return null (instead of throw exception)
	 */
	public T top() {
		// TODO
		if (isEmpty())
			return null;
		else {
			return head.info;
		}
	}

	/**
	 * Return true if the stack is empty and false otherwise.
	 */
	public boolean isEmpty() {
		// TODO
		if (size == 0)
			return true;
		else {
			return false;
		}
	}

	/**
	 * Return the number of elements in this stack.
	 */
	public int size() {
		// TODO
		return size;
	}

	/**
	 * Pushes a new element to the top of this stack.
	 */
	public void push(T elem) {
		if (head == null) {
			head = new LLNode<T>(elem);
		}
		else {
			LLNode<T> first = new LLNode<T>(elem);
			first.link = head;
			head = first;
		}
		size++;
		// TODO
	}

}

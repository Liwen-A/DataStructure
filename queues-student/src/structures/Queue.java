package structures;

import java.util.NoSuchElementException;

/**************************************************************************************
 * NOTE: before starting to code, check support/structures/UnboundedQueueInterface.java
 * for detailed explanation of each interface method, including the parameters, return
 * values, assumptions, and requirements
 ***************************************************************************************/
public class Queue<T> implements UnboundedQueueInterface<T> {
	Node<T> head;
	Node<T> tail;
	int numElem;

	public Queue() {		
            // TODO 1
		head = null;
		tail = null;
		numElem = 0;
    }
	
	public Queue(Queue<T> other) {
			for (int i = 0; i<other.numElem; i++) {
				this.enqueue(other.peek());
			}
			this.head = other.head;
			this.tail = other.tail;
			this.numElem = other.numElem;
            // TODO 2
	}
	
	@Override
	public boolean isEmpty() {
            // TODO 3
			return numElem==0;
	}

	@Override
	public int size() {
            // TODO 4
            return numElem;
	}

	@Override
	public void enqueue(T element) {
            // TODO 5
		Node<T> a = new Node<T>(element);
		if (isEmpty()) {
			head = a;
			tail = a;
		}
		else {
			tail.next = a;
			tail = tail.next;
		}
		numElem++;
	}

	@Override
	public T dequeue() throws NoSuchElementException {
            // TODO 6
		if (isEmpty())
			throw new NoSuchElementException();
		else {
			T temp = head.data;
			if (head.next == null) 
				tail = null;
			head = head.next;
			numElem--;
            return temp;
		}
	}

	@Override
	public T peek() throws NoSuchElementException {
            // TODO 7
		if (isEmpty())
			throw new NoSuchElementException();
		else {
			return head.data;
		}
	}

	
	@Override
	public UnboundedQueueInterface<T> reversed() {
            // TODO 8
		Queue<T> result = new Queue<T>();
		reverseHelper(this.head, result);
//		Queue<T> data = recur(this.head);
//		for (int i = 0; i < numElem; i++)
//			result.enqueue(data.dequeue());
		return result;
	}
	
	
private void reverseHelper(Queue<T>.Node<T> head2, Queue<T> result) {
		// TODO Auto-generated method stub
		if(head2 == null)
			return;
		reverseHelper(head2.next, result);
		result.enqueue(head2.data);
	}


//	private Queue<T> recur(Node<T> a) {
//		Queue<T> q = new Queue<T>();
//		if (a != null) {
//			recur(a.next);
//			q.enqueue(a.data);
//		}
//		return q;
//		
//	}
	

class Node<T> {
	public T data;
	public Node<T> next;
	public Node(T data) { this.data=data;}
	public Node(T data, Node<T> next) {
		this.data = data; this.next=next;
		}
	}
}


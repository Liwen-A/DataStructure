package structures;

import java.util.Iterator;

//import structures.Queue.Node;

public class RecursiveList<T> implements ListInterface{
	private int numElem;
	private LLNode<T> head;
	
	public RecursiveList(){
		head = null;
		numElem = 0;
	}
	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return new RLIterator<T>(head);
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return numElem;
	}
	@Override
	public ListInterface insertFirst(Object elem) {
		// TODO Auto-generated method stub
		if (elem == null)
			throw new NullPointerException();
		else if (numElem == 0) {
			head = new LLNode(elem,null);
			numElem++;
			return this;
		}
		else {
			LLNode n = new LLNode (elem,head);
			head = n;
			numElem++;
			return this;
		}
	}
	@Override
	public ListInterface insertLast(Object elem) {
		// TODO Auto-generated method stub
		if (elem == null)
			throw new NullPointerException();
		else if (numElem ==0) {
			head = new LLNode(elem,null);
			numElem++;
			return this;
		}
		else {
			LLNode a = Goto(head,numElem-1);
			a.next = new LLNode(elem,null);
			numElem++;
			return this;
		}
	}
	@Override
	public ListInterface insertAt(int index, Object elem) {
		// TODO Auto-generated method stub
		if (index > numElem || index < 0)
			throw new IndexOutOfBoundsException();
		else if (elem == null)
			throw new NullPointerException();
		else if (head==null && index != 0)
			throw new IndexOutOfBoundsException();
		else if (head == null && index == 0) {
			head = new LLNode(elem);
			numElem++;
			return this;
		}
		else if (head != null && index == 0) {
			head = new LLNode(elem,head);
			numElem++;
			return this;
		}
		else {
			LLNode a = Goto(head,index - 1);
			a.next = new LLNode(elem,a.next);
			numElem++;
			return this;
		}

	}
	@Override
	public Object removeFirst() {
		// TODO Auto-generated method stub
		if (isEmpty())
			throw new IllegalStateException();
		else {
			Object a = head.data;
			head = head.next;
			numElem--;
			return a;
		}
	}
	@Override
	public Object removeLast() {
		// TODO Auto-generated method stub
		if (isEmpty())
			throw new IllegalStateException();
		else if (head.next==null){
			Object s = head.data;
			head=null;
			numElem--;
			return s;
		}
		else {
			LLNode a = Goto(head,numElem-2);
			Object s = a.next.data;
			a.next = null;
			numElem--;
			return s;
		}
	}
	@Override
	public Object removeAt(int i) {
		// TODO Auto-generated method stub
		if (i < 0 || i>= numElem)
			throw new IndexOutOfBoundsException();
		else if (i == 0 && head.next == null) {
			Object s = head.data;
			head=null;
			numElem--;
			return s;
		}
		else if (i == 0 && head.next!=null) {
			Object s = head.data;
			numElem--;
			head = head.next;
			return s;
		}
		else {
			LLNode a = Goto(head,i-1);
			Object s = a.next.data;
			a.next = a.next.next;
			numElem--;
			return s;
		}
	}
	@Override
	public Object getFirst() {
		// TODO Auto-generated method stub
		if (isEmpty())
			throw new IllegalStateException();
		else {
			return head.data;
		}
	}
	@Override
	public Object getLast() {
		// TODO Auto-generated method stub
		if (isEmpty())
			throw new IllegalStateException();
		else {
			LLNode a = Goto(head,numElem-1);
			return a.data;
		}
	}
	@Override
	public Object get(int i) {
		// TODO Auto-generated method stub
		if (i < 0|| i >= numElem)
			throw new IndexOutOfBoundsException();
		else {
			LLNode a = Goto(head,i);
			return a.data;
		}
	}
	@Override
	public boolean remove(Object elem) {
		// TODO Auto-generated method stub
		if (elem == null)
			throw new NullPointerException();
		else {
			if (indexOf(elem)==-1)
				return false;
			else {
				if (indexOf(elem)==0) {
					head = head.next;
					numElem--;
					return true;
				}
				else {
					LLNode a = Goto(head,indexOf(elem)-1);
					a.next = a.next.next;
					numElem--;
					return true;
				}
			}
		}
	}
	@Override
	public int indexOf(Object elem) {
		// TODO Auto-generated method stub
		if (elem == null)
			throw new NullPointerException();
		else {
			if (position(head,elem)>numElem)
				return -1;
			else 
				return position(head,elem);
		}
	}
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return numElem==0;
	}

	private int position(LLNode l,Object a) {
		if (l == null)
			return 100;
		else if (l.data.equals(a))
			return 0;
		else {
			return  1+position(l.next,a);
		}
	}
	
	
	private LLNode Goto(LLNode pointer,int index) {
		if (index == 0) {
			return pointer;
		}
		else {
			pointer = pointer.next;
			return Goto(pointer,index-1);
		}
	}
	
}


class LLNode<T> {
	public T data;
	public LLNode<T> next;
	public LLNode(T data) { this.data=data;}
	public LLNode(T data, LLNode<T> next) {
		this.data = data; this.next=next;
		}
	}
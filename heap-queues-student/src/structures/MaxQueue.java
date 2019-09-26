package structures;

import comparators.IntegerComparator;

import java.util.Comparator;
import java.util.Iterator;

public class MaxQueue<V> implements PriorityQueue<Integer, V> {
	StudentArrayHeap<Integer,V> H;
	IntegerComparator c;
	
	public MaxQueue() {
		c = new IntegerComparator();
		H = new StudentArrayHeap<Integer, V>(c);
	}

	@Override
	public PriorityQueue<Integer, V> enqueue(Integer priority, V value) {
		// TODO Auto-generated method stub
		if (priority==null || value ==null)
			throw new NullPointerException();
		else {
			H.add(priority, value);
			return this;
		}
	}

	@Override
	public V dequeue() {
		// TODO Auto-generated method stub
		if (isEmpty())
			throw new IllegalStateException();
		else
			return H.remove();
	}

	@Override
	public V peek() {
		// TODO Auto-generated method stub
		if (isEmpty())
			throw new IllegalStateException();
		else
			return H.peek();
	}

	@Override
	public Iterator<Entry<Integer, V>> iterator() {
		// TODO Auto-generated method stub
		return H.heap.iterator();
	}

	@Override
	public Comparator<Integer> getComparator() {
		// TODO Auto-generated method stub
		return c;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return H.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return H.isEmpty();
	}
}

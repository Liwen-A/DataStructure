package structures;

import java.util.Iterator;
import java.util.NoSuchElementException;
public class RLIterator<T> implements Iterator<T>{
	LLNode<T> iter ;
	
	
	public RLIterator(LLNode<T> s) {
		iter = s;
	}
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return !(iter == null);
	}

	@Override
	public T next() {
		// TODO Auto-generated method stub
		if (!this.hasNext())
			throw new NoSuchElementException();
		else {
			T a = iter.data;
			iter = iter.next;
			return a;
		}
	}
	
	
	public void remove() {
		throw new UnsupportedOperationException();
	}
	

}

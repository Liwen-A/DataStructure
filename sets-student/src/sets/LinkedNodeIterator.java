package sets;

import java.util.Iterator;
import java.util.NoSuchElementException;

class LinkedNodeIterator<E> implements Iterator<E> {
    LinkedNode<E> iter;// TODO (1) define data variables
  
  // Constructors
  public LinkedNodeIterator(LinkedNode<E> head) {
      iter = head;// TODO (2) choose appropriate parameters and do the initialization
  }

  @Override
  public boolean hasNext() {
    // TODO (3)
	if (iter == null)
		return false;
	else
		return true;
  }

  @Override
  public E next() {
    // TODO (4)
	if (!this.hasNext()) {
		throw new NoSuchElementException();
	}
	else {
		E s = iter.getData();
		iter = iter.getNext();
		return s;
	}
  }

  @Override
  public void remove() {
    // Nothing to change for this method
    throw new UnsupportedOperationException();
  }
}

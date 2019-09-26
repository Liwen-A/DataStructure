package sets;

import java.util.Iterator;

/**
 * A LinkedList-based implementation of Set
 */

  /********************************************************
   * NOTE: Before you start, check the Set interface in
   * Set.java for detailed description of each method.
   *******************************************************/
  
  /********************************************************
   * NOTE: for this project you must use linked lists
   * implemented by yourself. You are NOT ALLOWED to use
   * Java arrays of any type, or any Collection-based class 
   * such as ArrayList, Vector etc. You will receive a 0
   * if you use any of them.
   *******************************************************/ 

  /********************************************************
   * NOTE: you are allowed to add new methods if necessary,
   * but do NOT add new files (as they will be ignored).
   *******************************************************/

public class LinkedSet<E> implements Set<E> {
  private LinkedNode<E> head = null;

  // Constructors
  public LinkedSet() {
  }

  public LinkedSet(E e) {
    this.head = new LinkedNode<E>(e, null);
  }

  private LinkedSet(LinkedNode<E> head) {
    this.head = head;
  }

  @Override
  public int size() {
    // TODO (1)
	if (head == null)
		return 0;
	else {
		int counter = 0;
		LinkedNodeIterator<E> iter = (LinkedNodeIterator<E>) this.iterator();
		while (iter.hasNext()) {
			counter++;
			iter.next();
		}
	    return counter;
	}
  }

  @Override
  public boolean isEmpty() {
    // TODO (2)
	if (head == null)
		return true;
	else 
		return false;
  }

  @Override
  public Iterator<E> iterator() {
    return new LinkedNodeIterator<E>(this.head);
  }

  @Override
  public boolean contains(Object o) {
    // TODO (3)
	boolean contain = false;
	LinkedNodeIterator<E> iter = (LinkedNodeIterator<E>) this.iterator();
	while (iter.hasNext()) {
		if (iter.next().equals(o))
			contain = true;
	}
    return contain;
  }

  @Override
  public boolean isSubset(Set<E> that) {
    // TODO (4)
	boolean isSubset = true;
	if (this.isEmpty())
		return true;
	else {
		LinkedNodeIterator<E> iter = (LinkedNodeIterator<E>) this.iterator();
		while (iter.hasNext()) {
			if (!that.contains(iter.next()))
				isSubset = false;
		}
		
	}
    return isSubset;
  }

  @Override
  public boolean isSuperset(Set<E> that) {
    // TODO (5)
	  if (that.isSubset(this))
    	return true;
	  else 
		  return false;
  }

  @Override
  public Set<E> adjoin(E e) {
    // TODO (6)
	if (this.isEmpty()) {
		Set<E> s = new LinkedSet(e);
		return s;
	}
	else {
		LinkedNode temp = new LinkedNode<E>(e, head);
		Set<E> s = new LinkedSet(temp);
		return s;
		}    
	}

  @Override
  public Set<E> union(Set<E> that) {
    // TODO (7)
	if (this.isEmpty()&&that.isEmpty())  
		return null;
	else if (this.isEmpty())
		return that;
	else if (that.isEmpty())
		return this;
	else {
		Set<E> s = this;
		LinkedNodeIterator<E> iter = (LinkedNodeIterator<E>) that.iterator();
		while (iter.hasNext()) {
			E a = iter.next();
			if(!this.contains(a))
					s = s.adjoin(a);
		}
		return s;
	}
  }

  @Override
  public Set<E> intersect(Set<E> that) {
    // TODO (8)
	if (this.isEmpty() || that.isEmpty())
		return null;
	else {
		LinkedNodeIterator<E> iter = (LinkedNodeIterator<E>) that.iterator();
		Set<E> s = new LinkedSet(null);
		while (iter.hasNext()) {
			E a = iter.next();
			if (this.contains(a))
				s = s.adjoin(a);
		}
		return s;
	}
  }

  @Override
  public Set<E> subtract(Set<E> that) {
    // TODO (9)
	LinkedNodeIterator<E> iter = (LinkedNodeIterator<E>) this.iterator();
	Set<E> s = new LinkedSet(null);
	while (iter.hasNext()) {
		E a = iter.next();
		if (!that.contains(a))
			s = s.adjoin(a);
	}
    return s;
  }

  @Override
  public Set<E> remove(E e) {
    // TODO (10)
	if (!this.contains(e))
		return this;
	else {
		Set<E> s = new LinkedSet(null);
		LinkedNodeIterator<E> iter = (LinkedNodeIterator<E>) this.iterator();
		while (iter.hasNext()) {
			E a = iter.next();
			if (!a.equals(e))
				s = s.adjoin(a);
		}
		return s;
	}
  }

  @Override
  @SuppressWarnings("unchecked")
  public boolean equals(Object o) {
    if (! (o instanceof Set)) {
      return false;
    }
    Set<E> that = (Set<E>)o;
    return this.isSubset(that) && that.isSubset(this);
  }

  @Override
    public int hashCode() {
    int result = 0;
    for (E e : this) {
      result += e.hashCode();
    }
    return result;
  }
}

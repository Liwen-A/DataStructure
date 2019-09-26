package structures;

import java.util.Comparator;
import java.util.Iterator;

public class StudentArrayHeap<P, V> extends AbstractArrayHeap<P, V> {
	protected StudentArrayHeap(Comparator<P> comparator) {
		super(comparator);
		// TODO Auto-generated constructor stub
	}

	public int getLeftChildOf(int index) {
		if (index < 0)
			throw new IndexOutOfBoundsException();
		else
			return 1+ 2*index;
	}
	
	public int getRightChildOf(int index) {
		if (index < 0)
			throw new IndexOutOfBoundsException();
		else
			return 2 + 2*index;
	}
	
	public int getParentOf(int index) {
		if (index < 1)
			throw new IndexOutOfBoundsException();
		else
			return (index - 1)/2;
	}
	
	public void bubbleUp(int index) {
		if (index < 1)
			return;
		else {
			Comparator<P> c = this.getComparator();
			int parent = getParentOf(index);
			while((index >0) && (c.compare(heap.get(index).getPriority(),heap.get(parent).getPriority())>0 )){
				this.swap(index, parent);
				index = parent;
				parent =(parent - 1)/2;
		}
		}
	}
	
	public void bubbleDown(int index) {
		Comparator<P> c = this.getComparator();
		int larger;
		while(index < size()/2) {
			int left = 2*index + 1;
			int right = 2*index + 2;
			if ((right < size()) && (c.compare(heap.get(left).getPriority(),heap.get(right).getPriority())< 0))
				larger = right;
			else
				larger = left;
			if(c.compare(heap.get(index).getPriority(), heap.get(larger).getPriority()) >= 0)
				break;
			this.swap(index, larger);
			index = larger;
			
				
		}
	}

}


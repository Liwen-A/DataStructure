package structures;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable<T>> implements
		BSTInterface<T> {
	protected BSTNode<T> root;

	public boolean isEmpty() {
		return root == null;
	}

	public int size() {
		return subtreeSize(root);
	}

	protected int subtreeSize(BSTNode<T> node) {
		if (node == null) {
			return 0;
		} else {
			return 1 + subtreeSize(node.getLeft())
					+ subtreeSize(node.getRight());
		}
	}

	public boolean contains(T t) {
		// TODO
		if (t == null)
			throw new NullPointerException();
		else
			return !(get(t)==null);
	}

	public boolean remove(T t) {
		if (t == null) {
			throw new NullPointerException();
		}
		boolean result = contains(t);
		if (result) {
			root = removeFromSubtree(root, t);
		}
		return result;
	}

	protected BSTNode<T> removeFromSubtree(BSTNode<T> node, T t) {
		// node must not be null
		int result = t.compareTo(node.getData());
		if (result < 0) {
			node.setLeft(removeFromSubtree(node.getLeft(), t));
			return node;
		} else if (result > 0) {
			node.setRight(removeFromSubtree(node.getRight(), t));
			return node;
		} else { // result == 0
			if (node.getLeft() == null) {
				return node.getRight();
			} else if (node.getRight() == null) {
				return node.getLeft();
			} else { // neither child is null
				T predecessorValue = getHighestValue(node.getLeft());
				node.setLeft(removeRightmost(node.getLeft()));
				node.setData(predecessorValue);
				return node;
			}
		}
	}

	private T getHighestValue(BSTNode<T> node) {
		// node must not be null
		if (node.getRight() == null) {
			return node.getData();
		} else {
			return getHighestValue(node.getRight());
		}
	}

	private BSTNode<T> removeRightmost(BSTNode<T> node) {
		// node must not be null
		if (node.getRight() == null) {
			return node.getLeft();
		} else {
			node.setRight(removeRightmost(node.getRight()));
			return node;
		}
	}

	public T get(T t) {
		// TODO
		if (t == null)
			throw new NullPointerException();
		if (root == null)
			return null;
		else {
			BSTNode current = root;
			while (!current.getData().equals(t)) {
				if (t.compareTo((T) current.getData())<0)
					current = current.getLeft();
				else 
					current = current.getRight();
				if (current == null)
					return null;
			}
			return (T) current.getData();
		}
	}


	public void add(T t) {
		if (t == null) {
			throw new NullPointerException();
		}
		root = addToSubtree(root, new BSTNode<T>(t, null, null));
	}

	protected BSTNode<T> addToSubtree(BSTNode<T> node, BSTNode<T> toAdd) {
		if (node == null) {
			return toAdd;
		}
		int result = toAdd.getData().compareTo(node.getData());
		if (result <= 0) {
			node.setLeft(addToSubtree(node.getLeft(), toAdd));
		} else {
			node.setRight(addToSubtree(node.getRight(), toAdd));
		}
		return node;
	}

	@Override
	public T getMinimum() {
		// TODO
		if (isEmpty())
			return null;
		else {
			Iterator<T> a = inorderIterator();
			T min = a.next();
			while (a.hasNext()) {
				T n = a.next();
				if (min.compareTo(n)>0)
					min = n;
			}
			return min;
		}
	}


	@Override
	public T getMaximum() {
		// TODO
		if (isEmpty())
			return null;
		else {
			Iterator<T> a = inorderIterator();
			T max = a.next();
			while (a.hasNext()) {
				T n = a.next();
				if(max.compareTo(n)<0)
					max = n;
			}
			return max;
		}
	}


	@Override
	public int height() {
		// TODO
		return high(root);
	}
	
	private int high(BSTNode a) {
		// TODO
		if (a == null)
			return -1;
		else {
			int c = 1 + high(a.getLeft());
			int b = 1 + high(a.getRight());
			if (c>= b) 
				return c;
			else 
				return b;
		}
	}


	public Iterator<T> preorderIterator() {
		// TODO
		Queue<T> queue = new LinkedList<T>();
		preorderTraverse(queue,root);
		return queue.iterator();
	}
	
	private void preorderTraverse(Queue<T> queue, BSTNode<T> node) {
		if (node != null) {
			queue.add(node.getData());
			preorderTraverse(queue,node.getLeft());
			preorderTraverse(queue,node.getRight());
		}
	}


	public Iterator<T> inorderIterator() {
		Queue<T> queue = new LinkedList<T>();
		inorderTraverse(queue, root);
		return queue.iterator();
	}


	private void inorderTraverse(Queue<T> queue, BSTNode<T> node) {
		if (node != null) {
			inorderTraverse(queue, node.getLeft());
			queue.add(node.getData());
			inorderTraverse(queue, node.getRight());
		}
	}

	public Iterator<T> postorderIterator() {
		// TODO
		Queue<T> queue = new LinkedList<T>();
		postorderTraverse(queue, root);
		return queue.iterator();
	}
	
	private void postorderTraverse(Queue<T> queue, BSTNode node) {
		if (node != null) {
			postorderTraverse(queue,node.getLeft());
			postorderTraverse(queue,node.getRight());
			queue.add((T) node.getData());
		}
	}


	@Override
	public boolean equals(BSTInterface<T> other) {
		// TODO
		if (other==null)
			throw new NullPointerException();
		else if(this.isEmpty()&&other.isEmpty())
			return true;
		else 
			return isSame(root,other.getRoot());
	}
	
	private boolean isSame(BSTNode a, BSTNode b) {
		if (a == null && b == null)
			return true;
		else if (a != null && b != null){
			return a.getData().equals(b.getData()) && isSame(a.getLeft(),b.getLeft()) && isSame(a.getRight(),b.getRight());
		}
		else 
			return false;
	}


	@Override
	public boolean sameValues(BSTInterface<T> other) {
		// TODO
		if (other==null)
			throw new NullPointerException();
		else if (this.isEmpty()&&other.isEmpty())
			return true;
		else if (this.size()!=other.size())
			return false;
		else {
			Iterator<T> a = other.inorderIterator();
			Iterator<T> b = this.inorderIterator();
			while (a.hasNext() && b.hasNext()) {
				if (!a.next().equals(b.next()))
					return false;
			}
			return true;
		}
	}

	@Override
	public boolean isBalanced() {
		// TODO
		if (isEmpty())
			return true;
		else {
			return Math.pow(2, height()) <= size() && size()< Math.pow(2, height()+1);
		}
	}

	@Override
    @SuppressWarnings("unchecked")

	public void balance() {
		// TODO
		T[] s = intoArray();	
		root = Arr2B(s,0,s.length-1);
	}
	
	
	private BSTNode<T> Arr2B (T[] a, int lower,int upper) {
		if (lower > upper)
			return null;
		int mid = (upper+lower)/2;
		BSTNode<T> node = new BSTNode<T>(a[mid],null,null);
		node.setLeft(Arr2B(a,lower,mid-1));
		node.setRight(Arr2B(a,mid+1,upper));
		return node;
	}

	private T[] intoArray() {
		int b= size();
		T[] result = (T[]) new Comparable[b];
		Iterator<T> a = inorderIterator();
		for (int i = 0; i<b; i++) {
			result[i] = a.next();
		}
		return result;
	}
	


	@Override
	public BSTNode<T> getRoot() {
        // DO NOT MODIFY
		return root;
	}

	public static <T extends Comparable<T>> String toDotFormat(BSTNode<T> root) {
		// header
		int count = 0;
		String dot = "digraph G { \n";
		dot += "graph [ordering=\"out\"]; \n";
		// iterative traversal
		Queue<BSTNode<T>> queue = new LinkedList<BSTNode<T>>();
		queue.add(root);
		BSTNode<T> cursor;
		while (!queue.isEmpty()) {
			cursor = queue.remove();
			if (cursor.getLeft() != null) {
				// add edge from cursor to left child
				dot += cursor.getData().toString() + " -> "
						+ cursor.getLeft().getData().toString() + ";\n";
				queue.add(cursor.getLeft());
			} else {
				// add dummy node
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count
						+ ";\n";
				count++;
			}
			if (cursor.getRight() != null) {
				// add edge from cursor to right child
				dot += cursor.getData().toString() + " -> "
						+ cursor.getRight().getData().toString() + ";\n";
				queue.add(cursor.getRight());
			} else {
				// add dummy node
				dot += "node" + count + " [shape=point];\n";
				dot += cursor.getData().toString() + " -> " + "node" + count
						+ ";\n";
				count++;
			}

		}
		dot += "};";
		return dot;
	}

	public static void main(String[] args) {
		for (String r : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
			BSTInterface<String> tree = new BinarySearchTree<String>();
			for (String s : new String[] { "d", "b", "a", "c", "f", "e", "g" }) {
				tree.add(s);
			}
			/*
			Iterator<String> iterator = tree.inorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
			iterator = tree.preorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
			iterator = tree.postorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();

			System.out.println(tree.remove(r));

			iterator = tree.inorderIterator();
			while (iterator.hasNext()) {
				System.out.print(iterator.next());
			}
			System.out.println();
		}
*/
		BSTInterface<String> tree1 = new BinarySearchTree<String>();
		for (String a : new String[] { "a", "b", "c", "d", "e", "f", "g" }) {
			tree1.add(a);
		}
		//System.out.println(tree1.size());
		//System.out.println(tree1.height());
		//System.out.println(tree1.isBalanced());
		//System.out.print(toDotFormat(tree1.getRoot()));
		tree1.balance();
		//System.out.println(tree1.size());
		//System.out.println(tree1.height());
		//System.out.println(tree1.isBalanced());
		System.out.print(toDotFormat(tree1.getRoot()));
		}
}}
package structures;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class ScapegoatTree<T extends Comparable<T>> extends
		BinarySearchTree<T> {
	private int upperBound;


	@Override
	public void add(T t) {
		// TODO
		super.add(t);
		upperBound++;
		BSTNode a = getNode(root,t);
		if (!Cod1()) {
			while(!((double)subtreeSize(a)/(double)subtreeSize(a.parent) > 2.0/3.0))
				a = a.parent;
			BSTNode w = a.parent;
			BSTNode p = w.parent;
			BinarySearchTree s = new BinarySearchTree();
			s.root = w;
			s.balance();
			if(w.getData().compareTo(p.getData())<0)
				p.setLeft(s.root);
			else
				p.setRight(s.root);
		}
	}
	
	
	private BSTNode getNode(BSTNode node, T t) {
			if (node==null)
				return null;
			if(node.getData().compareTo(t)<0)
				return getNode(node.getRight(),t);
			else if (node.getData().compareTo(t)>0)
				return getNode(node.getLeft(),t);
			else
				return node;
	}
	
	private double log23(double t) {
		return Math.log(t)/Math.log(3.0/2.0);
	}
	
	private boolean Cod2() {
		return upperBound <= 2*size();
	}
	private boolean Cod1(){
		return height() <= log23(upperBound);
	}

	@Override
	public boolean remove(T element) {
		// TODO
		boolean result = super.remove(element);
		int s = size();
		if (upperBound > 2*s) {
			this.balance();
			upperBound = s;
		}
		return result;
	}
	
	public static void main(String[] args) {
		ScapegoatTree<Integer> tree1 = new ScapegoatTree<Integer>();
		for (int i: new int[] {3, 1, 5, 0, 4, 2, 6} ) {
			tree1.add(i);
		}
		//System.out.print(toDotFormat(tree1.getRoot()));

		for (int i: new int[] {1, 2, 0, 4}) {
			tree1.remove(i);
		}
		
		BSTInterface<Integer> tree2 = new BinarySearchTree<Integer>();
		tree2.add(5);
		tree2.add(3);
		tree2.add(6);
		//System.out.print(toDotFormat(tree1.getRoot()));
		System.out.print(toDotFormat(tree2.getRoot()));
		
	}
}

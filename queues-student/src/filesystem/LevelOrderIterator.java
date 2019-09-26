package filesystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import structures.Queue;

/**
 * An iterator to perform a level order traversal of part of a 
 * filesystem. A level-order traversal is equivalent to a breadth-
 * first search.
 */
public class LevelOrderIterator extends FileIterator<File> {
	
	/**
	 * Instantiate a new LevelOrderIterator, rooted at the rootNode.
	 * @param rootNode
	 * @throws FileNotFoundException if the rootNode does not exist
	 */
	Queue<File> q;

	
	public LevelOrderIterator(File rootNode) throws FileNotFoundException {
        	// TODO 1
		if(!rootNode.exists())
			throw new FileNotFoundException();
		else {
			q = new Queue<File>();
			
			q.enqueue(rootNode);
		}
	}
	
	@Override
	public boolean hasNext() {
		if(q.isEmpty())
			return false;
		else {
			return true;
		}
	
	}

	@Override
	public File next() throws NoSuchElementException {
        	// TODO 3
		if (!hasNext())
			throw new NoSuchElementException();
		else {
            File a = q.dequeue();
            if (a.isDirectory()) {
            	
            	File [] files = a.listFiles();
            	Arrays.sort(files);
            	for (int i = 0; i <files.length;i++)
            		q.enqueue(files[i]);
            }
            return a;
		}
	}

	@Override
	public void remove() {
		// Leave this one alone.
		throw new UnsupportedOperationException();		
	}

}

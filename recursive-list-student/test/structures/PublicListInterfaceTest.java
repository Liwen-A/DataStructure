package structures;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class PublicListInterfaceTest {

	private ListInterface<String> list;

	@Before
	public void setup(){
          list = new RecursiveList<String>();
	}

	@Test (timeout = 500)
	public void testInsertFirstIsEmptySizeAndGetFirst1() {
		assertTrue("Newly constructed list should be empty.", list.isEmpty());
		assertEquals("Newly constructed list should be size 0.", 0, list.size());
		assertEquals("Insert First should return instance of self", list, list.insertFirst("hello"));
		assertFalse("List should now have elements.", list.isEmpty());
		assertEquals("List should now have 1 element.", 1, list.size());
		assertEquals("First element should .equals \"hello\".", "hello", list.getFirst());
		list.insertFirst("world");
		assertEquals(2, list.size());
		list.insertFirst("foo");
		assertEquals(3, list.size());
		assertEquals("First element should .equals \"foo\".", "foo", list.getFirst());
	}
	
	@Test
	public void testinsertfirst() {
		list = new RecursiveList<String>();
		list.insertFirst("a");
		list.insertFirst("b");
		list.insertFirst("c");
		assertEquals(3,list.size());
		assertEquals("c",list.getFirst());
				
	}
	
	@Test
	public void testinsertLast() {
		list = new RecursiveList<String>();
		list.insertLast("a");
		list.insertLast("b");
		list.insertLast("c");
		list.insertLast("d");
		assertEquals("a",list.getFirst());
		assertEquals("a",list.get(0));
		assertEquals("b",list.get(1));
		assertEquals("c",list.get(2));
		assertEquals("d",list.getLast());
		assertEquals("d",list.get(3));
	}
	
	@Test
	public void testindexOf() {
		list = new RecursiveList<String>();
		list.insertLast("a");
		list.insertLast("b");
		list.insertLast("c");
		list.insertLast("d");
		assertEquals(0,list.indexOf("a"));
		assertEquals(1,list.indexOf("b"));
		assertEquals(2,list.indexOf("c"));
		assertEquals(3,list.indexOf("d"));
	}
	
	@Test 
	public void testRemove() {
		list = new RecursiveList<String>();
		list.insertLast("a");
		list.insertLast("b");
		list.insertLast("c");
		list.insertLast("d");
		assertTrue(list.remove("b"));
		assertFalse(list.remove("h"));
		assertEquals(3,list.size());
		assertEquals(0,list.indexOf("a"));
		assertEquals(1,list.indexOf("c"));
		assertEquals(2,list.indexOf("d"));
		list.remove("a");
		assertEquals("c",list.getFirst());
		assertEquals("d",list.getLast());
		assertEquals(2,list.size());
	}
	
	@Test
	public void testRemoveInsertLast() {
		list = new RecursiveList<String>();
		list.insertLast("a");
		list.removeFirst();
		assertTrue(list.isEmpty());
		list.insertFirst("a");
		list.removeLast();
		assertTrue(list.isEmpty());
		
	}
	
	@Test
	public void testInsertAt() {
		list = new RecursiveList<String>();
		list.insertAt(0, "a");
		assertEquals(0,list.indexOf("a"));
		assertEquals(1,list.size());
		list.insertAt(1,"b");
		assertEquals(1,list.indexOf("b"));
		assertEquals(2,list.size());
	}
	
	@Test
	public void testRemoveAt() {
		list = new RecursiveList<String>();
		list.insertLast("a");
		list.insertLast("b");
		list.insertLast("c");
		list.insertLast("d");
		list.removeAt(0);
		assertEquals(3,list.size());
		assertEquals("b",list.getFirst());
		list.removeAt(2);
		assertEquals("c",list.getLast());
		
	}
	@Test
	public void  testsomeshit() {
		list = new RecursiveList<String>();
		list.insertAt(0,"a");
		assertEquals("a",list.get(0));
		assertEquals(1,list.size());
		list.insertAt(1, "b");
		assertEquals("b",list.get(1));
		assertEquals(2,list.size());
	}
	@Test
	public void  testsomeshit2() {
		list = new RecursiveList<String>();
		list.insertFirst("a");
		list.insertFirst("b");
		list.insertFirst("c");
		list.insertAt(2, "e");
		assertEquals(4,list.size());
		assertEquals("e",list.get(2));
		assertEquals(-1,list.indexOf("n"));
	}
}


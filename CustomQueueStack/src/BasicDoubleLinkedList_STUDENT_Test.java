import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Comparator;
import java.util.ArrayList;

public class BasicDoubleLinkedList_STUDENT_Test {
    private BasicDoubleLinkedList<String> list;

    @Before
    public void setUp() {
        list = new BasicDoubleLinkedList<>();
    }

    @Test
    public void testAddToEnd() {
        list.addToEnd("A");
        list.addToEnd("B");
        list.addToEnd("C");

        assertEquals(3, list.getSize());
        assertEquals("A", list.getFirst());
        assertEquals("C", list.getLast());
    }

    @Test
    public void testAddToFront() {
        list.addToFront("A");
        list.addToFront("B");
        list.addToFront("C");

        assertEquals(3, list.getSize());
        assertEquals("C", list.getFirst());
        assertEquals("A", list.getLast());
    }

    @Test
    public void testGetFirst() {
        list.addToEnd("A");
        assertEquals("A", list.getFirst());
        assertNull(new BasicDoubleLinkedList<String>().getFirst());
    }

    @Test
    public void testGetLast() {
        list.addToEnd("A");
        assertEquals("A", list.getLast());
        assertNull(new BasicDoubleLinkedList<String>().getLast());
    }

    @Test
    public void testRetrieveFirstElement() {
        list.addToEnd("A");
        list.addToEnd("B");
        assertEquals("A", list.retrieveFirstElement());
        assertEquals(1, list.getSize());
        assertEquals("B", list.getFirst());
    }

    @Test
    public void testRetrieveLastElement() {
        list.addToEnd("A");
        list.addToEnd("B");
        assertEquals("B", list.retrieveLastElement());
        assertEquals(1, list.getSize());
        assertEquals("A", list.getLast());
    }

    @Test
    public void testRemove() {
        list.addToEnd("A");
        list.addToEnd("B");
        list.addToEnd("C");

        Comparator<String> comparator = String::compareTo;
        assertNotNull(list.remove("B", comparator));
        assertEquals(2, list.getSize());
        assertNull(list.remove("B", comparator)); // B is already removed
    }

    @Test
    public void testToArrayList() {
        list.addToEnd("A");
        list.addToEnd("B");
        list.addToEnd("C");

        ArrayList<String> expectedList = new ArrayList<>();
        expectedList.add("A");
        expectedList.add("B");
        expectedList.add("C");

        assertEquals(expectedList, list.toArrayList());
    }
}

import static org.junit.Assert.*;
import java.util.Comparator;
import org.junit.Before;
import org.junit.Test;

public class SortedDoubleLinkedList_STUDENT_Test {
    
    private SortedDoubleLinkedList<String> sortedList;

    // Comparator to compare strings
    private Comparator<String> stringComparator = new Comparator<String>() {
        @Override
        public int compare(String s1, String s2) {
            return s1.compareTo(s2);
        }
    };

    @Before
    public void setUp() {
        sortedList = new SortedDoubleLinkedList<>(stringComparator);
    }

    @Test
    public void testAddElementToEmptyList() {
        sortedList.add("Banana");
        assertEquals(1, sortedList.getSize());
        assertEquals("Banana", sortedList.getFirst());
        assertEquals("Banana", sortedList.getLast());
    }

    @Test
    public void testAddElementInSortedOrder() {
        sortedList.add("Banana");
        sortedList.add("Apple");
        sortedList.add("Cherry");

        assertEquals(3, sortedList.getSize());
        assertEquals("Apple", sortedList.getFirst());
        assertEquals("Cherry", sortedList.getLast());
        
        // Test the order of elements
        String[] expectedOrder = {"Apple", "Banana", "Cherry"};
        int index = 0;
        for (String item : sortedList) {
            assertEquals(expectedOrder[index++], item);
        }
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAddToEndNotSupported() {
        sortedList.addToEnd("Grapes");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testAddToFrontNotSupported() {
        sortedList.addToFront("Peach");
    }

    @Test
    public void testRemoveElement() {
        sortedList.add("Banana");
        sortedList.add("Apple");
        sortedList.add("Cherry");

        // Remove "Banana"
        BasicDoubleLinkedList.Node removedNode = sortedList.remove("Banana", stringComparator);
        assertNotNull(removedNode);
        assertEquals("Banana", removedNode.data);
        assertEquals(2, sortedList.getSize());
        assertEquals("Apple", sortedList.getFirst());
    }

    @Test
    public void testRemoveNonExistentElement() {
        sortedList.add("Banana");
        sortedList.add("Apple");

        BasicDoubleLinkedList.Node removedNode = sortedList.remove("Orange", stringComparator);
        assertNull(removedNode);
        assertEquals(2, sortedList.getSize()); // Size should remain unchanged
    }
}

import java.util.Comparator;
import java.util.ListIterator;

/**
 * The SortedDoubleLinkedList class is an extension of the BasicDoubleLinkedList
 * @param <T> the type of elements in this list
 * @author Raushan Oshan
 */
public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {

    private Comparator<T> comparator;

    /**
     * Constructs a new SortedDoubleLinkedList with the specified comparator.
     *
     * @param comparableObject the comparator used to maintain order in the list
     */
    public SortedDoubleLinkedList(Comparator<T> comparableObject) {
        super();
        this.comparator = comparableObject;
    }

    /**
     * Adds an element to the list in sorted order based on the comparator.
     *
     * @param data the element to be added to the list
     */
    public void add(T data) {
        Node insertNode = new Node(data);
    

        // If the list is empty, set the head and tail to the new node
        if (size == 0) {
            head = insertNode;
            tail = insertNode;
            size++;
            return;
        }

        // Insert before the head if the data is less than the head's data
        if (comparator.compare(data, head.data) < 0) {
            insertNode.next = head;
            head.prev = insertNode;
            head = insertNode;
            size++;
            return;
        }

        // Insert after the tail if the data is greater than the tail's data
        if (comparator.compare(data, tail.data) > 0) {
            insertNode.prev = tail;
            tail.next = insertNode;
            tail = insertNode;
            size++;
            return;
        }

        // Find the correct insertion point by traversing the list
        Node leftNode = head;
        Node rightNode = head.next;

        while (rightNode != null) {
            // Check if the data is between leftNode and rightNode
            if (comparator.compare(data, leftNode.data) > 0 && comparator.compare(data, rightNode.data) < 0) {
                insertNode.prev = leftNode;
                insertNode.next = rightNode;
                leftNode.next = insertNode;
                rightNode.prev = insertNode;
                size++;
                break;
            }
            leftNode = rightNode;
            rightNode = rightNode.next;
        }
    }

    /**
     * This operation is not supported for sorted lists.
     *
     * @throws UnsupportedOperationException if this method is called
     */
    @Override
    public void addToEnd(T data) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Invalid operation for sorted list");
    }

    /**
     * This operation is not supported for sorted lists.
     *
     * @throws UnsupportedOperationException if this method is called
     */
    @Override
    public void addToFront(T data) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Invalid operation for sorted list");
    }

    /**
     * Returns an iterator over the elements in this sorted list.
     *
     * @return a ListIterator for the sorted list
     */
    @Override
    public ListIterator<T> iterator() {
        return super.iterator();
    }

    /**
     * Removes the first occurrence of the specified element from the sorted list.
     *
     * @param data the element to be removed from the list
     * @param comparator the comparator used to compare elements
     * @return the removed node, or null if the element was not found
     */
    @Override
    public BasicDoubleLinkedList.Node remove(T data, Comparator<T> comparator) {
        return super.remove(data, comparator);
    }
}

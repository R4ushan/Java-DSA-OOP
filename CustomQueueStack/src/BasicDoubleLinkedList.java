import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;
/**
 * A basic implementation of a doubly linked list.
 * 
 * @param <T> the type of elements in this list
 * @author Raushan Oshan
 */
public class BasicDoubleLinkedList<T> implements Iterable<T> {

    protected Node head;
    protected Node tail;
    protected int size;

    /**
     * Initializes an empty doubly linked list.
     */
    public BasicDoubleLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Returns the number of elements in the list.
     * 
     * @return the size of the list
     */
    public int getSize() {
        return size;
    }

    /**
     * Adds an element to the end of the list.
     * 
     * @param data the element to be added
     */
    public void addToEnd(T data) {
        Node endNode = new Node(data);

        if (size == 0) {
            head = endNode;
            tail = endNode;
        } else {
            endNode.prev = tail;
            tail.next = endNode;
            tail = endNode;
        }

        size++;
    }

    /**
     * Adds an element to the front of the list.
     * 
     * @param data the element to be added
     */
    public void addToFront(T data) {
        Node frontNode = new Node(data);

        if (size == 0) {
            head = frontNode;
            tail = frontNode;
        } else {
            frontNode.next = head;
            head.prev = frontNode;
            head = frontNode;
        }

        size++;
    }

    /**
     * Retrieves the first element in the list without removing it.
     * 
     * @return the first element, or null if the list is empty
     */
    public T getFirst() {
        return size == 0 ? null : head.data;
    }

    /**
     * Retrieves the last element in the list without removing it.
     * 
     * @return the last element, or null if the list is empty
     */
    public T getLast() {
        return size == 0 ? null : tail.data;
    }

    /**
     * Returns an iterator over the elements in this list.
     * 
     * @return a ListIterator for the list
     */
    @Override
    public ListIterator<T> iterator() {
        return new DoubleLinkedListIterator();
    }

    /**
     * Removes the first occurrence of the specified element from the list.
     * 
     * @param targetData the element to be removed
     * @param comparator the comparator used to compare elements
     * @return the removed node, or null if not found
     */
    public Node remove(T targetData, java.util.Comparator<T> comparator) {
        Node currentNode = head;

        if (size == 0) {
            return null;
        }

        while (currentNode != null) {
            if (comparator.compare(targetData, currentNode.data) == 0) {
                if (currentNode == head) {
                    head = currentNode.next;
                    if (head != null) {
                        head.prev = null;
                    }
                } else {
                    currentNode.prev.next = currentNode.next;
                }

                if (currentNode == tail) {
                    tail = currentNode.prev;
                    if (tail != null) {
                        tail.next = null;
                    }
                } else {
                    if (currentNode.next != null) {
                        currentNode.next.prev = currentNode.prev;
                    }
                }

                size--;
                return currentNode;
            }
            currentNode = currentNode.next;
        }
        return null; // Data not found
    }

    /**
     * Retrieves and removes the first element in the list.
     * 
     * @return the first element, or null if the list is empty
     */
    public T retrieveFirstElement() {
        if (size == 0) {
            return null;
        }

        T firstElement = getFirst();
        head = head.next;

        if (head != null) {
            head.prev = null;
        } else {
            tail = null; // List is now empty
        }

        size--;
        return firstElement;
    }

    /**
     * Retrieves and removes the last element in the list.
     * 
     * @return the last element, or null if the list is empty
     */
    public T retrieveLastElement() {
        if (size == 0) {
            return null;
        }

        T lastElement = getLast();
        tail = tail.prev;

        if (tail != null) {
            tail.next = null;
        } else {
            head = null; // List is now empty
        }

        size--;
        return lastElement;
    }

    /**
     * Converts the linked list to an ArrayList.
     * 
     * @return an ArrayList containing all elements of the list
     */
    public ArrayList<T> toArrayList() {
        ArrayList<T> arrayList = new ArrayList<>();
        Node currentNode = head;

        while (currentNode != null) {
            arrayList.add(currentNode.data);
            currentNode = currentNode.next;
        }

        return arrayList;
    }

    /**
     * Represents a node in the doubly linked list.
     */
    public class Node {
        T data;
        Node prev;
        Node next;

        public Node(T dataNode) {
            this.data = dataNode;
        }
    }

    /**
     * An iterator for traversing the elements in the doubly linked list.
     */
    private class DoubleLinkedListIterator implements ListIterator<T> {
        private Node cursor = head;

        @Override
        public boolean hasNext() {
            return cursor != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            T element = cursor.data;
            cursor = cursor.next;
            return element;
        }

        @Override
        public boolean hasPrevious() {
            return cursor != head;
        }

        @Override
        public T previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }

            if (cursor == null) {
                cursor = tail; 
            } else {
                cursor = cursor.prev;
            }

            return cursor.data;
        }

        @Override
        public int nextIndex() {
            
            throw new UnsupportedOperationException();
        }

        @Override
        public int previousIndex() {
            
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(T e) {
            
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(T e) {
            
            throw new UnsupportedOperationException();
        }
    }
}

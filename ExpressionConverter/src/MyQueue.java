import java.util.ArrayList;

/**
 * MyQueue is a generic implementation of a queue data structure using an ArrayList.
 * It implements the QueueInterface for basic queue operations.
 *
 * @param <T> the type of elements held in this queue
 */
public class MyQueue<T> implements QueueInterface<T> {

    // The maximum size of the queue
    private final int MAX_SIZE;
    
    // The list to hold the elements of the queue
    private ArrayList<T> elements;

    /**
     * Default constructor that initializes the queue with a maximum size of 50.
     */
    public MyQueue() {
        this(50);
    }

    /**
     * Constructor that initializes the queue with a specified maximum size.
     * 
     * @param size the maximum size of the queue
     */
    public MyQueue(int size) {
        MAX_SIZE = size;
        elements = new ArrayList<>(MAX_SIZE); // Initialize the ArrayList with the specified capacity
    }

    /**
     * Constructor that initializes the queue with elements from the provided ArrayList.
     * 
     * @param elements the initial elements to be added to the queue
     */
    public MyQueue(ArrayList<T> elements) {
        this(elements.size()); // Set the queue size to the size of the provided list
        for (T element : elements) {
            try {
                enqueue(element); // Attempt to enqueue each element
            } catch (QueueOverflowException e) {
                System.err.println(e); // Print an error message if the queue is full
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty(); // Check if the queue is empty
    }

    @Override
    public boolean isFull() {
        return elements.size() == MAX_SIZE; // Check if the queue has reached its maximum size
    }

    @Override
    public T dequeue() throws QueueUnderflowException {
        if (isEmpty()) {
            throw new QueueUnderflowException(); // Throw an exception if attempting to dequeue from an empty queue
        }
        return elements.remove(0); // Remove and return the element at the front of the queue
    }

    @Override
    public int size() {
        return elements.size(); // Return the number of elements in the queue
    }

    @Override
    public boolean enqueue(T e) throws QueueOverflowException {
        if (isFull()) {
            throw new QueueOverflowException(); // Throw an exception if attempting to enqueue when the queue is full
        }
        elements.add(e); // Add element at the end of the queue
        return true; // Return true if the enqueue operation is successful
    }

    @Override
    public String toString() {
        if (elements.isEmpty()) return ""; // Return an empty string if the queue is empty
        String result = elements.get(0).toString(); // Start with the first element's string representation
        for (int i = 1; i < elements.size(); i++) {
            if (elements.get(i) != null) {
                result += elements.get(i).toString(); // Append each subsequent element's string representation
            }
        }
        return result; // Return the concatenated string of all elements
    }

    @Override
    public String toString(String delimiter) {
        if (elements.isEmpty()) return ""; // Return an empty string if the queue is empty
        String result = elements.get(0).toString(); // Start with the first element's string representation
        for (int i = 1; i < elements.size(); i++) {
            if (elements.get(i) != null) {
                result += delimiter + elements.get(i).toString(); // Append each subsequent element with the specified delimiter
            }
        }
        return result; // Return the concatenated string of all elements separated by the delimiter
    }

    @Override
    public void fill(ArrayList<T> list) {
        for (T element : list) {
            try {
                enqueue(element); // Attempt to enqueue each element from the list
            } catch (QueueOverflowException e) {
                e.printStackTrace(); // Print the stack trace if the queue is full
            }
        }
    }
}

import java.util.ArrayList;

/**
 * MyStack is a generic implementation of a stack data structure using an ArrayList.
 * It implements the StackInterface for basic stack operations.
 *
 * @param <T> the type of elements held in this stack
 */
public class MyStack<T> implements StackInterface<T> {

    // The current number of elements in the stack
    private int numOfElements;
    
    // The maximum size of the stack
    private final int max;
    
    // The list to hold the elements of the stack
    private ArrayList<T> elements;

    /**
     * Default constructor that initializes the stack with a maximum size of 5.
     */
    public MyStack() {
        this(5);
    }

    /**
     * Constructor that initializes the stack with a specified maximum size.
     * 
     * @param size the maximum size of the stack
     */
    public MyStack(int size) {
        max = size; // Set the maximum size
        elements = new ArrayList<>(max); // Initialize the ArrayList with the specified capacity
        numOfElements = 0; // Initialize the number of elements to 0
    }

    /**
     * Constructor that initializes the stack with elements from the provided ArrayList.
     * 
     * @param elements the initial elements to be added to the stack
     */
    public MyStack(ArrayList<T> elements) {
        this(elements.size()); // Set the stack size to the size of the provided list
        for (T element : elements) {
            try {
                push(element); // Attempt to push each element onto the stack
            } catch (StackOverflowException e) {
                System.err.println(e); // Print an error message if the stack is full
            }
        }
    }

    @Override
    public boolean isEmpty() {
        return numOfElements == 0; // Check if the stack is empty
    }

    @Override
    public boolean isFull() {
        return numOfElements == max; // Check if the stack has reached its maximum size
    }

    @Override
    public T pop() throws StackUnderflowException {
        if (isEmpty()) {
            throw new StackUnderflowException(); // Throw an exception if attempting to pop from an empty stack
        }
        T element = elements.remove(--numOfElements); // Remove and return the top element of the stack
        return element;
    }

    @Override
    public T top() throws StackUnderflowException {
        if (isEmpty()) {
            throw new StackUnderflowException(); // Throw an exception if attempting to access the top of an empty stack
        }
        return elements.get(numOfElements - 1); // Return the top element of the stack without removing it
    }

    @Override
    public int size() {
        return numOfElements; // Return the number of elements in the stack
    }

    @Override
    public boolean push(T e) throws StackOverflowException {
        if (isFull()) {
            throw new StackOverflowException(); // Throw an exception if attempting to push when the stack is full
        }
        elements.add(e); // Add the element to the top of the stack
        numOfElements++; // Increment the count of elements
        return true; // Return true if the push operation is successful
    }

    @Override
    public String toString() {
        if (numOfElements == 0) return ""; // Return an empty string if the stack is empty
        String result = elements.get(0).toString(); // Start with the first element's string representation
        for (int i = 1; i < numOfElements; i++) {
            if (elements.get(i) != null) {
                result += elements.get(i).toString(); // Append each subsequent element's string representation
            }
        }
        return result; // Return the concatenated string of all elements
    }

    @Override
    public String toString(String delimiter) {
        if (numOfElements == 0) return ""; // Return an empty string if the stack is empty
        String result = elements.get(0).toString(); // Start with the first element's string representation
        for (int i = 1; i < numOfElements; i++) {
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
                push(element); // Attempt to push each element from the list onto the stack
            } catch (StackOverflowException e) {
                e.printStackTrace(); // Print the stack trace if the stack is full
            }
        }
    }
}

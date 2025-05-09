import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * CourseDBStructure class
 * @author Raushan Oshan
 */
public class CourseDBStructure implements CourseDBStructureInterface {

    private LinkedList<CourseDBElement>[] hashTable;
    private int hashTableSize;

    /**
     * Constructor with estimated size to initialize the hash table.
     * @param estimatedSize the estimated number of courses
     */
    @SuppressWarnings("unchecked")
    public CourseDBStructure(int estimatedSize) {
        this.hashTableSize = findNextPrime((int) (estimatedSize / 1.5));
        this.hashTable = new LinkedList[hashTableSize];
    }

    /**
     * Constructor used for testing, initializes with specified size.
     * @param testing indicates testing mode
     * @param size the fixed size of the hash table
     */
    @SuppressWarnings("unchecked")
    public CourseDBStructure(String testing, int size) {
        this.hashTableSize = size;
        this.hashTable = new LinkedList[hashTableSize];
    }

    /**
     * Helper to find the next prime greater than a minimum size.
     */
    private int findNextPrime(int min) {
        for (int num = min; true; num++) {
            if (isPrime(num)) return num;
        }
    }

    /**
     * Adds a CourseDBElement to the hash table.
     * @param element the CourseDBElement to add
     */
    @Override
    public void add(CourseDBElement element) {
        int index = Math.abs(element.getCRN()) % hashTableSize;
        if (hashTable[index] == null) {
            hashTable[index] = new LinkedList<>();
        }
        hashTable[index].removeIf(e -> e.getCRN() == element.getCRN());  // Remove if it already exists
        hashTable[index].add(element);
    }

    /**
     * Gets a CourseDBElement by its CRN.
     * @param crn the CRN to search for
     * @return the matching CourseDBElement
     * @throws IOException if not found
     */
    @Override
    public CourseDBElement get(int crn) throws IOException {
        int index = Math.abs(crn) % hashTableSize;
        if (hashTable[index] != null) {
            for (CourseDBElement element : hashTable[index]) {
                if (element.getCRN() == crn) return element;
            }
        }
        throw new IOException("Course not found");
    }

    /**
     * Helper method to check if a number is prime.
     */
    private boolean isPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    /**
     * @return an ArrayList of string representations of all courses
     */
    @Override
    public ArrayList<String> showAll() {
    ArrayList<String> courses = new ArrayList<>();
    for (int i = 0; i < hashTableSize; i++) {
        if (hashTable[i] != null) {  // Check if the linked list exists
            for (CourseDBElement element : hashTable[i]) {  // Enhanced for-loop to iterate through the linked list
                courses.add(element.toString());  // No need for newline in toString() method
            }
        }
    }
    return courses;
}


    /**
     * Returns the size of the hash table.
     * @return the hash table size
     */
    @Override
    public int getTableSize() {
        return hashTableSize;
    }
}

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class MorseCodeTree_STUDENT_Test {

    private MorseCodeTree morseCodeTree;

    @Before
    public void setUp() {
        // Initialize MorseCodeTree before each test
        morseCodeTree = new MorseCodeTree();
    }

    @Test
    public void testGetRoot() {
        // Test if the root is not null after building the tree
        assertNotNull("Root should not be null", morseCodeTree.getRoot());
    }

    @Test
    public void testInsertAndFetch() {
        // Insert a new character (example: Morse code for 'A' is ".-")
        morseCodeTree.insert(".-", "a");

        // Fetch the character using Morse code and verify if it returns the correct value
        String result = morseCodeTree.fetch(".-");
        assertEquals("The fetched character should be 'a'", "a", result);
    }

    @Test
    public void testFetchNonExistentCode() {
        // Test fetching a non-existent Morse code (should return empty string or null)
        String result = morseCodeTree.fetch("...---...");
        assertEquals("The fetched character for an invalid Morse code should be empty string", "", result);
    }

    @Test
    public void testToArrayList() {
        // Convert tree to ArrayList and check its size
        ArrayList<String> treeData = morseCodeTree.toArrayList();
        
        // Ensure the tree data matches the expected number of nodes
        assertEquals("The tree should contain 27 nodes", 27, treeData.size());
    }

    @Test
    public void testInsertAndCheckMultipleLetters() {
        // Insert some additional letters (e.g., Morse code for 'B' is "-...")
        morseCodeTree.insert("-...", "b");
        morseCodeTree.insert(".--.", "p");

        // Fetch the characters and check
        assertEquals("The fetched character should be 'b'", "b", morseCodeTree.fetch("-..."));
        assertEquals("The fetched character should be 'p'", "p", morseCodeTree.fetch(".--."));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testDeleteNotSupported() {
        // Test the delete operation (should throw UnsupportedOperationException)
        morseCodeTree.delete("A");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUpdateNotSupported() {
        // Test the update operation (should throw UnsupportedOperationException)
        morseCodeTree.update();
    }

    @Test
    public void testBuildTree() {
        // Check the tree structure after building it
        TreeNode<String> root = morseCodeTree.getRoot();
        assertNotNull("Root should not be null after building the tree", root);
        
        // Verify specific children of the root node
        assertNotNull("Left child of root should not be null", root.getLeftChild());
        assertNotNull("Right child of root should not be null", root.getRightChild());

        // Verify specific children of the root's left child
        TreeNode<String> leftChild = root.getLeftChild();
        assertNotNull("Left child of 'E' should not be null", leftChild.getLeftChild());
        assertNotNull("Right child of 'E' should not be null", leftChild.getRightChild());
    }
}


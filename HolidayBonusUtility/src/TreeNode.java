/**
 * Represents a node in a binary tree, typically used for storing data in structures like a Morse code tree.
 * This class stores a generic data type and provides methods for manipulating and retrieving data from the node,
 * as well as accessing its left and right children.
 *
 * @param <T> the type of data stored in the node
 * 
 * @author Raushan Oshan
 */
public class TreeNode<T> {
    // Class Variables
    protected T data = null;               // The data stored in the node
    protected TreeNode<T> left = null;      // Reference to the left child node
    protected TreeNode<T> right = null;     // Reference to the right child node
    
    /**
     * Constructs a new tree node with the specified data.
     *
     * @param data the data to be stored in the node
     */
    public TreeNode(T data) {
        this.data = data;
    }
    
    /**
     * Constructs a new tree node that is a copy of the given node.
     *
     * @param node the TreeNode to copy from
     */
    public TreeNode(TreeNode<T> node) {
        this.data = node.getData();
    }
    
    /**
     * Sets the left child of this node.
     *
     * @param node the TreeNode to set as the left child
     */
    public void setLeftChild(TreeNode<T> node) {
        this.left = node;
    }
    
    /**
     * Sets the right child of this node.
     *
     * @param node the TreeNode to set as the right child
     */
    public void setRightChild(TreeNode<T> node) {
        this.right = node;
    }
    
    /**
     * Retrieves the data stored in this node.
     *
     * @return the data of type T stored in the node
     */
    public T getData() {
        return this.data;
    }
    
    /**
     * Retrieves the left child of this node.
     *
     * @return the left child node, or null if no left child is set
     */
    public TreeNode<T> getLeftChild() {
        return this.left;
    }
    
    /**
     * Retrieves the right child of this node.
     *
     * @return the right child node, or null if no right child is set
     */
    public TreeNode<T> getRightChild() {
        return this.right;
    }
}
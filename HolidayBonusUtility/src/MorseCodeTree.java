/**
 * @author Raushan Oshan
 */
 import java.util.ArrayList;
 /**
  * MorseCodeTree class
  */
 public class MorseCodeTree extends java.lang.Object implements LinkedConverterTreeInterface<String> {
 
     TreeNode<String> treeRoot;
 
     /**
      * Constructor calls buildTree() to initialize the tree
      */
     public MorseCodeTree() {
         buildTree();
     }
 
     /**
      * Returns the root of the tree
      * @return root of the tree
      */
     @Override
     public TreeNode<String> getRoot() {
         return treeRoot;
     }
 
     /**
      * Sets the root of the tree
      * @param newNode new root node
      */
     @Override
     public void setRoot(TreeNode<String> newNode) {
         this.treeRoot = newNode;
     }
 
     /**
      * Inserts a node based on the given Morse code
      * @param code the Morse code
      * @param result the character to store
      */
     @Override
     public void insert(String code, String result) {
         addNode(treeRoot, code, result);
     }
 
     /**
      * Recursive method to add a node based on Morse code
      * @param root current root of the tree
      * @param code Morse code
      * @param letter character to add
      */
     @Override
     public void addNode(TreeNode<String> root, String code, String letter) {
         int subStringIndex = 1;
 
         TreeNode<String> addLeftNode = new TreeNode<>(letter);
         TreeNode<String> addRightNode = new TreeNode<>(letter);
 
         if (code == null) return;
 
         if (code.length() == 1) {
             if (letter.equals(".")) {
                 root.setLeftChild(addLeftNode);
             } else if (letter.equals("-")) {
                 root.setRightChild(addRightNode);
             }
             return;
         } else {
             if (code.charAt(0) == '.') {
                 root = root.getLeftChild();
             } else if (code.charAt(0) == '-') {
                 root = root.getRightChild();
             }
             addNode(root, code.substring(subStringIndex), letter);
         }
     }
 
     /**
      * Fetches the data associated with a given Morse code
      * @param code the Morse code
      * @return corresponding character
      */
     @Override
     public String fetch(String code) {
         return fetchNode(treeRoot, code);
     }
 
     /**
      * Recursive method to fetch data based on Morse code
      * @param root current root
      * @param code Morse code
      * @return character at the node
      */
     @Override
     public String fetchNode(TreeNode<String> root, String code) {
         int subStringIndex = 1;
         String letterAtNode = "";
 
         if (code.length() == 1) {
             if (code.charAt(0) == '.' && root != null && root.getLeftChild() != null) {
                 letterAtNode = root.getLeftChild().getData();
                 return letterAtNode;
             } else if (code.charAt(0) == '-' && root != null && root.getRightChild() != null) {
                 letterAtNode = root.getRightChild().getData();
                 return letterAtNode;
             }
         }
 
         if (code.length() != 0) {
             if (code.charAt(0) == '.') {
                 if (root != null) {
                     root = root.getLeftChild();
                 }
                 letterAtNode = fetchNode(root, code.substring(subStringIndex));
             } else if (code.charAt(0) == '-') {
                 if (root != null) {
                     root = root.getRightChild();
                 }
                 letterAtNode = fetchNode(root, code.substring(subStringIndex));
             }
         }
 
         return letterAtNode;
     }
 
     /**
      * Deletes a node (not supported for MorseCodeTree)
      * @param data data to delete
      * @return throws UnsupportedOperationException
      * @throws UnsupportedOperationException
      */
     @Override
     public LinkedConverterTreeInterface<String> delete(String data) throws UnsupportedOperationException {
         throw new UnsupportedOperationException();
     }
 
     /**
      * Updates the tree (not supported for MorseCodeTree)
      * @return throws UnsupportedOperationException
      * @throws UnsupportedOperationException
      */
     @Override
     public LinkedConverterTreeInterface<String> update() throws UnsupportedOperationException {
         throw new UnsupportedOperationException();
     }
 
     /**
      * Builds the MorseCodeTree by inserting nodes
      */
     @Override
     public void buildTree() {
         TreeNode<String> root = new TreeNode<String>("");
 
         // Create TreeNodes for each letter
         TreeNode<String> a = new TreeNode<String>("a");
         TreeNode<String> b = new TreeNode<String>("b");
         TreeNode<String> c = new TreeNode<String>("c");
         TreeNode<String> d = new TreeNode<String>("d");
         TreeNode<String> e = new TreeNode<String>("e");
         TreeNode<String> f = new TreeNode<String>("f");
         TreeNode<String> g = new TreeNode<String>("g");
         TreeNode<String> h = new TreeNode<String>("h");
         TreeNode<String> i = new TreeNode<String>("i");
         TreeNode<String> j = new TreeNode<String>("j");
         TreeNode<String> k = new TreeNode<String>("k");
         TreeNode<String> l = new TreeNode<String>("l");
         TreeNode<String> m = new TreeNode<String>("m");
         TreeNode<String> n = new TreeNode<String>("n");
         TreeNode<String> o = new TreeNode<String>("o");
         TreeNode<String> p = new TreeNode<String>("p");
         TreeNode<String> q = new TreeNode<String>("q");
         TreeNode<String> r = new TreeNode<String>("r");
         TreeNode<String> s = new TreeNode<String>("s");
         TreeNode<String> t = new TreeNode<String>("t");
         TreeNode<String> u = new TreeNode<String>("u");
         TreeNode<String> v = new TreeNode<String>("v");
         TreeNode<String> w = new TreeNode<String>("w");
         TreeNode<String> x = new TreeNode<String>("x");
         TreeNode<String> y = new TreeNode<String>("y");
         TreeNode<String> z = new TreeNode<String>("z");
 
         setRoot(root);
 
         // Set up children for the root and its sub-trees
         root.setLeftChild(e);
         root.setRightChild(t);
 
         e.setLeftChild(i);
         e.setRightChild(a);
 
         t.setLeftChild(n);
         t.setRightChild(m);
 
         i.setLeftChild(s);
         i.setRightChild(u);
 
         a.setLeftChild(r);
         a.setRightChild(w);
 
         n.setLeftChild(d);
         n.setRightChild(k);
 
         m.setLeftChild(g);
         m.setRightChild(o);
 
         s.setLeftChild(h);
         s.setRightChild(v);
 
         u.setLeftChild(f);
 
         r.setLeftChild(l);
 
         w.setLeftChild(p);
         w.setRightChild(j);
 
         d.setLeftChild(b);
         d.setRightChild(x);
 
         k.setLeftChild(c);
         k.setRightChild(y);
 
         g.setLeftChild(z);
         g.setRightChild(q);
     }
 
     /**
      * Returns an ArrayList of tree data in LNR (Inorder) order
      * @return ArrayList of tree data
      */
     @Override
     public ArrayList<String> toArrayList() {
         ArrayList<String> treeToArrayList = new ArrayList<String>();
         LNRoutputTraversal(treeRoot, treeToArrayList);
         return treeToArrayList;
     }
 
     /**
      * Traverses the tree in LNR order and adds data to the list
      * @param root current root
      * @param list ArrayList to store data
      */
     @Override
     public void LNRoutputTraversal(TreeNode<String> root, ArrayList<String> list) {
         if (root.getLeftChild() != null) {
             LNRoutputTraversal(root.getLeftChild(), list);
         }
 
         list.add(root.getData());
 
         if (root.getRightChild() != null) {
             LNRoutputTraversal(root.getRightChild(), list);
         }
     }
 }
 
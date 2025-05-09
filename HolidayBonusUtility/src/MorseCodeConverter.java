/**
 * Converts Morse code to English using a MorseCodeTree.
 * Provides methods to translate Morse code from strings or files and to print the tree.
 * 
 * @author Raushan Oshan
 */

 import java.io.File;
 import java.io.FileNotFoundException;
 import java.util.ArrayList;
 import java.util.Scanner;
 
 public class MorseCodeConverter {
 
     private static final MorseCodeTree converterTree = new MorseCodeTree();
 
     /**
      * Returns the Morse code tree elements in LNR (in-order) traversal as a space-separated string.
      * 
      * @return the tree data in LNR order.
      */
     public static String printTree() {
         ArrayList<String> treeData = converterTree.toArrayList();
         return String.join(" ", treeData);
     }
 
     /**
      * Translates a Morse code string to English.
      * Letters are separated by spaces (' '), and words by slashes ('/').
      * 
      * @param code the Morse code string.
      * @return the English translation.
      * @throws IllegalArgumentException if the input is null or empty.
      */
     public static String convertToEnglish(String code) {
         if (code == null || code.isEmpty()) {
             throw new IllegalArgumentException("Input can't be null or empty.");
         }
 
         String result = "";
         String[] words = code.split("/");
 
         for (int i = 0; i < words.length; i++) {
             String[] letters = words[i].trim().split(" ");
 
             for (String letter : letters) {
                 if (!letter.isEmpty()) {
                     result += converterTree.fetchNode(converterTree.getRoot(), letter);
                 }
             }
 
             if (i < words.length - 1) {
                 result += " ";
             }
         }
 
         return result;
     }
 
     /**
      * Reads Morse code from a file and translates it to English.
      * 
      * @param codeFile the file containing Morse code.
      * @return the English translation.
      * @throws FileNotFoundException if the file is not found or null.
      */
     public static String convertToEnglish(File codeFile) throws FileNotFoundException {
         if (codeFile == null) {
             throw new FileNotFoundException("File can't be null.");
         }
 
         String codeFromFile = "";
         Scanner scanner = new Scanner(codeFile);
 
         while (scanner.hasNextLine()) {
             codeFromFile += scanner.nextLine();
         }
         scanner.close();
 
         return convertToEnglish(codeFromFile);
     }
 }
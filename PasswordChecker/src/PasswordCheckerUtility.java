import java.util.ArrayList;

/**
     * @author Raushan Oshan
     * Checks if the provided password is valid based on various criteria.
     * @param password the password to validate
     * @return true if the password is valid
     * @throws LengthException if the password is shorter than 6 characters
     * @throws NoUpperAlphaException if the password lacks an uppercase letter
     * @throws NoLowerAlphaException if the password lacks a lowercase letter
     * @throws NoDigitException if the password lacks a digit
     * @throws NoSpecialCharacterException if the password lacks a special character
     * @throws InvalidSequenceException if the password contains more than two of the same character in sequence
     * @throws WeekPasswordException if the password just meets the minimum length requirement and below 10
     * @throws UnmatchedException if comparing passwords fails
     */

public class PasswordCheckerUtility {
    /**
     * 
     * @param pass
     * @return
     * @throws LengthException
     * @throws NoUpperAlphaException
     * @throws NoLowerAlphaException
     * @throws NoDigitException
     * @throws NoSpecialCharacterException
     * @throws InvalidSequenceException
     */
    // Method: isValidPassword
    // Purpose: Checks if a password meets all the required conditions for validity
    public static boolean isValidPassword(String pass) throws LengthException, NoUpperAlphaException, NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException, WeakPasswordException,UnmatchedException {

        // Check if the password length is less than 6 characters
        if (pass.length() < 6) {
            throw new LengthException("The password must be at least 6 characters long");
        }

        // Check if there is at least one uppercase letter
        boolean uppercaseFound = false;
        for (char c : pass.toCharArray()) {
            if (Character.isUpperCase(c)) {
                uppercaseFound = true;
                break;
            }
        }
        if (!uppercaseFound) {
            throw new NoUpperAlphaException("The password must contain at least one uppercase alphabetic character");
        }

        // Check if there is at least one lowercase letter
        boolean lowercaseFound = false;
        for (char c : pass.toCharArray()) {
            if (Character.isLowerCase(c)) {
                lowercaseFound = true;
                break;
            }
        }
        if (!lowercaseFound) {
            throw new NoLowerAlphaException("The password must contain at least one lowercase alphabetic character");
        }

        // Check if there is at least one digit
        boolean digitFound = false;
        for (char c : pass.toCharArray()) {
            if (Character.isDigit(c)) {
                digitFound = true;
                break;
            }
        }
        if (!digitFound) {
            throw new NoDigitException("The password must contain at least one digit");
        }

        // Check if there is at least one special character
        boolean specialCharFound = false;
        for (char c : pass.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {  // non-alphanumeric is a special character
                specialCharFound = true;
                break;
            }
        }
        if (!specialCharFound) {
            throw new NoSpecialCharacterException("The password must contain at least one special character");
        }

        // Check if there are more than 2 of the same characters in sequence
        for (int i = 0; i < pass.length() - 2; i++) {
            if (pass.charAt(i) == pass.charAt(i + 1) && pass.charAt(i + 1) == pass.charAt(i + 2)) {
                throw new InvalidSequenceException("The password cannot contain more than two of the same character in sequence");
            }
        }

        // If all checks pass, the password is valid
        return true;
    }
    /**
     * 
     * @param pass
     * @return
     * @throws WeakPasswordException
     */
    // Method: isWeakPassword
    // Purpose: Determines if a valid password is weak (between 6-9 characters long)
    public static boolean isWeakPassword(String pass) throws WeakPasswordException {
        try {
            // First, ensure the password is valid
            isValidPassword(pass);
        } catch (Exception e) {
            // If the password is invalid, rethrow the exception and exit
            return false;
        }

        // Check if the length is between 6 and 9 characters
        if (pass.length() >= 6 && pass.length() <= 9) {
            throw new WeakPasswordException("The password is OK but weak - it contains fewer than 10 characters.");
        }

        // If the password is longer than 9 characters, it's not weak
        return false;
    }
    /**
     * 
     * @param passwords
     * @return
     */
    // Method: getInvalidPasswords
    // Purpose: Checks a list of passwords and returns a list of invalid passwords with reasons
    public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) {
        ArrayList<String> invalidPasswords = new ArrayList<>();

        // Iterate through each password
        for (String pass : passwords) {
            try {
                // Validate the password
                isValidPassword(pass);
            } catch (Exception e) {
                // If an exception occurs, add the password and the exception message to the list
                invalidPasswords.add(pass + " " + e.getMessage());
            }
        }
        return invalidPasswords;
    }
    /**
     * 
     * @param a
     * @param b
     * @return
     * @throws UnmatchedException
     */
    public static boolean comparePasswordsWithReturn(String a, String b) throws UnmatchedException {
        if (a.equals(b)) {
            return true;
        } else {
            throw new UnmatchedException("Passwords do not match!");
        }
    }


}

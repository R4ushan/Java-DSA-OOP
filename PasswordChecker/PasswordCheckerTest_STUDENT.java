import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author 
 *
 */
public class PasswordCheckerTest_STUDENT {

    private ArrayList<String> studentPasswords;

    @Before
    public void setUp() throws Exception {
        studentPasswords = new ArrayList<>();
        // Adding some sample passwords
        studentPasswords.add("Abcde1!"); // Valid
        studentPasswords.add("abcdef");  // No uppercase, no digit, no special character
        studentPasswords.add("ABCDE1!"); // No lowercase
        studentPasswords.add("Abcdef!"); // No digit
        studentPasswords.add("Aaa111!"); // Invalid sequence (aaa)
    }

    @After
    public void tearDown() throws Exception {
        studentPasswords = null;  // Teardown
    }

    /**
     * Test if the password is less than 6 characters long.
     * This test should throw a LengthException for second case.
     */
    @Test
    public void testIsValidPasswordTooShort() {
        try {
            PasswordCheckerUtility.isValidPassword("Ab1!");
            fail("Expected LengthException not thrown");
        } catch (LengthException e) {
            assertTrue("Password too short", true);  // Exception expected
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        try {
            assertTrue(PasswordCheckerUtility.isValidPassword("Abcde1!"));  // Valid password
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    /**
     * Test if the password has at least one uppercase alpha character
     * This test should throw a NoUpperAlphaException for second case
     */
    @Test
    public void testIsValidPasswordNoUpperAlpha() {
        try {
            PasswordCheckerUtility.isValidPassword("abcdef1!");
            fail("Expected NoUpperAlphaException not thrown");
        } catch (NoUpperAlphaException e) {
            assertTrue("Password missing uppercase character", true);  // Exception expected
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        try {
            assertTrue(PasswordCheckerUtility.isValidPassword("Abcdef1!"));  // Valid password
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    /**
     * Test if the password has at least one lowercase alpha character
     * This test should throw a NoLowerAlphaException for second case
     */
    @Test
    public void testIsValidPasswordNoLowerAlpha() {
        try {
            PasswordCheckerUtility.isValidPassword("ABCDEF1!");
            fail("Expected NoLowerAlphaException not thrown");
        } catch (NoLowerAlphaException e) {
            assertTrue("Password missing lowercase character", true);  // Exception expected
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        try {
            assertTrue(PasswordCheckerUtility.isValidPassword("Abcdef1!"));  // Valid password
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    /**
     * Test if the password has at least one digit
     * One test should throw a NoDigitException
     */
    @Test
    public void testIsValidPasswordNoDigit() {
        try {
            PasswordCheckerUtility.isValidPassword("Abcdef!");
            fail("Expected NoDigitException not thrown");
        } catch (NoDigitException e) {
            assertTrue("Password missing a digit", true);  // Exception expected
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        try {
            assertTrue(PasswordCheckerUtility.isValidPassword("Abcdef1!"));  // Valid password
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    /**
     * Test if the password has at least one special character
     * One test should throw a NoSpecialCharacterException
     */
    @Test
    public void testIsValidPasswordNoSpecialCharacter() {
        try {
            PasswordCheckerUtility.isValidPassword("Abcdef1");
            fail("Expected NoSpecialCharacterException not thrown");
        } catch (NoSpecialCharacterException e) {
            assertTrue("Password missing a special character", true);  // Exception expected
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        try {
            assertTrue(PasswordCheckerUtility.isValidPassword("Abcdef1!"));  // Valid password
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    /**
     * Test if the password has more than 2 of the same character in sequence
     * This test should throw a InvalidSequenceException for second case
     */
    @Test
    public void testIsValidPasswordInvalidSequence() {
        try {
            PasswordCheckerUtility.isValidPassword("Aaa111!");
            fail("Expected InvalidSequenceException not thrown");
        } catch (InvalidSequenceException e) {
            assertTrue("Password contains invalid sequence", true);  // Exception expected
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        try {
            assertTrue(PasswordCheckerUtility.isValidPassword("Abcdef1!"));  // Valid password
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    /**
     * Test if the password is weak (between 6-9 characters long)
     * One test should throw a WeakPasswordException
     */
    @Test
    public void testIsWeakPassword() {
        try {
            PasswordCheckerUtility.isWeakPassword("Abcde1!");
            fail("Expected WeakPasswordException not thrown");
        } catch (WeakPasswordException e) {
            assertTrue("Password is weak", true);  // Exception expected
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }

        try {
            assertFalse(PasswordCheckerUtility.isWeakPassword("Abcdefghij1!"));  // Strong password, no exception expected
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    /**
     * Test correct passwords
     * This test should not throw an exception
     */
    @Test
    public void testIsValidPasswordSuccessful() {
        try {
            assertTrue(PasswordCheckerUtility.isValidPassword("Abcdef1!"));  // Valid password, no exception expected
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    /**
     * Test the invalidPasswords method
     * Check the results of the ArrayList of Strings returned by the validPasswords method
     */
    @Test
    public void testInvalidPasswords() {
        ArrayList<String> expectedInvalidPasswords = new ArrayList<>();
        expectedInvalidPasswords.add("abcdef The password must contain at least one uppercase alphabetic character");
        expectedInvalidPasswords.add("ABCDE1! The password must contain at least one lowercase alphabetic character");
        expectedInvalidPasswords.add("Abcdef! The password must contain at least one digit");
        expectedInvalidPasswords.add("Aaa111! The password cannot contain more than two of the same character in sequence");

        ArrayList<String> actualInvalidPasswords = PasswordCheckerUtility.getInvalidPasswords(studentPasswords);
        assertEquals(expectedInvalidPasswords, actualInvalidPasswords);  // Verify that invalid passwords match
    }

	/**
 	* Test if the passwords do not match
 	* This test should throw an UnmatchedException
 	*/
	@Test(expected = UnmatchedException.class)
	public void testComparePasswordsWithReturnUnmatched() throws UnmatchedException {
    	PasswordCheckerUtility.comparePasswordsWithReturn("Password1!", "Password2!");
			}


	

}

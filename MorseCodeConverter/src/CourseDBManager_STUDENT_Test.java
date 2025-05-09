import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Unit tests for the CourseDBManager class.
 * These tests cover adding courses, retrieving courses from a file, and displaying all courses.
 * 
 * Author: Raushan Oshan
 */
public class CourseDBManager_STUDENT_Test {

    private CourseDBManager courseDBManager;

    /**
     * Sets up the test environment before each test case.
     */
    @Before
    public void setUp() {
        courseDBManager = new CourseDBManager(); // Initialize the CourseDBManager instance
    }

    /**
     * Tests the addition and retrieval of a course by CRN.
     */
    @Test
    public void testAddAndGetCourse() {
        // Course details
        String id = "CS101";
        int crn = 12345;
        int credits = 3;
        String roomNum = "B101";
        String instructor = "Dr. Smith";

        // Add a course
        courseDBManager.add(id, crn, credits, roomNum, instructor);

        // Retrieve the course using CRN
        CourseDBElement retrievedCourse = courseDBManager.get(crn);

        // Check the results
        assertNotNull("Course should not be null", retrievedCourse); // Ensure the course was added
        assertEquals("Course ID should match", id, retrievedCourse.getCourseID());
        assertEquals("CRN should match", crn, retrievedCourse.getCRN());
        assertEquals("Credits should match", credits, retrievedCourse.getCredits());
        assertEquals("Room Number should match", roomNum, retrievedCourse.getRoomNum());
        assertEquals("Instructor should match", instructor, retrievedCourse.getInstructor());
    }

    /**
     * Tests reading courses from a file and adding them to the database.
     */
    @Test
    public void testReadFile() {
        // Create a temporary file with course data
        File tempFile = new File("courseData.txt");
        try (PrintWriter writer = new PrintWriter(tempFile)) {
            // Write sample course data to the file
            writer.println("CS101 12345 3 B101 Dr. Smith");
            writer.println("CS102 12346 4 B102 Dr. Jones");
        } catch (FileNotFoundException e) {
            fail("Failed to create temp file for testReadFile");
            return; // This return is technically unreachable, but keeps the compiler happy
        }

        // Read the file and add courses to the database
        try {
            courseDBManager.readFile(tempFile); // Assuming readFile handles file parsing and adding courses
        } catch (FileNotFoundException e) {
            fail("Failed to read temp file for testReadFile");
            return; // This return is technically unreachable, but keeps the compiler happy
        }

        // Retrieve the courses by CRN
        CourseDBElement course1 = courseDBManager.get(12345);
        CourseDBElement course2 = courseDBManager.get(12346);

        // Check the results
        assertNotNull("CS101 should be found", course1);
        assertNotNull("CS102 should be found", course2);
        assertEquals("Course ID for course1 should match", "CS101", course1.getCourseID());
        assertEquals("Course ID for course2 should match", "CS102", course2.getCourseID());

        // Cleanup: delete the temporary file
        tempFile.delete();
    }

    /**
     * Tests the retrieval of all courses in the database.
     */
    @Test
    public void testShowAll() {
        // Add some courses
        courseDBManager.add("CS101", 12345, 3, "B101", "Dr. Smith");
        courseDBManager.add("CS102", 12346, 4, "B102", "Dr. Jones");

        // Get the list of all courses
        ArrayList<String> courses = courseDBManager.showAll();

        // Check the results
        assertEquals(2, courses.size()); // Check if the number of courses is correct
        assertFalse(courses.contains("CS100")); // Ensure a non-existent course is not in the list
    }
}

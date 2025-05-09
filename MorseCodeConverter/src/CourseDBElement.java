/**
 * Represents a course in a database, storing information about the course's ID, CRN,
 * credits, room number, and instructor. This class implements the Comparable interface 
 * to allow sorting based on the CRN (Course Reference Number).
 *
 * @author Raushan Oshan
 */
public class CourseDBElement implements Comparable<CourseDBElement> {
    
    // Fields for course information
    private String id;         // Course ID
    private int crn;          // Course Reference Number
    private int credits;       // Number of credits for the course
    private String roomNum;    // Room number where the course is held
    private String instructor;  // Name of the course instructor

    /**
     * Constructs a CourseDBElement with the specified parameters.
     *
     * @param id         the course ID
     * @param crn        the course reference number
     * @param credits    the number of credits for the course
     * @param roomNum    the room number for the course
     * @param instructor  the instructor of the course
     */
    public CourseDBElement(String id, int crn, int credits, String roomNum, String instructor) {
        this.id = id;
        this.crn = crn;
        this.credits = credits;
        this.roomNum = roomNum;
        this.instructor = instructor;
    }

    // Getters

    /**
     * Returns the course ID.
     *
     * @return the course ID
     */
    public String getCourseID() {
        return id;
    }

    /**
     * Returns the course reference number (CRN).
     *
     * @return the CRN
     */
    public int getCRN() {
        return crn;
    }

    /**
     * Returns the number of credits for the course.
     *
     * @return the number of credits
     */
    public int getCredits() {
        return credits;
    }

    /**
     * Returns the room number for the course.
     *
     * @return the room number
     */
    public String getRoomNum() {
        return roomNum;
    }

    /**
     * Returns the instructor's name for the course.
     *
     * @return the instructor's name
     */
    public String getInstructor() {
        return instructor;
    }

    // Setters

    /**
     * Sets the course ID.
     *
     * @param id the new course ID
     */
    public void setID(String id) {
        this.id = id;
    }

    /**
     * Sets the course reference number (CRN).
     *
     * @param crn the new CRN
     */
    public void setCRN(int crn) {
        this.crn = crn;
    }

    /**
     * Sets the number of credits for the course.
     *
     * @param credits the new number of credits
     */
    public void setCredits(int credits) {
        this.credits = credits;
    }

    /**
     * Sets the room number for the course.
     *
     * @param roomNum the new room number
     */
    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    /**
     * Sets the instructor's name for the course.
     *
     * @param instructor the new instructor's name
     */
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    // Implementing the compareTo method to compare courses based on CRN
    @Override
    public int compareTo(CourseDBElement other) {
        return Integer.compare(this.crn, other.crn); // Compare CRNs
    }

    /**
     * Returns a string representation of the CourseDBElement.
     *
     * @return a string containing course details
     */
    @Override
    public String toString() {
        return String.format("\nCourse:%s CRN:%d Credits:%d Instructor:%s Room:%s",
                id, crn, credits, instructor, roomNum);
    }
}

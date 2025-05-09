import java.io.*;
import java.util.*;
/**
 * CourseDBManager class
 * @author Raushan Oshan
 */
public class CourseDBManager implements CourseDBManagerInterface {

    private CourseDBStructure DB = new CourseDBStructure(20);

    public CourseDBManager() {
        /*
        no arg
         */
    }

    /**
     * Adds a course to the CourseDBStructure.
     * @param id course id
     * @param crn course crn
     * @param credits number of credits
     * @param roomNum course room number
     * @param instructor name of the instructor
     */
    @Override
    public void add(String id, int crn, int credits, String roomNum, String instructor) {
        CourseDBElement course = new CourseDBElement(id, crn, credits, roomNum, instructor);
        DB.add(course);
    }

    /**
     * Finds a CourseDBElement by its CRN.
     * @param crn course crn (key)
     * @return the matching CourseDBElement
     * @throws IOException if the course is not found
     */
    @Override
     public CourseDBElement get(int crn) {
         try {
             return DB.get(crn);
         } catch (IOException e) {
             e.printStackTrace();
         }
         
         return null;
     }

    /**
     * Reads course data from a file and adds to the CourseDBStructure.
     * @param input the file containing course data
     * @throws FileNotFoundException if the file is not found
     */
    @Override
    public void readFile(File input) throws FileNotFoundException {
        try (Scanner fileScanner = new Scanner(input)) {
            while (fileScanner.hasNextLine()) {
                String courseID = fileScanner.next();
                int courseCRN = fileScanner.nextInt();
                int credits = fileScanner.nextInt();
                String roomNum = fileScanner.next();
                String professorName = fileScanner.nextLine().trim();
                add(courseID, courseCRN, credits, roomNum, professorName);
            }
        }
    }

    /**
     * @return list of all courses in the database as formatted strings
     */
    @Override
    public ArrayList<String> showAll() {
        return DB.showAll();
    }
}

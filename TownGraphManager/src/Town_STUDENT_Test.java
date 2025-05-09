/**
 *@author Raushan Oshan
 */

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class Town_STUDENT_Test {

    private Town townA;
    private Town townB;
    private Town townC;
    private Road roadAB;
    private Road roadBC;

    @Before
    public void setUp() {
        // Initialize towns
        townA = new Town("Springfield");
        townB = new Town("Shelbyville");
        townC = new Town("Ogdenville");

        // Initialize roads
        roadAB = new Road(townA, townB, 5, "Main Street");
        roadBC = new Road(townB, townC, 10, "Elm Street");

        // Add roads to towns
        townA.addToRoadList(roadAB);
        townB.addToRoadList(roadAB);
        townB.addToRoadList(roadBC);
        townC.addToRoadList(roadBC);
    }

    @Test
    public void testConstructorWithName() {
        Town town = new Town("Riverdale");
        assertEquals("Riverdale", town.getName());
        assertEquals(Integer.MAX_VALUE, town.getTotalDistance());
        assertNull(town.getPreviousTown());
        assertNull(town.getPreviousRoad());
    }

    @Test
    public void testAddToRoadList() {
        assertEquals(1, townA.getRoadList().size());
        assertTrue(townA.getRoadList().contains(roadAB));
    }

    @Test
    public void testCompareTo() {
        Town townD = new Town("Shelbyville");
        Town townE = new Town("Springfield");

        assertEquals(0, townA.compareTo(townE));  // Same name
        assertNotEquals(0, townA.compareTo(townD));  // Different name
    }

    @Test
    public void testEquals() {
        assertTrue(townA.equals(new Town("Springfield")));
        assertFalse(townA.equals(townB));
        assertFalse(townA.equals(null));
        assertFalse(townA.equals("String"));
    }

    @Test
    public void testHashCode() {
        assertEquals(townA.hashCode(), new Town("Springfield").hashCode());
        assertNotEquals(townA.hashCode(), townB.hashCode());
    }

    @Test
    public void testSetTotalDistance() {
        townA.setTotalDistance(100);
        assertEquals(100, townA.getTotalDistance());
    }

    @Test
    public void testSetPreviousTown() {
        townA.setPreviousTown(townB);
        assertEquals(townB, townA.getPreviousTown());
    }

    @Test
    public void testSetPreviousRoad() {
        townA.setPreviousRoad(roadAB);
        assertEquals(roadAB, townA.getPreviousRoad());
    }

    @Test
    public void testToString() {
        assertEquals("Springfield", townA.toString());
    }

    @Test
    public void testGetRoadList() {
        assertEquals(2, townB.getRoadList().size());
        assertTrue(townB.getRoadList().contains(roadAB));
        assertTrue(townB.getRoadList().contains(roadBC));
    }
}

/**
 *@author Raushan Oshan
 */

import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TownGraphManager_STUDENT_Test {
    private TownGraphManagerInterface graph;
    private String[] city;

    @Before
    public void setUp() throws Exception {
        graph = new TownGraphManager();
        city = new String[10];

        for (int i = 1; i < 10; i++) {
            city[i] = "City_" + i;
            graph.addTown(city[i]);
        }

        graph.addRoad(city[1], city[2], 3, "Street_1");
        graph.addRoad(city[1], city[4], 5, "Street_2");
        graph.addRoad(city[2], city[5], 2, "Street_3");
        graph.addRoad(city[3], city[6], 1, "Street_4");
        graph.addRoad(city[4], city[7], 4, "Street_5");
        graph.addRoad(city[5], city[8], 6, "Street_6");
        graph.addRoad(city[6], city[9], 2, "Street_7");
        graph.addRoad(city[7], city[9], 3, "Street_8");
        graph.addRoad(city[8], city[1], 2, "Street_9");
        graph.addRoad(city[2], city[3], 4, "Street_10");
    }

    @After
    public void tearDown() throws Exception {
        graph = null;
    }

    @Test
    public void testAddRoad() {
        ArrayList<String> roads = graph.allRoads();
        assertEquals("Street_1", roads.get(0));
        assertEquals("Street_10", roads.get(1));
        assertEquals("Street_2", roads.get(2));
        graph.addRoad(city[4], city[9], 5, "Street_11");
        roads = graph.allRoads();
        assertEquals("Street_1", roads.get(0));
        assertEquals("Street_10", roads.get(1));
        assertEquals("Street_11", roads.get(2));
        assertEquals("Street_2", roads.get(3));
    }

    @Test
    public void testGetRoad() {
        assertEquals("Street_1", graph.getRoad(city[1], city[2]));
        assertEquals("Street_4", graph.getRoad(city[3], city[6]));
    }

    @Test
    public void testAddCity() {
        assertEquals(false, graph.containsTown("City_10"));
        graph.addTown("City_10");
        assertEquals(true, graph.containsTown("City_10"));
    }

    @Test
    public void testDisjointGraph() {
        assertEquals(false, graph.containsTown("City_10"));
        graph.addTown("City_10");
        ArrayList<String> path = graph.getPath(city[1], "City_10");
        assertFalse(path.size() > 0);
    }

    @Test
    public void testContainsCity() {
        assertEquals(true, graph.containsTown("City_2"));
        assertEquals(false, graph.containsTown("City_10"));
    }

    @Test
    public void testContainsRoadConnection() {
        assertEquals(true, graph.containsRoadConnection(city[2], city[3]));
        assertEquals(false, graph.containsRoadConnection(city[5], city[7]));
    }

    @Test
    public void testAllRoads() {
        ArrayList<String> roads = graph.allRoads();
        assertEquals("Street_1", roads.get(0));
        assertEquals("Street_10", roads.get(1));
        assertEquals("Street_2", roads.get(2));
        assertEquals("Street_6", roads.get(6));
        assertEquals("Street_7", roads.get(7));
    }

    @Test
    public void testDeleteRoadConnection() {
        assertEquals(true, graph.containsRoadConnection(city[2], city[3]));
        graph.deleteRoadConnection(city[2], city[3], "Street_10");
        assertEquals(false, graph.containsRoadConnection(city[2], city[3]));
    }

    @Test
    public void testDeleteCity() {
        assertEquals(true, graph.containsTown("City_2"));
        graph.deleteTown(city[2]);
        assertEquals(false, graph.containsTown("City_2"));
    }

    @Test
    public void testAllCities() {
        ArrayList<String> cities = graph.allTowns();
        assertEquals("City_1", cities.get(0));
        assertEquals("City_9", cities.get(8));
        assertEquals("City_8", cities.get(7));
        assertEquals("City_4", cities.get(3));
    }

    @Test
    public void testGetPath() {
        ArrayList<String> path = graph.getPath(city[1], city[9]);
        assertNotNull(path);
        assertTrue(path.size() > 0);
        assertEquals("City_1 via Street_1 to City_2 3 mi", path.get(0).trim());
        assertEquals("City_2 via Street_10 to City_3 4 mi", path.get(1).trim());
        assertEquals("City_3 via Street_4 to City_6 1 mi", path.get(2).trim());
    }

    @Test
    public void testGetPathA() {
        ArrayList<String> path = graph.getPath(city[1], city[7]);
        assertNotNull(path);
        assertTrue(path.size() > 0);
        assertEquals("City_1 via Street_2 to City_4 5 mi", path.get(0).trim());
        assertEquals("City_4 via Street_5 to City_7 4 mi", path.get(1).trim());
    }

    @Test
    public void testGetPathB() {
        ArrayList<String> path = graph.getPath(city[1], city[9]);
        assertNotNull(path);
        assertTrue(path.size() > 0);
        assertEquals("City_1 via Street_1 to City_2 3 mi", path.get(0).trim());
        assertEquals("City_2 via Street_10 to City_3 4 mi", path.get(1).trim());
        assertEquals("City_3 via Street_4 to City_6 1 mi", path.get(2).trim());
    }
}

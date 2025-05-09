/**
 *@author Raushan Oshan
 */

import java.util.ArrayList;

public class Town implements Comparable<Town> {

    ArrayList<Road> roadList;
    String townName;
    int townHashCode;
    int totalDistance;
    Town previousTown;
    Road previousRoad;

    /**
     * Default constructor
     */
    public Town() {
        roadList = new ArrayList<>();
        townName = "";
        totalDistance = Integer.MAX_VALUE;
        previousTown = null;
        previousRoad = null;
    }

    /**
     * Constructor with name
     * @param name town name
     */
    public Town(String name) {
        roadList = new ArrayList<>();
        townName = name;
        totalDistance = Integer.MAX_VALUE;
        previousTown = null;
        previousRoad = null;
    }

    /**
     * Copy constructor
     * @param templateTown town to copy
     */
    public Town(Town templateTown) {
        roadList = new ArrayList<>();
        townName = templateTown.getName();
        totalDistance = Integer.MAX_VALUE;
        previousTown = null;
        previousRoad = null;
    }

    /**
     * Gets town name
     * @return town name
     */
    public String getName() {
        return townName;
    }

    /**
     * Compares towns by name
     * @param o town to compare
     * @return 0 if names match, else 1
     */
    @Override
    public int compareTo(Town o) {
        return this.townName.equalsIgnoreCase(o.getName()) ? 0 : 1;
    }

    /**
     * String representation of town
     * @return town name
     */
    @Override
    public String toString() {
        return townName;
    }

    /**
     * Hash code based on town name
     * @return hash code
     */
    @Override
    public int hashCode() {
        return townName.hashCode();
    }

    /**
     * Checks if two towns are equal by name
     * @param obj object to compare
     * @return true if names match
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Town townObject = (Town) obj;
        return this.townName.equals(townObject.getName());
    }

    /**
     * Adds a road to the town's road list
     * @param road road to add
     */
    public void addToRoadList(Road road) {
        roadList.add(road);
    }

    // Setters

    /**
     * Sets the total distance for pathfinding
     * @param distance total distance
     */
    public void setTotalDistance(int distance) {
        totalDistance = distance;
    }

    /**
     * Sets the previous town for pathfinding
     * @param prevTown previous town
     */
    public void setPreviousTown(Town prevTown) {
        previousTown = prevTown;
    }

    /**
     * Sets the previous road for pathfinding
     * @param prevRoad previous road
     */
    public void setPreviousRoad(Road prevRoad) {
        previousRoad = prevRoad;
    }

    // Getters

    /**
     * Gets the total distance for pathfinding
     * @return total distance
     */
    public int getTotalDistance() {
        return totalDistance;
    }

    /**
     * Gets the previous town for pathfinding
     * @return previous town
     */
    public Town getPreviousTown() {
        return previousTown;
    }

    /**
     * Gets the previous road for pathfinding
     * @return previous road
     */
    public Road getPreviousRoad() {
        return previousRoad;
    }

    /**
     * Gets the list of connected roads
     * @return list of roads
     */
    public ArrayList<Road> getRoadList() {
        return roadList;
    }
}

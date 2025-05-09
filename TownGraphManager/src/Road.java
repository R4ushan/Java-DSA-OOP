/**
 *@author Raushan Oshan
 */

public class Road implements Comparable<Road> {

    private Town oneTownOnRoad;
    private Town anotherTownOnRoad;
    private int weightOfEdge;
    private String roadName;

    /**
     * Default constructor
     */
    public Road() {
        this.oneTownOnRoad = null;
        this.anotherTownOnRoad = null;
        this.weightOfEdge = 0;
        this.roadName = "";
    }

    /**
     * Constructor with parameters
     * @param source source town
     * @param destination destination town
     * @param degrees road weight
     * @param name road name
     */
    public Road(Town source, Town destination, int degrees, String name) {
        this.oneTownOnRoad = source;
        this.anotherTownOnRoad = destination;
        this.weightOfEdge = degrees;
        this.roadName = name;
    }

    /**
     * Constructor with default weight
     * @param source source town
     * @param destination destination town
     * @param name road name
     */
    public Road(Town source, Town destination, String name) {
        this(source, destination, 1, name);
    }

    /**
     * Checks if road contains town
     * @param town town to check
     * @return true if town is on road
     */
    public boolean contains(Town town) {
        return town.equals(oneTownOnRoad) || town.equals(anotherTownOnRoad);
    }

    /**
     * Returns road name
     * @return road name
     */
    @Override
    public String toString() {
        return roadName;
    }

    /**
     * Gets road name
     * @return road name
     */
    public String getName() {
        return roadName;
    }

    /**
     * Gets destination town
     * @return destination town
     */
    public Town getDestination() {
        return anotherTownOnRoad;
    }

    /**
     * Gets source town
     * @return source town
     */
    public Town getSource() {
        return oneTownOnRoad;
    }

    /**
     * Compares road weights
     * @param o road to compare
     * @return comparison result
     */
    @Override
    public int compareTo(Road o) {
        return Integer.compare(this.getWeight(), o.getWeight());
    }

    /**
     * Gets road weight
     * @return road weight
     */
    public int getWeight() {
        return weightOfEdge;
    }

    /**
     * Checks if roads are equal
     * @param obj object to compare
     * @return true if roads are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Road road = (Road) obj;
        return roadName.equals(road.roadName) &&
                weightOfEdge == road.weightOfEdge &&
                ((oneTownOnRoad.equals(road.oneTownOnRoad) && anotherTownOnRoad.equals(road.anotherTownOnRoad)) ||
                 (oneTownOnRoad.equals(road.anotherTownOnRoad) && anotherTownOnRoad.equals(road.oneTownOnRoad)));
    }

    /**
     * Returns road hash code
     * @return hash code
     */
    @Override
    public int hashCode() {
        int result = roadName.hashCode();
        result = 31 * result + oneTownOnRoad.hashCode();
        result = 31 * result + anotherTownOnRoad.hashCode();
        result = 31 * result + Integer.hashCode(weightOfEdge);
        return result;
    }
}
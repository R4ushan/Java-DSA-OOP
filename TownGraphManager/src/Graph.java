/**
 * @author Raushan Oshan
 */

 import java.util.ArrayList;
 import java.util.Collections;
 import java.util.HashSet;
 import java.util.Set;
 import java.util.Hashtable;
 import java.util.Iterator;
 
 public class Graph<V, E> implements GraphInterface<Town, Road> {
 
     Hashtable<Integer, Town> townHashTable;
     Hashtable<Integer, Road> roadHashTable;
 
     int numOfNodes = 0;
 
     ArrayList<Town> visitedTowns;
     ArrayList<Town> unvisitedTowns;
 
     /**
      * No-arg constructor
      */
     public Graph() {
         townHashTable = new Hashtable<Integer, Town>();
         roadHashTable = new Hashtable<Integer, Road>();
 
         visitedTowns = new ArrayList<Town>();
         unvisitedTowns = new ArrayList<Town>();
     }
 
     @Override
     public Road getEdge(Town sourceVertex, Town destinationVertex) {
         for (Road road : roadHashTable.values()) {
             if ((road.getSource().equals(sourceVertex) && road.getDestination().equals(destinationVertex)) ||
                 (road.getSource().equals(destinationVertex) && road.getDestination().equals(sourceVertex))) {
                 return road;
             }
         }
         return null;
     }
 
     @Override
     public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
         Road newRoad = new Road(sourceVertex, destinationVertex, weight, description);
         roadHashTable.put(newRoad.hashCode(), newRoad);
 
         townHashTable.get(sourceVertex.getName().hashCode()).getRoadList().add(newRoad);
         townHashTable.get(destinationVertex.getName().hashCode()).getRoadList().add(newRoad);
 
         return newRoad; // Return the created road instead of null
     }
 
     @Override
     public boolean addVertex(Town v) {
         townHashTable.put(v.hashCode(), v);
         return true;
     }
 
     @Override
     public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
         for (Road road : roadHashTable.values()) {
             if ((road.getSource().equals(sourceVertex) && road.getDestination().equals(destinationVertex)) ||
                 (road.getSource().equals(destinationVertex) && road.getDestination().equals(sourceVertex))) {
                 return true;
             }
         }
         return false;
     }
 
     @Override
     public boolean containsVertex(Town v) {
         for (Town town : townHashTable.values()) {
             if (town.getName().equals(v.getName())) {
                 return true;
             }
         }
         return false;
     }
 
     @Override
     public Set<Road> edgeSet() {
         Set<Road> roadSet = new HashSet<Road>();
         for (Road road : roadHashTable.values()) {
             roadSet.add(road);
         }
         return roadSet;
     }
 
     @Override
     public Set<Road> edgesOf(Town vertex) {
         Set<Road> roadsFromTown = new HashSet<Road>();
         for (Road road : roadHashTable.values()) {
             if (road.getSource().equals(vertex) || road.getDestination().equals(vertex)) {
                 roadsFromTown.add(road);
             }
         }
         return roadsFromTown;
     }
 
     @Override
     public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
         Iterator<Road> roadIterator = roadHashTable.values().iterator();
         Road nextRoad;
 
         while (roadIterator.hasNext()) {
             nextRoad = roadIterator.next();
             if ((nextRoad.getSource().equals(sourceVertex) && nextRoad.getDestination().equals(destinationVertex)) ||
                 (nextRoad.getSource().equals(destinationVertex) && nextRoad.getDestination().equals(sourceVertex))) {
                 if (nextRoad.getWeight() > -1 && nextRoad.getName() != null) {
                     roadIterator.remove();
                     return nextRoad;
                 }
             }
         }
         return null;
     }
 
     @Override
     public boolean removeVertex(Town v) {
         Iterator<Town> townIterator = townHashTable.values().iterator();
         Town nextTown;
 
         while (townIterator.hasNext()) {
             nextTown = townIterator.next();
             if (nextTown.equals(v)) {
                 townIterator.remove();
                 return true;
             }
         }
         return false;
     }
 
     @Override
     public Set<Town> vertexSet() {
         Set<Town> townSet = new HashSet<Town>();
         for (Town town : townHashTable.values()) {
             townSet.add(town);
         }
         return townSet;
     }
 
     @Override
     public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
         ArrayList<String> pathDescriptions = new ArrayList<String>();
         String pathSegment = "";  // Renamed from tempStr
         int roadWeight = 0;
         Town currentTown = null;
 
         dijkstraShortestPath(sourceVertex);
 
         for (Town town : townHashTable.values()) {
             if (town.equals(destinationVertex)) {
                 currentTown = town;
             }
         }
 
         while (currentTown.getPreviousTown() != null) {
             pathSegment += currentTown.getPreviousTown() + " via ";
 
             for (Road road : roadHashTable.values()) {
                 if ((road.getSource().equals(currentTown.getPreviousTown()) && road.getDestination().equals(currentTown)) ||
                     (road.getSource().equals(currentTown) && road.getDestination().equals(currentTown.getPreviousTown()))) {
                     pathSegment += road.getName() + " to ";
                     roadWeight = road.getWeight();
                 }
             }
 
             pathSegment += currentTown.getName() + " ";
             pathSegment += roadWeight + " mi";
 
             pathDescriptions.add(pathSegment);
             pathSegment = "";  // Reset for the next segment
 
             currentTown = currentTown.getPreviousTown();
         }
 
         Collections.reverse(pathDescriptions);
         return pathDescriptions;
     }
 
     @Override
     public void dijkstraShortestPath(Town sourceVertex) {
         for (Town town : townHashTable.values()) {
             if (town.equals(sourceVertex)) {
                 town.setTotalDistance(0);
                 town.setPreviousTown(null);
             } else {
                 town.setTotalDistance(Integer.MAX_VALUE);
                 town.setPreviousTown(null);
             }
         }
 
         for (Town town : townHashTable.values()) {
             unvisitedTowns.add(town);
         }
 
         while (!unvisitedTowns.isEmpty()) {
             Town smallestTown = unvisitedTowns.get(0);
 
             for (int i = 0; i < unvisitedTowns.size(); i++) {
                 if (smallestTown.getTotalDistance() > unvisitedTowns.get(i).getTotalDistance()) {
                     smallestTown = unvisitedTowns.get(i);
                 }
             }
 
             Town currentTown = smallestTown;
             ArrayList<Road> currentTownRoads = currentTown.getRoadList();
 
             for (Road road : currentTownRoads) {
                 Town source = road.getSource();
                 Town destination = road.getDestination();
                 Town nextVertex = null;
 
                 if (!source.equals(currentTown) && destination.equals(currentTown)) {
                     nextVertex = source;
                 }
                 if (source.equals(currentTown) && !destination.equals(currentTown)) {
                     nextVertex = destination;
                 }
 
                 int tempRoadWeight = (currentTown.getTotalDistance() + road.getWeight());
 
                 if (tempRoadWeight < nextVertex.getTotalDistance()) {
                     nextVertex.setTotalDistance(tempRoadWeight);
                     nextVertex.setPreviousTown(currentTown);
                     nextVertex.setPreviousRoad(road);
                 }
             }
 
             visitedTowns.add(currentTown);
             unvisitedTowns.remove(currentTown);
         }
     }
 
     public Town getTown(int hashCode) {
         return townHashTable.get(hashCode);
     }
 
     public Hashtable<Integer, Road> returnRoadHashTable() {
         return roadHashTable;
     }
 }
 
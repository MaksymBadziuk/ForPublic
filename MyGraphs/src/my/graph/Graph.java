/******************************************************************************
 *
 * File :   Graph.java
 * 
 * Author : Badzyuk
 *
 * Date :   3 June 2016 y.
 *
 * History of modifications
 * Date       Rev.    Reason
 *
 *
 *****************************************************************************/
/**
 * File contains abstract graph class code
 *
 ******************************************************************************
 *
 *****************************************************************************/
package my.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

/****************************************************************************
 * Graph
 ***************************************************************************/
/**
 * Base class for all Graphs
 *
 **************************************************************************/
public abstract class Graph {

  /** Number of vertices in graph */
  private int numOfVertices;
  
  /** Number of edges in graph */
  private int numOfEdges;
  
  /****************************************************************************
   * Graph
   ***************************************************************************/
  /**
   * Default constructor.
   *
   ***************************************************************************/
  public Graph() {
    numOfEdges = 0;
    numOfVertices = 0;
  }//Graph
  
  /****************************************************************************
   * addEdge
   ***************************************************************************/
  /**
   * Add directed edge to a graph
   *
   * @param startVertice vertice as a start point of edge
   * @param endVertice vertice as an end point of edge 
   *
   ***************************************************************************/
  public void addEdge(int startVertice, int endVertice) {
    implementAddEdge(startVertice,endVertice);
    numOfEdges++;
  }//addEdge
  
  /****************************************************************************
   * addEdge
   ***************************************************************************/
  /**
   * Add bi-directed edge to a graph. Use to add edge to a non-directed graphs 
   * only. </br>
   * The number of edges will be incremented only once.
   *
   * @param vertice1 to be connected with edge
   * @param vertice2 to be connected with edge
   * @param biDirected 
   *
   ***************************************************************************/
  public void addEdge(int vertice1, int vertice2, boolean biDirected) {
    if (biDirected) {
      addEdge(vertice1,vertice2);
      addEdge(vertice2, vertice1);
      numOfEdges--;
    } else {
      addEdge(vertice1,vertice2);
    }//if
  }//addEdge
  
  /****************************************************************************
   * addVertice
   ***************************************************************************/
  /**
   * Add a vertice to a graph.
   *
   ***************************************************************************/
  public void addVertice() {
    implementAddVertice();
    numOfVertices++;
  }//addVertice
  
  /***************************************************************************
   * degreeSequence
   ***************************************************************************/
  /**
  * The degree sequence of a graph is a sorted (organized in numerical order 
  * from largest to smallest, possibly with repetitions) list of the degrees 
  * of the vertices in the graph.
  * 
  * @return The degree sequence of this graph.
  *
  ***************************************************************************/
   public List<Integer> degreeSequence() {

     // Create result list
     List<Integer> result = new ArrayList<Integer>();
     
     // Fill list
     // Run through all graph Vertices 
     for (int i = 0; i < getNumOfVertices(); i++) {

       // Calculate Degree for an every vertice
       int degree = getNeighbours(i).size();;
       
       // Add Degree to result list
       result.add(degree);
     }//for
     
     // Sort in descending order
     Collections.sort(result);
     Collections.reverse(result);
     
     return result;
   }//degreeSequence
  
  /****************************************************************************
   * fillAllEdges
   ***************************************************************************/
  /**
   * Fill all edges for all vertices.</br> Must be implemented in
   * child classes depending on graph inner implementation strategy 
   *
   ***************************************************************************/
  public abstract void fillAllEdges();
  
  /***************************************************************************
   * getDistance2
   ***************************************************************************/
  /**
   * Get all the vertices that are 2 away from the vertex in question.
   * @param v The starting vertex
   * @return A list of the vertices that can be reached in exactly two hops (by 
   * following two edges) from vertex v.
   *
   ***************************************************************************/
  public abstract List<Integer> getDistance2(int v); 
  
  /****************************************************************************
   * getInNeighbours
   ***************************************************************************/
  /**
   * Get incoming edges for requested vertice
   *
   * @param vertice for which incoming edges are requested 
   * 
   * @return  list of integers which represent vertices from which edges exist to 
   *          requested vertice
   *
   ***************************************************************************/
  public abstract List<Integer> getInNeighbours(int vertice);
  
  /****************************************************************************
   * getNeighbours
   ***************************************************************************/
  /**
   * Get all edges adjacent to requested vertice
   *
   * @param vertice for which all edges are requested 
   * 
   * @return list of integers which represent all neighbour vertices
   *
   ***************************************************************************/
  public List<Integer> getNeighbours(int vertice) {
    List<Integer> result = new ArrayList<Integer>();
    
    HashSet<Integer> set = new HashSet<Integer>();
    set.addAll(getInNeighbours(vertice));
    set.addAll(getOutNeighbours(vertice));
     
    result.addAll(set);
    
    return result;
  }//getNeighbours
  
  /****************************************************************************
   * getNumOfEdges
   ***************************************************************************/
  /**
   * Get current number of edges in graph
   *
   * @return number of edges
   *
   ***************************************************************************/
  public int getNumOfEdges() {
    return numOfEdges;
  }//getNumOfEdges
  
  /****************************************************************************
   * getNumOfVertices
   ***************************************************************************/
  /**
   * Get current number of vertices in graph
   *
   * @return number of vertices
   *
   ***************************************************************************/
  public int getNumOfVertices() {
    return numOfVertices;
  }//getNumOfVertices
  
  /****************************************************************************
   * getOutNeighbours
   ***************************************************************************/
  /**
   * Get outgoing edges for requested vertice
   *
   * @param vertice for which outgoing edges are requested 
   * 
   * @return list of integers which represent vertices to which requested vertice 
   *         have outgoing edges
   *
   ***************************************************************************/
  public abstract List<Integer> getOutNeighbours(int vertice);
  
  /****************************************************************************
   * implementAddVertice
   ***************************************************************************/
  /**
   * Abstract method with specific to this graph implementation method of adding edge 
   * to a graph.
   * This method should be implemented in child classes.
   *
   ***************************************************************************/
  protected abstract void implementAddEdge(int startVertice, int endVertice);

  /****************************************************************************
   * implementAddVertice
   ***************************************************************************/
  /**
   * Abstract method with specific to this graph implementation method of adding vertice 
   * to a graph.
   * This method should be implemented in child classes.
   *
   ***************************************************************************/
  protected abstract void implementAddVertice();

  /****************************************************************************
   * toString
   ***************************************************************************/
  /**
   * Return a String representation of the graph
   * 
   * @return A string representation of the graph
   *
   ***************************************************************************/
  public String toString() {
    String s = "\nGraph with " + numOfVertices + " vertices and " + numOfEdges + " edges.\n";
    s += "Degree sequence: " + degreeSequence() + ".\n";
    if (numOfVertices <= 20) s += adjacencyString();
    return s;
  }//toString
  
  /****************************************************************************
   * adjacencyString
   ***************************************************************************/
  /**
   * Generate string representation of adjacency list
   *
   * @return the String
   *
   ***************************************************************************/
  public abstract String adjacencyString();
}//Graph

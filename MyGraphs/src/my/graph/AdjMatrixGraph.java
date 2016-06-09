/******************************************************************************
 *
 * File :   AdjMatrixGraph.java
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
 * File contains graph implementation with adjacency matrix
 *
 ******************************************************************************
 *
 *****************************************************************************/
package my.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/****************************************************************************
 * AdjMatrixGraph
 ***************************************************************************/
/**
 * Class contains specific implemention of graph with adjacency matrix
 *
 ***************************************************************************/
public class AdjMatrixGraph extends Graph {

  /** Adjacency matrix for graph */
  private int[][] adjacencyMatrix;
  
  /****************************************************************************
   * AdjMatrixGraph
   ***************************************************************************/
  /**
   * Default constructor. Size of matrix is 4*4.
   *
   ***************************************************************************/
  public AdjMatrixGraph() {
    this(4);
  }//AdjMatrixGraph
  
  /****************************************************************************
   * AdjMatrixGraph
   ***************************************************************************/
  /**
   * Constructor with parameter
   *
   * @param sizeOfMatrix initial size of adjacency matrix
   *
   ***************************************************************************/
  public AdjMatrixGraph(int sizeOfMatrix) {
    super();
    adjacencyMatrix = new int[sizeOfMatrix][sizeOfMatrix];
  }//AdjMatrixGraph
  
  /**************************************************************************
   * fillAllEdges
   ***************************************************************************/
  /**
   * {@inheritDoc}
   *
   ***************************************************************************/
  public void fillAllEdges() {
    
    // Fill all edges
    for (int startPoint = 0; startPoint < getNumOfVertices(); startPoint++) {
      
      for (int endPoint = 0; endPoint < getNumOfVertices(); endPoint++) {
        addEdge(startPoint, endPoint);
      } //for
    } //for
  }//fillAllEdges

  /***************************************************************************
   * getDistance2
   ***************************************************************************/
  /**
   * {@inheritDoc}
   *
   ***************************************************************************/
  public List<Integer> getDistance2(int v) {
    // Create result list
    List<Integer> result = new ArrayList<Integer>();
    int numOfVertices = getNumOfVertices();
    
    // Fill list with 2-hop vertices from startPoint
    for (int endpoint1 = 0; endpoint1 < numOfVertices; endpoint1++) {
      
      // Get 1st endpoint hop
      int endpoint = adjacencyMatrix[v][endpoint1];
      if (endpoint != 0) {
        
        // Get 2nd endpoint hop
        for (int endpoint2 = 0; endpoint2 < numOfVertices; endpoint2++) {
          int hop2 = adjacencyMatrix[endpoint][endpoint2];
          if (hop2 != 0 && !result.contains(hop2)) {
            result.add(hop2);
          }//if
        }//for
      }//if
    }//for
    
    return result;
  }//getDistance2 
  
  /**************************************************************************
   * getInNeighbours
   ***************************************************************************/
  /**
   * {@inheritDoc}
   *
   ***************************************************************************/
  @Override
  public List<Integer> getInNeighbours(int vertice) {
    List<Integer> list = new ArrayList<Integer>();
    
    for (int row = 0; row < adjacencyMatrix.length; row++) {
      if (adjacencyMatrix[row][vertice] == 1 ){
        list.add(row);  
      }//if
    } //for
    
    return list;
  }//getInNeighbours 

  /****************************************************************************
   * getOutNeighbours
   ***************************************************************************/
  /**
   * {@inheritDoc}
   * 
   ***************************************************************************/
  @Override
  public List<Integer> getOutNeighbours(int vertice) {
    List<Integer> list = new ArrayList<Integer>();
    
    for (int column = 0; column < adjacencyMatrix.length; column++) {
      if (adjacencyMatrix[vertice][column] == 1 ){
        list.add(column);  
      }//if
    }//for
    
    return list;
  }//getOutNeighbours
  
  /****************************************************************************
   * implementAddEdge
   ***************************************************************************/
  /**
   * {@inheritDoc}
   *
   ***************************************************************************/
  @Override
  protected void implementAddEdge(int startVertice, int endVertice) {
    adjacencyMatrix[startVertice][endVertice] = 1;
  }//implementAddEdge
  
  /****************************************************************************
   * implementAddVertice
   ***************************************************************************/
  /**
   * {@inheritDoc}
   * 
   ***************************************************************************/
  @Override
  protected void implementAddVertice() {
    int currentNum = this.getNumOfVertices();
    
    // If no more places for vertice extend matrix
    if (currentNum >= adjacencyMatrix[0].length) {
      int[][] newAdjMtx = new int[currentNum*2][currentNum*2];
      
      for (int i = 0; i < adjacencyMatrix.length; i++) {
        for (int j = 0; j < adjacencyMatrix.length; j++) {
          newAdjMtx[i][j] = adjacencyMatrix[i][j];
        }//for
      }//for
      
      adjacencyMatrix = newAdjMtx;
    }//if
    
    // Zero new part of matrix
    for (int i = 0; i < currentNum + 1; i++) {
      adjacencyMatrix[i][currentNum] = 0;
      adjacencyMatrix[currentNum][i] = 0;
    }//for
  }//implementAddVertice
  
  /****************************************************************************
   * adjacencyString
   ***************************************************************************/
  /**
   * Generate string representation of adjacency matrix
   * 
   * @return the String
   *
   ***************************************************************************/
  public String adjacencyString() {
    int dim = adjacencyMatrix.length;
    String s = "Adjacency matrix";
    s += " (size " + dim + "x" + dim + " = " + dim* dim + " integers):";
    for (int i = 0; i < dim; i ++) {
      s += "\n\t"+i+": ";
      for (int j = 0; j < adjacencyMatrix[i].length; j++) {
        s += adjacencyMatrix[i][j] + ", ";
      }//for
    }//for
    return s;
  }//adjacencyString
}//AdjMatrixGraph

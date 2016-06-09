/******************************************************************************
 *
 * File :   AdjListGraph.java
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
 * File contains graph implementation with adjacency list
 *
 ******************************************************************************
 *
 *****************************************************************************/
package my.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/****************************************************************************
 * AdjListGraph
 ***************************************************************************/
/**
 * Class contains specific implemention of graph with adjacency list
 *
 ***************************************************************************/
public class AdjListGraph extends Graph {

  private Map<Integer, List<Integer>> adjacencyList;
  
  /****************************************************************************
   * AdjListGraph
   ***************************************************************************/
  /**
   * Default constructor.
   *
   ***************************************************************************/
  public AdjListGraph() {
    super();
    adjacencyList = new HashMap<Integer, List<Integer>>();
  }//AdjListGraph
  
  /****************************************************************************
   * fillAllEdges
   ***************************************************************************/
  /**
   * {@inheritDoc}
   * 
   ***************************************************************************/
  @Override
  public void fillAllEdges() {
    
    if (getNumOfVertices() > 1) {
      
      for (int vertice = 0; vertice < getNumOfVertices(); vertice++) {
        
        List<Integer> list = adjacencyList.get(vertice);
        
        for (int i = 0; i < getNumOfVertices(); i++) {
          if(!list.contains(i)) {
            list.add(i);
          }//if
        }//for
        adjacencyList.put(vertice, list);
      }//for
    }//if
  }//fillAllEdges

  /***************************************************************************
   * getDistance2
   ***************************************************************************/
  /**
   * {@inheritDoc}
   *
   ***************************************************************************/
  public List<Integer> getDistance2(int v) {
    List<Integer> result = new ArrayList<Integer>();
    
    List<Integer> hop1List = getOutNeighbours(v);
    
    for (Integer integer : hop1List) {
      if (!result.contains(integer)){
         result.add(integer);
      }//if
    }//for
    
    return result;
  }//getDistance2 
  
  /****************************************************************************
   * getInNeighbours
   ***************************************************************************/
  /**
   * {@inheritDoc}
   * 
   ***************************************************************************/
  @Override
  public List<Integer> getInNeighbours(int vertice) {
    List<Integer> result = new ArrayList<Integer>();
    
    for (int v = 0; v < getNumOfVertices(); v++) {
      List<Integer> list = adjacencyList.get(v);
      
      if(list.contains(vertice)) {
        result.add(v);
      }//if
    }//for
        
    return result;
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
    List<Integer> list = adjacencyList.get(vertice);
    
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
    List<Integer> adjl = adjacencyList.get(startVertice);
    
    if (!adjl.contains(endVertice)) {
      adjl.add(endVertice);
      adjacencyList.put(startVertice, adjl);
    }//if
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
    int indexOfLastVertice = getNumOfVertices();
    adjacencyList.put(indexOfLastVertice, new ArrayList<Integer>());
  }//implementAddVertice

  /****************************************************************************
   * adjacencyString
   ***************************************************************************/
  /**
   * Generate string representation of adjacency list
   * 
   * @return the String
   * 
   ***************************************************************************/
  public String adjacencyString() {
    String s = "Adjacency list";
    s += " (size " + getNumOfVertices() + "+" + getNumOfEdges() + " integers):";

    for (int v : adjacencyList.keySet()) {
      s += "\n\t"+v+": ";
      for (int w : adjacencyList.get(v)) {
        s += w+", ";
      }//if
    }//for
    return s;
  }//adjacencyString
}//AdjListGraph

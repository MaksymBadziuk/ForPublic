/******************************************************************************
 *
 * File :   GraphTest.java
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
 * File contains tests for Graph general class
 *
 ******************************************************************************
 *
 *****************************************************************************/
package my.graph.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import my.graph.AdjListGraph;
import my.graph.AdjMatrixGraph;
import my.graph.Graph;

/***************************************************************************
 * GraphTest
 ***************************************************************************/
/**
 * Class contains tests for Graph general class
 *
 **************************************************************************/
public class GraphTest {
  
  Graph graph;
  
  @Before
  public void setUp() throws Exception {
    graph = new AdjMatrixGraph();
//    graph = new AdjListGraph();
  }//setUp

  @After
  public void tearDown() throws Exception {
  }//tearDown

  @Test
  public void testCreateDefaultGraph() {
   // Should be 4*4 all 0
    
    assertTrue(graph.getNumOfVertices() == 0);
     
    for (int i = 0; i < 4; i++) {
      List<Integer> inList = graph.getInNeighbours(i);
      List<Integer> outList = graph.getInNeighbours(i);
       
      assertTrue(inList.size() == 0);
      assertTrue(outList.size() == 0);
    }//for
    
  }//testCreateDefaultGraph
   
  @Test
  public void testCreateSpecificGraph() {
    int numOfDefaultVertices = 5;
    
    // Test Adjacency matrix implementation
    graph = new AdjMatrixGraph(numOfDefaultVertices);
    
    assertTrue(graph.getNumOfVertices() == 0);
    
    for (int i = 0; i < numOfDefaultVertices; i++) {
      List<Integer> inList = graph.getInNeighbours(i);
      List<Integer> outList = graph.getInNeighbours(i);
       
      assertTrue(inList.size() == 0);
      assertTrue(outList.size() == 0);
    }//for

    // Test Adjacency List implementation
    graph = new AdjListGraph();
    
    assertTrue(graph.getNumOfVertices() == 0);
    
    for (int i = 0; i < numOfDefaultVertices; i++) {
      List<Integer> inList = graph.getInNeighbours(i);
      List<Integer> outList = graph.getInNeighbours(i);
       
      assertTrue(inList.size() == 0);
      assertTrue(outList.size() == 0);
    }//for
  }//testCreateSpecificGraph
  
  @Test
  public void testAddVerticeGraph() {
    // Default graph size is 4 
    assertTrue(graph.getNumOfVertices() == 0);
    graph.addVertice();
    assertTrue(graph.getNumOfVertices() == 1);
    graph.addVertice();
    assertTrue(graph.getNumOfVertices() == 2);
    graph.addVertice();
    assertTrue(graph.getNumOfVertices() == 3);
    graph.addVertice();
    assertTrue(graph.getNumOfVertices() == 4);
    graph.addVertice();
    assertTrue(graph.getNumOfVertices() == 5);
  }//testAddVerticeGraph
  
  @Test
  public void testAddEdgeGraph() {
    assertTrue(graph.getNumOfEdges() == 0);
    graph.addVertice();
    
    System.out.println(graph.toString());
    
    graph.addVertice();
    
    System.out.println(graph.toString());
    
    graph.addEdge(0, 1);
    assertTrue(graph.getNumOfEdges() == 1);
    
    System.out.println(graph.toString());
    
    graph.addEdge(1, 0);
    assertTrue(graph.getNumOfEdges() == 2);
    
    System.out.println(graph.toString());
    
    List<Integer> listIn = graph.getInNeighbours(0);
    List<Integer> listOut = graph.getOutNeighbours(0);
    assertTrue(listIn.size() == 1);
    assertTrue(listOut.size() == 1);
    assertTrue((listOut.size() +  listIn.size()) == 2);
    
    List<Integer> all = graph.getNeighbours(0);
    assertTrue(all.size() == 1);
  }//testAddEdgeGraph
  
  @Test
  public void testExtendGraph() {
    
    graph = new AdjMatrixGraph();
    
    graph.addVertice();
    graph.addVertice();
    graph.addVertice();
    graph.addVertice();
    
    assertTrue(graph.getNumOfVertices() == 4);
    
    graph.fillAllEdges();
    
    assertTrue(graph.getNumOfEdges() == 16);
    
    graph.addVertice();
    
    assertTrue(graph.getNumOfEdges() == 16);
    
    System.out.println(graph.toString());
  }//testExtendGraph
}//GraphTest

/******************************************************************************
 *
 * File :   AdjMatrixGraphTest.java
 * 
 * Author : Badzyuk
 *
 * Date :   9 June 2016 y.
 *
 * History of modifications
 * Date       Rev.    Reason
 *
 *
 *****************************************************************************/
/**
 * File contains tests for Adjacency Matrix implementation of Graph
 *
 ******************************************************************************
 *
 *****************************************************************************/
package my.graph.test;

import org.junit.Before;
import my.graph.AdjMatrixGraph;

/****************************************************************************
 * AdjMatrixGraphTest
 ***************************************************************************/
/**
 *
 ***************************************************************************/
public class AdjMatrixGraphTest extends GraphTest {

  /****************************************************************************
   * setUp
   ***************************************************************************/
  /**
   * Overrided setUp
   * 
   ***************************************************************************/
  @Before
  @Override
  public void setUp() throws Exception {
    super.setUp();
    graph = new AdjMatrixGraph();
  }//setUp
}//AdjMatrixGraphTest

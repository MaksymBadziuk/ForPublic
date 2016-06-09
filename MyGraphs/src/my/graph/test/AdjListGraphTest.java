/******************************************************************************
 *
 * File :   AdjListGraphTest.java
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
 * File contains tests for Adjacency List implementation of Graph
 *
 ******************************************************************************
 *
 *****************************************************************************/
package my.graph.test;

import org.junit.Before;

import my.graph.AdjListGraph;

/****************************************************************************
 * AdjListGraphTest
 ***************************************************************************/
/**
 *
 ***************************************************************************/
public class AdjListGraphTest extends GraphTest {

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
    graph = new AdjListGraph();
  }//setUp
}//AdjListGraphTest

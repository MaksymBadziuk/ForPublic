/******************************************************************************
 *
 * File :   WordNode.java
 * 
 * Author : Badzyuk, CPI-GVA
 *
 * Date :   14 апр. 2016 г.
 *
 * History of modifications
 * Date       Rev.    Reason
 *
 *
 *****************************************************************************/
/**
 * TODO: Add file's description.
 *
 ******************************************************************************
 * 
 *  CPI
 *
 *      U.S.A.  3222 Phoenixville Pike, suite 200 Malvern, PA 19355 
 *                      Tel:  +1-610-430-2700
 *
 *      C.H.    Ch. Pont-du-Centenaire 109, Plan-les-Ouates, P.O. Box 2650
 *                      Tel: +41-22-884-0505
 *
 *  http://www.cranepi.com
 *
 ******** (c) 2015 Crane Payment Innovations, Inc. All rights reserved. ********
 *
 * Decompilation prohibited except as permitted by law. No using, disclosing,
 * reproducing, accessing or modifying without prior written consent.
 *
 *****************************************************************************/
package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/****************************************************************************
 * WordNode
 ***************************************************************************/
/**
 * TODO: Add class's comments here.
 *
 *
 ***************************************************************************/
public class WordNode {
  
  // Piece of info to save
  private String word; 
  
  // List of states for transitions
  private List<String> transitions;

  public WordNode(String word) {
    super();
    this.word = word;
    this.transitions = new ArrayList<String>();
  }

  public String getWord() {
    return word;
  }

  public List<String> getTransitions() {
    return transitions;
  }

  public void addTransitionState(String stateWord ) {
    transitions.add(stateWord);
  }
  
  public String getRandomState(Random r) {
    if (transitions.isEmpty()) {
      return null;
    } else if (transitions.size() == 1) {
      return transitions.get(0);
    }
    
    int transitionsSize = transitions.size();
    
    int randomIndex = r.nextInt(transitions.size() - 1);
    
//    System.out.println(randomIndex);
    return transitions.get(randomIndex);
  }
  
  
}//WordNode

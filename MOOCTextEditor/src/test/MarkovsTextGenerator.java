/******************************************************************************
 *
 * File :   MarkovsTextGenerator.java
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
 * MarkovsTextGenerator
 ***************************************************************************/
/**
 * TODO: Add class's comments here.
 *
 *
 ***************************************************************************/
public class MarkovsTextGenerator {

  List<WordNode> nodesTree;
  
  
  public void train(String text) {
    nodesTree = new ArrayList<WordNode>();
    
    String[] words = text.split("[ ]+");
    
    // Iterate through words and create WordNodes if new word, if
    // the wordNode exist already than add a transition for it
    for (int i = 0; i < words.length; i++) {

      // take a word
      String word = words[i];
      
      // check if it already a Node
      WordNode node = findNode(word);
      
      // if yes add next word to Node transitions list
      if (node != null) {
        if ((i + 1) < words.length) {
          node.addTransitionState(words[i+1]);  
        }

      // if no create new WordNode add it to nodesTree        
      } else {
        WordNode newNodeToHoldInformation = new WordNode(word);
        
        if ((i + 1) < words.length) {
          newNodeToHoldInformation.addTransitionState(words[i+1]);  
        }
        
        nodesTree.add(newNodeToHoldInformation);
      }
    }//for
  }//train
  
  public String generateText(int numberOfWords) {
    StringBuffer result = new StringBuffer();
    Random rand = new Random();
    
    // Pick random node to start with
    WordNode startNodeForMarkovsRandomProcess = nodesTree.get(rand.nextInt(nodesTree.size()));
    result.append(startNodeForMarkovsRandomProcess.getWord());
    result.append(" ");
    
    // Generate Words from nodesTree specific number of times
    for (int i = 0; i < numberOfWords; i++) {
      String newWord = startNodeForMarkovsRandomProcess.getRandomState(new Random());
      
      if (newWord != null) {
        result.append(newWord);
      }
      
      result.append(" ");
//      System.out.println(result.toString());
      
      startNodeForMarkovsRandomProcess = findNode(newWord);
    }//for
    
    return result.toString();
  }//generateText 
  
  public WordNode findNode(String word) {
    for (int i = 0; i < nodesTree.size(); i++) {
      WordNode findNode = nodesTree.get(i);
       if (nodesTree.get(i).getWord().equals(word)) {
         return findNode;
       }
    } //for
    
    return null;
  }
    
  /****************************************************************************
   * main
   ***************************************************************************/
  /**
   * TODO: Add method's description.
   *
   * @param args
   *
   ***************************************************************************/
  public static void main(String[] args) {
    // TODO Auto-generated method stub

    MarkovsTextGenerator generator = new MarkovsTextGenerator();
//    String text = "You say yes, I say no You say stop and I say go, go, go Oh, no ";
    String text = "You say yes, I say no You say stop and I say go, go, go Oh, no You say goodbye and I say hello Hello, hello I don't know why you say goodbye I say hello Hello, hello I don't know why you say goodbye I say hello";
    
    generator.train(text);
    System.out.println(text);
    System.out.println();
    String generated = generator.generateText(40);
    
    System.out.println(generated);
      
  }
  
}



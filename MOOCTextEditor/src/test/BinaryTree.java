/******************************************************************************
 *
 * File :   BinaryTree.java
 * 
 * Author : Badzyuk, CPI-GVA
 *
 * Date :   19 апр. 2016 г.
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

import java.util.LinkedList;
import java.util.Queue;


/****************************************************************************
 * BinaryTree
 ***************************************************************************/
/**
 * Class grasps and holds information in TreeNodes and
 * associatiation between Nodes in Tree0like structure
 *
 ***************************************************************************/
public class BinaryTree<E> {

  private TreeNode<E> root;
  
  public BinaryTree(E rootValue) {
    this.root = new TreeNode(rootValue, null);
  }
  
  public TreeNode<E> getTreeRoot() {
    return root;
  } 
  
  public void insertNode(String value) {
    
    TreeNode<String> curr = (TreeNode<String>) root;
    int result;
    
    while(curr != null) {
      result = curr.getData().compareTo(value);
      
      System.out.println(curr.getData());
      
      if (result > 0) {

        // left
        if (curr.getLeftChild() == null) {
          curr.leftChild = new TreeNode<String>(value, curr);
          return;
        }
        curr = curr.getLeftChild();
      } else if (result < 0) {
        
        if (curr.getRightChild() == null) {
          curr.rightChild = new TreeNode<String>(value, curr);
          return;
        }
        //right
        curr = curr.getRightChild();
        
      } else {
        System.out.println("Node exist");
        return;
      }
    }
  }
  
  private boolean find(TreeNode<String> node, String item) {
    
    TreeNode<String> currentNode = node;
    int result;
    
    while(currentNode != null) {
      result = currentNode.getData().compareTo(item);
      
     if (result > 0) {
       currentNode = currentNode.getLeftChild();
     } else if (result < 0) {
       currentNode = currentNode.getRightChild();
     } else {
       return true;
     }
    }
    return false;
  }
  
  private boolean delete(String item) {
    
    TreeNode<String> rootCopy = (TreeNode<String>) getTreeRoot();
    boolean childs2 = false;
    
    // no such element
    if (!find(rootCopy, item)) {
      return false;
    }
    
    // element is a leaf
    TreeNode<String> currentNode = rootCopy;
    TreeNode<String> parent = null;
    int result;
    
    while(currentNode != null) {
      result = currentNode.getData().compareTo(item);
      parent = currentNode;
      
     if (result > 0) {
       currentNode = currentNode.getLeftChild();
       
       if (  currentNode.getData().compareTo(item) == 0
           && currentNode.getLeftChild() == null
           && currentNode.getRightChild() == null) {
           parent.leftChild = null;
           return true;
       } else if (currentNode.getData().compareTo(item) == 0
           && (currentNode.getLeftChild() == null
           || currentNode.getRightChild() == null)){
         
         if (currentNode.getLeftChild() == null) {
           parent.leftChild = currentNode.getRightChild();
         } else {
           parent.leftChild = currentNode.getLeftChild();
         }
         return true;
         
         // element has 2 childs    
       } else {
       }
       
     } else if (result < 0) {
       currentNode = currentNode.getRightChild();
       
       if (  currentNode.getData().compareTo(item) == 0
           && currentNode.getLeftChild() == null
           && currentNode.getRightChild() == null) {
           parent.rightChild = null;
           return true;
       } else if (currentNode.getData().compareTo(item) == 0
               && (currentNode.getLeftChild() == null
               || currentNode.getRightChild() == null)){
             
             if (currentNode.getLeftChild() == null) {
               parent.rightChild = currentNode.getRightChild();
             } else {
               parent.rightChild = currentNode.getLeftChild();
             }
             return true;
     
        
       } else {
         
       }
     } else {
       // element has 2 childs   
       childs2 = true;
       break;
     }
    }
    
    TreeNode<String> currentNodeC = currentNode;
    TreeNode<String> parentC = parent;
    
    // element has 2 childs  
    if (childs2) {

      // find smallest in right subtree
      TreeNode<String> parentOfSmallest = currentNodeC.getRightChild();
      TreeNode<String> smallestLeftNode = currentNodeC.getRightChild();
      while (smallestLeftNode.getLeftChild() != null) {
        parentOfSmallest = smallestLeftNode;
        smallestLeftNode = smallestLeftNode.getLeftChild();
      }

      // swap value from smallest to current
      currentNodeC.value = smallestLeftNode.getData();
      
      // delete smallest in right subtree
      parentOfSmallest.leftChild = null;
      
      return true;
    }
     
    System.out.println("Error.Shouldn't come here");
    return false;
  }
  
  public void printTree() {
    preorderTraversal((TreeNode<String>) getTreeRoot());
  }
  
  /****************************************************************************
   * main
   ***************************************************************************/
  /**
   * @param args
   ***************************************************************************/
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    System.out.println("BinaryTree Test");
    
    BinaryTree<String> familyTree = new BinaryTree<>("D");
    TreeNode<String> root = familyTree.getTreeRoot();
    
    root.addLeftChild("B");
//    root.addRightChild("F");
//    
//    root.getLeftChild().addLeftChild("A");
//    root.getLeftChild().addRightChild("C");
//    
//    root.getRightChild().addLeftChild("E");
//    root.getRightChild().addRightChild("G");
    
    root.addRightChild("G");
    
    root.getLeftChild().addLeftChild("A");
    root.getLeftChild().addRightChild("C");
    
    root.getRightChild().addLeftChild("E");
    root.getRightChild().addRightChild("K");
    
//    
//    System.out.println("Preorder Traversal");
//    preorderTraversal(familyTree.getTreeRoot());
//    
//    System.out.println();
//    System.out.println("Postoder Traversal");
//    postOrderTraversal(familyTree.getTreeRoot());
//    
//    System.out.println();
//    System.out.println("InOrder Traversal");
//    inOrderTraversal(familyTree.getTreeRoot());
//
//    System.out.println();
//    System.out.println("LevelOrder Traversal");
//    levelOrderTraversal(familyTree.getTreeRoot());

//    findNodeInBST( familyTree,"P");
    
//    System.out.println(iterativeFind(familyTree.getTreeRoot(), "A"));
//    
//    System.out.println("Node found: " + iterativeFind(familyTree.getTreeRoot(), "F"));
//    
//    familyTree.insertNode("F");
//    
//    System.out.println("Node found: " + iterativeFind(familyTree.getTreeRoot(), "F"));
    
    familyTree.printTree();
    System.out.println();
    System.out.println("Element deleted:" + familyTree.delete("O"));    
    System.out.println("Element deleted:" + familyTree.delete("B"));
    familyTree.printTree();
    familyTree.insertNode("B");
    familyTree.printTree();
    System.out.println("Element deleted:" + familyTree.delete("B"));
    familyTree.printTree();
  }

  /****************************************************************************
   * traverseTree
   ***************************************************************************/
  /**
   * TODO: Add method's description.
   *
   * @param familyTree
   *
   ***************************************************************************/
  private static void traverseTree(BinaryTree<String> familyTree) {
   
    preorderTraversal(familyTree.getTreeRoot());
   
    
  }

  /****************************************************************************
   * preorderTraversal
   ***************************************************************************/
  /**
   * Traverse the tree in depth
   *
   ***************************************************************************/
  private static void preorderTraversal(TreeNode<String> treeRoot) {

    if (treeRoot != null) {
      treeRoot.visit();
      preorderTraversal(treeRoot.getLeftChild());
      preorderTraversal(treeRoot.getRightChild());
    }
  }
  
  private static void postOrderTraversal(TreeNode<String> treeRoot) {

    if (treeRoot != null) {
      postOrderTraversal(treeRoot.getLeftChild());
      postOrderTraversal(treeRoot.getRightChild());
      treeRoot.visit();
    }
  }
  
  private static void inOrderTraversal(TreeNode<String> treeRoot) {

    if (treeRoot != null) {
      inOrderTraversal(treeRoot.getLeftChild());
      treeRoot.visit();
      inOrderTraversal(treeRoot.getRightChild());
    }
  }
  
  private static void levelOrderTraversal(TreeNode<String> treeRoot) {

    Queue<TreeNode<String>> queue = new LinkedList<TreeNode<String>>();
    
    queue.add(treeRoot);
    
    
    while(!queue.isEmpty()) {
      TreeNode<String> firstNodeInQueue = queue.remove();
      firstNodeInQueue.visit();
      
      if (firstNodeInQueue.getLeftChild() != null){
        queue.add(firstNodeInQueue.getLeftChild());
      } 
      if (firstNodeInQueue.getRightChild() != null){
        queue.add(firstNodeInQueue.getRightChild());
      }
    }//while
  }
  
  private static boolean flagFound = false;
  private static void findNodeInBST(BinaryTree<String> tree, String item) {
    flagFound = false;
   
    recursiveFind(tree.getTreeRoot(), item);
    
    System.out.println(flagFound);
    
  }
  
  private static void recursiveFind(TreeNode<String> node, String item) {
    if (node == null ) {
      return;
    }
    
    int result = node.getData().compareTo(item);
    
    System.out.println(node.getData());
    
    if ( result == 0 ) {
      flagFound = true;
    } else if (result > 0) {
      
      //takeLeftSubTree
      recursiveFind(node.getLeftChild(), item);
      
    } else {
      //takeRightSubTree
      recursiveFind(node.getRightChild(), item);
    }
  }

  private static boolean iterativeFind(TreeNode<String> node, String item) {
    
    TreeNode<String> currentNode = node;
    int result;
    
    while(currentNode != null) {
      result = currentNode.getData().compareTo(item);
      
     if (result > 0) {
       currentNode = currentNode.getLeftChild();
     } else if (result < 0) {
       currentNode = currentNode.getRightChild();
     } else {
       return true;
     }
    }
    return false;
  }
  
  /****************************************************************************
   * visitThis
   ***************************************************************************/
  /**
   *
   ***************************************************************************/
  private static void visitThis(String data) {
   System.out.print(data);
  }

  private static void visitLeft() {
    System.out.print("|");
   }
  
  private static void visitRight() {
    System.out.print("\t|");
   }
  
  
  // Class TreeNode that grasps and saves information in a tree
  public static class TreeNode<E> {
   
    private E value; 
    private TreeNode<E> parent;
    private TreeNode<E> leftChild;
    private TreeNode<E> rightChild;
    
    public TreeNode(E values, TreeNode<E> parent) {
      this.value = values;
      this.parent = parent;
      this.leftChild = null;
      this.rightChild = null;
    }
    
    /****************************************************************************
     * visit
     ***************************************************************************/
    /**
     * TODO: Add method's description.
     *
     *
     ***************************************************************************/
    public void visit() {
      System.out.print(this.value + " - ");
    }

    public void addLeftChild(E value) {
      leftChild = new TreeNode<E>(value, this);
    }
    
    public void addRightChild(E value) {
      rightChild = new TreeNode<E>(value, this);
    }
    
    public TreeNode<E> getLeftChild() {
      return leftChild;
    } 
    
    public TreeNode<E> getRightChild() {
      return rightChild;
    } 
  
    public E getData() {
      return value;
    }
  } 
  
}

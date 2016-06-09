package spelling;

import java.util.List;
import java.util.Queue;
import java.util.Set;

import test.BinaryTree.TreeNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should ignore the word's case.
	 * That is, you should convert the string to all lower case as you insert it. */
	public boolean addWord(String word)
	{
	    String lower = word.toLowerCase();
	    int index = 0;
	    TrieNode previous = root;
	    TrieNode next = previous;
	    char currentC;
	    
	    while(next != null && index < lower.length()) {
	      previous = next;
	      currentC = lower.charAt(index);
	      next = next.getChild(currentC);
	      index++;
      }//while

	    // Word is in dictionary
      if(next != null) {
        if (next.endsWord()) {
          return false;
        } else {
          next.setEndsWord(true);
          size++;
          return true;
        }
       
      // Add the char from index and set the word next
      } else {
        index--;
	      while (index < lower.length()) {
	        char currentChar = lower.charAt(index);
	        previous = previous.insert(currentChar);
	        index++;
	      }//while
	      
	      previous.setEndsWord(true);
	      size++;
	      return true;
      }//if
	}//addWord
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) 
	{
	  if (s.isEmpty()) {
	    return false;
	  }
	  
	  String input = s.toLowerCase();
	  TrieNode next = root;
	  int index = 0;
	  
	  while(next != null && index < input.length()) {
	    next = next.getChild(input.charAt(index));
	    index++;
	  }//while
	  
	  if (next == null) {
	    return false;
	  } else {
	    return true;
	  }
	}

	/** 
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param text The text to use at the word stem
     * @param n The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
       List<String> completions = new LinkedList<String>();
       
       String input = prefix.toLowerCase();
       TrieNode next = root;
       int index = 0;
       
       while(next != null && index < input.length()) {
         next = next.getChild(input.charAt(index));
         index++;
       }//while
       
       if (prefix.isEmpty()) {
         next = root;
       }
       
       // No stem i a trie
       if (next == null) {

       // Stem presents go broadth search from next TrieNode
       } else {
         
         Queue<TrieNode> queue = new LinkedList<TrieNode>();
         
         queue.add(next);
         
         int numberOfPreds = 0;
         Set<Character> validNextCharacters = null;
        
         while(  !queue.isEmpty() 
               && numberOfPreds < numCompletions) {
           TrieNode firstNodeInQueue = queue.remove();

           // Visit first node
           if (firstNodeInQueue.endsWord()) {
             completions.add(firstNodeInQueue.getText());
             numberOfPreds++;
           }
           validNextCharacters = firstNodeInQueue.getValidNextCharacters();
           
           // Add all childs to the queue
           for (Character character : validNextCharacters) {
             queue.add(firstNodeInQueue.getChild(character));
           } //for
         }//while
       }
       
       return completions;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}
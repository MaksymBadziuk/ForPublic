package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import test.WordNode;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
	  String[] words = sourceText.split("[ ]+");
	  
	  starter = words.length > 0 ? words[0] : "";
	  
    // Iterate through words and create WordNodes if new word, if
    // the wordNode exist already than add a transition for it
    for (int i = 0; i < words.length; i++) {

      // take a word
      String word = words[i];
      
      // check if it already a Node
      ListNode node = findNode(word);
      
      // if yes add next word to Node transitions list
      if (node != null) {
        if ((i + 1) < words.length) {
          node.addNextWord(words[i+1]);  
        }

      // if no create new WordNode add it to nodesTree        
      } else {
        ListNode newNodeToHoldInformation = new ListNode(word);
        
        if ((i + 1) < words.length) {
          newNodeToHoldInformation.addNextWord(words[i+1]);  
        }
        
        wordList.add(newNodeToHoldInformation);
      }
    }//for
	}
	
	 public ListNode findNode(String word) {
	    for (int i = 0; i < wordList.size(); i++) {
	      ListNode findNode = wordList.get(i);
	       if (wordList.get(i).getWord().equals(word)) {
	         return findNode;
	       }
	    } //for
	    
	    return null;
	  }
	
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
	  StringBuffer result = new StringBuffer();
    Random rand = new Random();
    
    // Pick random node to start with
    ListNode startNodeForMarkovsRandomProcess = findNode(starter);
    result.append(startNodeForMarkovsRandomProcess.getWord());
    result.append(" ");
    
    // Generate Words from nodesTree specific number of times
    for (int i = 0; i < numWords; i++) {
      String newWord = startNodeForMarkovsRandomProcess.getRandomNextWord(new Random());
      
      if (newWord != null) {
        result.append(newWord);
      }
      
      result.append(" ");
//      System.out.println(result.toString());
      
      startNodeForMarkovsRandomProcess = findNode(newWord);
    }//for
    
    return result.toString();
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
	  wordList = new LinkedList<ListNode>();
		train(sourceText);
	}
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
	  if (nextWords.isEmpty()) {
      return null;
    } else if (nextWords.size() == 1) {
      return nextWords.get(0);
    }
    
    int randomIndex = generator.nextInt(nextWords.size() - 1);
    
//    System.out.println(randomIndex);
    return nextWords.get(randomIndex);
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}



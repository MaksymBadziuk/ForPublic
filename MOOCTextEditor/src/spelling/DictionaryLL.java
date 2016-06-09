package spelling;

import java.util.LinkedList;

/**
 * A class that implements the Dictionary interface using a LinkedList
 *
 */
public class DictionaryLL implements Dictionary 
{

	private LinkedList<String> dict;
	
	public DictionaryLL() {
	  dict = new LinkedList<String>();
	}//DictionaryLL

    /** Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
    public boolean addWord(String word) {

      // check if word is inside Dic (  takes O(n) time than add O(1))  
      if ( ! isWord(word) ) {
        dict.add(word.toLowerCase());
        return true;
      }
      
      return false;
    }


    /** Return the number of words in the dictionary */
    public int size()
    {
        return dict.size();
    }

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
        
      String lower = s.toLowerCase();

      if(dict.contains(lower)) {
        return true;
      }

      return false;
    }

    
}

package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		
		size = 0;
		
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
	  if (element == null) {
	    throw new NullPointerException();
	  }//if
	  
	  LLNode<E> prevToEndNode = tail.prev;
	  LLNode<E> newNode = new LLNode<E>(element);
	  
	  tail.prev = newNode;
	  newNode.next = tail;
	  newNode.prev = prevToEndNode;
	  
	  prevToEndNode.next = newNode;
	  
	  size++;
	  
		return true;
	}//add

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		return getNodeByIndex(index).data;
	}//get

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		LLNode<E> newNode = new LLNode<E>(element);
		
		LLNode<E> targetNodeInsertBefore = getNodeByIndex(index); 
		LLNode<E> targetNodeInsertAfter  = targetNodeInsertBefore.prev; 
		
		
		targetNodeInsertAfter.next = newNode;
		targetNodeInsertBefore.prev = newNode;
		
		newNode.prev = targetNodeInsertAfter;
		newNode.next = targetNodeInsertBefore;
		size++;
	}

	private LLNode<E> getNodeByIndex(int index) {
	  if (index >= size || index < 0) {
      throw new IndexOutOfBoundsException();
    }//if
    
    LLNode<E> nextNode = head.next;
    
    for (int i = index; i > 0; i--) {
      
      nextNode = nextNode.next;
    }//for
    
    return nextNode;	  
	}//getNodeByIndex
	
	
	/** Return the size of the list */
	public int size() 
	{
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		LLNode<E> targetToRemove = getNodeByIndex(index);
		
		LLNode<E> targetNodeBefore = targetToRemove.prev; 
    LLNode<E> targetNodeAfter  = targetToRemove.next; 
		
    targetNodeBefore.next = targetNodeAfter;
    targetNodeAfter.prev = targetNodeBefore;
    size--;
		
		return targetToRemove.data;
	}//remove

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
	  LLNode<E> targetNode = getNodeByIndex(index);
	  
	  if (element == null) {
	    throw new NullPointerException();
	  }//if
	  
	  E cache = targetNode.data;
	  
	  targetNode.data = element;
	  
		return cache;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}

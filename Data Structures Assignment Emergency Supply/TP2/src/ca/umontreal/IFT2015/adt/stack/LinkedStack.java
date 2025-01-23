package ca.umontreal.IFT2015.adt.stack;

import ca.umontreal.IFT2015.adt.list.SinglyLinkedList;
import ca.umontreal.IFT2015.adt.list.List;

/**
* LinkedStack is an implementation of the ADT/interface Stack using a SinglyLinkedList
*   A collection of elements inserted and removed using the last-in first-out policy.
*   All operations execute in O(1).
*
* From Goodrich, Tamassia, Goldsasser
*
*   Adapter design pattern of ADT List
* 
* @author      Francois Major
* @version     1.0
* @since       1.0
*/
public class LinkedStack<E> implements Stack<E> {
    
    private List<E> list = new SinglyLinkedList<>(); // empty list

    @Override
    public int     size()      { return( this.list.size() ); }     // return the number of elements in the stack
    @Override
    public boolean isEmpty()   { return( this.list.isEmpty() ); }  // return true if the stack is empty, false otherwise
    @Override
    public void    push( E e ) { this.list.addFirst( e ); }        // insert element e at the top of the stack
    @Override
    public E       top()       { return this.list.first(); }       // return the element at the top of the stack, null if empty
    @Override
    public E       pop()       { return this.list.removeFirst(); } // remove and return the element at the top of the stack, null if empty

    @Override
    public String toString() { return this.list.toString(); }
}


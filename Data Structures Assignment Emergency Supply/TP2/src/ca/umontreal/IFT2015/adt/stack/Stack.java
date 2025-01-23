package ca.umontreal.IFT2015.adt.stack;

/**
* Stack is an interface for stack management operations (ADT)
*   A collection of elements inserted and removed using the last-in first-out policy.
*   This interface differs from java.util.Stack.
*
* From Goodrich, Tamassia, Goldsasser
* 
* @author      Francois Major
* @version     1.0
* @since       1.0
*/
public interface Stack<E> {
    public int     size();      // return the number of elements in the stack
    public boolean isEmpty();   // return true if the stack is empty, false otherwise
    public void    push( E e ); // insert element e at the top of the stack
    public E       top();       // return the element at the top of the stack, null if empty
    public E       pop();       // remove and return the element at the top of the stack, null if empty
}

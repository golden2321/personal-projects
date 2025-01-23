package ca.umontreal.IFT2015.adt.queue;

import ca.umontreal.IFT2015.adt.list.CircularList;
import ca.umontreal.IFT2015.adt.list.CircularlyLinkedList;

/**
* LinkedQueue is an implementation of the ADT/interface Queue using a singly linked list
*   A collection of elements inserted and removed using the first-in first-out policy.
*   All operations execute in O(1).
*
* From Goodrich, Tamassia, Goldsasser
* 
* @author      Francois Major
* @version     1.0
* @since       1.0
*/
public class LinkedCircularQueue<E> implements CircularQueue<E> {
    // attribute
    private CircularList<E> list = new CircularlyLinkedList<>(); // empty circular list
    public LinkedCircularQueue() {} // new circular queue based on the initially empty circular list
    @Override
    public int size() { return this.list.size(); }
    @Override
    public boolean isEmpty() { return this.list.isEmpty(); }
    @Override
    public void enqueue( E element ) { this.list.addLast( element ); }
    @Override
    public E first() { return this.list.first(); }
    @Override
    public E dequeue() { return this.list.removeFirst(); }
    @Override
    public void rotate() { this.list.rotate(); }
    @Override
    public String toString() { return this.list.toString(); }
}

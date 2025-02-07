package ca.umontreal.IFT2015.adt.queue;
import java.lang.IllegalStateException;

/**
* ArrayDeque is an implementation of the ADT/interface Queue and extension of ArrayQueue
*   A double-ended queue with insertion and deletion at both the front and the back of a queue
*   All operations execute in O(1).
* 
* @author      Francois Major
* @version     1.0
* @since       1.0
*/
public class ArrayDeque<E> extends ArrayQueue<E> implements Queue<E>, Deque<E> {

    @Override
    public E last() { // return the last element of the deque, null if empty
	if( this.isEmpty() ) return null;
	return this.data[( this.f + this.size - 1 ) % this.data.length];
    }
    @Override
    public void addLast( E e ) throws IllegalStateException { // insert element e at the end of the deque
	if( this.size == this.data.length ) throw new IllegalStateException( "Full Deque" );
	this.enqueue( e );
    }
    @Override
    public void addFirst( E e ) throws IllegalStateException { // insert element e at the front of the deque
	if( this.size == this.data.length ) throw new IllegalStateException( "Full Deque" );
	int avail = ( this.f - 1 + this.data.length ) % this.data.length;
	this.data[avail] = e; // use modulo for circularity
	this.f = avail;
	this.size++;
    }
    @Override
    public E removeFirst() { // remove and return the first element of the deque, null if empty
	if( isEmpty() ) return null;
	return this.dequeue();
    }
    @Override
    public E removeLast() { // remove and return the last element of the deque, null if empty
	if( isEmpty() ) return null;
	int last = ( this.f + this.size - 1 ) % this.data.length;
	E element = this.data[last];
	this.data[last] = null; // for garbage collection
	this.size--;
	return element;
    }
    @Override
    public String toString() {
	return super.toString() + " f: " + this.f + " size: " + this.size;
    }
}

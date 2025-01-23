package ca.umontreal.IFT2015.adt.list;

import java.util.NoSuchElementException;
import java.lang.IllegalStateException;
import java.lang.IndexOutOfBoundsException;
import java.util.Iterator;

/**
* ArrayList is an implementation of the ADT/interface List with an extensible Array
* 
* if not specified, all operations in O(1)
*
* From Goodrich, Tamassia, Goldsasser
*
* @author      Francois Major
* @version     1.0
* @since       1.0
*/

public class ArrayList<E> implements List<E> {
    public static final int CAPACITY = 1;  // default capacity
    private E[] data;                      // generic array used to store the elements
    private int size = 0;                  // current number of elements
    // check if provided index i between 0 and n-1
    protected void checkIndex( int i, int n ) throws IndexOutOfBoundsException {
	if( i < 0 || i >= n ) throw new IndexOutOfBoundsException( "index " + i + " out of bounds" );
    }
    //----- inner iterator class
    protected class ArrayListIterator implements Iterator<E> {
	private int index = 0; // reference to next element
	public boolean hasNext() { return this.index < size; }
	public E next() throws NoSuchElementException {
	    if( this.index >= size ) throw new NoSuchElementException( "No more element" );
	    return data[this.index++];
	}
    } //----- end of inner iterator class
    // constructors
    public ArrayList() { this( CAPACITY ); } // construct list with default capacity
    public ArrayList( int capacity ) {       // construct list with given capacity
	data = (E[]) new Object[capacity];   // safe cast
    }
    @Override
    public Iterator<E> iterator() { // return an iterator over the elements in proper order
	return new ArrayListIterator();
    }
    @Override
    public int size() { return this.size; } // return the number of elements in list
    @Override
    public boolean isEmpty() { return this.size == 0; } // return whether the list is empty or not
    @Override
    public E get( int i ) throws IndexOutOfBoundsException { // return the element at index i
	this.checkIndex( i, size );
	return this.data[i];
    }
    @Override
    public E set( int i, E e ) throws IndexOutOfBoundsException { // set element at index i to e
	this.checkIndex( i, this.size );
	E tmp = this.data[i];
	this.data[i] = e;
	return tmp;
    }
    protected void resize( int capacity ) { // resize this.data to have given capacity in O(n)
	E[] tmp = (E[]) new Object[capacity]; // safe cast
	for( int k = 0; k < this.size; k++ )  // copy all elements
	    tmp[k] = this.data[k];
	this.data = tmp;                      // replace with new larger array
    }
    @Override
    public void add( int i, E e ) throws IndexOutOfBoundsException { // insert element e at index i in O(n)
	this.checkIndex( i, this.size + 1 );
	if( this.size == this.data.length ) // not enough space
	    this.resize( 2 * this.data.length ); // double the space of the array to store the elements
	for( int k = this.size - 1; k >= i; k-- ) // shift elements
	    this.data[k+1] = this.data[k];
	this.data[i] = e; // insert element e at index i
	this.size++; // adjust number of elements
    }
    @Override
    public E remove( int i ) throws IndexOutOfBoundsException { // remove element at index i in O(n)
	this.checkIndex( i, this.size );
	E tmp = this.data[i];
	for( int k = i; k < this.size - 1; k++ ) // shift elements
	    this.data[k] = this.data[k+1];
	this.data[this.size-1] = null; // garbage collection
	this.size--; // adjust number of elements
	if( this.data.length / 2 > this.size ) // shrink if array is half empty
	    this.resize( this.data.length / 2 );
	return tmp;
    }
    @Override
    public E first() { // return first element
	if( this.isEmpty() ) throw new NoSuchElementException( "list is empty" );
	return this.data[0];
    }
    @Override
    public E last() { // return last element
	if( this.isEmpty() ) throw new NoSuchElementException( "list is empty" );
	return this.data[this.size-1];
    }
    @Override
    public void addFirst( E e ) { this.add( 0, e ); } // add element e at index 0
    @Override
    public void addLast( E e ) { this.add( this.size, e ); } // add element e at index size-1
    @Override
    public void add( E e ) { this.add( this.size, e ); } // add element e at index size (compatible Java List; append)
    @Override	
    public E removeFirst() { return this.remove( 0 ); } // remove and return element at index 0
    @Override	
    public E remove( E e ) { // search, remove, and return element e in O(n)
	int index = -1;
	for( int i = 0; i < this.size; i++ ) // search by iteration over all elements
	    if( this.data[i].equals( e ) ) {
		index = i;
		break; // if found, stop iterating
	    }
	return this.remove( index );
    }
    @Override
    public int indexOf( E e ) { // search and return the index of element e in O(n)
	int index = -1;
	for( int i = 0; i < this.size; i++ ) // search by iteration over all elements
	    if( this.data[i].equals( e ) ) {
		index = i;
		break; // if found, stop iterating
	    }
	return index;
    }
    @Override
    public boolean equals( List<E> other ) { // check list equality in O(n)
	if( other == null ) return false;
	if( this.getClass() != other.getClass() ) return false;
	ArrayList that = (ArrayList) other; // use non-aparmeterized type
	if( this.size() != that.size() ) return false;
	for( int i = 0; i < this.size; i++ ) // iterate over all elements
	    if( !this.data[i].equals( other.get( i ) ) )
		return false;
	return true; // if end up here, then the lists are equal
    }
    @Override
    public String toString() { // pretty print of the ArrayList with capacity information
	if( this.isEmpty() ) return "[]";
	String pp = "[" + this.data[0];
	for( int i = 1; i < this.size; i++ )
	    pp += "," + this.data[i];
	pp += "](capacity=" + this.data.length + ")";
	return pp;
    }
}

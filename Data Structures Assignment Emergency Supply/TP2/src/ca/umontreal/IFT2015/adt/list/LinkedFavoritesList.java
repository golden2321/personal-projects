package ca.umontreal.IFT2015.adt.list;

import java.util.Iterator;
import java.lang.Iterable;
import java.lang.IllegalArgumentException;

/**
* FavoritesList implements a list of elements ordered according to access frequencies
*    and implements the FavoritesList interface
* 
*     performing n accesses executes in O(n^2) (like insertion sort)
*
* Based on Goodrich, Tamassia, Goldwasser
*
* @author      Francois Major
* @version     1.0
* @since       1.0
*/

public class LinkedFavoritesList<E> implements FavoritesList<E> {

    // Inner class for wrapping elements in Countable<E>
    protected class WrapperE implements Countable<WrapperE> {
	private E value;
	private int count;
	public WrapperE( E value ) { this.value = value; this.count = 0; }
	public E getValue() { return this.value; }
	@Override
	public int getCount() { return this.count; }
	@Override
	public void increment() { this.count++; }
	@Override
	public int compareTo( WrapperE other ) { return Integer.compare( this.count, other.count ); }
    }

    // Inner class to wrap WrapperEs in Positions to provide a Position interface
    protected class WrappedPosition implements Position<E> {
        private Position<WrapperE> innerPosition;

        public WrappedPosition( Position<WrapperE> innerPosition ) {
            this.innerPosition = innerPosition;
        }

        @Override
        public E getElement() { return innerPosition.getElement().getValue(); }
        public Position<WrapperE> getInnerPosition() { return innerPosition; }
    }

    // Physical data structure
    protected PositionalList<WrapperE> list = new LinkedPositionalList<>(); // the item list

    // developer's utilities
    // shorthand notation to get the value of an WrapperE given its Position
    protected E value( Position<WrapperE> p ) { return p.getElement().getValue(); }
    // shorthand notation to get the count of an WrapperE given its Position
    protected int count( Position<WrapperE> p ) { return p.getElement().getCount(); }

    // Constructor
    public LinkedFavoritesList() {} // construct initially empty LinkedPositionalList (of WrapperE<E>)
    
    // move item at Position p earlier in the list based on access count in O(n)
    protected void moveUp( Position<E> p ) {
	WrappedPosition wrappedPosition = (WrappedPosition) p; // safe cast
	Position<WrapperE> innerPosition = wrappedPosition.getInnerPosition();
	int cnt = this.count( innerPosition ); // get count at Position p
	Position<WrapperE> walk = innerPosition;
	while( walk != this.list.first() && this.count( this.list.before( walk ) ) < cnt )
	    walk = this.list.before( walk ); // found smaller count ahead of p
	if( walk != innerPosition ) // if a position ahead of p was found
	    this.list.moveBefore( walk, innerPosition );
    }
    // public methods
    // return number of items in the list
    @Override
    public int size() { return this.list.size(); }
    // return true if the list is empty
    @Override
    public boolean isEmpty() { return this.list.isEmpty(); }
    // access element e (possibly new), and increase its access count
    @Override
    public Position<E> add( E e ) {
	Position<WrapperE> p = this.list.addLast( new WrapperE( e ) ); // add element at the end of the list
	return new WrappedPosition( p );
    }
    // fast access element e by its Position
    @Override
    public E access( Position<E> p ) { // assume p is not null
	WrappedPosition wrappedPosition = (WrappedPosition) p; // safe cast
	WrapperE item = wrappedPosition.getInnerPosition().getElement();
	item.increment(); // increment counter
	this.moveUp( p ); // move forward if needed
	return item.getValue();
    }
    // remove element equal to e from the list of favorites, if found
    @Override
    public E remove( Position<E> p ) {
	System.out.println( "LinkedFavoritesList.remove..." ); // debug, will be removed
	WrappedPosition wrappedPosition = (WrappedPosition) p; // safe cast
	WrapperE removedWrapperE = list.remove( wrappedPosition.getInnerPosition() );
	return removedWrapperE.getValue();
    }
    // return an iterable collection of the k most frequently accessed elements
    @Override
    public Iterable<E> getFavorites( int k ) throws IllegalArgumentException {
	if( k < 0 || k > this.size() )
	    throw new IllegalArgumentException( "Invalid k = " + k );
	List<E> result = new SinglyLinkedList<>();
	Iterator<WrapperE> iter = this.list.iterator();
	for( int j = 0; j < k; j++ )
	    result.add( iter.next().getValue() );
	return result;
    }
    @Override
    public void sort() {
	this.list.sort();
    }
}

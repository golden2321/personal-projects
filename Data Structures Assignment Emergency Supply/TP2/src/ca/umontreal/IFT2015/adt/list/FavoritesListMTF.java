package ca.umontreal.IFT2015.adt.list;

import java.util.Iterator;
import java.lang.Iterable;
import java.lang.IllegalArgumentException;

/**
* FavoritesListMTF implements a list of elements ordered according to access frequencies
* 
*     performing n accesses executes in O(k*n) Move-to-first strategy
*
* Based on Goodrich, Tamassia, Goldwasser
*
* @author      Francois Major
* @version     1.0
* @since       1.0
*/

public class FavoritesListMTF<E> extends LinkedFavoritesList<E> {

    // move item of Position p at the front of the list
    @Override
    protected void moveUp( Position<E> p ) {
	WrappedPosition wrappedPosition = (WrappedPosition) p; // safe cast
	Position<WrapperE> innerPosition = wrappedPosition.getInnerPosition();
	if( innerPosition != this.list.first() )
	    this.list.moveFirst( innerPosition );
    }
    // return an iterable collection of the k most frequently accessed elements
    @Override
    public Iterable<E> getFavorites( int k ) throws IllegalArgumentException {
	if( k < 0 || k > this.size() )
	    throw new IllegalArgumentException( "Invalid k = " + k );
	// make a copy of the original list
	PositionalList<WrapperE> tmp = new LinkedPositionalList<>();
	for( WrapperE item : this.list ) tmp.addLast( item ); // copy items in tmp O(n)
	// repeat find, report, and remove biggest count's element k times
	List<E> result = new SinglyLinkedList<>();
	for( int j = 0; j < k; j++ ) { // O(k)
	    Position<WrapperE> topPos = tmp.first();
	    Position<WrapperE> walk = tmp.after( topPos );
	    while( walk != null ) { // O(n)
		if( this.count( walk ) > this.count( topPos ) )
		    topPos = walk;
		walk = tmp.after( walk );
	    }
	    // element with highest count found, remove it and store it in results
	    result.add( this.value( topPos ) );
	    tmp.remove( topPos );
	}
	return result;
    }
}

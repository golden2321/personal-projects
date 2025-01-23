package ca.umontreal.IFT2015.maps;

import java.lang.Iterable;
import java.util.Iterator;
import java.util.ArrayList;
import java.lang.UnsupportedOperationException;
import java.util.NoSuchElementException;
import java.util.Comparator;

import ca.umontreal.IFT2015.adt.list.Position;
import ca.umontreal.IFT2015.trees.LinkedBinaryTree;

/**
* RBTreeMap is an implementation of the ADT SortedMap
*   which extends TreeMap and uses BalanceableBinaryTree
* 
* Based on Goodrich, Tamassia, Goldwasser
*
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/

public class RBTreeMap<K,V> extends TreeMap<K,V> {

    // constructors
    public RBTreeMap() { super(); }
    public RBTreeMap( Comparator<K> comp ) { super( comp ); }

    // use of the attribute aux from BalanceableBinaryTree
    // with convention that 0 is black; 1 is red
    // BSTNodes are created as black by default
    private boolean isBlack( Position<Entry<K,V>> p ) { return tree.getAux( p ) == 0; }
    private boolean isRed( Position<Entry<K,V>> p ) { return tree.getAux( p ) == 1; }
    private void makeBlack( Position<Entry<K,V>> p ) { tree.setAux( p, 0 ); }
    private void makeRed( Position<Entry<K,V>> p ) { tree.setAux( p, 1 ); }
    private void setColor( Position<Entry<K,V>> p, boolean toRed ) { tree.setAux( p, toRed ? 1 : 0 ); }

    // override the TreeMap rebalancing hook called after insertion
    protected void rebalanceInsert( Position<Entry<K,V>> p ) {
	if( !isRoot( p ) ) {
	    makeRed( p ); // this new internal node is initially colored red
	    resolveRed( p ); // but this may cause a double-red problem
	}
    }

    // resolve double red above red position p
    private void resolveRed( Position<Entry<K,V>> p ) {
	Position<Entry<K,V>> parent, uncle, middle, grand; // used in case analysis
	parent = parent( p );
	if( isRed( parent ) ) { // double red
	    uncle = sibling( parent );
	    if( isBlack( uncle ) ) { // case #1 deformed 4-node
		middle = restructure( p ); // trinode restructuring
		makeBlack( middle );
		makeRed( left( middle ) );
		makeRed( right( middle ) );
	    } else { // case #2 overfull 5-node
		makeBlack( parent ); // recoloring
		makeBlack( uncle );
		grand = parent( parent );
		if( !isRoot( grand ) ) {
		    makeRed( grand ); // grandparent becomes red
		    resolveRed( grand ); // propagate at red grandparent
		}
	    }
	}
    }

    // override the TreeMap rebalancing hook called after deletion
    protected void rebalanceDelete( Position<Entry<K,V>> p ) {
	if( isRed( p ) ) makeBlack( p ); // deleted parent was black, it restores black depth
	else if( !isRoot( p ) ) {
	    Position<Entry<K,V>> sib = sibling( p );
	    if( isInternal( sib ) && ( isBlack( sib ) || isInternal( left( sib ) ) ) )
		remedyDoubleBlack( p ); // sib's subtree has nonzero black height
	}
    }

    // remedy presumed double black at given non-root position
    private void remedyDoubleBlack( Position<Entry<K,V>> p ) {
	Position<Entry<K,V>> z = parent( p );
	Position<Entry<K,V>> y = sibling( p );
	if( isBlack( y ) ) {
	    if( isRed( left( y ) ) || isRed( right( y ) ) ) { // case #1 trinode restructuring
		Position<Entry<K,V>> x = ( isRed( left( y ) ) ? left( y ) : right( y ) );
		Position<Entry<K,V>> middle = restructure( x );
		setColor( middle, isRed( z ) ); // restructured subtree root gets z's old color
		makeBlack( left( middle ) );
		makeBlack( right( middle ) );
	    } else { // case #2 recoloring
		makeRed( y );
		if( isRed( z ) ) makeBlack( z ); // problem solved
		else if( !isRoot( z ) )
		    remedyDoubleBlack( z ); // propagate problem
	    }
	} else { // case #3 reorient 3-node
	    rotate( y );
	    makeBlack( y );
	    makeRed( z );
	    remedyDoubleBlack( p ); // restart at p
	}
    }

    public String toString() {
	if( this.isEmpty() ) return "{}";
	return "" + this.entrySet();
    }
}

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
* AVLTreeMap is an implementation of the ADT SortedMap
*   which extendds TreeMap and uses BalanceableBinaryTree
* 
* Based on Goodrich, Tamassia, Goldwasser
*
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/

public class AVLTreeMap<K,V> extends TreeMap<K,V> {

    //private int numberOfRestructure = 0;
    //public int getNumberOfRestructure() { return this.numberOfRestructure; }
    //public void resetNumberOfRestructure() { this.numberOfRestructure = 0; }

    // constructors
    public AVLTreeMap() { super(); }
    public AVLTreeMap( Comparator<K> comp ) { super( comp ); }

    // return the height of a given position
    public int height( Position<Entry<K,V>> p ) { return this.tree.getAux( p ); }

    // recompute the height of the given position based on its children's heights
    public void recomputeHeight( Position<Entry<K,V>> p ) {
	this.tree.setAux( p, 1 + Math.max( height( left( p ) ), height( right( p ) ) ) );
    }

    // return whether a position has balance factor between -1 and +1 inclusive
    protected boolean isBalanced( Position<Entry<K,V>> p ) {
	return Math.abs( height( left( p ) ) - height( right( p ) ) ) <= 1;
    }

    // return a child of p with height no smaller than that of the other child
    protected Position<Entry<K,V>> tallerChild( Position<Entry<K,V>> p ) {
	if( height( left( p ) ) > height( right( p ) ) ) return left( p );
	if( height( left( p ) ) < height( right( p ) ) ) return right( p );
	// equal heights
	if( isRoot( p ) ) return left( p ); // choice is irrelevant
	if( p == left( parent( p ) ) ) return left( p ); // return aligned child
	else return right( p );
    }

    // rebalance utilities after insertion or deletion
    //   traverse upward from p, perform trinode restructuring when imbalanced,
    //   continuing until balance is restored
    protected void rebalance( Position<Entry<K,V>> p ) {
	//System.out.println( "rebalance( " + p + " )" );
	int oldHeight, newHeight;
	//int numRes = 0;
	do {
	    oldHeight = height( p ); // not yet recalculated if internal
	    //System.out.println( "p: " + p + " height = " + height( p ) );
	    if( !isBalanced( p ) ) { // imbalance detected
		// perform trinode restructuring, setting p to resulting root,
		// and recompute new local heights
		//System.out.println( "restructure" );
		p = restructure( tallerChild( tallerChild( p ) ) );
		//this.numberOfRestructure++;
		//numRes++;
		recomputeHeight( left( p ) );
		recomputeHeight( right( p ) );
	    }
	    recomputeHeight( p );
	    newHeight = height( p );
	    //System.out.println( "newHeight = " + newHeight );
	    p = parent( p );
	} while( oldHeight != newHeight && p != null );
	//System.out.println( "done");
	//if( numRes > 1 ) System.out.println( numRes + " restructures were made for " + this.size() + " entries" );
    }

    // overrides the TreeMap rebalancing hook
    protected void rebalanceInsert( Position<Entry<K,V>> p ) { rebalance( p ); }
    protected void rebalanceDelete( Position<Entry<K,V>> p ) {
	if( !isRoot( p ) )
	    rebalance( parent( p ) );
    }

    public String toString() {
	if( this.isEmpty() ) return "{}";
	return "" + this.entrySet();
    }
}

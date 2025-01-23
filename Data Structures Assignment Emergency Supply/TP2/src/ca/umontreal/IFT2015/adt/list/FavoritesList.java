package ca.umontreal.IFT2015.adt.list;

import java.lang.Iterable;
/**
* FavoritesList is an interface defining a list that maintains access frequencies and
*     provides an iterator on the favorite elements
* 
* @author      Francois Major
* @version     1.0
* @since       1.0 2024.09.20
*/

public interface FavoritesList<E> {
    public int size();
    public boolean isEmpty();
    public Position<E> add( E e );
    public E access( Position<E> p );
    public E remove( Position<E> p );
    public Iterable<E> getFavorites( int k ) throws IllegalArgumentException;
    public void sort();
}

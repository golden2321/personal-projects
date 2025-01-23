package ca.umontreal.IFT2015.adt.list;

/**
* CountableItem is an interface for items that have incrementable counts
* 
* Based on Goodrich, Tamassia, Goldwasser
*
* @author      Francois Major
* @version     1.0
* @since       1.0 2024.09.20
*/

public interface Countable<E> extends Comparable<E> {
    int getCount();
    void increment();
}

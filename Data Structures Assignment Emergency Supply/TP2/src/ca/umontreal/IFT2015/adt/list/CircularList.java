package ca.umontreal.IFT2015.adt.list;

//import java.lang.Iterable;
//import java.util.Iterator;
//import java.lang.IndexOutOfBoundsException;

/**
* List is an interface for the list ADT
*   use generic's framework to allow for user's desired element type
* 
* @author      Francois Major
* @version     1.0
* @since       1.0
*/
public interface CircularList<E> extends List<E> {
    public void rotate(); // rotate the first element to the back of the list
}

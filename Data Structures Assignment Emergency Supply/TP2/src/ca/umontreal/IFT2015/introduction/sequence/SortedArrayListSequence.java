package ca.umontreal.IFT2015.introduction.sequence;

/**
Created by François Major on 2021.09.16

Permission is hereby granted, free of charge, to any person obtaining a copy of this Software and
associated documentation files, to deal in the Software without restriction, including without
limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The following copyright notice and this permission notice shall be included in all copies or
substantial portions of the Software: “MajorLab Software: Copyright 1994-2022 Université de
Montréal, François Major’s Laboratory”.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

/**
* SortedArrayListSequence implements an ordered Sequence of Comparable elements using ArrayList
* 
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/
public class SortedArrayListSequence<T extends Comparable<T>> implements Sequence<T> {

    // physical data structure
    private List<T> sequence = new ArrayList<>();

    // implementation
    @Override
    public int  size()        { return this.sequence.size(); }       // O(1)
    @Override
    public T    get( int i )  { return this.sequence.get( i ); }     // O(1)
    @Override
    public int  index( T t )  { return this.sequence.indexOf( t ); } // O(n) could be improved to O(log n) using findIndex

    // utility

    // binary search in O(log n)
    // return the smallest index of range [low..high] inclusive storing an entry
    //   with an element greater than or equal to t, otherwise index high+1, by convention
    private int findIndex( T t, int low, int high ) {
	if( high < low ) return high + 1; // no entry qualifies
	int mid = ( low + high ) / 2;
	int comp = this.sequence.get( mid ).compareTo( t );
	if( comp == 0 ) return mid; // found entry with exact match
	else if( comp > 0 ) return findIndex( t, low, mid - 1 ); // search left of mid
	else return findIndex( t, mid + 1, high ); //search right of mid
    }
    // interface to search the entire table
    private int findIndex( T t ) { return findIndex( t, 0, this.sequence.size() - 1 ); }
    // add element t after the last item smaller or equal to t, O(n)
    @Override
    public void add( T t ) {
	int insertIndex = findIndex( t );    // O(log n)
	this.sequence.add( insertIndex, t ); // O(n)
    }
    // add element t after the last item smaller or equal to t
    @Override
    public void delete( T t ) {
	int tIndex = findIndex( t );    // O(log n)
	this.sequence.remove( tIndex ); // O( n )
    }
    @Override
    public Iterator<T> iterator() { return this.sequence.iterator(); }
}

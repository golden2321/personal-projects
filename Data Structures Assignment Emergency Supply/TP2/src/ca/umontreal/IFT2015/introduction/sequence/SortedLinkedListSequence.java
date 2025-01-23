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

import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

/**
* SortedLinkedListSequence implements an ordered Sequence of Comparable elements using LinkedList
* 
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/
public class SortedLinkedListSequence<T extends Comparable<T>> implements Sequence<T> {

    // physical data structure
    private List<T> sequence = new LinkedList<>();

    // Sequence implementation
    @Override
    public int  size()        { return this.sequence.size(); }       // O(1)
    @Override
    public T    get( int i )  { return this.sequence.get( i ); }     // O(n)
    @Override
    public void delete( T t ) { this.sequence.remove( t ); }         // O(n)
    @Override
    public int  index( T t )  { return this.sequence.indexOf( t ); } // O(n)

    // find the index of the first element greater than argument t
    // add t at the found index, or at the end of the sequence if no element was found
    //   (in this case t is the largest element of the sequence)
    @Override
    public void add( T t ) {
	int insertIndex = 0;
	for( T current: this.sequence ) {                        // O(n)
	    if( current.compareTo( t ) > 0 ) {
		// found the first greater element => exit loop
		break;
	    }
	    insertIndex++;
	}
	this.sequence.add( insertIndex, t ); // O(n)
    }
    @Override
    public Iterator<T> iterator() { return this.sequence.iterator(); }
}

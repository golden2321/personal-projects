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

import ca.umontreal.IFT2015.adt.list.SinglyLinkedList;
import ca.umontreal.IFT2015.adt.list.List;
import java.util.Iterator;

/**
* SinglyLinkedListSequence implements a Sequence of Comparable items using SinglyLinkedList
* 
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/
public class SinglyLinkedListSequence<T extends Comparable<T>> implements Sequence<T> {

    // physical data structure
    private List<T> sequence = new SinglyLinkedList<>();

    // Sequence implementation
    @Override
    public int  size()        { return this.sequence.size(); }       // O(1)
    @Override
    public T    get( int i )  { return this.sequence.get( i ); }     // O(n)
    @Override
    public void add( T t )    { this.sequence.addFirst( t ); }       // O(1)
    @Override
    public void delete( T t ) { this.sequence.remove( t ); }         // O(n)
    @Override
    public int  index( T t )  { return this.sequence.indexOf( t ); } // O(n)
    @Override
    public Iterator<T> iterator() { return this.sequence.iterator(); }
}

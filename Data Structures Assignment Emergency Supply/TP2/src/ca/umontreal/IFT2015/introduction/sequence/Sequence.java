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

/**
* Sequence is an interface for simple sequence management operations (ADT)
* It manipulates Comparable elements
* 
* @author      Francois Major
* @version     1.0
* @since       1.0
*/
public interface Sequence<T extends Comparable<T>> extends Iterable<T> {
    public int  size();         // returns the number of elements in sequence
    public void add( T t );     // add element t
    public void delete( T t );  // delete element t
    public T    get( int i );   // returns the element at index i
    public int  index( T t );   // returns the index of the first occurrence of t, or -1 if absent
}

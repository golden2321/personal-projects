package ca.umontreal.IFT2015.introduction.muzik;

/**
Created by François Major on 2024.09.04

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
* TrackReader is an interface to various implementations of trackADT readers.
*   It is created by providing a file as argument to the constructor.
*   It locally maintains the list of trackADT, and is iterable (use of foreach).
*   It gives access to the filename (getFileName) and number of tracks (size).
*   It gives access to a specific track by its trackID (get).
*   It returns a random track from the list (takeOne).
* 
* @author      Francois Major
* @version     1.0
* @since       1.0, 2024.09.04
*/

public interface TrackReader extends Iterable<TrackADT> {
    
    // getter for the file name
    public String   getFileName();
    public int      size();
    public TrackADT get( int trackID );
    public TrackADT takeOne();
}

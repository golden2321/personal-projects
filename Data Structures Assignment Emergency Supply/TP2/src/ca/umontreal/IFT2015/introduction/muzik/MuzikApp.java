package ca.umontreal.IFT2015.introduction.muzik;

/**
Created by François Major on 2021.09.16
Modified by François Major on 2024.09.04 (adjusted to modifications in Track)

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

import java.util.concurrent.TimeUnit;
import java.util.Iterator;
import java.lang.Math;

import ca.umontreal.IFT2015.introduction.sequence.*;
import ca.umontreal.IFT2015.adt.list.SinglyLinkedList;
import ca.umontreal.IFT2015.adt.list.DoublyLinkedList;
import ca.umontreal.IFT2015.adt.list.CircularlyLinkedList;

/**
* MuzikApp is a class to test tracks manipulation and,
*   test and compare the various sequence implementations
*
* @author      Francois Major
* @version     1.0
* @since       1.0, 2021.09.16
*/

public class MuzikApp {

    private static final int millisecondsPerSecond = 1000000;
    private static final int sizeToProcess = 20000;

    public static void main( String[] args ) {
	
	// comparing Sequence data structures for the tracks
	Sequence<TrackADT> trackALSequence = new ArrayListSequence();
	Sequence<TrackADT> trackSASequence = new SortedArrayListSequence();
	Sequence<TrackADT> trackLLSequence = new LinkedListSequence();
	Sequence<TrackADT> trackSLSequence = new SortedLinkedListSequence();
	Sequence<TrackADT> trackSNSequence = new SinglyLinkedListSequence();
	Sequence<TrackADT> trackDNSequence = new DoublyLinkedListSequence();

	// for time measurements
	long startTime;

	// number of tracks to be deleted after a test
	int deleteSize;

	// read a list of tracks from TSV file
	startTime = System.nanoTime();
	TrackReader tracks = new TSVTrackReader( "data/Library.tsv" );
	System.out.println( "finished reading " + tracks.size() + " tracks " +
			    ( System.nanoTime() - startTime ) / millisecondsPerSecond + " milliseconds" );
	
	// // Compare the data structures
	// System.out.println( "begin benchmarking" );

	// // ArrayListSequence ****************************************
	// // add the tracks
	// startTime = System.nanoTime();
	// for( TrackADT track: tracks ) trackALSequence.add( track );
	// System.out.println( "finished adding " + trackALSequence.size() +
	// 		    " tracks to ArrayListSequence " + ( System.nanoTime() - startTime ) / millisecondsPerSecond +
	// 		    " milliseconds" );

	// // delete the tracks
	// deleteSize = trackALSequence.size(); // delete them all
	// startTime = System.nanoTime();
	// for( int i = 0; i < deleteSize; i++ ) {
	//     int deleteIndex = (int)( Math.random() * trackALSequence.size() );
	//     trackALSequence.delete( trackALSequence.get( deleteIndex ) );
	// }
	// System.out.println( "finished deleting " + (deleteSize - trackALSequence.size()) + " tracks from ArrayListSequence " +
	// 		    ( System.nanoTime() - startTime ) / millisecondsPerSecond + " milliseconds" );

	// // SortedArrayListSequence ****************************************
	// // add the tracks
	// startTime = System.nanoTime();
	// for( TrackADT track: tracks ) trackSASequence.add( track );
	// System.out.println( "finished adding " + trackSASequence.size() + " tracks to SortedArrayListSequence " + ( System.nanoTime() - startTime ) / millisecondsPerSecond + " milliseconds" );

	// //	for( int i = 0; i < 10; i++ ) System.out.println( trackSLSequence.get( i ).getTitle() );

	// // delete the tracks
	// deleteSize = trackSASequence.size();
	// startTime = System.nanoTime();
	// for( int i = 0; i < deleteSize; i++ ) {
	//     int deleteIndex = (int)( Math.random() * trackSASequence.size() );
	//     trackSASequence.delete( trackSASequence.get( deleteIndex ) );
	// }
	// System.out.println( "finished deleting " + (deleteSize - trackSASequence.size()) + " tracks from SortedArrayListSequence " + ( System.nanoTime() - startTime ) / millisecondsPerSecond + " milliseconds" );

	// // LinkedListSequence ****************************************
	// // add the tracks
	// startTime = System.nanoTime();
	// for( TrackADT track: tracks ) trackLLSequence.add( track );
	// System.out.println( "finished adding " + trackLLSequence.size() + " tracks to LinkedListSequence " + ( System.nanoTime() - startTime ) / millisecondsPerSecond + " milliseconds" );

	// // delete the tracks
	// deleteSize = trackLLSequence.size();
	// startTime = System.nanoTime();
	// for( int i = 0; i < deleteSize; i++ ) {
	//     int deleteIndex = (int)( Math.random() * trackLLSequence.size() );
	//     trackLLSequence.delete( trackLLSequence.get( deleteIndex ) );
	// }
	// System.out.println( "finished deleting " + (deleteSize - trackLLSequence.size()) + " tracks from LinkedListSequence " + ( System.nanoTime() - startTime ) / millisecondsPerSecond + " milliseconds" );

	// // SortedLinkedListSequence ****************************************
	// // add the tracks
	// startTime = System.nanoTime();
	// for( TrackADT track: tracks ) trackSLSequence.add( track );
	// System.out.println( "finished adding " + trackSLSequence.size() + " tracks to SortedLinkedListSequence " + ( System.nanoTime() - startTime ) / millisecondsPerSecond + " milliseconds" );

	// // delete the tracks
	// deleteSize = trackSLSequence.size();
	// startTime = System.nanoTime();
	// for( int i = 0; i < deleteSize; i++ ) {
	//     int deleteIndex = (int)( Math.random() * trackSLSequence.size() );
	//     trackSLSequence.delete( trackSLSequence.get( deleteIndex ) );
	// }
	// System.out.println( "finished deleting " + (deleteSize - trackSLSequence.size()) + " tracks from SortedLinkedListSequence " + ( System.nanoTime() - startTime ) / millisecondsPerSecond + " milliseconds" );
	
	// // test course implemented lists

	// // SinglyLinkedListSequence
	// // add the tracks
	// startTime = System.nanoTime();
	// for( TrackADT track: tracks ) trackSNSequence.add( track );
	// System.out.println( "finished adding " + trackSNSequence.size() + " tracks to SinglyLinkedListSequence " + ( System.nanoTime() - startTime ) / millisecondsPerSecond + " milliseconds" );

	// // // test reverse speed
	// // startTime = System.nanoTime();
	// // for( int i = 0; i < 1000; i++ )
	// //     SN.reverse();
	// // System.out.println( "finished reversing 1000 x " + SN.size() + " tracks of a SinglyLinkedList " + ( System.nanoTime() - startTime ) / millisecondsPerSecond + " milliseconds" );

	// // delete the tracks
	// startTime = System.nanoTime();
	// deleteSize = trackSNSequence.size();
	// for( int i = 0; i < deleteSize; i++ ) {
	//     int deleteIndex = (int)( Math.random() * trackSNSequence.size() );
	//     trackSNSequence.delete( trackSNSequence.get( deleteIndex ) );
	// }
	// System.out.println( "finished deleting " + (deleteSize - trackSNSequence.size()) + " tracks from SinglyLinkedListSequence " + ( System.nanoTime() - startTime ) / millisecondsPerSecond + " milliseconds" );

	// // DoublyLinkedListSequence
	// // add the tracks
	// startTime = System.nanoTime();
	// for( TrackADT track : tracks ) trackDNSequence.add( track );
	// System.out.println( "finished adding " + trackDNSequence.size() + " tracks to DoublyLinkedListSequence " + ( System.nanoTime() - startTime ) / millisecondsPerSecond + " milliseconds" );

	// // delete the tracks
	// startTime = System.nanoTime();
	// deleteSize = trackDNSequence.size();
	// for( int i = 0; i < deleteSize; i++ ) {
	//     int deleteIndex = (int)( Math.random() * trackDNSequence.size() );
	//     trackDNSequence.delete( trackDNSequence.get( deleteIndex ) );
	// }
	// System.out.println( "finished deleting " + (deleteSize - trackDNSequence.size()) + " tracks from DoublyLinkedListSequence " + ( System.nanoTime() - startTime ) / millisecondsPerSecond + " milliseconds" );
	// System.out.println( "finished benchmarking" );

	// test CircularlyLinkedList
	// build a player that loads n Tracks from the trackList
	// play circularly the Tracks m times

	int n = 7;
	int m = 2;
	CircularlyLinkedList<TrackADT> pickup = new CircularlyLinkedList();
	// load
	for( int i = 0; i < n; i++ ) pickup.addFirst( tracks.takeOne() );
	// play
	for( int i = 0; i < m; i++ ) {
	    // play 'em all
	    System.out.println( "Pickup tour " + i );
	    for( int k = 0; k < n; k++ ) {
		System.out.println(
		    "playing " +
		    pickup.first().getName() +
		    " by " +
		    pickup.first().getArtist() );
		pickup.rotate();
	    }
	}

	// // test iterator on the circular list
	// System.out.println( "Iterate pickup:" );
	// Iterator<Track> iteratePickup = pickup.iterator();
	// while( iteratePickup.hasNext() )
	//     System.out.println( iteratePickup.next().getTitle() );

	// // Test recursive reverse
	// System.out.println( "test reverse" );
	
	// SinglyLinkedList<String> template = new SinglyLinkedList();
	// template.addLast( "A" );
	// template.addLast( "B" );
	// System.out.println( template );
	// template.recursiveReverse();
	// System.out.println( template );
    }
}

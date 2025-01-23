package ca.umontreal.IFT2015.introduction.muzik;

/**
Created by François Major on 2022.09.10

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

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import ca.umontreal.IFT2015.adt.list.FavoritesList;
import ca.umontreal.IFT2015.adt.list.LinkedFavoritesList;
import ca.umontreal.IFT2015.adt.list.FavoritesListMTF;

import ca.umontreal.IFT2015.adt.list.Position;

/**
* FavoritesMuzikApp is a class to test the package
* 
* @author      Francois Major
* @version     1.0
* @since       1.0, 2022.09.10
*/

public class FavoritesMuzikApp {

    public static void main( String[] args ) {
	
	// read a list of tracks from TSV file
	TrackReader tracks = new TSVTrackReader( "data/Library.csv" );
	
	// test FavoritesList
	// build a player that loads n Tracks from the tracks
	// play m randomly selected Tracks from the n loaded
	// get the list of k favorites tracks

	int n = 5;
	int m = 10;
	int k = 5;
	FavoritesList<TrackADT> pickup = new LinkedFavoritesList<>();
	List<TrackADT> playList = new ArrayList<>();
	Map<TrackADT,Position<TrackADT>> trackPositions = new HashMap<>();
	// load, first access makes counts = 1 for all tracks
	System.out.println( "Playing this week:" );
	for( int i = 0; i < n; i++ ) {
	    TrackADT t = tracks.takeOne( );
	    System.out.println( i + ". " + t.getName() + " by " + t.getArtist() );
	    playList.add( t );
	    trackPositions.put( (TrackADT)t, pickup.add( t ) );
	}
	for( int i = 0; i < m; i++ ) {
	    int index = (int)( Math.random() * n );
	    TrackADT t = playList.get( index );
	    pickup.access( trackPositions.get( t ) );
	    System.out.println( "playing song #" + index + ": " + playList.get( index ).getName() );
	}
	int rank = 1;
	System.out.println( "Top-" + k + " songs this week: " );
	for( TrackADT t : pickup.getFavorites( k ) )
	    System.out.println( rank++ + ". " + t.getName() + " by " + t.getArtist() );

	// compare sorted list vs move-to-front

	// sorted list
	int nn = 10000;
	int mm = 1000000;
	int kk = 5;
	FavoritesList<TrackADT> pickup2 = new LinkedFavoritesList<>();
	List<TrackADT> playList2 = new ArrayList<>();
	Map<TrackADT,Position<TrackADT>> trackPositions2 = new HashMap<>();
	// load, first access makes counts = 1 for all tracks
	long startTime = System.nanoTime();
	for( int i = 0; i < nn; i++ ) {
	    TrackADT t = tracks.takeOne();
	    playList2.add( t );
	    trackPositions2.put( (TrackADT)t, pickup2.add( t ) );
	}
	for( int i = 0; i < mm; i++ ) {
	    int index = (int)( Math.random() * nn );
	    pickup2.access( trackPositions2.get( playList2.get( index ) ) );
	}
	int rank2 = 1;
	System.out.println( "Top-" + kk + " songs this week: " );
	for( TrackADT t : pickup2.getFavorites( k ) )
	    System.out.println( rank2++ + ". " + t.getName() + " by " + t.getArtist() );
	System.out.println( "for nn: " + nn + " mm: " + mm + " k: " + k + " it takes " + ( System.nanoTime() - startTime ) / 1000000 + " milliseconds for a sorted list strategy" );

	FavoritesList<TrackADT> pickup3 = new FavoritesListMTF<>();
	List<TrackADT> playList3 = new ArrayList<>();
	Map<TrackADT,Position<TrackADT>> trackPositions3 = new HashMap<>();
	// load, first access makes counts = 1 for all tracks
	startTime = System.nanoTime();
	for( int i = 0; i < nn; i++ ) {
	    TrackADT t = tracks.takeOne();
	    playList3.add( t );
	    trackPositions3.put( (TrackADT)t, pickup3.add( t ) );
	}
	for( int i = 0; i < mm; i++ ) {
	    int index = (int)( Math.random() * nn );
	    pickup3.access( trackPositions3.get( playList3.get( index ) ) );
	}
	int rank3 = 1;
	System.out.println( "Top-" + kk + " songs this week: " );
	for( TrackADT t : pickup3.getFavorites( k ) )
	    System.out.println( rank3++ + ". " + t.getName() + " by " + t.getArtist() );
	System.out.println( "for n: " + nn + " m: " + mm + " k: " + k + " it takes " + ( System.nanoTime() - startTime ) / 1000000 + " milliseconds for a move-to-front strategy" );

	rank3 = 1;
	startTime = System.nanoTime();
	pickup3.sort();
	for( TrackADT t : pickup3.getFavorites( k ) )
	    System.out.println( rank3++ + ". " + t.getName() + " by " + t.getArtist() );
	System.out.println( "for n: " + nn + " m: " + mm + " k: " + k + " it takes " + ( System.nanoTime() - startTime ) / 1000000 + " milliseconds to sort" );	
    }
}

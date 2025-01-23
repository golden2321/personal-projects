package ca.umontreal.IFT2015.introduction.muzik;

/**
Created by François Major on 2021.09.16
Modified by François Major on 2024.09.04 (adjusted to the modifications in Track.java)

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

import ca.umontreal.IFT2015.util.Error;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.lang.Math;

/**
* TSVTrackReader is a class to interface TSV files created by ReadXMLFile and MuzikCreateTSV.
* An instance of TSVTrackReader is constructed using a file name.
* The file must be TSV format with tabs between fields.
* The TrackADT instances of the file are stored locally in a List.
* 
* @author      Francois Major
* @version     1.0
* @since       1.0, 2022.09.10
*/

public class TSVTrackReader implements TrackReader {

    // create a Track by TSV String line
    /**
     * @param line a String that contains a TSV-formatted line
     * Build a Track instance
     */
    public static TrackADT makeTrackFromTSVLine( String line ) {
	// parse a Track TSV line:
	// Caetano Veloso	How Beautiful Could A Being Be - 11B	Livro	104	207986	Bossa nova	1997	29756
	// 8 data pieces seperated by tabs: artist, name, album, bpm, totalTime, genre, year, trackID
	TrackADT t = new Track();
	String[] data = line.split( "\t", 31 );
	t.setArtist(    data[0] );
	t.setName(      data[1] );
	t.setAlbum(     data[2] );
	t.setBPM(       data[3] );
	t.setTotalTime( data[4] ); // in milliseconds	
	t.setGenre(     data[5] );
	t.setYear(      data[6] );
	t.setTrackID(   data[7] );
	return t;
    }
    
    // attributes
    private String fileName;
    private FileReader reader;
    private BufferedReader bufferReader;
    private String line;
    private List<TrackADT> tracks;

    // getter for the file name
    public String getFileName() { return this.fileName; }
    public int size() { return this.tracks.size(); }
    public TrackADT get( int trackID ) {
	for( TrackADT track: this.tracks )
	    if( track.getTrackID() == trackID )
		return track;
	return null;
    }
    public TrackADT takeOne() {	return this.tracks.get( (int)( Math.random() * this.tracks.size() ) ); }
    public Iterator<TrackADT> iterator() { return this.tracks.iterator(); }

    /**
     * Constructor with file name
     * @param  fileName   the file name with complete path
     * @see               Track
     */
    public TSVTrackReader( String fileName ) {
	this.fileName = fileName;
	this.tracks = new ArrayList<>();
	try {
	    this.reader = new FileReader( this.fileName );
	} catch( FileNotFoundException e ) {
	    Error.generalError( "FileNotFound " + this.fileName );
	}
	try {
	    this.bufferReader = new BufferedReader( this.reader );
	    // read the header of the tsv file (title bar)
	    this.line = this.bufferReader.readLine();
	    // read first data line
	    this.line = this.bufferReader.readLine();
	    // read all tracks in trackList
	    while( this.line != null ) {
		// parse line and add to trackList
		this.tracks.add( makeTrackFromTSVLine( this.line ) );
		// read next line
		this.line = this.bufferReader.readLine();
	    }
	    this.bufferReader.close();
	} catch( IOException e ) {
	    Error.generalError( "IO Exception caught" );
        }
    }
}

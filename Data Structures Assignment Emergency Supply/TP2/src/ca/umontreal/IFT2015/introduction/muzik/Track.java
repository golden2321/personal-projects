package ca.umontreal.IFT2015.introduction.muzik;

/**
Created by François Major on 2021.09.16
Modified by François Major on 2024.09.04 (Adjusted to the new TrackADT interface)
Modified by François Major on 2024.09.06 (Changed setBPM to detect errors in the bpm string)

Permission is hereby granted, free of charge, to any person obtaining a copy of this Software and
associated documentation files, to deal in the Software without restriction, including without
limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The following copyright notice and this permission notice shall be included in all copies or
substantial portions of the Software: “MajorLab Software: Copyright 1994-2024 Université de
Montréal, François Major’s Laboratory”.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES
OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/

/**
* Track is the base class for muzik tracks
*   to be used in applications that manipulate muzik track instances.
* A Track object encapsulates the instance information needed
* for the various Muzik applications. This
* information includes:
* <ul>
* <li>artist: The interpreting artist
* <li>name: The track's or song's title
* <li>album: The album
* <li>bpm: The tempo (in Beats Per Minutes)
* <li>totalTime: The duration in milliseconds
* <li>genre: The genre, which is one of the genre enum type (Genre.java)
* <li>year: The year this track came out
* <li>trackID: A unique id in the library
* </ul>
* <p>
* 
* @author      Francois Major
* @version     1.11, 2024.09.06 implemented error detection for bpm (setBPM)
* @version     1.1,  2024.09.04 made it to implement TrackADT; switched attribute tempo to bpm; added attributes album and trackID;
* @since       1.0,  2022.09.10
*/

public class Track implements TrackADT {
    // TrackADT extends Comparable => requires the implementation of the int CompareTo( Track other ) method

    // attributes
    private String   artist;
    private String   name;
    private String   album;
    private int      bpm;
    private long     totalTime;
    private Genre    genre;
    private int      year;
    private int      trackID;

    // getters
    @Override
    public String   getArtist()    { return this.artist; }
    @Override
    public String   getName()      { return this.name; }
    @Override
    public String   getAlbum()     { return this.album; }
    @Override
    public int      getBPM()       { return this.bpm; }
    @Override
    public long     getTotalTime() { return this.totalTime; }
    @Override
    public Genre    getGenre()     { return this.genre; }
    @Override
    public int      getYear()      { return this.year; }
    @Override
    public int      getTrackID()   { return this.trackID; }

    // setters (all with String arguments)
    @Override
    public void setArtist( String artist )       { this.artist = artist; }
    @Override
    public void setName( String name )           { this.name = name; }
    @Override
    public void setAlbum( String album)          { this.album = album; }
    @Override
    public void setBPM( String bpm ) {
	try {
	    this.bpm = Integer.parseInt( bpm );
	} catch( NumberFormatException e ) {
	    this.bpm = 0;  // set to 0, if the string is invalid
        }
    }
    @Override
    public void setTotalTime( String totalTime ) { this.totalTime = Long.parseLong( totalTime ); }
    @Override
    public void setGenre( String genre )         { this.genre = new Genre( genre ); }
    @Override
    public void setYear( String year )           { if( !year.equals( "" ) ) this.year = Integer.parseInt( year ); else this.year = 0; }
    @Override
    public void setTrackID( String trackID )     { this.trackID = Integer.parseInt( trackID ); }

    // default constructor
    /**
     * Build a Track, default constructor
    */
    public Track() { }
    
    // constructor with arguments
    /**
     * @param artist track artist in a String
     * @param name track name in a String
     * @param album track album in a String
     * @param bpm track tempo in an int
     * @param totalTime track time in an int
     * @param genre track genre in a Genre enum type (see Genre)
     * @param year track year in an int
     * @param trackID trackID in an int
     * Build a Track instance
     * @see    Track
     * @see    Genre
    */
    public Track( String artist,
		  String name,
		  String album,
		  int bpm,
		  long totalTime,
		  Genre genre,
		  int year,
		  int trackID ) {
	this.artist = artist;
	this.name = name;
	this.album = album;
	this.bpm = bpm;
	this.totalTime = totalTime;
	this.genre = genre;
	this.year = year;
	this.trackID = trackID;
    }

    // Utility function
    
    /**
     * Returns true if the name of this Track equals that of the other,
     *   false otherwise
     *
     * @param other String to match the name
     * @return      name of this Track equals that of the other
     * @see         Track
     */
    public boolean equals( TrackADT other ) {
	return this.name.equals( other.getName() );
    }

    // Comparable interface
    
    /**
     * Returns the result of compareTo between the name of this Track
     *   and that of the other.
     *
     * @param other other Track instance
     * @return      0 if name of this Track equals that of the argument
     *              positive int if it is greater
     *              negative int if it is smaller
     * @see         Track
     */
    @Override
    public int compareTo( TrackADT other ) {
	return this.name.compareTo( other.getName() );
    }

    // Pretty printing
    
    /**
     * Returns a String for this Track's information to be both human readable and CSV formatted.
     *
     * @return  a String with the info of this Track
     * @see     Track
     */
    @Override
    public String toString() {
	return
	    this.getArtist() + "\t" +
	    this.getName() + "\t" +
	    this.getAlbum() + "\t" +
	    this.getBPM() + "\t" +
	    this.getTotalTime() + "\t" +
	    this.getGenre() + "\t" +
	    this.getYear() + "\t" +
	    this.getTrackID();
    }
}

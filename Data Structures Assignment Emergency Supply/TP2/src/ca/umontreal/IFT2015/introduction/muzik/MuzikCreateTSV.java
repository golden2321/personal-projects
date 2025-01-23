package ca.umontreal.IFT2015.introduction.muzik;

/**
Created by François Major on 2023.09.10

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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
* MuzikCreateTSV is a class to create a TSV for iTunes library
*
* @author      Francois Major
* @version     1.1, 2024.09.04 change String time to int totalTime; added attributes album and trackID.
* @since       1.0, 2022.09.10
*/

public class MuzikCreateTSV {

    /**
     * @param time time as a String in milliseconds
     * Returns time as a hh:mm:ss String.
     *
     * @return String
     */
    private static String timeFormat( int time ) {
	long totalSeconds = time / 1000;
	long hours = totalSeconds / 3600;
	long remainingSeconds = totalSeconds % 3600;
	long minutes = remainingSeconds / 60;
	long seconds = remainingSeconds % 60;
	if( hours > 0 )
	    return String.format( "%02d:%02d:%02d", hours, minutes, seconds );
	if( minutes > 0 )
	    return String.format( "%02d:%02d", minutes, seconds );
	else
	    return String.format( "%02d", seconds );
    }

    public static void main( String[] args ) {

	BufferedWriter writer = null;

	try {
	    writer = new BufferedWriter( new FileWriter( "data/Library.tsv" ) );
	    
	    // read a list of tracks from TSV file
	    List<TrackADT> trackList = new ArrayList<>();
	    TrackReader tracks = new TSVTrackReader( "data/Library.csv" );
	    System.out.println( tracks.size() + " tracks read" );
	    writer.write( "Artist\t" + "Name\t" + "Album\t" + "BPM\t" + "TotalTime\t" + "Genre\t" + "Year\t" + "TrackID" ); // title bar
	    writer.newLine();
	    for( TrackADT t : tracks ) {
		writer.write( t.getArtist() + "\t" + t.getName() + "\t" + t.getAlbum() + "\t" + t.getBPM() + "\t" + t.getTotalTime() + "\t" + t.getGenre() + "\t" + t.getYear() + "\t" + t.getTrackID() );
		writer.newLine();
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
	try {
	    // Close the BufferedWriter
	    if (writer != null) {
		writer.close();
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}

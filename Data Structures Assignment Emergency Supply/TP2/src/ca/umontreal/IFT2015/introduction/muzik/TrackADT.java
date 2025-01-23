package ca.umontreal.IFT2015.introduction.muzik;

/**
Created by François Major on 2024.09.04

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
* TrackADT is the interface for muzik tracks
* 
* @author      Francois Major
* @version     1.0
* @since       1.0, 2024.09.04
*/
    
public interface TrackADT extends Comparable<TrackADT> {
    // Getters
    public String getArtist();
    public String getName();
    public String getAlbum();
    public int    getBPM();
    public long   getTotalTime();
    public Genre  getGenre();
    public int    getYear();
    public int    getTrackID();
    // String setters
    public void setArtist(    String artist );      
    public void setName(      String name );          
    public void setAlbum(     String album);         
    public void setBPM(       String bpm );            
    public void setTotalTime( String totalTime );
    public void setGenre(     String genre );        
    public void setYear(      String year );          
    public void setTrackID(   String trackID );    
}

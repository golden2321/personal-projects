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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
* ReadXMLFile is a class to read the XML library dumped by Apple Music App and create a TSV version
* 
* @author      Francois Major
* @version     1.1, 2024.09.04 converted String time to int totalTime; switched attribute tempo to bpm; added attributes album and trackID
* @since       1.0, 2022.09.10
*/


public class ReadXMLFile {
    public static void main( String[] args ) {
	int counter = 0;
	BufferedWriter writer = null;

	try {
	    writer = new BufferedWriter( new FileWriter( "data/Library.tsv" ) );
	    try {
		File fXmlFile = new File( "data/Library.xml" );
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse( fXmlFile );

		doc.getDocumentElement().normalize();

		NodeList nList = doc.getElementsByTagName( "dict" );

		writer.write( "Artist\t" + "Name\t" + "Album\t" + "BPM\t" + "Total Time\t" + "Genre\t" + "Year\t" + "Track ID" );
		writer.newLine();

		for( int i = 0; i < nList.getLength(); i++ ) {
		    Node nNode = nList.item( i );
		    counter++;

		    if( nNode.getNodeType() == Node.ELEMENT_NODE ) {
			Element eElement = (Element)nNode;
			NodeList childNodes = eElement.getChildNodes();
			String currentKey = "";
			String trackId = "", name = "", artist = "", album = "", bpm = "", totalTime = "", genre = "", year = "", playlistID = "";
		    
			for( int j = 0; j < childNodes.getLength(); j++ ) {
			    Node childNode = childNodes.item( j );

			    if( "key".equals( childNode.getNodeName() ) ) {
				currentKey = childNode.getTextContent().trim();
			    } else if( !"#text".equals( childNode.getNodeName() ) ) {
				if( "Track ID".equals(currentKey) ) trackId = childNode.getTextContent().trim();
				if( "Name".equals(currentKey) ) name = childNode.getTextContent().trim();
				if( "Artist".equals(currentKey) ) artist = childNode.getTextContent().trim();
				if( "Album".equals(currentKey) ) album = childNode.getTextContent().trim();
				if( "BPM".equals(currentKey) ) bpm = childNode.getTextContent().trim();
				if( "Total Time".equals(currentKey) ) totalTime = childNode.getTextContent().trim();
				if( "Genre".equals(currentKey) ) genre = childNode.getTextContent().trim();
				if( "Year".equals(currentKey) ) year = childNode.getTextContent().trim();
				if( "Playlist ID".equals(currentKey) ) playlistID = childNode.getTextContent().trim();
			    }
			}
			if( !totalTime.equals( "" ) ) { // all tracks have time; remove playlist info
			    writer.write( artist + "\t" + name + "\t" + album + "\t" + bpm + "\t" + totalTime + "\t" + genre + "\t" + year + "\t" + trackId );
			    writer.newLine();
			}
		    }
		}
	    } catch( Exception e ) {
		e.printStackTrace();
	    }
	} catch( IOException e ) {
	    e.printStackTrace();
	}
	try {
	    // Close the BufferedWriter
	    if( writer != null ) {
		writer.close();
	    }
	} catch( IOException e ) {
	    e.printStackTrace();
	}
    }
}

package ca.umontreal.IFT2015.maps;

import java.lang.Iterable;
//import java.util.Map;
//import java.util.HashMap;

/**
* Maps is an interface for the ADT Maps
* 
* Based on Goodrich, Tamassia, Goldwasser
*
* @author      Francois Major
* @version     %I%, %G%
* @since       1.0
*/

public class CountKmers {
    
    public static void main( String[] args ) {

	if( args.length == 0 ) {
	    System.out.println( "usage: CountKmers [FastaFormattedFile] [kmerMinSize] [kmerMaxSize]" );
	    System.exit( 0 );
	}

	// assume no error in input
	String fileName  = args[0];
	// find kmer distribution for sizes between kmerMinSize and kmerMaxSize
	int kmerMinSize = 21; // default value
	int kmerMaxSize = 31; // default value
	if( args.length > 1 ) kmerMinSize = Integer.parseInt( args[1] ); // optional argument
	if( args.length > 2 ) kmerMaxSize = Integer.parseInt( args[2] ); // optional argument
	System.out.println( "CountKmers " + fileName + " " + kmerMinSize + " " + kmerMaxSize );

	// read input sequence
	SimpleFastaReader transcript = new SimpleFastaReader( fileName );
	String sequence = transcript.getSequence();
	System.out.println( transcript.getHeader() + " has " + sequence.length() + " nucleotides" );
	// use an array for unique kmer counts of each size
	int[] uniqCount = new int[kmerMaxSize-kmerMinSize+1];
	for( int kmerSize = kmerMinSize; kmerSize <= kmerMaxSize; kmerSize++ ) { // for each kmer size
	    //Map<String,Integer> freq = new HashMap<>(); // java HashMap
	    //Map<String,Integer> freq = new UnsortedTableMap<>(); // our Map
	    Map<String,Integer> freq = new SortedTableMap<>(); // our Map
	    //Map<String,Integer> freq = new ChainHashMap<>( 41 ); // our ChainHashMap
	    uniqCount[kmerSize-kmerMinSize] = 0; // array offset
	    for( int pos = 0; pos <= sequence.length() - kmerSize; pos++ ) { // for each kmer start pos
		String kmer = sequence.substring( pos, pos + kmerSize ); // extract kmer
		Integer count = freq.get( kmer ); // get previous freq, or get null if new kmer
		if( count == null ) count = 0; // if new kmer, initial count = 0
		freq.put( kmer, count + 1 ); // increment kmer count
	    }
	    // the map contains entries for the distinct kmer
	    // find the most frequent of each kmerSize
	    // count the number of unique kmer for each kmerSize
	    int maxCount = 0; // init max count
	    String maxKmer = "no kmer"; // init max kmer
	    for( String kmer : freq.keySet() ) { // iterate over all kmers
		int kmerFreq = freq.get( kmer ); // get kmer frequency
		if( maxCount < kmerFreq ) {
		    maxCount = kmerFreq; // update max count
		    maxKmer = kmer; // update max kmer
		}
		if( freq.get( kmer ) == 1 ) uniqCount[kmerSize-kmerMinSize]++; // count unique kmers of this size
	    }
	    // report data for each kmer size
	    System.out.println( "Size = " + kmerSize +
				"; total of " + freq.size() + " distinct kmers; " +
				uniqCount[kmerSize-kmerMinSize] + " unique kmers" +
				"; most frequent kmer is " + maxKmer + " with " + maxCount + " occurrences;" );
	}
    }
}


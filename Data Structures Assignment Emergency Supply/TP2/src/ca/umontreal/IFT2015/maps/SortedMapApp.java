package ca.umontreal.IFT2015.maps;

import java.util.concurrent.TimeUnit;
import java.util.Iterator;
import java.lang.Math;

import ca.umontreal.IFT2015.adt.list.Position;
//import java.util.concurrent.ConcurrentSkipListMap;
//import java.util.Map;
//import java.util.HashMap;
//import java.util.TreeMap;

public class SortedMapApp {

    public static void main( String[] args ) {

	int numberOfKeys = 2000000;
	int numberIn = numberOfKeys;

	int[] keys = new int[numberOfKeys];
	for( int k = 0; k < numberOfKeys; k++ )
	    keys[k] = k; //(int)(Math.random() * numberOfKeys );

	SortedMap<Integer,Integer> STM = new SortedTableMap<>();
	SortedMap<Integer,Integer> SLM = new SkipListMap( Integer.MIN_VALUE, Integer.MAX_VALUE );
	// ConcurrentSkipListMap<Integer,Integer> CSLM = new ConcurrentSkipListMap<>();
	//TreeMap<Integer,Integer> TM = new TreeMap<>();
	// Map<Integer,Integer> HM = new HashMap<>();
	// Map<Integer,Integer> HM = new ProbeHashMap<>();
	// Map<Integer,Integer> CHM = new ChainHashMap<>();
        //TreeMap<Integer,Integer> AVLTM = new AVLTreeMap<>();
	// TreeMap<Integer,Integer> SPTM = new SplayTreeMap<>();

	long startTime, endTime;

	// PUT
	
	startTime = System.currentTimeMillis();
	for( int k = 0; k <= numberOfKeys; k++ )
	    STM.put( (int)(Math.random() * numberIn ), k );
	endTime = System.currentTimeMillis();
	System.out.println( "STM: " + STM.size() + " entries inserted in " + (endTime - startTime) + " milliseconds" );

	startTime = System.currentTimeMillis();
	for( int k = 0; k <= numberOfKeys; k++ )
	    SLM.put( (int)(Math.random() * numberIn ), k );
	endTime = System.currentTimeMillis();
	System.out.println( "SLM: " + SLM.size() + " entries inserted in " + (endTime - startTime) + " milliseconds" );

	// startTime = System.currentTimeMillis();
	// for( int k = 0; k <= numberOfKeys; k++ )
	//     CSLM.put( (int)(Math.random() * numberIn ), k );
	// endTime = System.currentTimeMillis();
	// System.out.println( "CSLM: " + CSLM.size() + " entries inserted in " + (endTime - startTime) + " milliseconds" );

	// startTime = System.currentTimeMillis();
	// for( int k = 0; k < numberOfKeys; k++ )
	//     TM.put( keys[k], k );
	// endTime = System.currentTimeMillis();
	// System.out.println( "TM: " + TM.size() + " entries inserted in " + (endTime - startTime) + " milliseconds" );

	// startTime = System.currentTimeMillis();
	// for( int k = 0; k <= numberOfKeys; k++ )
	//     HM.put( (int)(Math.random() * numberIn  ), k );
	// endTime = System.currentTimeMillis();
	// System.out.println( "HM: " + HM.size() + " entries inserted in " + (endTime - startTime) + " milliseconds" );

	// startTime = System.currentTimeMillis();
	// for( int k = 0; k <= numberOfKeys; k++ )
	//     CHM.put( (int)(Math.random() * numberIn ), k );
	// endTime = System.currentTimeMillis();
	// System.out.println( "CHM: " + CHM.size() + " entries inserted in " + (endTime - startTime) + " milliseconds" );

	// startTime = System.currentTimeMillis();
	// for( int k = 0; k < numberOfKeys; k++ )
	//     AVLTM.put( keys[k], k );
	// endTime = System.currentTimeMillis();
	// System.out.println( "AVLTM: " + AVLTM.size() + " entries inserted in " + (endTime - startTime) + " milliseconds" );

	// startTime = System.currentTimeMillis();
	// for( int k = 0; k <= numberOfKeys; k++ )
	//     SPTM.put( (int)(Math.random() * numberIn  ), k );
	// endTime = System.currentTimeMillis();
	// System.out.println( "SPTM: " + SPTM.size() + " entries inserted in " + (endTime - startTime) + " milliseconds" );

	// // SEARCH
	
	startTime = System.currentTimeMillis();
	for( int k = 0; k <= numberOfKeys; k++ )
	    STM.get( (int)(Math.random() * numberIn ) );
	endTime = System.currentTimeMillis();
	System.out.println( "STM: " + STM.size() + " entries searched in " + (endTime - startTime) + " milliseconds" );

	startTime = System.currentTimeMillis();
	for( int k = 0; k <= numberOfKeys; k++ )
	    SLM.get( (int)(Math.random() * numberIn ) );
	endTime = System.currentTimeMillis();
	System.out.println( "SLM: " + SLM.size() + " entries searched in " + (endTime - startTime) + " milliseconds" );

	// startTime = System.currentTimeMillis();
	// for( int k = 0; k <= numberOfKeys; k++ )
	//     CSLM.get( (int)(Math.random() * numberIn  ) );
	// endTime = System.currentTimeMillis();
	// System.out.println( "CSLM: " + CSLM.size() + " entries searched in " + (endTime - startTime) + " milliseconds" );

	// startTime = System.currentTimeMillis();
	// for( int k = 0; k < numberOfKeys; k++ )
	//     TM.get( keys[k] );
	// endTime = System.currentTimeMillis();
	// System.out.println( "TM: " + TM.size() + " entries searched in " + (endTime - startTime) + " milliseconds" );

	// startTime = System.currentTimeMillis();
	// for( int k = 0; k <= numberOfKeys; k++ )
	//     HM.get( (int)(Math.random() * numberIn  ) );
	// endTime = System.currentTimeMillis();
	// System.out.println( "HM: " + HM.size() + " entries searched in " + (endTime - startTime) + " milliseconds" );

	// startTime = System.currentTimeMillis();
	// for( int k = 0; k <= numberOfKeys; k++ )
	//     CHM.get( (int)(Math.random() * numberIn  ) );
	// endTime = System.currentTimeMillis();
	// System.out.println( "CHM: " + CHM.size() + " entries searched in " + (endTime - startTime) + " milliseconds" );

	// startTime = System.currentTimeMillis();
	// for( int k = 0; k < numberOfKeys; k++ )
	//     AVLTM.get( keys[k] );
	// endTime = System.currentTimeMillis();
	// System.out.println( "AVLTM: " + AVLTM.size() + " entries searched in " + (endTime - startTime) + " milliseconds" );

	// startTime = System.currentTimeMillis();
	// for( int k = 0; k <= numberOfKeys; k++ )
	//     SPTM.get( (int)(Math.random() * numberIn  ) );
	// endTime = System.currentTimeMillis();
	// System.out.println( "SPTM: " + SPTM.size() + " entries searched in " + (endTime - startTime) + " milliseconds" );
	
	// // REMOVE
	
	startTime = System.currentTimeMillis();
	for( int k = 0; k <= numberOfKeys; k++ )
	    STM.remove( (int)(Math.random() * numberIn  ) );
	endTime = System.currentTimeMillis();
	System.out.println( "STM: " + STM.size() + " entries removed in " + (endTime - startTime) + " milliseconds" );

	startTime = System.currentTimeMillis();
	for( int k = 0; k <= numberOfKeys; k++ )
	    SLM.remove( (int)(Math.random() * numberIn  ) );
	endTime = System.currentTimeMillis();
	System.out.println( "SLM: " + SLM.size() + " entries removed in " + (endTime - startTime) + " milliseconds" );

	// startTime = System.currentTimeMillis();
	// for( int k = 0; k <= numberOfKeys; k++ )
	//     CSLM.remove( (int)(Math.random() * numberIn  ) );
	// endTime = System.currentTimeMillis();
	// System.out.println( "CSLM: " + CSLM.size() + " entries removed in " + (endTime - startTime) + " milliseconds" );

	// startTime = System.currentTimeMillis();
	// for( int k = 0; k < numberOfKeys; k++ )
	//     TM.remove( keys[k] );
	// endTime = System.currentTimeMillis();
	// System.out.println( "TM: " + TM.size() + " entries removed in " + (endTime - startTime) + " milliseconds" );

	// startTime = System.currentTimeMillis();
	// for( int k = 0; k <= numberOfKeys; k++ )
	//     HM.remove( (int)(Math.random() * numberIn  ) );
	// endTime = System.currentTimeMillis();
	// System.out.println( "HM: " + HM.size() + " entries removed in " + (endTime - startTime) + " milliseconds" );

	// startTime = System.currentTimeMillis();
	// for( int k = 0; k <= numberOfKeys; k++ )
	//     CHM.remove( (int)(Math.random() * numberIn  ) );
	// endTime = System.currentTimeMillis();
	// System.out.println( "CHM: " + CHM.size() + " entries removed in " + (endTime - startTime) + " milliseconds" );

	// startTime = System.currentTimeMillis();
	// for( int k = 0; k < numberOfKeys; k++ )
	//     AVLTM.remove( keys[k] );
	// endTime = System.currentTimeMillis();
	// System.out.println( "AVLTM: " + (numberOfKeys - AVLTM.size()) + " entries removed in " + (endTime - startTime) + " milliseconds" );

	// startTime = System.currentTimeMillis();
	// for( int k = 0; k <= numberOfKeys; k++ )
	//     SPTM.remove( (int)(Math.random() * numberIn  ) );
	// endTime = System.currentTimeMillis();
	// System.out.println( "SPTM: " + SPTM.size() + " entries removed in " + (endTime - startTime) + " milliseconds" );
    }
}

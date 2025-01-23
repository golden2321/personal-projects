package ca.umontreal.IFT2015.graphs;

import java.io.*;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

import ca.umontreal.IFT2015.pqueues.Entry;
    
public class GraphApp {
    public static void main( String[] args ) {
	Graph<String,String> G = new AdjacencyMapGraph<>( false );
	System.out.println( G );

	Vertex<String> aa = G.insertVertex( "A" );
	Vertex<String> bb = G.insertVertex( "B" );
	Vertex<String> cc = G.insertVertex( "C" );
	Vertex<String> dd = G.insertVertex( "D" );
	Vertex<String> ee = G.insertVertex( "E" );

	Edge<String> ae = G.insertEdge( aa, ee, "AE" );
	Edge<String> ad = G.insertEdge( aa, dd, "AD" );
	Edge<String> ac = G.insertEdge( aa, cc, "AC" );
	Edge<String> ab = G.insertEdge( aa, bb, "AB" );
	Edge<String> bc = G.insertEdge( bb, cc, "BC" );
	Edge<String> cd = G.insertEdge( cc, dd, "CD" );
	Edge<String> ce = G.insertEdge( cc, ee, "CE" );
        System.out.println( G );

	Set<Vertex<String>> discovered = new HashSet<>();
	Map<Vertex<String>,Edge<String>> forest = new HashMap<>();
	AdjacencyMapGraph.DFS( G, aa, discovered, forest );
	System.out.println( "Path between vertices " + aa.getElement() + " and " + ee.getElement() + ":" );
	for( Edge<String> e : AdjacencyMapGraph.constructPath( G, aa, ee, forest ) )
	    System.out.println( e.getElement() );

	Graph<String,String> g148 = new AdjacencyMapGraph<>( true );
	System.out.println( g148 );

	Vertex<String> bos = g148.insertVertex( "BOS" );
	Vertex<String> jfk = g148.insertVertex( "JFK" );
	Vertex<String> mia = g148.insertVertex( "MIA" );
	Vertex<String> chi = g148.insertVertex( "ORD" );
	Vertex<String> dfw = g148.insertVertex( "DFW" );
	Vertex<String> sfo = g148.insertVertex( "SFO" );
	Vertex<String> lax = g148.insertVertex( "LAX" );

	Edge<String> bosjfk = g148.insertEdge( bos, jfk, "BOS->JFK" );
	Edge<String> bosmia = g148.insertEdge( bos, mia, "BOS->MIA" );
	Edge<String> bossfo = g148.insertEdge( bos, sfo, "BOS->SFO" );
	Edge<String> jfkbos = g148.insertEdge( jfk, bos, "JFK->BOS" );
	Edge<String> jfkdfw = g148.insertEdge( jfk, dfw, "JFK->DFW" );
	Edge<String> jfkmia = g148.insertEdge( jfk, mia, "JFK->MIA" );
	Edge<String> jfksfo = g148.insertEdge( jfk, sfo, "JFK->SFO" );
	Edge<String> chimia = g148.insertEdge( chi, mia, "ORD->MIA" );
	Edge<String> chidfw = g148.insertEdge( chi, dfw, "ORD->DFW" );
	Edge<String> sfolax = g148.insertEdge( sfo, lax, "SFO->LAX" );
	Edge<String> laxchi = g148.insertEdge( lax, chi, "LAX->ORD" );
	Edge<String> dfwlax = g148.insertEdge( dfw, lax, "DFW->LAX" );
	Edge<String> dfwsfo = g148.insertEdge( dfw, sfo, "DFW->SFO" );
	Edge<String> dfwchi = g148.insertEdge( dfw, chi, "DFW->ORD" );
	Edge<String> miadfw = g148.insertEdge( mia, dfw, "MIA->DFW" );
	Edge<String> mialax = g148.insertEdge( mia, lax, "MIA->LAX" );
	System.out.println( g148 );

	discovered = new HashSet<>();
	forest = new HashMap<>();
	AdjacencyMapGraph.DFS( g148, bos, discovered, forest );
	System.out.println( "Path between vertices " + bos.getElement() + " and " + sfo.getElement() + ":" );
	for( Edge<String> e : AdjacencyMapGraph.constructPath( g148, bos, sfo, forest ) )
	    System.out.println( e.getElement() );

	discovered = new HashSet<>();
	forest = new HashMap<>();
	AdjacencyMapGraph.BFS( g148, bos, discovered, forest );
	System.out.println( "BFS de BOS :" );
	for( Map.Entry<Vertex<String>,Edge<String>> i : forest.entrySet() )
	    System.out.println( i.getKey().getElement() + " " + i.getValue().getElement() );
    }
}

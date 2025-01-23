package ca.umontreal.IFT2015.adt.list;

/**
* TestPosition is a class to exemplify the concept of Position
* 
* @author      Francois Major
* @version     1.0
* @since       1.0 (2024.09.24)
*/

public class TestPosition {
    public static void main( String[] args ) {
	
	// Using List
	List<String> maListe = new ArrayList<>();        // Positions are indices in the array
	// List<String> maListe = new SinglyLinkedList<>(); // Positions are pointers in the list
	maListe.add( "premier" );
	maListe.add( "deuxieme" );
	maListe.add( "troisieme" );
	maListe.add( "quatrieme" );
	maListe.add( "cinquieme" );
	maListe.add( "Paule" );
	maListe.add( "septieme" );
	maListe.add( "huitieme" );
	maListe.add( "neuvieme" );
	System.out.println( maListe );

	// get Paule's index
	int paulePosition = 5; // given we know
	// instructions' order is important
	// Add Paul to the position after Paule
	maListe.add( paulePosition + 1, "Paul" ); // O(n)
	// Add Paulette at Paule's position
	maListe.add( paulePosition, "Paulette" ); // O(n), Paule's position is lost
	System.out.println( maListe );

	// Using PositionalList
	PositionalList<String> maListePositionnelle = new LinkedPositionalList<>();
	maListePositionnelle.addLast( "premier" );
	maListePositionnelle.addLast( "deuxieme" );
	maListePositionnelle.addLast( "troisieme" );
	maListePositionnelle.addLast( "quatrieme" );
	maListePositionnelle.addLast( "cinquieme" );
	Position<String> positionPaule = maListePositionnelle.addLast( "Paule" ); // save position on the fly
	maListePositionnelle.addLast( "septieme" );
	maListePositionnelle.addLast( "huitieme" );
	maListePositionnelle.addLast( "neuvieme" );
	System.out.println( maListePositionnelle );

	// instructions' order is not important
	// Add Paul after Paule
	maListePositionnelle.addAfter( positionPaule, "Paul" );      // O(1)
	// Add Paulette before Paule
	maListePositionnelle.addBefore( positionPaule, "Paulette" ); // O(1), Paule's position never changes
	System.out.println( maListePositionnelle );
    }
}

package ca.umontreal.IFT2015.trees;
import ca.umontreal.IFT2015.adt.list.Position;

public class BTreeApp {

        public static void main( String[] args ) {
	LinkedBinaryTree<Integer> tree = new LinkedBinaryTree();
	Position<Integer> root = tree.addRoot( 0 );
	Position<Integer> one = tree.addLeft( root, 1 );
	Position<Integer> two = tree.addRight( root, 2 );
	System.out.println( tree );

	// create two trees to test attach
	LinkedBinaryTree<Integer> leftTree = new LinkedBinaryTree();
	Position<Integer> leftTreeRoot = leftTree.addRoot( 10 );
	Position<Integer> eleven = leftTree.addLeft( leftTreeRoot, 11 );
	Position<Integer> twelve = leftTree.addRight( leftTreeRoot, 12 );
	System.out.println( leftTree );

	LinkedBinaryTree<Integer> rightTree = new LinkedBinaryTree();
	Position<Integer> rigthTreeRoot = rightTree.addRoot( 20 );
	Position<Integer> twentyOne = rightTree.addLeft( rigthTreeRoot, 21 );
	Position<Integer> twentyTwo = rightTree.addRight( rigthTreeRoot, 22 );
	System.out.println( rightTree );
	
	tree.attach( one, leftTree, rightTree );
	System.out.println( "after attach" );
	System.out.println( tree );

	Position<Integer> twentyThree = tree.addRight( twentyTwo, 23 );
	System.out.println( tree );
	tree.remove( twentyThree );
	tree.addLeft( twentyThree, 24 );
	System.out.println( tree );
    }
}

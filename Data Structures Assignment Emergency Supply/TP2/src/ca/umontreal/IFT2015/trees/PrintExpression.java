package ca.umontreal.IFT2015.trees;
import ca.umontreal.IFT2015.adt.list.Position;

public class PrintExpression {

    public static void main( String[] args ) {
	LinkedBinaryTree<String> expression = new LinkedBinaryTree();
	Position<String> root = expression.addRoot( "+" );
	Position<String> leftTimes = expression.addLeft( root, "x" );
	Position<String> rightTimes = expression.addRight( root, "x" );
	Position<String> two = expression.addLeft( leftTimes, "2" );
	Position<String> minus = expression.addRight( leftTimes, "-" );
	Position<String> three = expression.addLeft( rightTimes, "3" );
	Position<String> b = expression.addRight( rightTimes, "b" );
	Position<String> a = expression.addLeft( minus, "a" );
	Position<String> one = expression.addRight( minus, "1" );
	expression.toExpression( root );
    }
}

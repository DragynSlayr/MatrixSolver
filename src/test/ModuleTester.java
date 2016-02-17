package test;

import solver.Fraction;
import solver.Vector;

/**
 * A class for testing aspects of the MatrixSolver and supporting classes
 * 
 * @author Inderpreet Dhillon
 *
 */
public class ModuleTester {

	/**
	 * Main testing method
	 * 
	 * @param args
	 *            Command line arguments, unsupported
	 */
	public static void main(String[] args) {
		int[] a = { 2, -2, -5 };
		int[] b = { 2, 5, -4 };

		Vector aVector = new Vector(a);
		Vector bVector = new Vector(b);

		System.out.println(aVector);
		System.out.println(bVector);

		System.out.println(Vector.addVectors(aVector, bVector));
		System.out.println(Vector.addVectors(bVector, aVector));

		System.out.println(Vector.subtractVectors(aVector, bVector));
		System.out.println(Vector.subtractVectors(bVector, aVector));

		System.out.println(aVector.divide(new Fraction(-2)));
		System.out.println(bVector.multiply(new Fraction(5)));
	}
}

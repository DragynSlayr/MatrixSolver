package test;

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
		int[] a = { 3, 4, 2, 2 };
		Vector v = new Vector(a);

		System.out.println(v.getDistance());
	}
}

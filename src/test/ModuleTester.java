package test;

import solver.Exponential;
import solver.Fraction;

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
		Exponential exp = new Exponential(new Fraction(1, 2), new Fraction(-1,
				2));

		System.out.println(exp);
	}
}

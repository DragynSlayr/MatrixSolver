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
		Fraction a = new Fraction(2);
		Fraction b = new Fraction(3);
		Fraction c = new Fraction(1, 4);
		Fraction d = new Fraction(5, 2);

		Exponential ab = new Exponential(a, b);
		Exponential bc = new Exponential(b, c);
		Exponential cd = new Exponential(c, d);
		Exponential da = new Exponential(d, a);

		System.out.println(ab);
		System.out.println(bc);
		System.out.println(cd);
		System.out.println(da);
	}
}

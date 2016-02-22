package test;

import solver.Fraction;
import solver.Matrix;

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

		// int[][] a = { { 3, 9, 2 }, { 1, 1, 0 }, { 2, 1, -1 } };
		Fraction[][] a = {
				{ new Fraction(1, 2), new Fraction(7, 3), new Fraction(2) },
				{ new Fraction(0), new Fraction(1), new Fraction(-1) },
				{ new Fraction(10, 3), new Fraction(42), new Fraction(-3) } };
		int[][] b = { { 21 }, { 2 }, { 1 } };
		// int[][] a = { { 3, 2, 1 }, { 9, 3, 6 }, { 1, 2, 4 } };

		Matrix aMatrix = new Matrix(a);
		System.out.println(aMatrix);

		Matrix bMatrix = new Matrix(b);
		System.out.println(bMatrix);

		Fraction[] solutions = Matrix.findSolution(aMatrix, bMatrix);

		for (int i = 0; i < solutions.length; i++) {
			System.out.printf("X%d: %s\n", i + 1, solutions[i]);
		}
	}
}

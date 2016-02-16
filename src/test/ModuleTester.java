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
		int[][] a = { { 3, 4 }, { 9, 1 } };
		int[][] b = { { 7 }, { 2 } };

		Matrix aMatrix = new Matrix(a);
		Matrix bMatrix = new Matrix(b);

		Fraction[] solutions = Matrix.findSolution(aMatrix, bMatrix);

		for (int i = 0; i < solutions.length; i++) {
			System.out.printf("X%d: %s\n", i, solutions[i]);
		}
	}
}
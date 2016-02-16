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
		int[][] a = { { 1, 1, 1 }, { 0, 2, 5 }, { 2, 5, -1 } };
		int[][] b = { { 6 }, { -4 }, { 27 } };

		Matrix aMatrix = new Matrix(a);
		Matrix bMatrix = new Matrix(b);

		System.out.println(aMatrix);
		System.out.println(bMatrix);

		System.out.println("Det: " + aMatrix.getDeterminant() + "\n");

		Fraction[] solutions = Matrix.findSolution(aMatrix, bMatrix);

		for (int i = 0; i < solutions.length; i++) {
			System.out.printf("X%d: %s\n", (i + 1), solutions[i]);
		}
	}
}
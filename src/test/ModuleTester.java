package test;

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
		int[][] a = { { 1, 2, 3 }, { 4, 5, 6 } };
		int[][] b = { { 1, 2 }, { 3, 4 }, { 5, 6 } };
		int[][] c = { { 10, 4, -1 }, { -1, 5, 4 }, { 5, -9, -6 } };
		int[][] d = { { -9, 1, 9, 8 }, { 0, 6, 2, 1 }, { 0, -30, -4, -6 } };

		Matrix aMatrix = new Matrix(a);
		Matrix bMatrix = new Matrix(b);
		Matrix cMatrix = new Matrix(c);
		Matrix dMatrix = new Matrix(d);

		System.out.println(aMatrix);
		System.out.println(bMatrix);
		System.out.println(cMatrix);
		System.out.println(dMatrix);

		System.out.println(Matrix.multiplyMatrices(aMatrix, bMatrix));
		System.out.println(Matrix.multiplyMatrices(bMatrix, aMatrix));
		System.out.println(Matrix.multiplyMatrices(cMatrix, dMatrix));
		System.out.println(Matrix.multiplyMatrices(dMatrix, cMatrix));
	}
}
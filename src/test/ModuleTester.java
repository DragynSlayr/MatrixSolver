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
		int[][] b = { { 1 }, { 2 } };

		Matrix aMatrix = new Matrix(a);
		Matrix bMatrix = new Matrix(b);

		System.out.println(aMatrix);
		System.out.println(bMatrix);

		for (int i = 0; i < aMatrix.getColumns(); i++) {
			System.out.println(Matrix.replaceColumn(aMatrix, bMatrix, i));
		}
	}
}
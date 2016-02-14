package test;

import java.util.Arrays;

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
		int[][] a = { { 1, 2 }, { 3, 4 } };
		// int[][] a = { { 10, 4, -1 }, { -1, 5, 4 }, { 5, -9, -6 } };
		// int[][] a = { { -9, 1, 9, 8 }, { 0, 6, 2, 1 }, { 0, -30, -4, -6 },
		// { 0, -24, 4, -13 } };

		Matrix m = new Matrix(a);
		System.out.println(m);

		for (int i = 0; i < m.getColumns(); i++) {
			Fraction[] column = m.getColumn(i);
			System.out.println(Arrays.toString(column));
		}
	}
}
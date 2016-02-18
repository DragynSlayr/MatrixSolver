package test;

import solver.Matrix;
import solver.SolverAI;

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

		int[][] a = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 },
				{ 13, 14, 15, 16 } };
		// int[][] a = { { 3, 2, 1 }, { 9, 3, 6 }, { 1, 2, 4 } };

		Matrix aMatrix = new Matrix(a);
		System.out.println(aMatrix);

		SolverAI ai = new SolverAI(aMatrix);
		System.out.println(ai.solve());

	}
}

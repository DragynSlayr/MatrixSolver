package test;

import solver.Matrix;

public class ModuleTester {
	public static void main(String[] args) {
		// int[][] a = { { 1, 2 }, { 3, 4 } };
		// int[][] a = { { 10, 4, -1 }, { -1, 5, 4 }, { 5, -9, -6 } };
		// int[][] a = { { -3, -2, -3 }, { 4, -7, -5 }, { 1, -8, -5 } };
		int[][] a = { { -9, 1, 9, 8 }, { 0, 6, 2, 1 }, { 0, -30, -4, -6 },
				{ 0, -24, 4, -13 } };

		Matrix m = new Matrix(a);

		System.out.println(m);
		System.out.println(m.findInverse());
	}
}

// TODO Wednesday February 17 noon, 448 front street south-east

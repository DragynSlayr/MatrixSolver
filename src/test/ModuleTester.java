package test;

import solver.Matrix;

public class ModuleTester {
	public static void main(String[] args) {
		// int[][] a = { { 10, 4, -1 }, { -1, 5, 4 }, { 5, -9, -6 } };
		int[][] a = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		Matrix m = new Matrix(a);
		System.out.println(m);

		System.out.println(m.getDeterminant());
		System.out.println(m.getAdjugate());
	}
}

// TODO Wednesday February 17 noon, 448 front street south-east

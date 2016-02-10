package solver;

public class SolverAI {

	private Matrix matrix, identity;

	/**
	 * Create an AI with a matrix to solve
	 * 
	 * @param matrix
	 *            The matrix that the AI will attempt to solve
	 */
	public SolverAI(Matrix matrix) {
		// Store the matrix
		this.matrix = matrix;

		// If the matrix is square then create an identity matrix for it
		if (matrix.isSquare()) {
			createIdentityMatrix();
		}
	}

	/**
	 * Builds an identity matrix for the user's matrix
	 */
	private void createIdentityMatrix() {
		int[][] matrix = { { 1, 2, 3 }, { 2, 3, 1 }, { 3, 1, 2 } };
		this.identity = new Matrix(matrix);
	}

	/**
	 * Basic solving example, works for some at least one 3x3 matrix
	 */
	public void exampleSolve() {
		System.out.println("Start\n" + matrix);

		this.matrix.addRows(0, 1);
		System.out.println("Add 2 to 1\n" + this.matrix);

		this.matrix.multiplyRow(0, 3);
		System.out.println("Multiply 1 by 3\n" + this.matrix);

		this.matrix.divideRow(0, 3);
		System.out.println("Divide 1 by 3\n" + this.matrix);

		this.matrix.multiplyAndAddRows(0, 1, 1, -3);
		System.out.println("Add -3 of 2 to 1\n" + this.matrix);

		this.matrix.swapRows(0, 1);
		System.out.println("Swap 1 and 2\n" + this.matrix);

		this.matrix.swapRows(1, 0);
		System.out.println("Swap 2 and 1\n" + this.matrix);

		this.matrix.addRows(0, 2);
		System.out.println("Add 3 to 1\n" + this.matrix);

		this.matrix.multiplyAndAddRows(1, 1, 2, -2);
		System.out.println("Add -2 of 3 to 2\n" + this.matrix);
	}

	/**
	 * Checks if the current matrix has an identity
	 * 
	 * @return True if the matrix has an identity, False otherwise
	 */
	private boolean hasIdentity() {
		return identity != null;
	}
}

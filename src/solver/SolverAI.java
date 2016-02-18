package solver;

/**
 * A class for solving Matrices, autonomously
 * 
 * @author Inderpreet
 *
 */
public class SolverAI {

	private Matrix matrix, identity;
	private int stopColumn;

	/**
	 * Create an AI with a matrix to solve
	 * 
	 * @param matrix
	 *            The matrix that the AI will attempt to solve
	 */
	public SolverAI(Matrix matrix) {
		// Store the matrix
		this.matrix = matrix;

		if (matrix.isSquare()) {
			// Create an identity matrix for the matrix
			createIdentityMatrix();

			// Set the max column to go to
			stopColumn = this.matrix.getColumns();
		} else {
			// Set the stop column to the smaller of the rows and columns
			stopColumn = Math.min(this.matrix.getColumns(),
					this.matrix.getRows());
		}
	}

	/**
	 * Builds an identity matrix for the user's matrix
	 */
	private void createIdentityMatrix() {
		// Create a 2d array of the same size as the matrix
		int[][] matrix = new int[this.matrix.getRows()][this.matrix
				.getColumns()];

		// Set all elements to zero
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				// Set the current element to 1 if i and j are equal, 0
				// otherwise
				matrix[i][j] = ((i == j) ? 1 : 0);
			}
		}

		// Set the identity to a new Matrix
		this.identity = new Matrix(matrix);
	}

	public Matrix solve() {
		// Get a copy of the Matrix
		Matrix temp = this.matrix.getCopy();

		// Sort the copy, using Bubble Sort
		temp = bubbleSortMatrix(temp);

		System.out.println("Sorted\n" + temp);

		// Reduce each row so it has a leading one
		for (int i = 0; i < temp.getRows(); i++) {
			temp = makeLeadingOne(temp, i, 0);

			System.out.printf("Leading one in %d\n%s\n", i + 1, temp);
		}

		// Make leading zeroes in every row except the first
		for (int j = 1; j < temp.getRows(); j++) {
			temp = subtractRowFromRow(temp, 0, j);

			System.out.printf("Subtracted %d from %d\n%s\n", 0, j, temp);
		}

		// Reduce each row so it has a leading one
		for (int k = 1; k < temp.getRows(); k++) {
			temp = makeLeadingOne(temp, k, 1);

			System.out.printf("Leading one in %d\n%s\n", k + 1, temp);
		}

		// Return the solved Matrix
		return temp;
	}

	/**
	 * Subtract two rows from a matrix
	 * 
	 * @param matrix
	 *            The Matrix to use
	 * @param rowOne
	 *            The row to subtract
	 * @param rowTwo
	 *            The row to subtract the first from
	 * @return The Matrix after subtraction
	 */
	private Matrix subtractRowFromRow(Matrix matrix, int rowOne, int rowTwo) {
		// Make a copy of the Matrix
		Matrix subtracted = matrix.getCopy();

		// Subtract the two rows
		subtracted.subtractRows(rowTwo, rowOne);

		// Return the Matrix after subtraction
		return subtracted;
	}

	/**
	 * Reduces a row to have a leading one
	 * 
	 * @param matrix
	 *            The Matrix to operate on
	 * @param rowIndex
	 *            The row to reduce
	 * @return The Matrix with the row at rowIndex having a leading one
	 */
	private Matrix makeLeadingOne(Matrix matrix, int rowIndex, int columnIndex) {
		// Get a copy of the Matrix
		Matrix reduced = matrix.getCopy();

		// Get the leading element of the row
		Fraction leadingFraction = reduced.getElement(rowIndex, columnIndex);

		// A Fraction reference to hold the divisor for reduction
		Fraction divisor;

		// Check if the leading element is a zero
		if (leadingFraction.equals(new Fraction(0))) {
			// Set the divisor to one so division by zero doesn't occur
			divisor = new Fraction(1);
		} else {
			// If the leading element is non zero, it will be the divisor
			divisor = leadingFraction;
		}

		// Traverse the row
		for (int i = columnIndex; i < reduced.getColumns(); i++) {
			// Get the element at the index
			Fraction currentElement = matrix.getElement(rowIndex, i);

			// Divide the current element by the divisor
			Fraction reducedElement = Fraction.divideFraction(currentElement,
					divisor);

			// Set the element
			reduced.setElement(rowIndex, i, reducedElement);
		}

		// Return the Matrix with a row reduced
		return reduced;
	}

	/**
	 * Sorts a Matrix by first element in each row, highest to lowest, using
	 * Bubble Sort
	 * 
	 * @param matrix
	 *            The Matrix to sort
	 * @return The sorted Matrix, rows sorted from highest to lowest
	 */
	private Matrix bubbleSortMatrix(Matrix matrix) {
		// Make a copy of the Matrix
		Matrix sorted = matrix.getCopy();

		// Counter for how many times rows were swapped
		int swaps;

		// Loop until the Matrix is sorted, when swaps = 0
		do {
			// Reset swaps
			swaps = 0;

			// Iterate through the rows of the Matrix
			for (int i = 0; i < sorted.getRows() - 1; i++) {
				// Get both elements of the rows we want to compare
				Fraction firstRowLead = sorted.getElement(i, 0);
				Fraction secondRowLead = sorted.getElement(i + 1, 0);

				// Compare elements
				boolean firstSmaller = firstRowLead.compareTo(secondRowLead) < 0;

				if (firstSmaller) {
					// Swap the rows if the first row is smaller than the second
					sorted.swapRows(i, i + 1);

					// Increment swaps
					swaps++;
				}
			}
		} while (swaps != 0);

		// Return the sorted Matrix
		return sorted;
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
}

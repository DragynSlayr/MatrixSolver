package solver;

/**
 * A class for representing and manipulating Matrices
 * 
 * @author Inderpreet Dhillon
 *
 */
public class Matrix {

	/**
	 * Get the minor of a matrix at an element
	 * 
	 * @param row
	 *            The row of the element
	 * @param column
	 *            The column of the element
	 * @param matrix
	 *            The matrix to get the minor of
	 * @return A matrix that is a minor of matrix at (row, column)
	 */
	public static Matrix getMinorAt(int row, int column, Matrix matrix) {
		// Create a new Fraction[][] that is n-1xn-1 of the matrix
		Fraction[][] minorArray = new Fraction[matrix.rows - 1][matrix.columns - 1];

		// Variables to track index of the Fraction[][]
		int iIndex = 0, jIndex = 0;
		boolean found = false;

		// Traverse the matrix
		for (int i = 0; i < matrix.rows; i++) {
			for (int j = 0; j < matrix.columns; j++) {
				if (i != row && j != column) {
					// Only add rows and columns that are not being excluded
					minorArray[iIndex][jIndex] = matrix.getElement(i, j);

					// Increment jIndex and update found
					jIndex++;
					found = true;
				}
			}
			// Only increment iIndex if a valid value was found
			if (found) {
				iIndex++;
				found = false;
			}
			jIndex = 0;
		}

		// Return the minor
		return new Matrix(minorArray);
	}

	/**
	 * Swap rows and columns of a matrix
	 * 
	 * @param matrix
	 *            The matrix to transpose
	 * @return A transposition of the matrix
	 */
	public static Matrix transpose(Matrix matrix) {
		// Create a matrix that has the appropriate dimensions for the transpose
		Matrix transposed = new Matrix(matrix.columns, matrix.rows);

		// Transpose the matrix
		for (int j = 0; j < matrix.columns; j++) {
			for (int i = 0; i < matrix.rows; i++) {
				transposed.setElement(j, i, matrix.getMatrix()[i][j]);
			}
		}

		// Return the transpose
		return transposed;
	}

	private Fraction[][] matrix;
	private int columns, rows;

	/**
	 * Create a new matrix from a 2d Fraction array
	 * 
	 * @param matrix
	 *            A 2d array of Fractions, this will represent the matrix
	 */
	public Matrix(Fraction[][] matrix) {
		// Call basic constructor
		this(matrix.length, matrix[0].length);

		// Store parameters
		this.matrix = matrix;
	}

	/**
	 * Creates an empty matrix
	 * 
	 * @param rows
	 *            The height of the matrix
	 * @param columns
	 *            The width of the matrix
	 */
	public Matrix(int rows, int columns) {
		// Store parameters
		this.rows = rows;
		this.columns = columns;

		// Set a default matrix
		this.matrix = getDefaultMatrix();
	}

	/**
	 * Creates a new matrix from a 2d array
	 * 
	 * @param matrix
	 *            A 2d array of integers, this will represent the matrix
	 */
	public Matrix(int[][] matrix) {
		// Call main constructor
		this(Fraction.convertToFraction(matrix));
	}

	/**
	 * Adds two rows of the matrix and stores the result into the first row
	 * 
	 * @param firstRow
	 *            The row to store the result in
	 * @param secondRow
	 *            The row to add to the first
	 */
	public void addRows(int firstRow, int secondRow) {
		// Iterate through both rows simultaneously
		for (int i = 0; i < columns; i++) {
			// Add the second row to the first
			this.matrix[firstRow][i].add(this.matrix[secondRow][i]);
		}
	}

	/**
	 * Divide the matrix by a Fraction
	 * 
	 * @param fraction
	 *            The fraction to divide by
	 * @return The matrix after division
	 */
	public Matrix divide(Fraction fraction) {
		return this.multiply(Fraction.getInverse(fraction));
	}

	/**
	 * Divides each element of a row by a number, in place
	 * 
	 * @param rowIndex
	 *            The row to divide
	 * @param divisor
	 *            The integer to divide by
	 */
	public void divideRow(int rowIndex, int divisor) {
		// Iterate through the row
		for (int i = 0; i < this.columns; i++) {
			// Divide each element
			this.matrix[rowIndex][i].divide(divisor);
		}
	}

	/**
	 * Get the inverse of the Matrix, assuming the matrix is square
	 * 
	 * @return The inverse of the matrix
	 */
	public Matrix findInverse() {
		// Get the determinant
		Fraction determinant = this.getDeterminant();

		// Get the adjoint
		Matrix adjoint = this.getAdjoint();

		// Return the inverse using the formula: (1/det(A))*adj(A)
		return adjoint.divide(determinant);
	}

	/**
	 * Get the adjoint of the matrix, the transpose of the cofactor
	 * 
	 * @return The adjoint of the matrix
	 */
	public Matrix getAdjoint() {
		// Create a matrix for the cofactor
		Matrix cofactorMatrix = new Matrix(this.rows, this.columns);

		// Traverse the matrix
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns; j++) {
				// Get the minor for each index of the matrix
				Matrix minor = Matrix.getMinorAt(i, j, this);

				// Get the determinant of the minor
				Fraction minorDeterminant = minor.getDeterminant();

				// Get the cofactor of the minor
				Fraction minorCofactor = minor.getCofactor(i, j);

				// The product of the cofactor and determinant of the minor
				Fraction product = Fraction.multiplyFraction(minorCofactor,
						minorDeterminant);

				// Put the production in the cofactor matrix
				cofactorMatrix.setElement(i, j, product);
			}
		}

		// Return the transpose of the cofactor matrix, the adjoint
		return Matrix.transpose(cofactorMatrix);
	}

	/**
	 * Get the cofactor for an element
	 * 
	 * @param row
	 *            The row of the element
	 * @param column
	 *            The column of the element
	 * @return The cofactor at the element's index
	 */
	private Fraction getCofactor(int row, int column) {
		// Get 1^(row + column)
		if ((row + column) % 2 == 0) {
			return new Fraction(1);
		} else {
			return new Fraction(-1);
		}
	}

	/**
	 * Gets a matrix filled with zeroes
	 * 
	 * @return A 2d array of zeroes
	 */
	private Fraction[][] getDefaultMatrix() {
		// Create a sized array
		Fraction[][] matrix = new Fraction[this.rows][this.columns];

		// Fill the array with zeroes
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = new Fraction(0);
			}
		}

		// Return the array
		return matrix;
	}

	/**
	 * Get the determinant of the matrix
	 * 
	 * @return The determinant of the matrix if the matrix is square, 0
	 *         otherwise
	 */
	public Fraction getDeterminant() {
		if (this.isSquare()) {
			return this.getRecursiveDeterminant();
		} else {
			return new Fraction(0);
		}
	}

	/**
	 * Gets an element at a row and column
	 * 
	 * @param row
	 *            The row of the element
	 * @param column
	 *            The column of the element
	 * @return The element at the row and column
	 */
	public Fraction getElement(int row, int column) {
		return this.matrix[row][column];
	}

	/**
	 * Get the matrix
	 * 
	 * @return A 2d array of fractions that represent the matrix
	 */
	public Fraction[][] getMatrix() {
		// Return the 2d array
		return this.matrix;
	}

	/**
	 * Get the determinant of a matrix by shrinking the matrix recursively,
	 * until it is easily computed
	 * 
	 * @return The determinant of a matrix
	 */
	private Fraction getRecursiveDeterminant() {
		// Check size of the matrix and adjust accordingly
		if (this.columns == 1 && this.rows == 1) {
			// Determinant of a 1x1 matrix is the element
			return this.getElement(0, 0);
		} else if (this.columns == 2 && this.rows == 2) {
			// 2x2 determinant can be found with a formula
			return this.getSmallDeterminant();
		} else {
			// For a matrix greater than 2x2

			// Get the row to calculate determinant along
			Fraction[] detRow = this.getRow(0);

			// Create a variable to hold the sum of the determinant(s) of the
			// row's minor
			Fraction total = new Fraction(0);

			// Iterate through row
			for (int i = 0; i < detRow.length; i++) {
				// Multiply the element by the cofactor of the element
				Fraction cofactor = Fraction.multiplyFraction(detRow[i],
						getCofactor(0, i));

				// Get the minor at the element
				Matrix minor = Matrix.getMinorAt(0, i, this);

				// Add the product of the cofactor and the determinant to the
				// total
				total.add(Fraction.multiplyFraction(cofactor,
						minor.getDeterminant()));
			}
			// Return the total (determinant)
			return total;
		}
	}

	/**
	 * Get a row at an index
	 * 
	 * @param row
	 *            The index of the row
	 * @return The row at the index
	 */
	public Fraction[] getRow(int row) {
		return this.matrix[row];
	}

	/**
	 * Gets the determinant of a 2x2 matrix
	 * 
	 * @return The determinant, using the formula
	 */
	private Fraction getSmallDeterminant() {
		// Multiply opposing corners
		Fraction ad = Fraction.multiplyFraction(this.getElement(0, 0),
				this.getElement(1, 1));
		Fraction bc = Fraction.multiplyFraction(this.getElement(0, 1),
				this.getElement(1, 0));

		// Return the difference of the products
		return Fraction.subtractFraction(ad, bc);
	}

	/**
	 * Checks if the matrix is square
	 * 
	 * @return True if height is equal to width, False otherwise
	 */
	public boolean isSquare() {
		// A matrix is square when rows and columns are the same
		return this.columns == this.rows;
	}

	/**
	 * Multiply the matrix by a Fraction
	 * 
	 * @param fraction
	 *            The Fraction to multiply by
	 * @return The matrix after multiplication
	 */
	public Matrix multiply(Fraction fraction) {
		// Create a Matrix that has the same dimension as this one
		Matrix multiplied = new Matrix(this.rows, this.columns);

		// Traverse the matrix
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns; j++) {
				// Multiply the element at the index by the fraction
				Fraction product = Fraction.multiplyFraction(
						this.getElement(i, j), fraction);

				// Set the element of the new Matrix to the product
				multiplied.setElement(i, j, product);
			}
		}

		// Return the new Matrix
		return multiplied;
	}

	/**
	 * Multiply two rows by an integer and then add them, place result in first
	 * row
	 * 
	 * @param firstRow
	 *            The row to place the result in
	 * @param firstRowMultiple
	 *            Integer to multiply first row by
	 * @param secondRow
	 *            Row to add to first
	 * @param secondRowMultiple
	 *            Integer to multiply second row by
	 */
	public void multiplyAndAddRows(int firstRow, int firstRowMultiple,
			int secondRow, int secondRowMultiple) {
		// Multiply both rows
		multiplyRow(firstRow, firstRowMultiple);
		multiplyRow(secondRow, secondRowMultiple);

		// Add row two to row one
		addRows(firstRow, secondRow);

		// Divide second row
		divideRow(secondRow, secondRowMultiple);
	}

	/**
	 * Multiply two rows by an integer and then subtract them, place result in
	 * first row
	 * 
	 * @param firstRow
	 *            The row to place the result in
	 * @param firstRowMultiple
	 *            Integer to multiply first row by
	 * @param secondRow
	 *            Row to subtract from first
	 * @param secondRowMultiple
	 *            Integer to multiply second row by
	 */
	public void multiplyAndSubtractRows(int firstRow, int firstRowMultiple,
			int secondRow, int secondRowMultiple) {
		// Multiply both rows
		multiplyRow(firstRow, firstRowMultiple);
		multiplyRow(secondRow, secondRowMultiple);

		// Add row two to row one
		subtractRows(firstRow, secondRow);

		// Divide second row
		divideRow(secondRow, secondRowMultiple);
	}

	/**
	 * Multiplies each element of a row by an integer
	 * 
	 * @param rowIndex
	 *            The row to multiply
	 * @param multiple
	 *            The integer to multiply by
	 */
	public void multiplyRow(int rowIndex, int multiple) {
		// Iterate through the row
		for (int i = 0; i < this.columns; i++) {
			// Multiply each element of row
			this.matrix[rowIndex][i].multiply(multiple);
		}
	}

	/**
	 * Sets the value of a single element of the matrix
	 * 
	 * @param row
	 *            The row of the element
	 * @param column
	 *            The column of the element
	 * @param value
	 *            The value to use
	 */
	public void setElement(int row, int column, Fraction value) {
		this.matrix[row][column] = value;
	}

	/**
	 * Set the value of a particular element of the matrix
	 * 
	 * @param row
	 *            The row of the element
	 * @param column
	 *            The column of the element
	 * @param value
	 *            The new value
	 */
	public void setElement(int row, int column, int value) {
		setElement(row, column, new Fraction(value));
	}

	/**
	 * Set the matrix values
	 * 
	 * @param matrix
	 *            A 2d array of values to use
	 */
	public void setMatrix(Fraction[][] matrix) {
		this.matrix = matrix;
	}

	/**
	 * Assigns an array to the matrix
	 * 
	 * @param matrix
	 *            The array to assign
	 */
	public void setMatrix(int[][] matrix) {
		setMatrix(Fraction.convertToFraction(matrix));
	}

	/**
	 * Assign a row
	 * 
	 * @param rowIndex
	 *            The index of the assignment
	 * @param row
	 *            The new row
	 */
	public void setRow(int rowIndex, Fraction[] row) {
		this.matrix[rowIndex] = row;
	}

	/**
	 * Assign a single row of the matrix
	 * 
	 * @param rowIndex
	 *            The index to assign to
	 * @param row
	 *            The new row
	 */
	public void setRow(int rowIndex, int[] row) {
		setRow(rowIndex, Fraction.convertRowToFraction(row));
	}

	/**
	 * Subtracts two rows of the matrix and stores the result into the first row
	 * 
	 * @param firstRow
	 *            The row to store the result in
	 * @param secondRow
	 *            The row to subtract from the first
	 */
	public void subtractRows(int firstRow, int secondRow) {
		// Iterate through both rows simultaneously
		for (int i = 0; i < columns; i++) {
			// Subtract the second row from the first
			this.matrix[firstRow][i].subtract(this.matrix[secondRow][i]);
		}
	}

	/**
	 * Swaps two rows
	 * 
	 * @param firstRow
	 *            The first row to swap
	 * @param secondRow
	 *            The second row to swap
	 */
	public void swapRows(int firstRow, int secondRow) {
		// Store first row temporarily
		Fraction[] tempRow = this.matrix[firstRow];

		// Swap first with second
		this.matrix[firstRow] = this.matrix[secondRow];

		// Swap second with first
		this.matrix[secondRow] = tempRow;
	}

	/**
	 * Gets each row and column of the matrix in standard form
	 */
	@Override
	public String toString() {
		// Empty string to hold the matrix
		String matrixString = "";

		// Iterate through the matrix
		for (Fraction[] row : this.matrix) {
			for (Fraction element : row) {
				// Add each element to the string
				matrixString += element + "\t";
			}
			// Add a new line at the end of the row
			matrixString += "\n";
		}

		// Return the matrix as a string
		return matrixString;
	}
}

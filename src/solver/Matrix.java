package solver;

public class Matrix {

	/**
	 * Convert an 2d integer array to 2d Fraction array
	 * 
	 * @param matrix
	 *            The 2d integer array to convert
	 * @return A 2d Fraction array
	 */
	public static Fraction[][] convertToFraction(int[][] matrix) {
		// 2d Fraction array of same size as matrix
		Fraction[][] converted = new Fraction[matrix.length][matrix[0].length];

		// Traverse matrix and convert to Fraction
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				converted[i][j] = new Fraction(matrix[i][j]);
			}
		}

		// Return converted 2d Fraction array
		return converted;
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
		this(convertToFraction(matrix));
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

	public Fraction getElement(int row, int column) {
		return this.matrix[row][column];
	}

	private Fraction getSmallDeterminant() {
		Fraction ad = Fraction.multiplyFraction(this.getElement(0, 0),
				this.getElement(1, 1));

		Fraction bc = Fraction.multiplyFraction(this.getElement(0, 1),
				this.getElement(1, 0));

		return Fraction.subtractFraction(ad, bc);
	}

	private Fraction getRecursiveDeterminant() {
		if (this.columns == 1 && this.rows == 1) {
			return this.getElement(0, 0);
		} else if (this.columns == 2 && this.rows == 2) {
			return this.getSmallDeterminant();
		} else {
			// TODO Compute discriminant of 3+n*3+n matrix
			Fraction[] detRow = this.getRow(0);
			Fraction total = new Fraction(0);
			for (int i = 0; i < detRow.length; i++) {
				Fraction cofactor = Fraction.multiplyFraction(detRow[i],
						getCofactor(0, i));
				Matrix smaller = Matrix.getMinorAt(0, i, this);
				total.add(Fraction.multiplyFraction(cofactor,
						smaller.getDeterminant()));
			}
			return total;
		}
	}

	private Fraction getCofactor(int iIndex, int jIndex) {
		if ((iIndex + jIndex) % 2 == 0) {
			return new Fraction(1);
		} else {
			return new Fraction(-1);
		}
	}

	public static Matrix getMinorAt(int row, int column, Matrix m) {
		Fraction[][] matrix = new Fraction[m.rows - 1][m.columns - 1];
		int iIndex = 0, jIndex = 0;
		boolean found = false;

		for (int i = 0; i < m.rows; i++) {
			for (int j = 0; j < m.columns; j++) {
				if (i != row && j != column) {
					matrix[iIndex][jIndex] = m.getElement(i, j);
					jIndex++;
					found = true;
				}
			}
			if (found) {
				iIndex++;
				found = false;
			}
			jIndex = 0;
		}

		return new Matrix(matrix);
	}

	public int getHeight() {
		return this.rows;
	}

	public int getWidth() {
		return this.columns;
	}

	public Fraction getDeterminant() {
		if (this.isSquare()) {
			return this.getRecursiveDeterminant();
		} else {
			return new Fraction(0);
		}
	}

	public Fraction[] getRow(int index) {
		return this.matrix[index];
	}

	public Matrix getAdjugate() {
		Matrix cofactor = new Matrix(this.rows, this.columns);

		for (int i = 0; i < cofactor.rows; i++) {
			for (int j = 0; j < cofactor.columns; j++) {
				// cofactor.setElement(i, j, value);
			}
		}

		return Matrix.transpose(cofactor);
	}

	/**
	 * Convert an integer array to a Fraction array
	 * 
	 * @param row
	 *            The array to convert
	 * @return The Fraction representation of that row
	 */
	public Fraction[] convertRowToFraction(int[] row) {
		// Fraction array of same size as row
		Fraction[] converted = new Fraction[row.length];

		// Traverse row and convert elements to Fraction
		for (int i = 0; i < row.length; i++) {
			converted[i] = new Fraction(row[i]);
		}

		// Return converted array
		return converted;
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
	 * Get the matrix
	 * 
	 * @return A 2d array of fractions that represent the matrix
	 */
	public Fraction[][] getMatrix() {
		// Return the 2d array
		return this.matrix;
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
		setMatrix(convertToFraction(matrix));
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
		setRow(rowIndex, convertRowToFraction(row));
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
}

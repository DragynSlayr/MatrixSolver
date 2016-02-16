package solver;

/**
 * A class for representing and manipulating Matrices
 * 
 * @author Inderpreet Dhillon
 *
 */
public class Matrix {

	/**
	 * Adds two matrices together
	 * 
	 * @param one
	 *            The first Matrix
	 * @param two
	 *            The Matrix to add to the first
	 * @return A Matrix that represents one + two
	 */
	public static Matrix addMatrices(Matrix one, Matrix two) {
		// Create a new Matrix to hold the sum
		Matrix sum = new Matrix(one.rows, one.columns);

		// Traverse the matrices
		for (int i = 0; i < one.rows; i++) {
			for (int j = 0; j < one.columns; j++) {
				// Add the values at the current index for both matrices
				Fraction value = Fraction.addFraction(one.getElement(i, j),
						two.getElement(i, j));

				// Place the sum in the new Matrix
				sum.setElement(i, j, value);
			}
		}

		// Return the sum of the matrices
		return sum;
	}

	/**
	 * Find the solution to a Matrix, provided with solutions
	 * 
	 * @param matrix
	 *            The Matrix to solve
	 * @param solution
	 *            The solution Matrix
	 * @return A Fraction array of solutions
	 */
	public static Fraction[] findSolution(Matrix matrix, Matrix solution) {
		// Create an array to hold found solutions
		Fraction[] solutions = new Fraction[matrix.columns];

		// Find the determinant of the matrix
		Fraction determinant = matrix.getDeterminant();

		// Traverse columns of the matrix
		for (int i = 0; i < matrix.columns; i++) {
			// Transpose the matrix with the solution matrix
			Matrix transposition = Matrix.replaceColumn(matrix, solution, i);

			// Store the quotient of the transposed determinant
			Fraction transposedDeterminant = transposition.getDeterminant();

			// Store the quotient of the two determinants
			solutions[i] = Fraction.divideFraction(transposedDeterminant,
					determinant);
		}

		// Return the solutions
		return solutions;
	}

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
	 * Multiply two matrices using dot product
	 * 
	 * @param one
	 *            The first Matrix
	 * @param two
	 *            The Matrix to multiply the first by
	 * @return The product of the matrices: one * two
	 */
	public static Matrix multiplyMatrices(Matrix one, Matrix two) {
		// Check that the matrices can be multiplied
		if (one.columns == two.rows) {
			// Create Matrix to represent product
			Matrix product = new Matrix(one.rows, two.columns);

			// Traverse product Matrix
			for (int i = 0; i < product.rows; i++) {
				for (int j = 0; j < product.columns; j++) {
					// Perform dot product on row i of one and column j of two
					Fraction[] rowI = one.getRow(i);
					Fraction[] columnJ = two.getColumn(j);

					// Create Fraction to hold the sum of the products
					Fraction total = new Fraction(0);

					// Traverse selected row and column simultaneously
					for (int k = 0; k < rowI.length; k++) {
						// Add sum to total
						total.add(Fraction
								.multiplyFraction(rowI[k], columnJ[k]));
					}

					// Set the value of the index
					product.setElement(i, j, total);
				}
			}

			// Return product Matrix
			return product;
		} else {
			// Return a zero matrix
			int[][] noValue = { { 0 } };
			return new Matrix(noValue);
		}
	}

	/**
	 * Replaces a column of the Matrix with a Fraction array
	 * 
	 * @param m
	 *            The Matrix to replace a row of
	 * @param column
	 *            The column to use for replacement
	 * @param columnIndex
	 *            The index to place the column at
	 * @return A Matrix that has column: columnIndex replaced with column
	 */
	public static Matrix replaceColumn(Matrix m, Matrix column, int columnIndex) {
		// Create a copy of the Matrix
		Matrix replaced = m.getCopy();

		// /Traverse the new Matrix
		for (int i = 0; i < m.rows; i++) {
			// Replace each element
			replaced.setElement(i, columnIndex, column.getElement(i, 0));
		}

		// Return the new matrix
		return replaced;
	}

	/**
	 * Finds the difference between two matrices
	 * 
	 * @param one
	 *            The first Matrix
	 * @param two
	 *            The Matrix to subtract from the first
	 * @return A Matrix that represents one - two
	 */
	public static Matrix subtractMatrices(Matrix one, Matrix two) {
		// Subtracting is the same as adding the negation of one to the other
		return Matrix.addMatrices(one, two.multiply(new Fraction(-1)));
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

	@Override
	public boolean equals(Object obj) {
		// Check if the object is a Matrix
		if (obj instanceof Matrix) {
			// Cast object to Matrix
			Matrix comparator = (Matrix) obj;

			// Check that the rows and columns are equal in size
			boolean rowsEqual = (this.rows == comparator.rows);
			boolean columnsEqual = (this.columns == comparator.columns);

			if (rowsEqual && columnsEqual) {
				// Return the result of checking each element for equality
				return isSameElements(comparator);
			} else {
				// Return false if the object is not the same size as the Matrix
				return false;
			}
		} else {
			// Return false if the object is not a Matrix
			return false;
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
	 * Gets the column at an index
	 * 
	 * @param columnIndex
	 *            The index of the column
	 * @return The column at the index
	 */
	public Fraction[] getColumn(int columnIndex) {
		// Create an array for the column
		Fraction[] column = new Fraction[this.rows];

		// Traverse the rows
		for (int i = 0; i < this.rows; i++) {
			// Add the element at the index to the column array
			column[i] = this.getElement(i, columnIndex);
		}

		// Return the column
		return column;
	}

	/**
	 * Get the number of columns in the matrix
	 * 
	 * @return The columns in the matrix
	 */
	public int getColumns() {
		return this.columns;
	}

	/**
	 * Creates an unlinked copy of this matrix
	 * 
	 * @return The copy of the Matrix
	 */
	public Matrix getCopy() {
		// Create a Matrix of same size as this
		Matrix copy = new Matrix(this.rows, this.columns);

		// Traverse this Matrix
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns; j++) {
				// Copy the elements of this Matrix to the copy Matrix
				copy.setElement(i, j, this.getElement(i, j));
			}
		}

		// Return the copy
		return copy;
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
	 * Get the number of rows in the matrix
	 * 
	 * @return The rows in the matrix
	 */
	public int getRows() {
		return this.rows;
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
	 * Checks if the elements of this Matrix are identical to another
	 * 
	 * @param m
	 *            The Matrix to compare with
	 * @return true if the elements are the same, false otherwise
	 */
	private boolean isSameElements(Matrix m) {
		// Traverse this matrix
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns; j++) {
				// Check if the elements ar the same
				boolean isSameElement = this.getElement(i, j).equals(
						m.getElement(i, j));

				if (!isSameElement) {
					// Return false if the elements differ
					return false;
				}
			}
		}

		// Return true when all elements have been checked and none have
		// differed
		return true;
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

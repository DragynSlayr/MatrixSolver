package solver;

public class Vector {

	/**
	 * Adds two Vectors together
	 * 
	 * @param one
	 *            The first Vector
	 * @param two
	 *            The Vector to add to the first
	 * @return A Vector that represents one + two
	 */
	public static Vector addMatrices(Vector one, Vector two) {
		// Create a new Vector to hold the sum
		Vector sum = new Vector(one.rows);

		// Traverse the vectors
		for (int i = 0; i < one.rows; i++) {
			// Add the values at the current index for both vectors
			Fraction value = Fraction.addFraction(one.getElement(i),
					two.getElement(i));

			// Place the sum in the new Matrix
			sum.setElement(i, value);
		}

		// Return the sum of the vectors
		return sum;
	}

	/**
	 * Finds the difference between two vectors
	 * 
	 * @param one
	 *            The first Vector
	 * @param two
	 *            The Vector to subtract from the first
	 * @return A Matrix that represents one - two
	 */
	public static Vector subtractMatrices(Vector one, Vector two) {
		// Subtracting is the same as adding the negation of one to the other
		return Vector.addMatrices(one, two.multiply(new Fraction(-1)));
	}

	private Fraction[] vector;

	private int rows;

	/**
	 * Creates a new Vector with a Fraction array
	 * 
	 * @param vector
	 *            The Fraction array to use
	 */
	public Vector(Fraction[] vector) {
		// Call basic constructor
		this(vector.length);

		// Store parameters
		this.vector = vector;
	}

	/**
	 * Creates a new Vector with the number of rows
	 * 
	 * @param rows
	 *            The number of rows in the Vector
	 */
	public Vector(int rows) {
		// Store parameters
		this.rows = rows;

		// Set default vector
		this.vector = getDefaultVector();
	}

	/**
	 * Divide the Vector by a Fraction
	 * 
	 * @param fraction
	 *            The fraction to divide by
	 * @return The Vector after division
	 */
	public Vector divide(Fraction fraction) {
		return this.multiply(Fraction.getInverse(fraction));
	}

	/**
	 * Gets a vector filled with zeroes
	 * 
	 * @return A zero vector
	 */
	private Fraction[] getDefaultVector() {
		// Create a vector of the right size
		Fraction[] vector = new Fraction[this.rows];

		// Fill the vector with zeroes
		for (int i = 0; i < vector.length; i++) {
			vector[i] = new Fraction(0);
		}

		// Return the vector
		return vector;
	}

	/**
	 * Get an element at the row
	 * 
	 * @param row
	 *            The row to get the element from
	 * @return The element at the row
	 */
	public Fraction getElement(int row) {
		return this.vector[row];
	}

	/**
	 * Get the Vector
	 * 
	 * @return A 1d array of fractions that represent the Vector
	 */
	public Fraction[] getVector() {
		return this.vector;
	}

	/**
	 * Multiply the Vector by a Fraction
	 * 
	 * @param fraction
	 *            The Fraction to multiply by
	 * @return The Vector after multiplication
	 */
	public Vector multiply(Fraction fraction) {
		// Create a Vector of same size
		Vector multiplied = new Vector(this.rows);

		// Traverse the Vector
		for (int i = 0; i < this.rows; i++) {
			// Multiply the element at the index by the fraction
			Fraction product = Fraction.multiplyFraction(this.vector[i],
					fraction);

			// Set the element of the Vector to the product
			multiplied.setElement(i, product);
		}

		// Return the new Vector
		return multiplied;
	}

	/**
	 * Set an element in the Vector
	 * 
	 * @param row
	 *            The row to set at
	 * @param value
	 *            The new value
	 */
	public void setElement(int row, Fraction value) {
		this.vector[row] = value;
	}

	/**
	 * Gets each row of the Vector as a String
	 */
	public String toString() {
		// Empty String to hold Vector
		String vectorString = "";

		// Traverse the vector
		for (int i = 0; i < this.rows; i++) {
			vectorString += this.vector[i] + "\n";
		}

		// Return the Vector as a String
		return vectorString;
	}
}

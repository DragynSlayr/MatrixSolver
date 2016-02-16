package solver;

import java.util.ArrayList;

/**
 * A class for representing and manipulating fractions
 * 
 * @author Inderpreet Dhillon
 *
 */
public class Fraction {

	/**
	 * Adds two fractions
	 * 
	 * @param one
	 *            The first fraction
	 * @param two
	 *            The fraction to add to the first
	 * @return A new Fraction that is the sum of the others
	 */
	public static Fraction addFraction(Fraction one, Fraction two) {
		// Copy the first Fraction
		Fraction difference = one.getCopy();

		// Add the second to the first
		difference.add(two);

		// Return the sum of the two Fractions
		return difference;
	}

	/**
	 * Convert an integer array to a Fraction array
	 * 
	 * @param row
	 *            The array to convert
	 * @return The Fraction representation of that row
	 */
	public static Fraction[] convertRowToFraction(int[] row) {
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
	 * Convert an 2d integer array to 2d Fraction array
	 * 
	 * @param iArray
	 *            The 2d integer array to convert
	 * @return A 2d Fraction array
	 */
	public static Fraction[][] convertToFraction(int[][] iArray) {
		// 2d Fraction array of same size as matrix
		Fraction[][] converted = new Fraction[iArray.length][iArray[0].length];

		// Traverse matrix and convert to Fraction
		for (int i = 0; i < iArray.length; i++) {
			for (int j = 0; j < iArray[0].length; j++) {
				converted[i][j] = new Fraction(iArray[i][j]);
			}
		}

		// Return converted 2d Fraction array
		return converted;
	}

	/**
	 * Divide two Fractions
	 * 
	 * @param one
	 *            The first fraction
	 * @param two
	 *            The fraction to divide the first by
	 * @return A new Fraction that is the quotient of the others
	 */
	public static Fraction divideFraction(Fraction one, Fraction two) {
		// Copy the first fraction
		Fraction product = one.getCopy();

		// Use instance method of the copy
		product.multiplyFraction(Fraction.getInverse(two));

		// Return the product
		return product;
	}

	/**
	 * Get the inverse of a fraction
	 * 
	 * @param fraction
	 *            The fraction to get the inverse of
	 * @return The inverse fraction
	 */
	public static Fraction getInverse(Fraction fraction) {
		// Swap numerator and denominator
		return new Fraction(fraction.denominator, fraction.numerator);
	}

	/**
	 * Multiply two Fractions
	 * 
	 * @param one
	 *            The first fraction
	 * @param two
	 *            The fraction to multiply the first by
	 * @return A new Fraction that is the product of the others
	 */
	public static Fraction multiplyFraction(Fraction one, Fraction two) {
		// Copy the first fraction
		Fraction product = one.getCopy();

		// Use instance method of the copy
		product.multiplyFraction(two);

		// Return the product
		return product;
	}

	/**
	 * Subtracts two fractions
	 * 
	 * @param one
	 *            The first fraction
	 * @param two
	 *            The fraction to subtract from the first
	 * @return A new Fraction that is the difference between the others
	 */
	public static Fraction subtractFraction(Fraction one, Fraction two) {
		// Copy the first Fraction
		Fraction difference = one.getCopy();

		// Subtract the second from the first
		difference.subtract(two);

		// Return the difference between the two Fractions
		return difference;
	}

	// Instance variables
	private int numerator, denominator;

	/**
	 * Creates a fraction with default denominator
	 * 
	 * @param numerator
	 *            The top part of the fraction
	 */
	public Fraction(int numerator) {
		this(numerator, 1);
	}

	/**
	 * Create a fraction
	 * 
	 * @param numerator
	 *            The top part of the fraction
	 * @param denominator
	 *            The bottom part of the fraction
	 */
	public Fraction(int numerator, int denominator) {
		// Store numerator and denominator
		this.numerator = numerator;
		this.denominator = denominator;
	}

	/**
	 * Add a fraction to this one
	 * 
	 * @param fraction
	 *            The fraction to add
	 */
	public void add(Fraction fraction) {
		// Calculate numerator manually
		int top = (this.numerator * fraction.denominator)
				+ (fraction.numerator * this.denominator);

		// Calculate denominator manually
		int bottom = this.denominator * fraction.denominator;

		// Set numerator and denominator
		this.numerator = top;
		this.denominator = bottom;
	}

	/**
	 * Divide the fraction by a number
	 * 
	 * @param number
	 *            The number to divide by
	 */
	public void divide(int number) {
		this.denominator *= number;
	}

	/**
	 * Divide this fraction by another
	 * 
	 * @param fraction
	 *            The fraction to divide by
	 */
	public void divideFraction(Fraction fraction) {
		// Division of a fraction is the same as multiplication by the inverse
		Fraction inverted = Fraction.getInverse(fraction);
		this.multiplyFraction(inverted);
	}

	@Override
	public boolean equals(Object obj) {
		// Check if the object is a Fraction
		if (obj instanceof Fraction) {
			// Cast object to Fraction
			Fraction f = (Fraction) obj;

			// Compare numerators and denominators
			boolean sameNumerators = (this.numerator == f.numerator);
			boolean sameDenominators = (this.denominator == f.denominator);

			// Return true if the numerator and denominator of the object match
			// this, false otherwise
			return (sameNumerators && sameDenominators);
		} else {
			// Return false when the object is not a Fraction
			return false;
		}
	}

	/**
	 * Get the factors common to two ArrayLists
	 * 
	 * @param firstList
	 *            The first list of factors
	 * @param secondList
	 *            The factors to compare to the first list
	 * @return An ArrayList containing only factors that are within both
	 *         parameter lists
	 */
	private ArrayList<Integer> getCommonFactors(ArrayList<Integer> firstList,
			ArrayList<Integer> secondList) {
		// New list to hold common factors
		ArrayList<Integer> commonFactors = new ArrayList<Integer>();

		// Calculate the length of the list with the greatest size
		int maxLength = Math.max(firstList.size(), secondList.size());

		// Determine if the first list is larger than the second
		boolean firstLarger = firstList.size() > secondList.size();

		// Go through each element of the larger list
		for (int i = 0; i < maxLength; i++) {
			// Determine which list was larger, prevents out of bounds
			// exceptions
			if (firstLarger) {
				// Check if the second list has the element from the first
				if (secondList.contains(firstList.get(i))) {
					// Add the element if it is in both lists
					commonFactors.add(firstList.get(i));
				}
			} else {
				// Check if the first list has the element of the second
				if (firstList.contains(secondList.get(i))) {
					// Add the element if it is in both lists
					commonFactors.add(secondList.get(i));
				}
			}
		}
		// Sort the list, low to high
		commonFactors.sort(null);

		// Return the common factors
		return commonFactors;
	}

	/**
	 * Provides a copy of the current Fraction
	 * 
	 * @return A Fraction with same numerator and denominator as caller
	 */
	public Fraction getCopy() {
		// Return the copy
		return new Fraction(this.numerator, this.denominator);
	}

	/**
	 * Get the factors of a number
	 * 
	 * @param number
	 *            The number to get the factors of
	 * @return An ArrayList containing the number's factors
	 */
	private ArrayList<Integer> getFactorsOf(int number) {
		// A new ArrayList to store the factors
		ArrayList<Integer> factors = new ArrayList<Integer>();

		// Account for negative numbers
		number = Math.abs(number);

		// Loop until greater than half the number
		for (int i = 1; i <= number / 2; i++) {
			// Check if i divides the number
			if (number % i == 0) {
				if (!factors.contains(i)) {
					// Add i to the factors list, avoiding duplicates
					factors.add(i);
				}

				if (!factors.contains(number / i)) {
					// Add the quotient to the list, avoiding duplicates
					factors.add(number / i);
				}
			}
		}
		// Sort factors
		factors.sort(null);

		// Return the list of factors
		return factors;
	}

	/**
	 * Get the greatest common multiple of a fraction
	 * 
	 * @param fraction
	 *            The fraction to find GCM of
	 * @return The GCM of a fraction
	 */
	private int getGreatestCommonMultiple(Fraction fraction) {
		// Get the factors of the numerator
		ArrayList<Integer> numeratorFactors = getFactorsOf(fraction.numerator);

		// Get the factors of the denominator
		ArrayList<Integer> denominatorFactors = getFactorsOf(fraction.denominator);

		// Intersect the lists of factors
		ArrayList<Integer> commonFactors = getCommonFactors(numeratorFactors,
				denominatorFactors);

		// Check that factors exist
		if (commonFactors.size() > 0) {
			// Return the last (greatest) element of the factors list
			return commonFactors.get(commonFactors.size() - 1);
		} else {
			return 1;
		}
	}

	/**
	 * Multiply the fraction by a number
	 * 
	 * @param number
	 *            The number to multiply by
	 */
	public void multiply(int number) {
		this.numerator *= number;
	}

	/**
	 * Multiply this fraction by another
	 * 
	 * @param fraction
	 *            The fraction to multiply by
	 */
	public void multiplyFraction(Fraction fraction) {
		// Multiply straight across
		this.numerator *= fraction.numerator;
		this.denominator *= fraction.denominator;
	}

	/**
	 * Reduce a fraction
	 */
	private void reduce() {
		if (this.denominator != 1) {
			// Calculate greatest common denominator
			int gcm = getGreatestCommonMultiple(this);

			// Divide by GCM
			this.numerator /= gcm;
			this.denominator /= gcm;
		}
	}

	/**
	 * Subtract a fraction from this one
	 * 
	 * @param fraction
	 *            The fraction to subtract by
	 */
	public void subtract(Fraction fraction) {
		// Calculate numerator
		int top = (this.numerator * fraction.denominator)
				- (fraction.numerator * this.denominator);

		// Calculate denominator
		int bottom = this.denominator * fraction.denominator;

		// Set numerator and denominator
		this.numerator = top;
		this.denominator = bottom;
	}

	/**
	 * Get a string representation of the fraction, automatically reduced
	 */
	@Override
	public String toString() {
		// Reduce the fraction
		this.reduce();

		if (this.denominator < 0) {
			this.denominator *= -1;
			this.numerator *= -1;
		}

		// Check if the denominator needs to be displayed
		if (this.denominator == 1 || this.numerator == 0) {
			return String.valueOf(this.numerator);
		} else {
			return this.numerator + "/" + this.denominator;
		}
	}
}

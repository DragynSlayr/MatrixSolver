package solver;

import java.util.ArrayList;

public class Fraction {

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
		int top = (this.numerator * fraction.denominator)
				+ (fraction.numerator * this.denominator);
		int bottom = this.denominator * fraction.denominator;
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

	public static Fraction multiplyFraction(Fraction one, Fraction two) {
		Fraction result = one.getCopy();
		result.multiplyFraction(two);
		return result;
	}

	public static Fraction subtractFraction(Fraction one, Fraction two) {
		Fraction result = one.getCopy();
		result.subtract(two);
		return result;
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
		int top = (this.numerator * fraction.denominator)
				- (fraction.numerator * this.denominator);
		int bottom = this.denominator * fraction.denominator;
		this.numerator = top;
		this.denominator = bottom;
	}

	public Fraction getCopy() {
		Fraction fraction = new Fraction(this.numerator, this.denominator);
		return fraction;
	}

	/**
	 * Get a string representation of the fraction, automatically reduced
	 */
	@Override
	public String toString() {
		this.reduce();
		return this.numerator + "/" + this.denominator;
	}
}

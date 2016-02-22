package solver;

/**
 * A class for representing and manipulating exponential numbers
 * 
 * @author Inderpreet Dhillon
 *
 */
public class Exponential {

	private Fraction base, power;

	/**
	 * Creates an Exponential with the base and power
	 * 
	 * @param base
	 *            The base
	 * @param power
	 *            The number that the base is raised to
	 */
	public Exponential(Fraction base, Fraction power) {
		// Store parameters
		this.base = base;
		this.power = power;
	}

	/**
	 * Creates an Exponential with the base and power
	 * 
	 * @param base
	 *            The base
	 * @param power
	 *            The number that the base is raised to
	 */
	public Exponential(Fraction base, int power) {
		// Call designated constructor
		this(base, new Fraction(power));
	}

	/**
	 * Creates an Exponential with the base and power
	 * 
	 * @param base
	 *            The base
	 * @param power
	 *            The number that the base is raised to
	 */
	public Exponential(int base, Fraction power) {
		// Call designated constructor
		this(new Fraction(base), power);
	}

	/**
	 * Creates an Exponential with the base and power
	 * 
	 * @param base
	 *            The base
	 * @param power
	 *            The number that the base is raised to
	 */
	public Exponential(int base, int power) {
		// Call designated constructor
		this(new Fraction(base), new Fraction(power));
	}

	/**
	 * Creates an Exponential with the entered values for base and power
	 * 
	 * @param baseNumerator
	 *            The numerator of the base
	 * @param baseDenominator
	 *            The denominator of the base
	 * @param powerNumerator
	 *            The numerator of the power
	 * @param powerDenominator
	 *            The denominator of the power
	 */
	public Exponential(int baseNumerator, int baseDenominator,
			int powerNumerator, int powerDenominator) {
		// Call designated constructor
		this(new Fraction(baseNumerator, baseDenominator), new Fraction(
				powerNumerator, powerDenominator));
	}

	/**
	 * Return a String representation of the Exponential
	 */
	public String toString() {
		// Get the string representation of both base and power
		String baseString = this.base.toString();
		String powerString = this.power.toString();

		// Check if either string needs brackets for clarity
		if (baseString.contains("/")) {
			baseString = "(" + baseString + ")";
		}
		if (powerString.contains("/")) {
			powerString = "(" + powerString + ")";
		}

		// Return the Exponential String
		return baseString + "^" + powerString;
	}
}

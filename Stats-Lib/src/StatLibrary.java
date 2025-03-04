import java.util.Arrays;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class StatLibrary {
	// Something that's common in all stats classes that you take, is learning about
	// Central Tendancies

	    // Default constructor

	public StatLibrary() {

	}

	private int value; // The value of the element
    private int count; // The count of occurrences of the element

	
	// Constructor to initialize value and count

	public StatLibrary(int num, int count) {
		this.value = num;
		this.count = count;
	}
	// Mean, Median, and Mode
	// The list must be in order, so order the luist before finding median. Should
	// beable to call collections sort or arraylist sort of some kind
	// Mean? ~> It's sum of elements divided by count. Another word for it could be
	// expected.

		/**
    	* Method to find the mean of an array of integers.
     	* @param input The array of integers.
    	* @return The mean of the array.
    	*/
		public double findMean(int[] input) { // 2 options, using an array or an array list.
		// Find the sum
		double sum = 0;
		for (int c : input) {
			sum += c;
		}

		double result = sum / (double) input.length;

		return result;
	}

	// Mdedian? ~> Either the middle number if ood, or "average" of the two middlest
	// numbers
	// This method searches through an array and find the middle value of a sorted
	// array.

	/**
     * Method to find the median of an array of integers.
     * @param arr The array of integers.
     * @return The median of the array.
     */
	public double findMedian(int[] arr) {

		Arrays.sort(arr);
		double mid;
		if (arr.length % 2 == 0) {
			int mid1 = arr.length / 2;
			int mid2 = (arr.length / 2) + 1;
			mid = ((arr[mid1] + arr[mid2]) / 2.0);
			return mid;
		}
		return arr[arr.length / 2];
	}

	// Mode? ~> Is the number that occurs the most, but it is unique, meaning there
	// can only be one number be mode
	// This method searches through an array and find the mode if it exists.

	/**
	 * Method to find the mode of an array of integers.
	 * @param arr The array of integers.
	 * @return The mode of the array. If there is no unique mode, returns -1.
	 */
	public int findMode(int[] arr) {
		ArrayList<StatLibrary> counter = new ArrayList<>();
		// Count the occurrences of each element
		for (int i = 0; i < arr.length; i++) {
			int tempHolder = arr[i];
			boolean found = false;
			for (StatLibrary ele : counter) { 
				if (ele.getValue() == tempHolder) {// If the value is found, increase the count
					ele.increaseCount();
					found = true;
					break;
				}
			}

			if (!found) {
				counter.add(new StatLibrary(tempHolder, 1));
			}
		}

		// This little code determine whether there are multilple "mode"

		int maxCount = 0;
		int mode = 0;
		boolean multipleMode = false;
		// Find the element with the highest count
		for (StatLibrary ele : counter) {
			if (ele.getCount() > maxCount) {
				maxCount = ele.getCount();
				mode = ele.getValue();
				multipleMode = false;
			} else if (ele.getCount() == maxCount) {
				multipleMode = true;
			}
		}
		// If there are multiple modes, return -1
		if (multipleMode) {
			return -1;
		} else
			return mode;
	}

	/**
     * Getter method to retrieve the count of occurrences.
     * @return The count of occurrences.
     */
	public int getCount() {
		return count;
	}
	/**
     * Getter method to retrieve the value.
     * @return The value.
     */
	public int getValue() {
		return value;
	}

	/**
     * Method to increase the count of occurrences by 1.
     */
	public void increaseCount() {
		count++;
	}

	// Factorial method for BigInteger
	/**
     * Method to calculate the factorial of a BigInteger.
     * @param n The BigInteger to calculate the factorial for.
     * @return The factorial of the BigInteger.
     */
	public BigInteger factorial(BigInteger n) {
		if (n.compareTo(BigInteger.ZERO) < 0) {
			throw new IllegalArgumentException("Factorial is not defined for negative value");
		}
		BigInteger sum = BigInteger.ONE;
		for (BigInteger i = BigInteger.valueOf(2); i.compareTo(n) <= 0; i.add(BigInteger.ONE)) {
			sum = sum.multiply(i);
		}
		return sum;
	}

	// This one is for long
	/**
     * Method to calculate the factorial of a long.
     * @param n The long to calculate the factorial for.
     * @return The factorial of the long.
     */
	public long factorial(long n) {
		if (n == 0) {
			throw new IllegalArgumentException("Factorial is ont defined for negative value");
		}
		long sum = 1;
		for (int i = 2; i <= n; i++) {
			sum *= i;
		}
		return sum;
	}

	// This method combine the two arrays with values from both arrays.
	/**
     * Method to perform the union of two sets.
     * @param S The first set.
     * @param A The second set.
     * @return An ArrayList containing the union of the two sets.
     */
	public ArrayList unionOf(int[] S, int[] A) {
		Arrays.sort(S);
		Arrays.sort(A);
		ArrayList<Integer> result = new ArrayList<>();

		for (int ele : S) {
			if (!result.contains(ele)) {
				result.add(ele);
			}
		}

		for (int ele : A) {
			if (!result.contains(ele)) {
				result.add(ele);
			}
		}
		// System.out.println(result);
		return result;
	}

		/**
		 * Method to perform the intersection of two sets.
		 * @param S The first set.
		 * @param A The second set.
		 * @return An ArrayList containing the intersection of the two sets.
		 */
		public ArrayList intersectionOf(int[] S, int[] A) {
		ArrayList<Integer> result = new ArrayList<>();
		Arrays.sort(S);
		Arrays.sort(A);
		for (int ele : S) {
			for (int val : A) {
				if (ele == val) {
					result.add(ele);
				}
			}
		}
		// System.out.println(result);
		return result;
	}

	/**
     * Method to find the complement of a subset in the universal set.
     * @param S The universal set.
     * @param A The subset.
     * @return An ArrayList containing the complement of the subset in the universal set.
     */
	public ArrayList complement(int[] S, int[] A) {
		Arrays.sort(S);
		Arrays.sort(A);
		ArrayList<Integer> result = new ArrayList<>();
		List<Integer> set4 = Arrays.asList(Arrays.stream(A).boxed().toArray(Integer[]::new));

		for (int ele : S) {
			if (!set4.contains(ele)) {
				if (!result.contains(ele)) {
					result.add(ele);
				}
			}

		}
		return result;
	}

	/**
     * Method to find the sample variance of an array of integers.
     * @param data The array of integers.
     * @return The sample variance of the array.
     */

	public double findSampleVariance(int[] data) {
		double mean = findMean(data);
		double sum = 0;
		for (int c : data) {
			double difference = c - mean;
			sum += Math.pow(difference, 2);
		}
		int n = data.length;
		return (sum / (n - 1));
	}

	/**
     * Method to find the standard deviation of an array of integers.
     * @param data The array of integers.
     * @return The standard deviation of the array.
     */
	public double FindStandardDeviation(int[] data) {
		double s = findSampleVariance(data);
		return Math.sqrt(s);
	}

	/**
     * Method to find the product of two numbers.
     * @param m The first number.
     * @param n The second number.
     * @return The product of the two numbers.
     */
	public double findMNRule(double m, double n) {
		return m * n;
	}

	/**
     * Method to find the permutation of n and r.
     * @param n The total number of items.
     * @param r The number of items to choose.
     * @return The permutation of n and r.
     */
	public double findPermutation(int n, int r) {
		return factorial(n) / (double) factorial(n - r);
	}

	/**
	 * Method to calculate combinations (nCr).
	 * @param n The total number of items.
	 * @param r The number of items to choose.
	 * @return The number of combinations (nCr).
	 */
	public double findCombination(int n, int r) {
		double temp = findPermutation(n, r);
		return temp / factorial(r);
	}

	/**
     * Method to check if two events are dependent.
     * @param A The probability of event A.
     * @param B The probability of event B.
     * @param condition The conditional probability.
     * @return True if the events are dependent, false otherwise.
     */
	public boolean isDependent(double A, double B, double condition) {
		if (A == condition || B == condition || (A * B) == condition) {
			return false;
		}
		return true;
	}

	/**
     * Method to find the conditional probability.
     * @param A The probability of event A.
     * @param B The probability of event B.
     * @param pGiven The conditional probability.
     * @return The conditional probability.
     */
	public double findConditional(double A, double B, double pGiven) {
		if (isDependent(A, B, pGiven)) {
			return (pGiven * A) / B;
		} else {
			return A;
		}
	}

	/**
     * Method to find the multiplicative law of probability.
     * @param p1 The first probability.
     * @param p2 The second probability.
     * @return The probability of both events occurring.
     */
	public double findMultiLaw(double p1, double p2) {
		return p1 * p2;
	}

	/**
     * Method to find the additive law of probability.
     * @param A The probability of event A.
     * @param B The probability of event B.
     * @param pAnd The probability of both events occurring.
     * @return The probability of either event occurring.
     */
	public double findAddLaw(double A, double B, double pAnd) {
		return A + B - pAnd;
	}

	/**
     * Method to find the additive law of probability for mutually exclusive events.
     * @param A The probability of event A.
     * @param B The probability of event B.
     * @return The probability of either event occurring.
     */
	public double findAddLaw(double A, double B) {
		return A + B;
	}

	/**
     * Method to find the complement of a probability.
     * @param B The probability of the event.
     * @return The complement of the probability.
     */
	public double findComplement(double B) {
		return 1 - B;
	}

	/**
     * Method to find the complement of a probability over multiple trials.
     * @param B The probability of the event.
     * @param n The number of trials.
     * @return The complement of the probability over multiple trials.
     */

	public double findComplement(double B, int n) {
		return (1 - Math.pow(B, n));
	}

	/**
     * Method to find the probability using Bayes' theorem.
     * @param pGiven The array of conditional probabilities.
     * @param B The array of probabilities of the events.
     * @param n The index of the event to find the probability for.
     * @return The probability of the event using Bayes' theorem.
     */
	public double findBayes(double[] pGiven, double[] B, int n) {
		double sum = 0;
		for (int i = 0; i < pGiven.length; i++) {
			sum += pGiven[i] * B[i];
		}
		double num = pGiven[n - 1] * B[n - 1];
		return num / sum;
	}

	/**
     * Method to find the expected value of a random variable.
     * @param y The array of values of the random variable.
     * @param pY The probability of the random variable.
     * @return The expected value of the random variable.
     */
	public double findExpectedRandom(double[] y, double pY) {
		double sum = 0;
		for (int i = 0; i < y.length; i++) {
			sum += y[i] * pY;
		}
		return sum;
	}

	/**
     * Method to find the variance of a random variable.
     * @param Expected The expected value of the random variable.
     * @param y The array of values of the random variable.
     * @param pY The probability of the random variable.
     * @return The variance of the random variable.
     */
	public double findVarianceRandom(double Expected, double[] y, double pY) {
		double sum = 0;
		for (int i = 0; i < y.length; i++) {
			sum += Math.pow((y[i] - Expected), 2) * pY;
		}
		return sum;
	}

	/**
     * Method to find the binomial distribution.
     * @param n The number of trials.
     * @param y The number of successes.
     * @param p The probability of success on a single trial.
     * @return The binomial distribution.
     */
	public double findBinomialDistr(int n, int y, double p) {
		double combination = findCombination(n, y);
		return combination * Math.pow(p, y) * Math.pow(1 - p, n - y);
	}

	/**
     * Method to find the expected value of the binomial distribution.
     * @param n The number of trials.
     * @param p The probability of success on a single trial.
     * @return The expected value of the binomial distribution.
     */
	public double findExpectedBinomial(int n, double p) {
		return n * p;
	}

	/**
     * Method to find the variance of the binomial distribution.
     * @param n The number of trials.
     * @param p The probability of success on a single trial.
     * @return The variance of the binomial distribution.
     */
	public double findVarianceBinomial(int n, double p) {
		return n * p * (1 - p);
	}

	/**
     * Method to find the geometric distribution.
     * @param p The probability of success on a single trial.
     * @param n The number of trials.
     * @return The geometric distribution.
     */
	public double findGeometricDistr(double p, int n) {
		return Math.pow(1 - p, n - 1) * p;
	}

	/**
     * Method to find the expected value of the geometric distribution.
     * @param p The probability of success on a single trial.
     * @return The expected value of the geometric distribution.
     */
	public double findExpectedGeoDistri(double p) {
		return 1 / p;
	}

	/**
     * Method to find the variance of the geometric distribution.
     * @param p The probability of success on a single trial.
     * @return The variance of the geometric distribution.
     */
	public double findVarianceGeoDistr(double p) {
		return (1 - p) / Math.pow(p, 2);
	}

	/**
	* Method to find the probability of success on or before a given trial in the geometric distribution.
	* @param p The probability of success on a single trial.
	* @param n The number of trials.
	* @return The probability of success on or before the given trial.
	*/
	public double findGeoONorBefore(double p, int n) {
		return 1 - Math.pow(1 - p, n);
	}

	/**
     * Method to find the probability of success before a given trial in the geometric distribution.
     * @param p The probability of success on a single trial.
     * @param n The number of trials.
     * @return The probability of success before the given trial.
     */
	public double findGeoBefore(double p, int n) {
		return 1 - Math.pow(1 - p, n - 1);
	}

	/**
	 * Method to find the probability of success on or after a given trial in the geometric distribution.
	 * @param p The probability of success on a single trial.
	 * @param n The number of trials.
	 * @return The probability of success on or after the given trial.
	 */
	public double findGeoONorAfter(double p, int n) {
		return Math.pow(1 - p, n - 1);
	}

	/**
	 * Method to find the probability of success after a given trial in the geometric distribution.
	 * @param p The probability of success on a single trial.
	 * @param n The number of trials.
	 * @return The probability of success after the given trial.
	 */
	public double findGeoAfter(double p, int n) {
		return Math.pow(1 - p, n);
	}
	// End of shortcut methods

	/**
     * Method to find the hypergeometric distribution.
     * @param r The number of successes in the population.
     * @param y The number of successes in the sample.
     * @param N The population size.
     * @param n The sample size.
     * @return The hypergeometric distribution.
     */	
	public double findHGD(int r, int y, int N, int n) {
		double comb1 = findCombination(r, y);
		double comb2 = findCombination(N - r, n - y);
		double denomi = findCombination(N, n);
		return (comb1 * comb2) / denomi;
	}

	/**
     * Method to find the expected value of the hypergeometric distribution.
     * @param n The sample size.
     * @param r The number of successes in the population.
     * @param N The population size.
     * @return The expected value of the hypergeometric distribution.
     */
	public double findExpectedHGD(int n, int r, int N) {
		return (n * r) / (double) N;
	}

	/**
     * Method to find the variance of the hypergeometric distribution.
     * @param n The sample size.
     * @param r The number of successes in the population.
     * @param N The population size.
     * @return The variance of the hypergeometric distribution.
     */
	public double findVarianceHGD(int n, int r, double N) {
		return n * (r / N) * ((N - r) / N) * ((N - n) / (N - 1));
	}

	/**
 * Method to calculate the negative binomial distribution.
 * @param y The number of trials.
 * @param r The number of successes.
 * @param p The probability of success on a single trial.
 * @return The negative binomial distribution.
 */
	public double findNBD(int y, int r, double p) {
		double comb = findCombination(y - 1, r - 1);
		return comb * Math.pow(p, r) * Math.pow(1 - p, y - r);
	}

	/**
 * Method to find the expected value of the negative binomial distribution.
 * @param r The number of successes.
 * @param p The probability of success on a single trial.
 * @return The expected value of the negative binomial distribution.
 */
	public double findExpectedNBD(int r, double p) {
		return r / p;
	}

	/**
	 * Method to find the variance of the negative binomial distribution.
	 * @param r The number of successes.
	 * @param p The probability of success on a single trial.
	 * @return The variance of the negative binomial distribution.
	 */
	public double findVarianceNBD(int r, double p) {
		return (r * (1 - p)) / Math.pow(p, 2);
	}

	/**
	 * Method to print the elements of an array.
	 * This is used for displaying the mean, median, and mode.
	 * @param pB The array to be printed.
	 */
	public static void printArray(double[] pB) {
		for (double c : pB) {
			System.out.print(c + ", ");
		}
		System.out.println();
	}

}

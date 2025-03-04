import java.util.ArrayList;

/**
 * The StatLibraryTester class tests various methods from the StatLibrary class.
 */
public class StatLibraryTester {
    public static void main(String[] args) {
        
        // Create an instance of StatLibrary
        StatLibrary test = new StatLibrary();
        
        // Test the MN rule method
        System.out.println("MN rule test");
        System.out.println("Given m = 3, n = 5");
        System.out.println("Expected: 15");
        System.out.println(test.findMNRule(3, 5));
        
        // Test the mean calculation
        int[] sampleData = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println("\nMean test");
        System.out.println("Given data: {1, 2, 3, 4, 5, 6, 7, 8, 9}");
        System.out.println("Expected: 5.0");
        System.out.println(test.findMean(sampleData));
        
        // Test the median calculation
        System.out.println("\nMedian test");
        System.out.println("Given data: {1, 2, 3, 4, 5, 6, 7, 8, 9}");
        System.out.println("Expected: 5.0");
        System.out.println(test.findMedian(sampleData));
        
        // Test the mode calculation
        int[] sampleDataWithMode = {1, 2, 2, 3, 4, 5, 5, 5, 6, 7, 8, 9};
        System.out.println("\nMode test");
        System.out.println("Given data: {1, 2, 2, 3, 4, 5, 5, 5, 6, 7, 8, 9}");
        System.out.println("Expected: 5");
        System.out.println(test.findMode(sampleDataWithMode));
        
        // Test the factorial calculation
        System.out.println("\nFactorial test");
        System.out.println("Given n = 5");
        System.out.println("Expected: 120");
        System.out.println(test.factorial(5));
        
        // Test the union of two sets
        int[] setA = {1, 2, 3, 4, 5};
        int[] setB = {4, 5, 6, 7, 8};
        System.out.println("\nUnion test");
        System.out.println("Given sets: {1, 2, 3, 4, 5} and {4, 5, 6, 7, 8}");
        System.out.println("Expected: {1, 2, 3, 4, 5, 6, 7, 8}");
        System.out.println(test.unionOf(setA, setB));
        
        // Test the intersection of two sets
        System.out.println("\nIntersection test");
        System.out.println("Given sets: {1, 2, 3, 4, 5} and {4, 5, 6, 7, 8}");
        System.out.println("Expected: {4, 5}");
        System.out.println(test.intersectionOf(setA, setB));
        
        // Test the complement of a set
        int[] universalSet = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] subset = {2, 4, 6, 8, 10};
        System.out.println("\nComplement test");
        System.out.println("Given universal set: {1, 2, 3, 4, 5, 6, 7, 8, 9, 10} and subset: {2, 4, 6, 8, 10}");
        System.out.println("Expected: {1, 3, 5, 7, 9}");
        System.out.println(test.complement(universalSet, subset));
        
        // Test the negative binomial distribution
        System.out.println("\nNegative Binomial Distribution test");
        System.out.println("Given y = 10, r = 3, p = 0.5");
        System.out.println("Expected: 0.1171875");
        System.out.println(test.findNBD(10, 3, 0.5));
        
        // Test the expected value of the negative binomial distribution
        System.out.println("\nExpected Value of NBD test");
        System.out.println("Given r = 3, p = 0.5");
        System.out.println("Expected: 6.0");
        System.out.println(test.findExpectedNBD(3, 0.5));
        
        // Test the variance of the negative binomial distribution
        System.out.println("\nVariance of NBD test");
        System.out.println("Given r = 3, p = 0.5");
        System.out.println("Expected: 6.0");
        System.out.println(test.findVarianceNBD(3, 0.5));
        
        // Test the printArray method
        double[] arrayToPrint = {1.0, 2.0, 3.0, 4.0, 5.0};
        System.out.println("\nPrint Array test");
        System.out.println("Given array: {1.0, 2.0, 3.0, 4.0, 5.0}");
        System.out.print("Expected: 1.0, 2.0, 3.0, 4.0, 5.0, ");
        test.printArray(arrayToPrint);
    }
}
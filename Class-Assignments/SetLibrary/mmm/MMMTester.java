import java.util.ArrayList;

public class MMMTester {
    public static void main(String[] args) {
        
        // Create an instance of Mean_Median_Mode
        Mean_Median_Mode test = new Mean_Median_Mode();
        
        // Sample data for testing mean calculation
        int[] userSample = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        
        // Calculate and print the mean of userSample
        double storeResults = test.findMean(userSample);
        System.out.println("The average is: " + storeResults);
        
        // Sample data for testing median and mode calculation
        int[] userSample2 = {1, 2, 3, 3, 6, 7, 5, 9, 9};
        
        // Calculate and print the median of userSample2
        double medianResult = test.findMedian(userSample2);
        System.out.println("The median is: " + medianResult);
        
        // Calculate and print the mode of userSample2
        int modeResult = test.findMode(userSample2);
        System.out.println("The mode of test 1 is: " + modeResult);
        
        // Another sample data for testing mode calculation
        int[] userSample3 = {1, 2, 3, 8, 6, 7, 5, 9, 9, 9, 4, 5, 6};
        
        // Calculate and print the mode of userSample3
        int modeResult2 = test.findMode(userSample3);
        System.out.println("The mode of test 2 is: " + modeResult2);
    }
}
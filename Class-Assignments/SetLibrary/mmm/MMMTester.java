
import java.util.ArrayList;

/**
 * Write a description of class TestExample here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MMMTester
{
    public static void main(String[] args){
        
        Mean_Median_Mode test = new Mean_Median_Mode();
        
        int[] userSample = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        
        double storeResults = test.findMean(userSample);
        
        System.out.println("The average is: " + storeResults);
        
        int[] userSample2 = {1, 2, 3, 3, 6, 7, 5, 9, 9};
        
        
        
        double medianResult = test.findMedian(userSample2);
        System.out.println("The median is: " + medianResult);
        
        int modeResult = test.findMode(userSample2);
        System.out.println("The mode of test 1 is: " + modeResult);
        
        int[] userSample3 = {1, 2, 3, 8, 6, 7, 5, 9, 9, 9, 4, 5,6};
        int modeResult2 = test.findMode(userSample3);
        System.out.println("The mode of test 2 is: " + modeResult2);
    }
}

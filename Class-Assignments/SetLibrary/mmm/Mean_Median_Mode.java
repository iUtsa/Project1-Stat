import java.util.Arrays;
import java.util.ArrayList;

public class Mean_Median_Mode {
    // Something that's common in all stats classes that you take is learning about
    // Central Tendencies

    // Default constructor
    public Mean_Median_Mode() {
    }

    private int value; // The value of the element
    private int count; // The count of occurrences of the element

    // Constructor to initialize value and count
    public Mean_Median_Mode(int num, int count) {
        this.value = num;
        this.count = count;
    }

    /**
     * Method to find the mean of an array of integers.
     * @param input The array of integers.
     * @return The mean of the array.
     */
    public static double findMean(int[] input) {
        double sum = 0;
        // Calculate the sum of all elements
        for (int i = 0; i < input.length; i++) {
            sum = input[i] + sum;
        }
        // Calculate and return the mean
        return sum / input.length;
    }

    /**
     * Method to find the median of an array of integers.
     * @param arr The array of integers.
     * @return The median of the array.
     */
    public static double findMedian(int[] arr) {
        Arrays.sort(arr); // Sort the array
        if (arr.length % 2 == 0) {
            // If the array length is even, return the average of the two middle elements
            int mid1 = arr.length / 2;
            int mid2 = mid1 - 1;
            return (arr[mid1] + arr[mid2]) / 2.0;
        } else {
            // If the array length is odd, return the middle element
            return arr[arr.length / 2];
        }
    }

    /**
     * Method to find the mode of an array of integers.
     * @param arr The array of integers.
     * @return The mode of the array. If there is no unique mode, returns -1.
     */
    public static int findMode(int[] arr) {
        ArrayList<Mean_Median_Mode> counter = new ArrayList<>();
        // Count the occurrences of each element
        for (int i = 0; i < arr.length; i++) {
            int tempHolder = arr[i];
            boolean found = false;
            for (Mean_Median_Mode ele : counter) {
                if (ele.getValue() == tempHolder) {
                    ele.increaseCount();
                    found = true;
                    break;
                }
            }
            if (!found) {
                counter.add(new Mean_Median_Mode(tempHolder, 1));
            }
        }

        int maxCount = 0;
        int mode = 0;
        boolean multipleMode = false;
        // Find the element with the highest count
        for (Mean_Median_Mode ele : counter) {
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
        } else {
            return mode;
        }
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
}
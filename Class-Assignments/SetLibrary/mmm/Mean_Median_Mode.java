
//Let's off with a warmup :: Let's write a program that can find mean median and mode.
import java.util.Arrays;
import java.util.ArrayList;

public class Mean_Median_Mode
{
    //Something that's common in all stats classes that you take, is learning about
    //Central Tendancies

    public Mean_Median_Mode(){

    }

    private int value;
    private int count;
    public Mean_Median_Mode(int num, int count){
        this.value = num;
        this.count = count;
    }
    //Mean, Median, and Mode
    //The list must be in order, so order the luist before finding median. Should beable to call collections sort or arraylist sort of some kind
    //Mean? ~> It's sum of elements divided by count. Another word for it could be expected.
    public static double findMean(int[] input) { //2 options, using an aray or an array list.
        //Find the sum
        double sum = 0;
        for(int i = 0; i < input.length; i++){
            sum = input[i] + sum;
        }

        double result = sum / input.length;

        return result;
    }    

    //Mdedian? ~> Either the middle number if ood, or "average" of the two middlest numbers

    public static double findMedian(int[] arr){

        Arrays.sort(arr);
        double mid;
        if(arr.length%2 == 0){
            int mid1 = arr.length/2;
            int mid2 = (arr.length/2) + 1;
            mid = ((arr[mid1] + arr[mid2])/2.0);
            return mid;
        }
        return arr[arr.length/2];
    }

    //Mode? ~> Is the number that occurs the most, but it is unique, meaning there can only be one number be mode
    public static int findMode(int[] arr){
        ArrayList<Mean_Median_Mode> counter = new ArrayList<>();
        for(int i = 0; i < arr.length; i++){
            int tempHolder = arr[i];
            boolean found = false;
            for(Mean_Median_Mode ele : counter){
                if(ele.getValue() == tempHolder){
                    ele.increaseCount();
                    found = true;
                    break;
                }
            }

            if(!found){
                counter.add(new Mean_Median_Mode(tempHolder, 1));
            }
        }

        int maxCount = 0;
        int mode = 0;
        boolean multipleMode = false;
        for(Mean_Median_Mode ele : counter){
            if(ele.getCount() > maxCount){
                maxCount = ele.getCount();
                mode = ele.getValue();
                multipleMode = false;
            } else if(ele.getCount() == maxCount){
                multipleMode = true;
            }
        }

        if(multipleMode){
            return -1;
        }
        else return mode;
    }

    public int getCount(){
        return count;
    }

    public int getValue(){
        return value;
    }

    public void increaseCount(){
        count++;
    }
}

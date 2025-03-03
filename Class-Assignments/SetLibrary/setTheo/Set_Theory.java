package setTheo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Write a description of class Set_Theory here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Set_Theory
{

    public Set_Theory(){

    }

    public ArrayList unionOf(int[] S, int[] A){
        Arrays.sort(S);
        Arrays.sort(A);
        ArrayList<Integer> result = new ArrayList<>();

        for(int ele : S){
            if(!result.contains(ele)){
                result.add(ele);
            }
        }

        for(int ele : A){
            if(!result.contains(ele)){
                result.add(ele);
            }
        }
        //System.out.println(result);
        return result;
    }

    public ArrayList intersectionOf(int[] S, int[] A){
        ArrayList<Integer> result = new ArrayList<>();
        Arrays.sort(S);
        Arrays.sort(A);
        for(int ele : S){
            for(int val : A){
                if(ele == val){
                    result.add(ele);
                }
            }
        }
        //System.out.println(result);
        return result;
    }

    public ArrayList complement(int [] S, int [] A){
        Arrays.sort(S);
        Arrays.sort(A);
        ArrayList<Integer> result = new ArrayList<>();
        List<Integer> set4 = Arrays.asList(Arrays.stream(A).boxed().toArray(Integer[]::new));
        
        for(int ele : S){
            if(!set4.contains(ele)){
                if(!result.contains(ele)){
                    result.add(ele);
                }
            }

        }
        return result;
    }

    public int[] ArrayListToArrays(ArrayList<Integer> arr){
        int[] alist = new int[arr.size()];
        int index = 0;
        for(int ele : arr){
            alist[index++] = ele;
        }
        return alist;
    }

    public ArrayList ArraysToArrayList(int[] arr){
        ArrayList<Integer> arrList = new ArrayList<Integer>();
        for(int ele : arr){
            arrList.add(ele);
        }
        return arrList;
    }
}

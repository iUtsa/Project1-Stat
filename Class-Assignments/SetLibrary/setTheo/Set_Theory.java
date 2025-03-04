import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Set_Theory class provides methods to perform set operations such as union, intersection, and complement...
 */
public class Set_Theory {

    /**
     * Default constructor for the Set_Theory class.
     */
    public Set_Theory() {
    }

    /**
     * Method to perform the union of two sets.
     * @param S The first set.
     * @param A The second set.
     * @return An ArrayList containing the union of the two sets.
     */
    public ArrayList<Integer> unionOf(int[] S, int[] A) {
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
        return result;
    }

    /**
     * Method to perform the intersection of two sets.
     * @param S The first set.
     * @param A The second set.
     * @return An ArrayList containing the intersection of the two sets.
     */
    public ArrayList<Integer> intersectionOf(int[] S, int[] A) {
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
        return result;
    }

    /**
     * Method to find the complement of a subset in the universal set.
     * @param S The universal set.
     * @param A The subset.
     * @return An ArrayList containing the complement of the subset in the universal set.
     */
    public ArrayList<Integer> complement(int[] S, int[] A) {
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
     * Method to convert an ArrayList to an array.
     * @param arr The ArrayList to convert.
     * @return An array containing the elements of the ArrayList.
     */
    public int[] ArrayListToArrays(ArrayList<Integer> arr) {
        int[] alist = new int[arr.size()];
        int index = 0;
        for (int ele : arr) {
            alist[index++] = ele;
        }
        return alist;
    }

    /**
     * Method to convert an array to an ArrayList.
     * @param arr The array to convert.
     * @return An ArrayList containing the elements of the array.
     */
    public ArrayList<Integer> ArraysToArrayList(int[] arr) {
        ArrayList<Integer> arrList = new ArrayList<>();
        for (int ele : arr) {
            arrList.add(ele);
        }
        return arrList;
    }
}
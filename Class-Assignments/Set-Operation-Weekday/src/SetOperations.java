package src;
import java.util.ArrayList;

public class SetOperations {

    // Method to perform union of two ArrayLists
    public static ArrayList<String> union(ArrayList<String> set1, ArrayList<String> set2) {
        ArrayList<String> result = new ArrayList<>(set1);
        for (String item : set2) {
            if (!result.contains(item)) {
                result.add(item);
            }
        }
        return result;
    }

    // Method to perform intersection of two ArrayLists
    public static ArrayList<String> intersection(ArrayList<String> set1, ArrayList<String> set2) {
        ArrayList<String> result = new ArrayList<>();
        for (String item : set1) {
            if (set2.contains(item)) {
                result.add(item);
            }
        }
        return result;
    }

    // Method to find the complement of a subset A in the universal set
    public static ArrayList<String> complement(ArrayList<String> universalSet, ArrayList<String> subset) {
        ArrayList<String> result = new ArrayList<>(universalSet);
        result.removeAll(subset);
        return result;
    }
}
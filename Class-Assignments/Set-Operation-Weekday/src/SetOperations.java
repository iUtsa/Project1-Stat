import java.util.ArrayList;

public class SetOperations {

    /**
     * Method to perform union of two ArrayLists.
     * @param set1 The first set.
     * @param set2 The second set.
     * @return A new ArrayList containing the union of set1 and set2.
     */
    public static ArrayList<String> union(ArrayList<String> set1, ArrayList<String> set2) {
        ArrayList<String> result = new ArrayList<>(set1);
        for (String item : set2) {
            if (!result.contains(item)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Method to perform intersection of two ArrayLists.
     * @param set1 The first set.
     * @param set2 The second set.
     * @return A new ArrayList containing the intersection of set1 and set2.
     */
    public static ArrayList<String> intersection(ArrayList<String> set1, ArrayList<String> set2) {
        ArrayList<String> result = new ArrayList<>();
        for (String item : set1) {
            if (set2.contains(item)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Method to find the complement of a subset in the universal set.
     * @param universalSet The universal set.
     * @param subset The subset.
     * @return A new ArrayList containing the complement of the subset in the universal set.
     */
    public static ArrayList<String> complement(ArrayList<String> universalSet, ArrayList<String> subset) {
        ArrayList<String> result = new ArrayList<>(universalSet);
        result.removeAll(subset);
        return result;
    }
}
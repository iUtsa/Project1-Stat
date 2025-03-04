import java.util.ArrayList;

class SetOperationsTester {
    public static void main(String[] args) {
        ArrayList<String> universalSet = new ArrayList<>();
        
        // Add days of the week to the universal set
        universalSet.add("Monday");
        universalSet.add("Tuesday");
        universalSet.add("Wednesday");
        universalSet.add("Thursday");
        universalSet.add("FRIDAY!!");
        universalSet.add("Saturday");
        universalSet.add("Sunday");

        ArrayList<String> subsetA = new ArrayList<>();
        // Add specific days to subsetA
        subsetA.add("Monday");
        subsetA.add("Wednesday");
        subsetA.add("FRIDAY!!");

        ArrayList<String> subsetB = new ArrayList<>();
        // Add specific days to subsetB
        subsetB.add("Wednesday");
        subsetB.add("Thursday");
        subsetB.add("Saturday");

        // Perform and print the union of subsetA and subsetB
        System.out.println("Union: " + SetOperations.union(subsetA, subsetB));
        // Perform and print the intersection of subsetA and subsetB
        System.out.println("Intersection: " + SetOperations.intersection(subsetA, subsetB));
        // Perform and print the complement of subsetA in the universal set
        System.out.println("Complement of subsetA: " + SetOperations.complement(universalSet, subsetA));
    }
}
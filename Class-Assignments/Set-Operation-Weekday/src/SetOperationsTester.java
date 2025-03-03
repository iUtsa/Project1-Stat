package src;
import java.util.ArrayList;
class SetOperationsTester {
    public static void main(String[] args) {
        ArrayList<String> universalSet = new ArrayList<>();
        //tests
        universalSet.add("Monday");
        universalSet.add("Tuesday");
        universalSet.add("Wednesday");
        universalSet.add("Thursday");
        universalSet.add("FRIDAY!!");
        universalSet.add("Saturday");
        universalSet.add("Sunday");

        ArrayList<String> subsetA = new ArrayList<>();
        subsetA.add("Monday");
        subsetA.add("Wednesday");
        subsetA.add("FRIDAY!!");

        ArrayList<String> subsetB = new ArrayList<>();
        subsetB.add("Wednesday");
        subsetB.add("Thursday");
        subsetB.add("Saturday");

        System.out.println("Union: " + SetOperations.union(subsetA, subsetB));
        System.out.println("Intersection: " + SetOperations.intersection(subsetA, subsetB));
        System.out.println("Complement of subsetA: " + SetOperations.complement(universalSet, subsetA));
    }
}

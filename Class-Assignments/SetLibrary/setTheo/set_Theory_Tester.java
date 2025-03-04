/**
 * The set_Theory_Tester class tests the set operations such as union, intersection, and complement.
 */
public class set_Theory_Tester {
    public static void main(String[] args) {
        
        // Create an instance of Set_Theory
        Set_Theory set1 = new Set_Theory();
        
        // Define sets for testing
        int[] set = {1, 2, 3, 4, 5, 6, 7, 8, 9, 12, 32, 56, 23, 12, 99};
        int[] userSample = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] userSample2 = {10, 8, 11, 13, 16, 17, 25, 19, 29};
        
        // Perform and print the union of userSample and userSample2
        System.out.println("Union: " + set1.unionOf(userSample, userSample2));
        
        // Perform and print the intersection of userSample and userSample2
        System.out.println("Intersection: " + set1.intersectionOf(userSample, userSample2));
        
        // Perform and print the complement of userSample in the universal set
        System.out.println("Complement: " + set1.complement(set, userSample));
    }
}
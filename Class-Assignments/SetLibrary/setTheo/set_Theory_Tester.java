package setTheo;
/**
 * Write a description of class set_Theory_Tester here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class set_Theory_Tester
{
    public static void main(String[] args){
        
        Set_Theory set1 = new Set_Theory();
        
        int[] set = {1, 2, 3, 4, 5, 6, 7, 8, 9, 12, 32, 56, 23, 12, 99};
        int[] userSample = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] userSample2 = {10, 8, 11, 13, 16, 17, 25, 19, 29};
        
        System.out.println(set1.unionOf(userSample, userSample2));
        
        System.out.println(set1.intersectionOf(userSample, userSample2));
        
        System.out.println(set1.complement(set, userSample));
    }
}

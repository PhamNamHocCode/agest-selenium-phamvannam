import java.lang.reflect.Array;
import java.util.Objects;

public class ArrayListExercise {
    public int findMissingNumber(int[] arr){
        int n = arr.length + 1;
        long total = (long) n *(n+1)/2;
        long realTotal = 0;
        for (int num: arr){
            realTotal += num;
        }
        return (int)(total - realTotal);
    }

    public static boolean isPrime(int n){
        if (n< 2){
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++){
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void sumOfTwoPrime(int n){
        boolean isFound = false;
        System.out.println("Check if any number given as input is the sum of 2 prime numbers: ");
        for (int i = 2; i <= n / 2; i++) {
            if (isPrime(i)) {
                int num = n - i;
                if (isPrime(num)) {
                    System.out.println(n + " = " + num + " + " + i);
                    isFound = true;
                }
            }
        }

        if (!isFound) {
            System.out.println("Cannot find the result for "+n);
        }
    }
    public static boolean isInTheList(String targetString, String [] arr ){
        System.out.println("Check if the target string is present in the list: ");
        for (String str: arr){
            if (Objects.equals(targetString, str)){
                return true;
            }
        }

        return false;
    }

}
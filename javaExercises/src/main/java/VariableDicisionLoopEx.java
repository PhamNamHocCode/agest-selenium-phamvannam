public class VariableDicisionLoopEx {
    public static void convertAndInverse(int n){
        System.out.print("Convert from a negative number to an inverse positive number: " + n + "--> ");
        int num = Math.abs(n);
        int result = 0;

        while(num != 0){
            int tmp = num % 10;
            result = result * 10 + tmp;
            num /= 10;
        }

        System.out.print(result + "\n");
    }

    public static void findFactorial(int n){
        System.out.print("Find the factorial of a given number: " + n + "--> ");
        long result = 1;
        for (int i = 1; i<=n; i++){
            result *= i;
        }
        System.out.print(result + "\n");
    }
    // 1|2|34567|8|9
    public static String cutString(String N, int start, int end){
        String result = "";
        for (int i = start; i < end; i ++){
            result += N.charAt(i);
        }
        return result;
    }

    public static boolean isPalindrome(String N){
        if (N.length() <= 1) return true;

        if (N.charAt(0) != N.charAt(N.length() - 1)){
            return false;
        }
        String subString = cutString(N, 1, N.length() - 1);
        return isPalindrome(subString);
    }

    public static String reverseString(String S){
        String result = "";
        for (int i = S.length() - 1; i >= 0; i--){
            result += S.charAt(i);
        }
        return  result;
    }
}

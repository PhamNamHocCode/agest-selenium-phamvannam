public class Main {
    public static void main(String[] args){
        System.out.println("______________________________________________\nArray and List");
        int [] inputArr = { };
        System.out.println("The missing number in the array is: " + new ArrayListExercise().findMissingNumber(inputArr));

        ArrayListExercise.sumOfTwoPrime(2);

        String[] strArr = {"apple", "banana", "cherry"};
        String targetStr = "banana";
        System.out.println(ArrayListExercise.isInTheList(targetStr, strArr));

        System.out.println("______________________________________________\nVariable, Dicision and Loop");
        VariableDicisionLoopEx.convertAndInverse((int) -1E9);

        VariableDicisionLoopEx.findFactorial(10);

        System.out.println("Check if a given string is palindrome using recursion {ABCDCBA}: -->"+ VariableDicisionLoopEx.isPalindrome("ABCDCBA"));

        System.out.println("Reverse a string {Welcome to Agest} -->: " + VariableDicisionLoopEx.reverseString("Welcome to Agest"));
    }
}

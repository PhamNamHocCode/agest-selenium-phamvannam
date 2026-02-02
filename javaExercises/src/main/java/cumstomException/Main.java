package cumstomException;

public class Main {
    public static void main(String[] args) throws NoVowelsException, CustomException {
//        throw new CustomException("This is my custom Exception");
        String s1 = "Welcome to Agest";
        String s2 = "Myth.";
        if (NoVowelsException.isVowels(s2)){
            throw new NoVowelsException("String " + s2 + " contains vowels");
        }
        else{
            throw new NoVowelsException(("Error: String "+ s2 + "does not contain any vowels"));
        }
    }
}

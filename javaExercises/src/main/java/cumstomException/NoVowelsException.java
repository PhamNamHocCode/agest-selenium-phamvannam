package cumstomException;

public class NoVowelsException extends Exception {
    public NoVowelsException(String message) {
        super(message);
    }
    public static boolean isVowels(String S){
        String lowerInput = S.toLowerCase();
        char character;
        for (int i = 0; i < lowerInput.length(); i++) {
            character = lowerInput.charAt(i);
            if (character == 'u' || character == 'e' || character == 'o' || character == 'a' || character == 'i') {
                return true;
            }
        }
        return false;
    }
}

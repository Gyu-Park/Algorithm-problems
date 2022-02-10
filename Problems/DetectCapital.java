/**
 * We define the usage of capitals in a word to be right when one of the following cases holds:
 * 
 * All letters in this word are capitals, like "USA".
 * All letters in this word are not capitals, like "leetcode".
 * Only the first letter in this word is capital, like "Google".
 * 
 * Given a string word, return true if the usage of capitals in it is right.
 */
package Problems;

public class DetectCapital {
    public static boolean detectCapitalUse(String word) {
        if (word.length() == 1)
            return true;

        int max, min;
        if (word.charAt(0) >= 97) {
            max = 122;
            min = 97;
        } else {
            max = word.charAt(1) < 97 ? 90 : 122;
            min = word.charAt(1) < 97 ? 65 : 97;
        }
        for (int i = 1; i < word.length(); i++) {
            if (word.charAt(i) < min || word.charAt(i) > max)
                return false;
        }
        return true;
    }

    // another solution
    // check if each letter is lowercase or uppercase
    public static boolean anotherDetectCapitalUse(String word) {
        if(word.length() < 2) return true;
        if(Character.isUpperCase(word.charAt(0)) && Character.isUpperCase(word.charAt(1))){
            for(int i = 2; i < word.length(); i++){
                if(Character.isLowerCase(word.charAt(i))) return false;
            }
        }
        else{
            for(int i = 1; i < word.length(); i++){
                if(Character.isUpperCase(word.charAt(i))) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String word = "FFFFFFFFFFFFFFFFFFFFf";
        System.out.println(detectCapitalUse(word));
    }
}

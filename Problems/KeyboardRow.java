/**
 * Given an array of strings words, 
 * return the words that can be typed using letters of 
 * the alphabet on only one row of American keyboard like the image below.
 * 
 * In the American keyboard:
 * 
 * the first row consists of the characters "qwertyuiop",
 * the second row consists of the characters "asdfghjkl", and
 * the third row consists of the characters "zxcvbnm".
 */
package Problems;

import java.util.*;

public class KeyboardRow {
    public static String[] findWords(String[] words) {
        int len = words.length;
        int count = 0;
        List<String> list = new ArrayList<>();
        while (count < len) {
            String word = words[count].toLowerCase();
            Set<Character> set = new HashSet<>();
            set = createSet(set, word.charAt(0));
            boolean isRowWord = true;
            for (int i = 0; i < word.length(); i++) {
                if (!set.contains(word.charAt(i))) {
                    isRowWord = false;
                    break;
                }
            }
            if (isRowWord) {
                list.add(words[count]);
            }
            count++;
        }
        String[] res = new String[list.size()];
        return list.toArray(res);
    }

    private static Set<Character> createSet(Set<Character> set, char c) {
        if (c == 'z' || c == 'x' || c == 'c' || c == 'v' 
            || c == 'b' || c == 'n' || c == 'm') {
            set.add('z');
            set.add('x');
            set.add('c');
            set.add('v');
            set.add('b');
            set.add('n');
            set.add('m');
        } else if (c == 'a' || c == 's' || c == 'd' || c == 'f' 
            || c == 'g' || c == 'h' || c == 'j' || c == 'k' || c == 'l') {
            set.add('a');
            set.add('s');
            set.add('d');
            set.add('f');
            set.add('g');
            set.add('h');
            set.add('j');
            set.add('k');
            set.add('l');
        } else {
            set.add('q');
            set.add('w');
            set.add('e');
            set.add('r');
            set.add('t');
            set.add('y');
            set.add('u');
            set.add('i');
            set.add('o');
            set.add('p');
        }
        return set;
    }
    
    public static void main(String[] args) {
        String[] words = {"abdfs","cccd","a","qwwewm"};
        String[] res = findWords(words);
        for (String s : res) {
            System.out.print(s + " ");
        }
    }
}

/**
https://www.youtube.com/watch?v=PIeiiceWe_w
Youtube title: Google Coding Interview With A Facebook Software Engineer
Youtuber: Clément Mihailescu
**/
package Problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GoogleCodingInterviewProblem1 {

    private static Map<String, Integer> map;

    public static ArrayList<String> interview1(Integer number, String[] words) {

        createMap();

        Set<Integer> set = new HashSet<>();
        String stNumber = number.toString();
        for (int i = 0; i < stNumber.length(); i++) {
            if (!set.contains(Integer.parseInt(stNumber.substring(i, i + 1))))
                set.add(Integer.parseInt(stNumber.substring(i, i + 1)));
        }

        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                if (map.get(words[i].substring(j, j + 1)) != null) {
                    int num = map.get(words[i].substring(j, j + 1));
                    if (!set.contains(num)) {
                        break;
                    }
                    if (j == words[i].length() - 1) {
                        result.add(words[i]);
                    }
                } else {
                    break;
                }
            }
        }

        Collections.sort(result);
        return result;
    }

    private static void createMap() {
        map = new HashMap<>();
        String st = "abcdefghijklmnopqrstuvwxyz";
        int index = -1;
        for (int i = 2; i < 10; i++) {
            if (i == 7 || i == 9) {
                map.put(st.substring(++index, index + 1), i);
                map.put(st.substring(++index, index + 1), i);
                map.put(st.substring(++index, index + 1), i);
                map.put(st.substring(++index, index + 1), i);
            } else {
                map.put(st.substring(++index, index + 1), i);
                map.put(st.substring(++index, index + 1), i);
                map.put(st.substring(++index, index + 1), i);
            }
        }
    }

    public static void main(String[] args) {
        String[] words = new String[] { "foo", "bar", "baz", "foobar", "emo", "cap", "car", "cat" };
        int phoneNum = 3662277;
        ArrayList<String> result = interview1(phoneNum, words);
        System.out.println(result);
    }
}

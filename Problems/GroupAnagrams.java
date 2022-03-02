package Problems;

import java.util.*;

public class GroupAnagrams {
    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs.length == 0)
            return res;

        Map<String, Integer> map = new HashMap<>();
        int count = 0;
        for (String st : strs) {
            char[] charArray = st.toCharArray();
            Arrays.sort(charArray);
            String stt = "";
            for (int i = 0; i < charArray.length; i++) {
                stt += charArray[i];
            }
            if (map.containsKey(stt)) {
                res.get(map.get(stt)).add(st);
            } else {
                map.put(stt, count++);
                List<String> list = new ArrayList<>();
                list.add(st);
                res.add(list);
            }
        }
        return res;
    }

    // a better solution (Instead of sorting charArray,
    // store each char in a string in order and make a key String)
    public static List<List<String>> betterGroupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs.length == 0)
            return res;

        Map<String, Integer> map = new HashMap<>();
        int count = 0;
        for (String st : strs) {
            char[] charArray = new char[26];
            for (int i = 0; i < st.length(); i++) {
                charArray[st.charAt(i) - 'a']++;
            }
            String stt = new String(charArray);
            if (map.containsKey(stt)) {
                res.get(map.get(stt)).add(st);
            } else {
                map.put(stt, count++);
                List<String> list = new ArrayList<>();
                list.add(st);
                res.add(list);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String[] strs = { "eat", "tea", "tan", "ate", "nat", "bat" };
        List<List<String>> res = betterGroupAnagrams(strs);
        for (List<String> list : res) {
            for (String st : list) {
                System.out.print(st + " ");
            }
            System.out.println("");
        }
    }
}

/**
 * You are given an array of strings products and a string searchWord.
 * 
 * Design a system that suggests at most three product names from products 
 * after each character of searchWord is typed. 
 * Suggested products should have common prefix with searchWord. 
 * If there are more than three products with a common prefix return the three lexicographically minimums products.
 * 
 * Return a list of lists of the suggested products after each character of searchWord is typed.
 */
package Problems;

import java.util.*;

public class SearchSuggestionsSystem {
    // solution using arrays.sort()
    // time complexity: O(nk)
    // space complexity: O(1)
    public static List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> res = new ArrayList<>();
        Arrays.sort(products, (a, b) -> a.compareTo(b));
        for (int i = 1; i <= searchWord.length(); i++) {
            List<String> list = new ArrayList<>();
            for (int j = 0; j < products.length; j++) {
                if (list.size() == 3)
                    break;
                if (i > products[j].length())
                    continue;
                if(searchWord.substring(0, i).equals(products[j].substring(0, i)))
                    list.add(products[j]);
            }
            res.add(list);
        }
        return res;
    }

    // solution using sorting and binary search the prefix
    // time complexity: O(nlogn)
    // space complexity: O(n)
    public static List<List<String>> anotherSuggestedProducts(String[] products, String searchWord) {
        List<List<String>> res = new ArrayList<>();
        TreeMap<String, Integer> map = new TreeMap<>();
        Arrays.sort(products);
        List<String> productsList = Arrays.asList(products);

        for (int i = 0; i < products.length; i++) {
            map.put(products[i], i);
        }

        String key = "";
        for (char c : searchWord.toCharArray()) {
            key += c;
            // binary search using ceilingKey and floorKey
            String ceiling = map.ceilingKey(key); // String that is right next to (greater than) key char
            String floor = map.floorKey(key + "~");  // String that is right next to (smaller than) key + '~' char
            if (ceiling == null || floor == null) break;
            res.add(productsList.subList(map.get(ceiling), Math.min(map.get(ceiling) + 3, map.get(floor) + 1)));
        }

        while (res.size() < searchWord.length()) res.add(new ArrayList<>());
        return res;
    }
    
    public static void main(String[] args) {
        String[] products = {"code", "codephone", "coddle", "coddles", "codes"};
        String searchWord = "coddle";
        List<List<String>> res = anotherSuggestedProducts(products, searchWord);
        for (List<String> list : res) {
            for (String st : list) {
                System.out.print(st + " ");
            } System.out.println();
        }
    }
}

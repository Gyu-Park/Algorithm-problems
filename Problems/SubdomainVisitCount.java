package Problems;

import java.util.*;

public class SubdomainVisitCount {
    // time complexity: O(n ^ k)
    // spcae complexoty: O(2nk + k)
    public static List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<>(); // O(nk)
        StringBuilder subDomain = new StringBuilder();
        for (String cpdomain : cpdomains) { // O(n ^ (k ^ 2)) == O(n ^ 9)
            int index = cpdomain.indexOf(" ");
            int val = Integer.parseInt(cpdomain.substring(0, index));
            String domain = cpdomain.substring(index + 1);
            subDomain.setLength(0);
            subDomain.append(domain);
            while (true) { // O(k ^ 2) where k is length of string elements that is splitted by dots. 
                map.put(subDomain.toString(), map.getOrDefault(subDomain.toString(), 0) + val);
                int dotIndex = subDomain.indexOf(".");
                if (dotIndex == -1)
                    break;
                subDomain.delete(0, dotIndex + 1);
            }
        }

        List<String> res = new ArrayList<>();
        for (String domain : map.keySet()) // O(nk)
            res.add(map.get(domain) + " " + domain);
        return res;
    }
    
    public static void main(String[] args) {
        String[] cpdomains = {"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};
        System.out.println(subdomainVisits(cpdomains));
    }
}

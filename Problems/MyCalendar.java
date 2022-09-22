package Problems;

import java.util.*;

public class MyCalendar {
    
    List<int[]> list;

    public MyCalendar() {
        list = new ArrayList<>();
    }
    
    public boolean book(int start, int end) {
        for (int i = 0; i < list.size(); i++) {
            int[] event = list.get(i);
            if (Math.max(event[0], start) < Math.min(event[1], end)) 
                return false;
        }
        int[] newEvent = {start, end};
        list.add(newEvent);
        return true;
    }

    // better solution using a treeset
    TreeSet<int[]> books = new TreeSet<int[]>((int[] a, int[] b) -> a[0] - b[0]);

    public boolean book1(int s, int e) {
        int[] book = new int[] { s, e };
        int[] floor = books.floor(book);
        int[] ceiling = books.ceiling(book);
        if (floor != null && s < floor[1]) 
            return false; // (s, e) start within floor
        if (ceiling != null && ceiling[0] < e) 
            return false; // ceiling start within (s, e)
        books.add(book);
        return true;
    }
    
}

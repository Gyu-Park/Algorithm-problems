/**
 * You are a product manager and currently leading a team to develop a new product. 
 * Unfortunately, the latest version of your product fails the quality check. 
 * Since each version is developed based on the previous version, all the versions after a bad version are also bad.
 * 
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, 
 * which causes all the following ones to be bad.
 * 
 * You are given an API bool isBadVersion(version) which returns whether version is bad. 
 * Implement a function to find the first bad version. You should minimize the number of calls to the API.
 */
package Problems;

public class FirstBadVersion {
    public static int firstBadVersion(int n) {
        return binarySearch(0, n, n / 2);
    }

    private static int binarySearch(int start, int end, int mid) {
        if (!isBadVersion(mid) && isBadVersion(mid + 1))
            return mid + 1;
        if (isBadVersion(mid) && !isBadVersion(mid - 1))
            return mid;
        if (!isBadVersion(mid))
            return binarySearch(mid, end, mid + ((end - mid) / 2));
        if (isBadVersion(mid))
            return binarySearch(start, mid, start + ((mid - start) / 2));

        return -1;
    }

    // better solution
    public static int betterFirstBadVersion(int n) {
        int start = 1, end = n;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (!isBadVersion(mid))
                start = mid + 1;
            else
                end = mid;
        }
        return start;
    }

    public static boolean isBadVersion(int n) {
        if (n >= 1702766719)
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        int n = 2126753390;
        System.out.println(firstBadVersion(n));
        System.out.println(betterFirstBadVersion(n));
    }
}

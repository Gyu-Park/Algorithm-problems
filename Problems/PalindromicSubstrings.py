# Given a string s, return the number of palindromic substrings in it.

# A string is a palindrome when it reads the same backward as forward.

# A substring is a contiguous sequence of characters within the string.

 
def countSubstrings(s: str) -> int:
    n = len(s)
    dp = [[0] * n for _ in range(n)]
    
    res = 0
    for i in range(n-1, -1, -1):
        for j in range(i, n):
            dp[i][j] = s[i] == s[j] and ((j-i+1) < 3 or dp[i+1][j-1])
            res += dp[i][j]
    return res

# https://leetcode.com/problems/palindromic-substrings/discuss/1129426/JS-Python-Java-C%2B%2B-or-Optimized-Mathematical-Solution-w-Explanation
def countSubstrings2(S: str) -> int:
    ans, n, i = 0, len(S), 0
    while (i < n):
        j, k = i - 1, i
        while k < n - 1 and S[k] == S[k+1]: k += 1                
        ans += (k - j) * (k - j + 1) // 2
        i, k = k + 1, k + 1
        while ~j and k < n and S[k] == S[j]:
            j, k, ans = j - 1, k + 1, ans + 1
    return ans

# https://leetcode.com/problems/palindromic-substrings/discuss/105687/Python-Straightforward-with-Explanation-(Bonus-O(N)-solution)
def countSubstrings3(S):
    N = len(S)
    ans = 0
    for center in range(2*N - 1):
        left = center / 2
        right = left + center % 2
        while left >= 0 and right < N and S[left] == S[right]:
            ans += 1
            left -= 1
            right += 1
    return ans

print(countSubstrings2("aaabbaa"))
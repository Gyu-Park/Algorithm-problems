# Given two strings s and t, return true if they are both one edit distance apart, otherwise return false.

# A string s is said to be one distance apart from a string t if you can:

# Insert exactly one character into s to get t.
# Delete exactly one character from s to get t.
# Replace exactly one character of s with a different character to get t.

class Solution:
    def isOneEditDistance(self, s: str, t: str) -> bool:
        if s == t:
            return False
        sLen, tLen = len(s), len(t)
        if sLen > tLen: # ensure sLen < tLen
            return Solution.isOneEditDistance(self, t, s)
        if sLen + 1 < tLen:
            return False

        for i in range(tLen):
            if i < sLen and s[i] != t[i] and tLen == sLen:
                return s[:i] + s[i+1:] == t[:i] + t[i+1:]
            elif (i >= sLen or s[i] != t[i]) and s == t[:i] + t[i+1:]:
                # tLen is longer
                return True
        return False
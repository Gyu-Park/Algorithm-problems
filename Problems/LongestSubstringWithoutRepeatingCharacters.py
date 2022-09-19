def lengthOfLongestSubstring(s: str) -> int:
    used = {}
    start = 0
    res = 0
    
    for i, c in enumerate(s):
        if c in used and used[c] >= start:
            start = used[c] + 1
        else:
            res = max(i - start + 1, res)
        used[c] = i
    
    return res

s = "pwwkew"
print(lengthOfLongestSubstring(s))
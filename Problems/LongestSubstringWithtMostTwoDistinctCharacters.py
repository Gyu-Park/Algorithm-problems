def lengthOfLongestSubstringTwoDistinct(s: str) -> int:
    i = 0
    j = -1
    res = 0
    
    for k in range(1, len(s)):
        if s[k] == s[k - 1]: 
            continue
        if j > -1 and s[k] != s[j]:
            res = max(res, k - i)
            i = j + 1
        j = k - 1
    
    return res if res > len(s) - i else len(s) - i

    # locate i = j + 1, meaning that there's new character, so put at most left
    # locate j = k - 1 when s[k] != s[k-1]
    #   ex) aabbaabbaabb in this case, with j trick, we can check if s[k] exists behind

print(lengthOfLongestSubstringTwoDistinct("aabbaaacbbbaaa"))

def lengthOfLongestSubstringTwoDistinct2(s: str) -> int:
        
    # sliding window
    
    left, res = -1, 0
    d = {}
    
    for right, char in enumerate(s):
        if len(d) == 2 and char not in d:
            left = min(d.values())
            for val, pos in d.items():
                if pos == left:
                    del_val = val
            d.pop(del_val)
        d[char] = right
        res = max(res, right - left)
    return res

print(lengthOfLongestSubstringTwoDistinct2("aabbaaacbbbaaa"))
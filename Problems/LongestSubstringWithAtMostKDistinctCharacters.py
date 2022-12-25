from collections import defaultdict, deque


class Solution:
    def lengthOfLongestSubstringKDistinct(self, s: str, k: int) -> int:
        if k == 0:
            return 0
        deq = deque()
        dic = defaultdict()
        res = 0
        for i, c in enumerate(s, 1):
            if len(dic) < k or len(dic) == k and c in dic:
                deq.append((i, c))
                dic[c] = i
            elif len(dic) == k and c not in dic:
                res = max(res, len(deq))
                while True:
                    tup = deq.popleft()
                    if dic[tup[1]] == tup[0]:
                        dic.pop(tup[1])
                        break
                deq.append((i, c))
                dic[c] = i
        return max(res, len(deq))

    def lengthOfLongestSubstringKDistinct(self, s: str, k: int) -> int:
        dic = defaultdict()
        res = low = 0
        for i, c in enumerate(s):
            dic[c] = i
            if len(dic) > k:
                low = min(dic.values())
                dic.pop(s[low])
                low += 1
            res = max(res, 1 + i - low)
        return res

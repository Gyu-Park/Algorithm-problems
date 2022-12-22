from typing import List

# dfs solution


class Solution:
    def restoreIpAddresses(self, s: str) -> List[str]:
        if len(s) < 4:
            return []
        res = []
        st = ""
        Solution.dfs(s, st, 0, 0, res)
        return res

    def dfs(self, s, st, index, count, res):
        if count == 4 and index != len(s):
            return
        if count == 4 and index == len(s):
            res.append(st[:-1])
            return
        stBuild = ""
        for i, c in enumerate(s[index:]):
            if i == 3:
                break
            stBuild += c
            if int(stBuild) > 255 or (len(stBuild) > 1 and stBuild[0] == '0'):
                break
            st += c
            st += "."
            Solution.helper(self, s, st, index + i + 1, count + 1, res)
            st = st[:-1]

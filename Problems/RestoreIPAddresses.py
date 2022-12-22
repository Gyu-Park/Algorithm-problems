from typing import List

# dfs solution


class Solution:
    def restoreIpAddresses(self, s: str) -> List[str]:
        res = []
        if len(s) < 4:
            return res
        self.dfs(s, "", 0, res)
        return res

    def dfs(self, s, st, count, res):
        if count == 4 and 0 < len(s):
            return
        if count == 4 and 0 == len(s):
            res.append(st[:-1])
            return
        stBuild = ""
        for i, c in enumerate(s):
            stBuild += c
            if i == 3 or int(stBuild) > 255 or (len(stBuild) > 1 and stBuild[0] == '0'):
                break
            self.dfs(s[i + 1:], st + stBuild + ".", count + 1, res)

    # another solution
    def restoreIpAddresses2(self, s):
        res = []
        self.dfs2(s, 0, "", res)
        return res

    def dfs2(self, s, idx, path, res):
        if idx == 4 and s:
            return
        if idx == 4 and not s:
            res.append(path[:-1])
            return
        for i in range(1, len(s)+1):
            if s[:i] == '0' or (s[0] != '0' and 0 < int(s[:i]) < 256):
                self.dfs(s[i:], idx + 1, path + s[:i]+".", res)

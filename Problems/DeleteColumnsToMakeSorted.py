from typing import List


class Solution:
    def minDeletionSize(self, strs: List[str]) -> int:
        res = 0
        for col in range(len(strs[0])):
            pre = strs[0][col]
            for row in range(1, len(strs)):
                if strs[row][col] < pre:
                    res += 1
                    break
                pre = strs[row][col]
        return res

    # zip(*); * is unpacking operator.
    # zip(*strs) allows to separate each column of each row and combine it together.
    def minDeletionSize2(self, strs: List[str]) -> int:
        return sum(list(column) != sorted(column) for column in zip(*strs))

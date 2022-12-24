from collections import OrderedDict, defaultdict, deque
from typing import List, Optional


class TreeNode:
    def __init__(self, val, left, right):
        self.val = val
        self.left = left
        self.right = right


class Solution:
    def verticalOrder(self, root: Optional[TreeNode]) -> List[List[int]]:
        if root is None:
            return []
        dic = {}
        dq = deque()
        dq.append((root, 50))
        while len(dq) > 0:
            node = dq.popleft()
            if node[1] not in dic:
                dic[node[1]] = [node[0].val]
            else:
                dic[node[1]].append(node[0].val)
            if node[0].left is not None:
                dq.append((node[0].left, node[1] - 1))
            if node[0].right is not None:
                dq.append((node[0].right, node[1] + 1))
        return [x for x in OrderedDict(sorted(dic.items())).values()]

    def verticalOrder2(self, root: TreeNode) -> List[List[int]]:
        columnTable = defaultdict(list)
        queue = deque([(root, 0)])

        while queue:
            node, column = queue.popleft()

            if node is not None:
                columnTable[column].append(node.val)

                queue.append((node.left, column - 1))
                queue.append((node.right, column + 1))

        return [columnTable[x] for x in sorted(columnTable.keys())]


e = TreeNode(7, None, None)
d = TreeNode(15, None, None)
c = TreeNode(20, d, e)
b = TreeNode(9, None, None)
a = TreeNode(3, b, c)
sol = Solution()
print(sol.verticalOrder(a))

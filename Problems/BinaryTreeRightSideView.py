# iterative solution
# it is like bfs; traverse level by level and put nodes into a list
# and choose the last node, which must be the right most node in each height of tree
def rightSideView(self, root):
    view = []
    if root:
        level = [root]
        while level:
            view += level[-1].val,
            level = [kid for node in level for kid in (
                node.left, node.right) if kid]
    return view


# recursive solution
# modified preorder traversal
# in each height of tree, it always visits the node at the most right; then append the value into a list
# only the right most node in each height can satisfy the condition (h == len(view))
def rightSideView(self, root):
    def dfs(node, h):
        if node:
            if h == len(view):
                view.append(node.val)
            dfs(node.right, h + 1)
            dfs(node.left, h + 1)
    view = []
    dfs(root, 0)
    return view

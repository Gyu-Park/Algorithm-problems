#include <iostream>
#include <queue>
#include <vector>
using namespace std;

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

class Solution {
   public:
    vector<int> rightSideView(TreeNode *root) {
        // using bfs and label height in each nodes and keep updating the res vector
        vector<int> res;
        if (root == nullptr)
            return res;
        queue<pair<int, TreeNode *>> q;
        pair<int, TreeNode *> p(0, root);
        q.push(p);
        while (!q.empty()) {
            pair<int, TreeNode *> pp = q.front();
            q.pop();
            int h = pp.first;
            TreeNode *node = pp.second;
            if (res.size() > h)
                res[h] = node->val;
            else
                res.push_back(node->val);
            if (node->left != nullptr)
                q.push(pair<int, TreeNode *>(h + 1, node->left));
            if (node->right != nullptr)
                q.push(pair<int, TreeNode *>(h + 1, node->right));
        }
        return res;
    }
};
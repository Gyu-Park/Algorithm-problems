#include <iostream>
#include <vector>
#include <map>
#include <queue>
using namespace std;

struct TreeNode
{
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
};

class Solution
{
public:
    vector<vector<int>> verticalOrder(TreeNode *root)
    {
        map<int, vector<int>> map;
        queue<pair<int, TreeNode *>> queue;
        queue.push({0, root});
        while (!queue.empty())
        {
            pair<int, TreeNode *> p = queue.front();
            queue.pop();
            if (p.second != NULL)
            {
                if (map.find(p.first) == map.end())
                    map.insert(pair<int, vector<int>>(p.first, vector<int>()));
                map[p.first].push_back(p.second->val);
                queue.push({p.first - 1, p.second->left});
                queue.push({p.first + 1, p.second->right});
            }
        }

        vector<vector<int>> res;
        for (const auto &s : map)
            res.push_back(s.second);
        return res;
    }
};
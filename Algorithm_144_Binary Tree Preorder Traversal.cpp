#include <iostream>
#include <vector>
#include <stack>
using namespace std;

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

class Solution
{
  public:
    vector<int> preorderTraversal(TreeNode *root)
    {
        vector<int> res;
        if (!root) return res;
        stack<TreeNode *> sta;
        sta.push(root);
        while (!sta.empty())
        {
            TreeNode *cur = sta.top();
            sta.pop();
            res.push_back(cur->val);
            if (cur->right) sta.push(cur->right);
            if (cur->left) sta.push(cur->left);
        }
        return res;
    }
};
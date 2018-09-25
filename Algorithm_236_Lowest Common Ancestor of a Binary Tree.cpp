#include <iostream>
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
    TreeNode *lowestCommonAncestor(TreeNode *root, TreeNode *p, TreeNode *q)
    {
        if (!root || root == p || root == q) return root;
        TreeNode *leftRes = lowestCommonAncestor(root->left, p, q);
        TreeNode *rightRes = lowestCommonAncestor(root->right, p, q);
        return !leftRes ? rightRes : !rightRes ? leftRes : root;
    }
};
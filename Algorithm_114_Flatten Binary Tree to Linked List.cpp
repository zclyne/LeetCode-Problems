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
        TreeNode *prev;
        void flatten(TreeNode *root)
        {
            if (!root) return;
            flatten(root->right);
            flatten(root->left);
            root->right = prev;
            root->left = NULL;
            prev = root;
            return;
        }
};
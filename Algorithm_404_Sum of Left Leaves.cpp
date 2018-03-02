#include <iostream>
using namespace std;
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    int sum;
    int sumOfLeftLeaves(TreeNode* root) {
        sum=0;
        sumOfLeftLeaves(root,1);
        return sum;
    }
    void sumOfLeftLeaves(TreeNode* root, int lr)
    {
        if (!root) return;
        if (!root->left && !root->right && lr==0) sum+=root->val;
        if (root->left) sumOfLeftLeaves(root->left,0);
        if (root->right) sumOfLeftLeaves(root->right,1);
    }
};
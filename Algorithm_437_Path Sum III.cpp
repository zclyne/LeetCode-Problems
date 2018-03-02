#include <iostream>

//Definition for a binary tree node.
struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

class Solution {
public:
    int givenStart(TreeNode* root, int sum)
    {
        if (!root) return 0;
        int s = givenStart(root->left, sum - root->val) + givenStart(root->right, sum - root->val);
        if (root->val == sum) s++;
        return s;
    }
    int pathSum(TreeNode* root, int sum) {
        if (!root) return 0;
        else return pathSum(root->left, sum) + pathSum(root->right, sum) + givenStart(root, sum);
    }
};
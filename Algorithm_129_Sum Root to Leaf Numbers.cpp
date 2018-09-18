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
    int sumNumbers(TreeNode *root)
    {
        if (!root) return 0;
        int total = 0;
        getSum(root, 0, total);
        return total;
    }
    void getSum(TreeNode *root, int curSum, int &total)
    {
        curSum *= 10;
        curSum += root->val;
        if (!root->left && !root->right)
        {
            total += curSum;
            return;
        }
        if (root->left) getSum(root->left, curSum, total);
        if (root->right) getSum(root->right, curSum, total);
    }
};
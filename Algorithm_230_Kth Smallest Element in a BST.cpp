#include <iostream>
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
    int kthSmallest(TreeNode *root, int k)
    {
        stack<TreeNode *> sta;
        TreeNode *tmp = root;
        while (tmp)
        {
            sta.push(tmp);
            tmp = tmp->left;
        }
        while (k > 0)
        {
            tmp = sta.top();
            sta.pop();
            TreeNode *rightSub = tmp->right;
            while (rightSub)
            {
                sta.push(rightSub);
                rightSub = rightSub->left;
            }
            k--;
        }
        return tmp->val;
    }
};
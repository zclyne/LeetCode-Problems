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
    int countNodes(TreeNode *root)
    {
        if (!root) return 0;
        if (!root->left && !root->right) return 1;
        int maxLevel = -1;
        TreeNode *tmp = root;
        while (tmp)
        {
            maxLevel++;
            tmp = tmp->left;
        }
        int LRLevel = -1;
        tmp = root->left;
        while (tmp)
        {
            LRLevel++;
            tmp = tmp->right;
        }
        return LRLevel == maxLevel - 1 ? pow(2, LRLevel + 1) + countNodes(root->right) : pow(2, LRLevel + 1) + countNodes(root->left);
    }
};

// another solution
class Solution
{
  public:
    int countNodes(TreeNode *root)
    {
        if (!root) return 0;
        int hl = 0, hr = 0;
        TreeNode *l = root, *r = root;
        while (l)
        {
            hl++;
            l = l->left;
        }
        while (r)
        {
            hr++;
            r = r->right;
        }
        if (hl == hr) return pow(2, hl) - 1;
        return 1 + countNodes(root->left) + countNodes(root->right);
    }
};
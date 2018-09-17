#include <iostream>
#include <vector>
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
    vector<vector<int> > pathSum(TreeNode *root, int sum)
    {
        vector<int> midRes;
        vector<vector<int> > res;
        getPath(root, sum, midRes, res);
        return res;
    }
    void getPath(TreeNode *root, int sum, vector<int> midRes, vector<vector<int> > &res)
    {
        if (!root) return;
        sum -= root->val;
        midRes.push_back(root->val);
        if (!root->left && !root->right && !sum) res.push_back(midRes);
        if (root->left) getPath(root->left, sum, midRes, res);
        if (root->right) getPath(root->right, sum, midRes, res);
    }
};
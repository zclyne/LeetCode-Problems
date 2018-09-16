#include <iostream>
#include <vector>
using namespace std;
struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};
class Solution {
public:
    vector<TreeNode*> generateTrees(int n) {
        if (n==0)
        {
            vector<TreeNode*> res;
            return res;
        }
        return getTree(1, n);
    }
    vector<TreeNode*> getTree(int l, int r)
    {
        vector<TreeNode*> res;
        if (l > r)
        {
            res.push_back(NULL);
            return res;
        }
        for (int i = l; i <= r ; i++)
        {
            vector<TreeNode*> left = getTree(l, i-1);
            vector<TreeNode*> right = getTree(i+1, r);
            for (int j = 0; j < left.size(); j++)
            {
                for (int k = 0; k < right.size(); k++)
                {
                    TreeNode *root = new TreeNode(i);
                    root->left = left[j];
                    root->right = right[k];
                    res.push_back(root);
                }
            }
        }
        return res;
    }
};
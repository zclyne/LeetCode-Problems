#include <iostream>
#include <algorithm>
#include <climits>
using namespace std;
struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};
class Solution {
public:
    struct info {
        bool res;
        int min;
        int max;
        info(bool r, int min, int max) : res(r), min(min), max(max) {}
    };
    bool isValidBST(TreeNode* root) {
        if (!root) return true;
        return judge(root)->res;
    }
    info* judge(TreeNode *root)
    {
        if (!root->left && !root->right) return new info(true, root->val, root->val);
        info *leftInfo = root->left ? judge(root->left) : new info(true, root->val, root->val);
        info *rightInfo = root->right ? judge(root->right) : new info(true, root->val, root->val);
        bool newRes = (root->val < rightInfo->min && root->val > leftInfo->max) ? (leftInfo->res && rightInfo->res) : false;
        int newMin = min(min(root->val, leftInfo->min), rightInfo->min);
        int newMax = max(max(root->val, leftInfo->max), rightInfo->max);
        return new info(newRes, newMin, newMax);
    }
};
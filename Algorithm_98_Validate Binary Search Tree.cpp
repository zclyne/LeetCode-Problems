#include <iostream>
#include <algorithm>
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
        info *leftInfo = root->left ? judge(root->left) : NULL;
        info *rightInfo = root->right ? judge(root->right) : NULL;
        bool leftRes = leftInfo ? leftInfo->res && (leftInfo->max < root->val) : true;
        bool rightRes = rightInfo ? rightInfo->res && (rightInfo->min > root->val) : true;
        bool newRes = leftRes && rightRes;
        int newMin = root->val;
        if (leftInfo && leftInfo->min < newMin) newMin = leftInfo->min;
        if (rightInfo && rightInfo->min < newMin) newMin = rightInfo->min;
        int newMax = root->val;
        if (leftInfo && leftInfo->max > newMax) newMax = leftInfo->max;
        if (rightInfo && rightInfo->max > newMax) newMax = rightInfo->max;
        return new info(newRes, newMin, newMax);
    }
};


// other's answer:
// bool isValidBST(TreeNode* root) {
//     return isValidBST(root, NULL, NULL);
// }

// bool isValidBST(TreeNode* root, TreeNode* minNode, TreeNode* maxNode) {
//     if(!root) return true;
//     if(minNode && root->val <= minNode->val || maxNode && root->val >= maxNode->val)
//         return false;
//     return isValidBST(root->left, minNode, root) && isValidBST(root->right, root, maxNode);
// }
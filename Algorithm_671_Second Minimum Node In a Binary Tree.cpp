#include <iostream>
#include <climits>
#include <algorithm>
using namespace std;
struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};
/*class Solution {
public:
    int max=INT_MIN;
    int min,secondMin;
    void getmax(TreeNode *root)
    {
        if (!root) return;
        if (root->val>max) max=root->val;
        getmax(root->left);
        getmax(root->right);
    }
    void getSecondMin(TreeNode *root)
    {
        if (!root) return;
        if (root->val < secondMin && root->val > min) secondMin=root->val;
        getSecondMin(root->left);
        getSecondMin(root->right);
    }
    int findSecondMinimumValue(TreeNode* root) {
        getmax(root);
        min=root->val;
        secondMin=max;
        if (min==max) return -1;
        getSecondMin(root);
        return secondMin;
    }
};*/
class Solution{
public:
    int getMin(TreeNode *root, int v)
    {
        if (!root) return -1;
        if (root->val!=v) return root->val;
        int leftMin=getMin(root->left,v);
        int rightMin=getMin(root->right,v);
        if (leftMin==-1) return rightMin;
        if (rightMin==-1) return leftMin;
        return min(leftMin,rightMin);
    }
    int findSecondMinimumValue(TreeNode *root)
    {
        return getMin(root,root->val);
    }
};
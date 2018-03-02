#include <iostream>
#include <cmath>
using namespace std;
struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};
int min=INT_MAX;
int getMinimumDifference(TreeNode* root)
{
    getMin(root);
    return min;
}
void getMin(TreeNode *root)
{
    if (!root) return;
    TreeNode *lef=root->left?root->left:NULL;
    TreeNode *rig=root->right?root->right:NULL;
    while (lef && lef->right) lef=lef->right;
    while (rig && rig->left) rig=rig->left;
    int lDif=lef?abs(root->val-lef->val):INT_MAX;
    int rDif=rig?abs(root->val-rig->val):INT_MAX;
    int tmp_min=lDif<rDif?lDif:rDif;
    if (tmp_min<min) min=tmp_min;
    getMin(root->left);
    getMin(root->right);
}
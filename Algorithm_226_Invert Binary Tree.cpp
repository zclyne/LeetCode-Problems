#include <iostream>
using namespace std;
struct TreeNode
{
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};
TreeNode* invertTree(TreeNode* root)
{
	if (!root) return NULL;
	if (root->left) invertTree(root->left);
	if (root->right) invertTree(root->right);
	TreeNode* tmp=root->left;
	root->left=root->right;
	root->right=tmp;
	return root;
}

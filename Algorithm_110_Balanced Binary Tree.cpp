#include <iostream>
using namespace std;
struct TreeNode
{
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};
bool isBalanced(TreeNode* root)
{
	if (root==NULL) return true;
	else
	{
		int d1=depth(root->left);
		int d2=depth(root->right);
		if (d1-d2>1 || d1-d2<-1) return false;
		else return (isBalanced(root->left) && isBalanced(root->right));
	}
}
int depth(TreeNode *root)
{
	if (root==NULL) return 0;
	else
	{
		int lenl=depth(root->left);
		int lenr=depth(root->right);
		if (lenl>=lenr) return lenl+1;
		else return lenr+1;
	}
}

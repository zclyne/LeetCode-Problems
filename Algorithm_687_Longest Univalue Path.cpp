#include <iostream>
using namespace std;
int max;
struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 };
int longestUnivaluePath(TreeNode* root)
{
	max=0;
	findLongest(root);
	return max;
}
int findLongest(TreeNode *root)
{
	if (!root) return 0;
	int lLen=findLongest(root->left);
	int rLen=findLongest(root->right);
	int larrow=0,rarrow=0;
	if (root->left && root->left->val==root->val) larrow+=lLen+1;
	if (root->right && root->right->val==root->val) rarrow+=rLen+1;
	if (larrow+rarrow>max) max=larrow+rarrow;
	return larrow>rarrow?larrow:rarrow;
}

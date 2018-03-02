#include <iostream>
using namespace std;
struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};
TreeNode* mergeTrees(TreeNode* t1, TreeNode* t2)
{
	if (!t1 && !t2) return NULL;
	TreeNode *cur;
	if (t1 && t2) cur=new TreeNode(t1->val+t2->val);
	else if (!t1 && t2) cur=new TreeNode(t2->val);
	else cur=new TreeNode(t1->val);
	cur->left=mergeTrees(t1?t1->left:NULL,t2?t2->left:NULL);
	cur->right=mergeTrees(t1?t1->right:NULL,t2?t2->right:NULL);
	return cur;
}

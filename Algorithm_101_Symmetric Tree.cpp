#include <iostream>
using namespace std;
struct TreeNode
{
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};
bool sym(TreeNode *a,TreeNode *b)
{
	if (a==NULL && b==NULL) return true;
	else if (a!=NULL && b==NULL || a==NULL && b!=NULL) return false;
	else
	{
		if (a->val!=b->val) return false;
		else return (sym(a->left,b->right) && sym(a->right,b->left));
	}
}
bool isSymmetric(TreeNode* root)
{
	return sym(root,root);
}
int main()
{
	TreeNode *t1=new TreeNode(1);
	TreeNode *t2=new TreeNode(2);
	TreeNode *t3=new TreeNode(3);
	t1->left=t2;
	t1->right=t3;
	cout<<isSymmetric(t1);
}

#include <iostream>
using namespace std;
int maxLen=0;
struct TreeNode
{
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};
int dep(TreeNode *root,int thisLen)
{
	if (root!=NULL)
	{
		thisLen++;
		if (thisLen>maxLen) maxLen=thisLen;
		dep(root->left,thisLen);
		dep(root->right,thisLen);
	}
	return maxLen;
}
int maxDepth(TreeNode* root)
{
	return dep(root,0);
}
int main()
{
	TreeNode *t1=new TreeNode(1);
	TreeNode *t2=new TreeNode(2);
	TreeNode *t3=new TreeNode(3);
	TreeNode *t4=new TreeNode(4);
	t1->left=t2;
	t1->right=t3;
	t2->left=t4;
	cout<<maxDepth(t1);
}

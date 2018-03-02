#include <iostream>
#include <algorithm>
using namespace std;
struct TreeNode
{
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};
int minDepth(TreeNode* root)
{
	if (root==NULL) return 0;
	else if (root->left==NULL && root->right!=NULL) return minDepth(root->right)+1;
	else if (root->left!=NULL && root->right==NULL) return minDepth(root->left)+1;
	else return min(minDepth(root->left),minDepth(root->right))+1;
}
int main()
{
	TreeNode *t1=new TreeNode(1);
	TreeNode *t2=new TreeNode(2);
	t1->left=t2;
	cout<<minDepth(t1);
	return 0;
}

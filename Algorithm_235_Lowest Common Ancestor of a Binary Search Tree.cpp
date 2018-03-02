#include <iostream>
using namespace std;
struct TreeNode
{
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};
bool find(TreeNode *root, TreeNode *p)
{
	if (!root) return false;
	if (root==p) return true;
	return find(root->left,p) || find(root->right,p);
}
TreeNode* lowestCommonAncestor(TreeNode* root, TreeNode* p, TreeNode* q)
{
	if (!root) return NULL;
	if (find(root->left,p) && find(root->left,q)) return lowestCommonAncestor(root->left,p,q);
	if (find(root->right,p) && find(root->right,q)) return lowestCommonAncestor(root->right,p,q);
	return root;
}
int main()
{
	TreeNode *t1=new TreeNode(1);
	TreeNode *t2=new TreeNode(2);
	TreeNode *t3=new TreeNode(3);
	TreeNode *t4=new TreeNode(4); 
	TreeNode *t5=new TreeNode(5);
	t1->left=t2,
	t1->right=t3;
	t2->left=t4;
	t2->right=t5;
	TreeNode *tmp=lowestCommonAncestor(t1,t4,t5);
	cout<<tmp->val;
	return 0;
}

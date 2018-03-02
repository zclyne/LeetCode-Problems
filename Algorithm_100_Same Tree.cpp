#include <iostream>
using namespace std;
struct TreeNode
{
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};
bool isSameTree(TreeNode* p, TreeNode* q)
{
	if (p==NULL && q==NULL) return true;
    else if (p==NULL && q!=NULL || p!=NULL && q==NULL || p->val!=q->val) return false;
	else
	{
		bool flag;
		if (p->left!=NULL && q->left!=NULL) flag=isSameTree(p->left,q->left);
		else if (p->left==NULL && q->left==NULL) flag=true;
		else return false;
		if (flag)
		{
			if (p->right!=NULL && q->right!=NULL) flag=isSameTree(p->right,q->right);
			else if (p->right==NULL && q->right==NULL) flag=true;
			else return false;
		}
		return flag;
	}
}
int main()
{
	TreeNode *t1=new TreeNode(1);
	TreeNode *t2=new TreeNode(2);
	TreeNode *t3=new TreeNode(3);
	t1->left=t2;
	t1->right=t3;
	TreeNode *t4=new TreeNode(1);
	TreeNode *t5=new TreeNode(2);
	TreeNode *t6=new TreeNode(3);
	t4->left=t5;
	t4->right=t6;
	cout<<isSameTree(t1,t4);
}

#include <iostream>
#include <vector>
#include <queue>
using namespace std;
struct TreeNode
{
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};
vector<vector<int> > levelOrderBottom(TreeNode* root)
{
	vector<vector<int> > result;
	if (root==NULL) return result;
	queue<TreeNode*> que;
	que.push(root);
	while (que.front()!=NULL)
	{
		vector<int> vec;
		int size=que.size();
		for (int i=0;i<size;i++)
		{
			TreeNode *tmp=que.front();
			vec.push_back(tmp->val);
			que.pop();
			if (tmp->left!=NULL) que.push(tmp->left);
			if (tmp->right!=NULL) que.push(tmp->right);
		}
		result.insert(result.begin(),vec);
	}
	return result;
}
int main()
{
	TreeNode *t1=new TreeNode(1);
	TreeNode *t2=new TreeNode(2);
	TreeNode *t3=new TreeNode(3);
	t1->left=t2;
	t1->right=t3;
	vector<vector<int> >res=levelOrderBottom(t1);
	for (int i=0;i<res.size();i++)
	{
		for (int j=0;j<res[i].size();j++) cout<<res[i][j]<<' ';
	}
	return 0;
}

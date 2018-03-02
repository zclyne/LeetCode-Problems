#include <iostream>
#include <vector>
using namespace std;
struct TreeNode
{
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};
string recursive(TreeNode *root, vector<string> &s,string res)
{
	if (root)
	{
		string tmp=to_string(root->val);
		if (!root->left && !root->right)
		{
			res+=tmp;
			s.push_back(res);
		}
		else
		{
			res+=tmp+"->";
			recursive(root->left,s,res);
			recursive(root->right,s,res);
		}
	}
	return res;
}
vector<string> binaryTreePaths(TreeNode* root)
{
	vector<string> s;
	string res="";
	recursive(root,s,res);
	return s;
}

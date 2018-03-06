#include <vector>
#include <algorithm>
using namespace std;
struct TreeNode
{
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};
class Solution {
public:
	vector<vector<int> > res;
	void getRes(TreeNode *root, int d)
	{
		if (!root) return;
		if (d>=res.size()) res.push_back({});
		res[d].push_back(root->val);
		if (root->left) getRes(root->left,d+1);
		if (root->right) getRes(root->right,d+1);
	}
    vector<vector<int>> levelOrderBottom(TreeNode* root) {
		if (!root) return res;
		getRes(root,0);
		reverse(res.begin(),res.end());
		return res;
    }
};
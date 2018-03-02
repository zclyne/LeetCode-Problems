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
TreeNode* sortedArrayToBST(vector<int>& nums)
{
	if (nums.size()==0) return NULL;
	if (nums.size()==1) return new TreeNode(nums[0]);
	int mid=nums.size()/2;
	TreeNode *root=new TreeNode(nums[mid]);
	vector<int> leftNodes(nums.begin(),nums.begin()+mid);
	vector<int> rightNodes(nums.begin()+mid+1,nums.end());
	root->left=sortedArrayToBST(leftNodes);
	root->right=sortedArrayToBST(rightNodes);
	return root;
}

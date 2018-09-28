#include <iostream>
#include <vector>
using namespace std;
class NumArray
{
private:
    struct segmentTreeNode
    {
        int start, end;
        segmentTreeNode *left, *right;
        int sum;
        segmentTreeNode(int s, int e) : start(s), end(e) {}
    };
    segmentTreeNode *root;
    segmentTreeNode *buildTree(int start, int end, vector<int> nums)
    {
        segmentTreeNode *root = new segmentTreeNode(start, end);
        if (start == end) root->sum = nums[start];
        else
        {
            int mid = (start + end) / 2;
            root->left = buildTree(start, mid, nums);
            root->right = buildTree(mid + 1, end, nums);
            root->sum = root->left->sum + root->right->sum;
        }
         return root;
    }
    void update(segmentTreeNode *root, int i, int val)
     {
        if (root->start == root->end) root->sum = val;
        else
        {
            int mid = (root->start + root->end) / 2;
            if (mid >= i) update(root->left, i, val);
            else update(root->right, i, val);
            root->sum = root->left->sum + root->right->sum;
       }
    }
    int sumRange(segmentTreeNode *root, int i, int j)
    {
        if (root->start == i && root->end == j) return root->sum;
        int mid = (root->start + root->end) / 2;
        if (i > mid) return sumRange(root->right, i, j);
        if (j <= mid) return sumRange(root->left, i, j);
        return sumRange(root->left, i, mid) + sumRange(root->right, mid + 1, j);
    }
public:
    NumArray(vector<int> nums)
    {
        if (nums.size()) root = buildTree(0, nums.size() - 1, nums);
        else root = NULL;
    }

    void update(int i, int val)
    {
        if (root) update(root, i, val);
    }

    int sumRange(int i, int j)
    {
        return root ? sumRange(root, i, j) : 0;
    }
};

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */
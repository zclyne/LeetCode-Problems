#include <iostream>
using namespace std;
struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};
class Solution {
public:
TreeNode *sumRoot;
    TreeNode* convertBST(TreeNode* root) {
        sumRoot=getSumTree(root);
        TreeNode *cur=root, *s=sumRoot, *pre=NULL;
        add(root,sumRoot,pre,0);
        return root;
    }
    TreeNode* getSumTree(TreeNode* root)
    {
        if (!root) return NULL;
        TreeNode *newRoot=new TreeNode(0);
        int leftSum=0,rightSum=0;
        if (root->left)
        {
            newRoot->left=getSumTree(root->left);
            leftSum=newRoot->left->val;
        }
        if (root->right)
        {
            newRoot->right=getSumTree(root->right);
            rightSum=newRoot->right->val;
        }
        newRoot->val=root->val+leftSum+rightSum;
        return newRoot;
    }
    void add(TreeNode *cur, TreeNode *s, TreeNode *pre, int sum)
    {
        if (!cur) return;
        if (pre && pre->left==s) sum+=pre->val-s->val;
        cur->val+=sum+(s->right?s->right->val:0);
        add(cur->left,s->left,s,sum);
        add(cur->right,s->right,s,sum);
    }
};
/*class Solution {
public:
    int sum=0;
    TreeNode* convertBST(TreeNode* root)
    {
        if (!root) return NULL;
        if (root->right) convertBST(root->right);
        sum+=root->val;
        root->val=sum;
        if (root->left) convertBST(root->left);
        return root;
    }
};*/
#include <iostream>
#include <stack>
using namespace std;
struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};
class BSTIterator
{
    private:
    stack<TreeNode *> sta;
    void getSmallest(TreeNode *root)
    {
        while (root)
        {
            sta.push(root);
            root = root->left;
        }
    }
    public:
    TreeNode *node;
    BSTIterator(TreeNode *root)
    {
        getSmallest(root);
    }

    /** @return whether we have a next smallest number */
    bool hasNext()
    {
        return !sta.empty();
    }

    /** @return the next smallest number */
    int next()
    {
        TreeNode *toReturn = sta.top();
        sta.pop();
        if (toReturn->right) getSmallest(toReturn->right);
        return toReturn->val;
    }
};

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = BSTIterator(root);
 * while (i.hasNext()) cout << i.next();
 */
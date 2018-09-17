#include <iostream>
#include <vector>
using namespace std;

struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};

struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};

class Solution
{
  public:
    TreeNode *sortedListToBST(ListNode *head)
    {
        if (!head) return NULL;
        vector<int> list;
        while (head)
        {
            list.push_back(head->val);
            head = head->next;
        }
        return getTree(list, 0, list.size() - 1);
    }
    TreeNode *getTree(vector<int> &list, int l, int r)
    {
        if (l > r) return NULL;
        TreeNode *root = new TreeNode(list[(l + r) / 2]);
        root->left = getTree(list, l, (l + r) / 2 - 1);
        root->right = getTree(list, (l + r) / 2 + 1, r);
        return root;
    }
};
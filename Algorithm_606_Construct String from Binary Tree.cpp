#include <iostream>
#include <sstream>
using namespace std;
struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode(int x) : val(x), left(NULL), right(NULL) {}
};
class Solution {
public:
    string result="";
    void create(TreeNode* t)
    {
        if (!t) return;
        string tmp;
        result+='(';
        stringstream stream;
        result+=to_string(t->val);
        if (t->left) create(t->left);
        else if (!t->left && t->right) result+="()";
        if (t->right) create(t->right);
        result+=')';
    }
    string tree2str(TreeNode* t) {
        if (!t) return result;
        create(t);
        result.erase(result.begin());
        result.erase(result.end()-1);
        return result;
    }
};
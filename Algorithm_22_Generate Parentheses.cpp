#include <stack>
#include <queue>
#include <vector>
#include <iostream>
using namespace std;
class Solution {
public:
    vector<string> generateParenthesis(int n) {
        vector<string> res;
        queue<string> que;
        int left,right;
        string tmp="";
        que.push(tmp);
        for (int i=0;i<2*n;i++)
        {
            while (!que.empty() && que.front().size()==i)
            {
                tmp=que.front();
                que.pop();
                left=count(tmp,'(');
                right=count(tmp,')');
                if (left<n) que.push(tmp+'(');
                if (right<n && left>right) que.push(tmp+')');
            }
        }
        while (!que.empty())
        {
            res.push_back(que.front());
            que.pop();
        }
        return res;
    }
    int count(string s, char c)
    {
        int count=0;
        for (int i=0;i<s.size();i++) if (s[i]==c) count++;
        return count;
    }
};
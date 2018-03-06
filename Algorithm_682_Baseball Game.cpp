#include <vector>
#include <stack>
using namespace std;
class Solution {
public:
    int calPoints(vector<string>& ops) {
        stack<int> sta;
        int sum=0,t1,t2;
        for (int i=0;i<ops.size();i++)
        {
            if (ops[i]=="+")
            {
                t1=sta.top();
                sta.pop();
                t2=sta.top();
                sta.push(t1);
                sta.push(t1+t2);
                sum+=t1+t2;
            }
            else if (ops[i]=="D")
            {
                sum+=2*sta.top();
                sta.push(2*sta.top());
            }
            else if (ops[i]=="C")
            {
                sum-=sta.top();
                sta.pop();
            }
            else
            {
                t1=atoi(ops[i].c_str());
                sta.push(t1);
                sum+=t1;
            }
        }
        return sum;
    }
};
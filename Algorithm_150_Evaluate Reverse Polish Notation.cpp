#include <iostream>
#include <vector>
#include <stack>
#include <cstdlib>
using namespace std;

class Solution
{
  public:
    int evalRPN(vector<string> &tokens)
    {
        if (tokens.size() == 0) return 0;
        stack<int> sta;
        int opr1, opr2;
        for (int i = 0; i < tokens.size(); i++)
        {
            if (tokens[i] == "+" || tokens[i] == "-" || tokens[i] == "*" || tokens[i] == "/")
            {
                opr1 = sta.top();
                sta.pop();
                opr2 = sta.top();
                sta.pop();
                if (tokens[i] == "+") sta.push(opr1 + opr2);
                else if(tokens[i] == "-") sta.push(opr2 - opr1);
                else if (tokens[i] == "*") sta.push(opr1 * opr2);
                else sta.push(opr2 / opr1);
            }
            else sta.push(atoi(tokens[i].c_str()));
        }
        return sta.top();
    }
};
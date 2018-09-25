#include <iostream>
#include <stack>
using namespace std;

class Solution
{
  public:
    int calculate(string s)
    {
        int curNum = 0, res = 0;
        char lastSign = '+';
        stack<int> sta;
        for (int i = 0; i <= s.size(); i++)
        {
            if (s[i] >= '0' && s[i] <= '9')
            {
                curNum *= 10;
                curNum += s[i] - '0';
            }
            else if (s[i] == ' ') continue;
            else
            {
                if (lastSign == '+') sta.push(curNum);
                else if (lastSign == '-') sta.push(-curNum);
                else
                {
                    int staTop = sta.top();
                    sta.pop();
                    if (lastSign == '*') staTop *= curNum;
                    else staTop /= curNum;
                    sta.push(staTop);
                }
                lastSign = s[i];
                curNum = 0;
            }
        }
        while (!sta.empty())
        {
            res += sta.top();
            sta.pop();
        }
        return res;
    }
};
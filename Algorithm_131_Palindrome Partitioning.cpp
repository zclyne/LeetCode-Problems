#include <iostream>
#include <vector>
using namespace std;
class Solution
{
  public:
    bool isValid(string str)
    {
        for (int i = 0; i < str.size(); i++) if (str[i] != str[str.size() - i - 1]) return false;
        return true;
    }
    void getVector(vector<vector<string> > &res, vector<string> tmpVec, string s, int start)
    {
        if (start >= s.size())
        {
            res.push_back(tmpVec);
            tmpVec.clear();
            return;
        }
        for (int i = 1; start + i <= s.size(); i++)
        {
            string sub = s.substr(start, i);
            if (isValid(sub))
            {
                tmpVec.push_back(sub);
                getVector(res, tmpVec, s, start + i);
                tmpVec.pop_back();
            }
        }
    }
    vector<vector<string> > partition(string s)
    {
        vector<vector<string> > res;
        vector<string> tmpVec;
        getVector(res, tmpVec, s, 0);
        return res;
    }
};
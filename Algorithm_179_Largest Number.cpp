#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
using namespace std;
class Solution
{
  public:
    string largestNumber(vector<int> &nums)
    {
        string res = to_string(nums[0]);
        if (nums.size() == 1) return res;
        for (int i = 1; i < nums.size() ; i++)
        {
            string tmp = to_string(nums[i]);
            res = link(res, tmp);
        }
        return res;
    }
    string link(string &s1, string &s2)
    {
        int len1 = s1.size(), len2 = s2.size();
        int minLen = min(len1, len2);
        for (int i = 0; i < minLen; i++)
        {
            if ((s1[i] - '0') < (s2[i] - '0')) return s2 + s1;
            else if ((s1[i] - '0') > (s2[i] - '0')) return s1 + s2;
        }
        if (len1 == len2) return s1 + s2;
        if (minLen == len1)
        {
            string subs2 = s2.substr(minLen, s2.size() - minLen);
            return s1 + link(s1, subs2);
        }
        else
        {
            string subs1 = s1.substr(minLen, s1.size() - minLen);
            return s2 + link(subs1, s2);
        }
        
    }
};
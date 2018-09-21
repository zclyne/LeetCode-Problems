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
        if (nums.size() == 0) return "";
        vector<string> numsStr;
        string res;
        for (int i = 0; i < nums.size() ; i++) numsStr.push_back(to_string(nums[i]));
        sort(numsStr.begin(), numsStr.end(), myCompare);
        for (int i = 0; i < numsStr.size() ; i++) res += numsStr[i];
        int start;
        for (start = 0; res[start] == '0' ; start++);
        res = res.substr(start, res.size() - start);
        return res.size() > 0 ? res : "0";
    }
    struct compare {
        bool operator() (string &s1, string &s2) {return s1 + s2 > s2 + s1;}
    } myCompare;
};
#include <iostream>
#include <vector>
#include <unordered_map>
using namespace std;

// map solution
class Solution
{
  public:
    vector<int> majorityElement(vector<int> &nums)
    {
        unordered_map<int, int> map;
        for (int i = 0; i < nums.size(); i++) map[nums[i]]++;
        int thres = nums.size() / 3;
        vector<int> res;
        for (unordered_map<int, int>::iterator iter = map.begin(); iter != map.end(); iter++) if (iter->second >= thres) res.push_back(iter->first);
        return res;
    }
};

// better solution
class Solution
{
  public:
    vector<int> majorityElement(vector<int> &nums)
    {
        vector<int> res;
        if (nums.size() == 0) return res;
        int num1 = nums[0], num2 = nums[0], count1 = 0, count2 = 0;
        for (int i = 0; i < nums.size(); i++)
        {
            if (nums[i] == num1) count1++;
            else if (nums[i] == num2) count2++;
            else if (count1 == 0)
            {
                num1 = nums[i];
                count1++;
            }
            else if (count2 == 0)
            {
                num2 = nums[i];
                count2++;
            }
            else
            {
                count1--;
                count2--;
            }
        }
        count1 = count2 = 0;
        for (int i = 0; i < nums.size(); i++)
        {
            if (nums[i] == num1) count1++;
            else if (nums[i] == num2) count2++;
        }
        if (count1 > nums.size() / 3) res.push_back(num1);
        if (count2 > nums.size() / 3) res.push_back(num2);
        return res;
    }
};
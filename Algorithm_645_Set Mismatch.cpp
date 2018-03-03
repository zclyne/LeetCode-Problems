#include <vector>
#include <unordered_map>
using namespace std;
class Solution {
public:
    vector<int> findErrorNums(vector<int>& nums) {
        unordered_map<int,int> map;
        vector<int> result;
        result.resize(2);
        for (int i=0;i<nums.size();i++) map[nums[i]]++;
        for (int i=1;i<=nums.size();i++)
        {
            if (map[i]==2) result[0]=i;
            else if (map[i]==0) result[1]=i; 
        }
        return result;
    }
};
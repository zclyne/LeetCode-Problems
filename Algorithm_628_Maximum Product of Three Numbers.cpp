#include <vector>
#include <functional>
#include <algorithm>
using namespace std;
class Solution {
public:
    int maximumProduct(vector<int>& nums) {
        sort(nums.begin(),nums.end());
        int len=nums.size();
        int p1=nums[len-1]*nums[len-2]*nums[len-3];
        int p2=nums[0]*nums[1]*nums[len-1];
        return p1>p2?p1:p2;
    }
};
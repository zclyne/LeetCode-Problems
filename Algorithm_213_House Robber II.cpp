#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
class Solution {
public:
    int rob(vector<int>& nums) {
        int n = nums.size(); 
        if (n < 2) return n ? nums[0] : 0;
        if (n == 2) return max(nums[0], nums[1]);
        return max(robber(nums, 0, n - 2), robber(nums, 1, n - 1));
    }
private:
    int robber(vector<int> &nums, int l, int r) {
        if (r - l == 2) return max(nums[l] + nums[r], nums[l + 1]);
        vector<int> sums(nums.size());
        sums[0] = nums[0];
        sums[1] = nums[1];
        sums[2] = nums[2];
        for (int i = l + 2; i <= r; i++)
        {
            if (i == 2) sums[i] = sums[i - 2] + nums[i];
            else sums[i] = max(sums[i - 3] + nums[i], sums[i - 2] + nums[i]);
        }
        cout << "l = " << l << ", r =  " << r << endl;
        for (int i = 0; i <= r; i ++) cout << nums[i] << ", ";
        cout << endl;
        for (int i = 0; i < nums.size(); i++) cout << sums[i] << ", ";
        cout << endl << endl;
        return max(sums[r], sums[r - 1]);
    }
};

int main()
{
    Solution s;
    vector<int> nums;
    nums.push_back(6);
    nums.push_back(3);
    nums.push_back(10);
    nums.push_back(8);
    nums.push_back(2);
    nums.push_back(10);
    nums.push_back(3);
    nums.push_back(5);
    nums.push_back(10);
    nums.push_back(5);
    nums.push_back(3);
    cout << s.rob(nums);
    return 0;
}
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
class Solution {
public:
    void wiggleSort(vector<int>& nums) {
        vector<int> tmp(nums);
        sort(tmp.begin(), tmp.end());
        int median = tmp[tmp.size() / 2];
        int less = 0, greater = nums.size() % 2 == 0 ? nums.size() - 1 : nums.size() - 2;
        for (int i = nums.size() % 2 == 0 ? tmp.size() / 2 - 1 : tmp.size() / 2; i >= 0; i--)
        {
            nums[less] = tmp[i];
            less += 2;
        }
        for (int i = nums.size() % 2 == 0 ? tmp.size() / 2 : tmp.size() / 2 + 1; i < tmp.size(); i++)
        {
            nums[greater] = tmp[i];
            greater -= 2;
        }
    }
};

// better solution
class Solution {
public:
    void wiggleSort(vector<int>& nums) {
        int m = nums.size();
        auto mptr = nums.begin() + (m-1)/2; 
        nth_element(nums.begin(), mptr, nums.end()); 
        int median = *mptr; 
        int i = 1;   // position for the larger values: start with first odd index
        int j = ((m - 1) & 1) ? m - 2 : m - 1;  // position for the smaller values: start with last even index
        for (int l = 0; l < m; ++l) {
            if (nums[l] > median) {
                if (l <= i && (l & 1)) continue;       // skip the elements which are already checked: 1, 3, 5, ..., i
                swap(nums[l--], nums[i]);
                i += 2;
            } else if (nums[l] < median) {
                if (l >= j && (l & 1) == 0) continue;     // skip the elements whcih are checked: j, j + 2, ..., lastEvenIndex
                swap(nums[l--], nums[j]);
                j -= 2;
            }
       }
    }
};
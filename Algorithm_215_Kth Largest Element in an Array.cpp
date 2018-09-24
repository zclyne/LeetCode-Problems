#include <iostream>
#include <vector>
#include <queue>
using namespace std;

// priority_queue solution
class Solution
{
  public:
    int findKthLargest(vector<int> &nums, int k)
    {
        priority_queue<int> que;
        for (int i = 0; i < nums.size(); i++) que.push(nums[i]);
        for (int i = 0; i < k - 1; i++) que.pop();
        return que.top();
    }
};

// partition solution
class Solution
{
  public:
    int findKthLargest(vector<int> &nums, int k)
    {
        int left = 0, right = nums.size() - 1;
        while (true)
        {
            int pos = partition(nums, left, right);
            if (pos == k - 1) return nums[pos];
            else if (pos > k - 1) right = pos - 1;
            else if (pos < k - 1) left = pos + 1;
        }
    }
    int partition(vector<int> &nums, int left, int right)
    {
        int pivot = nums[left];
        while (left < right)
        {
            while (right > left && nums[right] < pivot) right--;
            if (right > left) nums[left++] = nums[right];
            while (right > left && nums[left] > pivot) left++;
            if (right > left) nums[right--] = nums[left];
        }
        nums[left] = pivot;
        return left;
    }
};
# 解法一：设数组长度为n，则要寻找的目标必定在范围[1, ..., l + 1]内
# 使用数组的index作为哈希，来记录范围[1, ..., l + 1]内的数字出现的频率
# 第一遍循环把数组内所有不在此范围内的数字都置为0，因为这些数字不影响最后的答案
# 第二遍循环以nums[i] % n为index，将nums[nums[i] % n] += n，注意此处的n是l + 1
# 这一步的取模操作作用是取出原本nums[i]位置上的值，因为该位置上的值可能已经被上一步所修改
# 此时，nums[i] // n就是数字i在原数组中出现的次数，而nums[i] % n就是nums[i]原本的值
# 这个原始值在[1, ..., l + 1]范围内

class Solution:
    def firstMissingPositive(self, nums: List[int]) -> int:
        # increase the length of nums from l to l + 1
        # this step makes it easy to handle the situation where the answer is exactly l + 1
        nums.append(0)
        n = len(nums)
        # get rid of the numbers that lies outside [1, ..., l + 1]
        for i in range(n):
            if nums[i] < 0 or nums[i] >= n:
                nums[i] = 0
        # count the frequency of the numbers within [1, ..., l + 1]
        # therefore, nums[i] // n is the frequency, and nums[i] % n is the original number
        for i in range(n):
            nums[nums[i] % n] += n
        for i in range(1, n):
            # nums[i] // n == 0 means that i never appears in the original array nums
            if nums[i] // n == 0:
                return i
        return n



# 解法二：把数字放置到该数字对应的下标位置上
# 例如，若当前数字为5，就把当前数字与nums[4]交换
# 该算法的时间复杂度为O(n)，因为每次交换都保证了nums[nums[i] - 1]上存储到了正确的值
# 而数组中最多存在n个错误的配对情况，所以最多发生n次交换
# 第二遍循环检查元素nums[i]上存储的值是否正确，若不正确，表明i + 1没有在原数组中出现过

class Solution2:
    def firstMissingPositive(self, nums: List[int]) -> int:
        n = len(nums)
        for i in range(n):
            while nums[i] > 0 and nums[i] <= n and nums[i] != nums[nums[i] - 1]:
                tmp = nums[nums[i] - 1]
                nums[nums[i] - 1] = nums[i]
                nums[i] = tmp
                print(nums)
        for i in range(n):
            if nums[i] != i + 1:
                return i + 1
        return n + 1
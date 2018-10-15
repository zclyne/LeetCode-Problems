import java.util.HashMap;
// 思路：用HashMap存储下每个元素的出现频率、第一次出现的位置和最后一次出现的位置
// HashMap的键为Integer, 值为int[]
// 遍历nums的同时记录下原数组nums的degree
// 最后遍历map，找到拥有同样degree的数，并返回其中subArray长度最短的即可

// my improved solution
class Solution {
    public int findShortestSubArray(int[] nums) {
        if (nums.length <= 1) return nums.length;
        HashMap<Integer, int[]> map = new HashMap<>();
        int maxFreq = 0;
        for (int i = 0; i < nums.length; i++)
        {
            int[] info;
            if (!map.containsKey(nums[i]))
            {
                info = new int[] {1, i, i}; // {freq, startIdx, endIdx}
                map.put(nums[i], info);
            }
            else
            {
                info = map.get(nums[i]);
                info[0]++; // increment freq
                info[2] = i; // endIdx = i
            }
            if (info[0] > maxFreq) maxFreq = info[0];
        }
        if (maxFreq == 1) return 1;
        int minLen = nums.length;
        for (int[] info : map.values()) if (info[0] == maxFreq && info[2] - info[1] + 1 < minLen) minLen = info[2] - info[1] + 1;
        return minLen;
    }
}

// My first solution
class Solution {
    public int findShortestSubArray(int[] nums) {
        if (nums.length <= 1) return nums.length;
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        HashMap<Integer, Integer> startMap = new HashMap<>();
        HashMap<Integer, Integer> endMap = new HashMap<>();
        int maxFreq = 0;
        HashSet<Integer> maxFreqNums = new HashSet<>();
        for (int i = 0; i < nums.length; i++)
        {
            if (!freqMap.containsKey(nums[i]))
            {
                freqMap.put(nums[i], 1);
                startMap.put(nums[i], i);
            }
            else freqMap.put(nums[i], freqMap.get(nums[i]) + 1);
            endMap.put(nums[i], i);
            int curFreq = freqMap.get(nums[i]);
            if (curFreq == maxFreq) maxFreqNums.add(nums[i]);
            else if (curFreq > maxFreq)
            {
                maxFreq = curFreq;
                maxFreqNums.clear();
                maxFreqNums.add(nums[i]);
            }
        }
        if (maxFreq == 1) return 1;
        int minLen = nums.length;
        for (Integer num : maxFreqNums)
        {
            int startIdx = startMap.get(num);
            int endIdx = endMap.get(num);
            if (endIdx - startIdx + 1 < minLen) minLen = endIdx - startIdx + 1;
        }
        return minLen;
    }
}
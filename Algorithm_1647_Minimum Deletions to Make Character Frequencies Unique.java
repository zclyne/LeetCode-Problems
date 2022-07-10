import java.util.*;

// 由于charCount是从小到大排序的，所以如果出现charCount[i] >= charCount[i + 1]
// 的情况，说明：
// 1. charCount[i] == charCount[i + 1]，两个字符的freq相等，需要把当前char的freq
// 减小1
// 2. charCount[i] > charCount[i + 1]，说明上一个char的freq必然被减小过了，否则不等式不可能成立（因为排序）
// 所以要把当前char的freq再减小到上一个char的freq - 1
// 注意freq不能小于0

class Solution {
    public int minDeletions(String s) {
        int[] charCount = new int[26];
        for (char c : s.toCharArray()) {
            charCount[c - 'a']++;
        }

        Arrays.sort(charCount);

        int result = 0;

        for (int i = 24; i >= 0 && charCount[i] > 0; i--) {
            if (charCount[i] >= charCount[i + 1]) {
                int prev = charCount[i];
                charCount[i] = Math.max(charCount[i + 1] - 1, 0);
                result += prev - charCount[i];
            }
        }

        return result;
    }
}



// My solution
// 用map存储被使用的freq，每次从大到小找下一个可用的freq

class Solution2 {
    public int minDeletions(String s) {
        int[] charCount = new int[26];
        for (char c : s.toCharArray()) {
            charCount[c - 'a']++;
        }

        Arrays.sort(charCount);

        // record the frequency and number of each character
        Map<Integer, Integer> map = new HashMap<>();
        for (int count : charCount) {
            map.put(count, map.getOrDefault(count, 0) + 1);
        }

        int start = charCount[25]; // start from the maximum frequency 
        int result = 0;

        for (int i = 25; i >= 0 && charCount[i] > 0; i--) {
            int curFreq = charCount[i];
            int numCharWithFreq = map.get(curFreq);
            start = Math.min(start, curFreq);
            if (numCharWithFreq > 1) { // is not unique
                int newFreq = getNextAvailableFreq(map, start);
                result += curFreq - newFreq;
                start = newFreq;
                map.put(curFreq, numCharWithFreq - 1);
                map.put(newFreq, 1); // the new frequency is taken now
            } else {
                start = curFreq;
            }
        }

        return result;
    }

    private int getNextAvailableFreq(Map<Integer, Integer> map, int start) {
        if (start == 0) {
            return 0;
        }
        while (start > 0 && map.containsKey(start)) {
            start--;
        }
        return start;
    }
}
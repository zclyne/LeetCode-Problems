import java.util.ArrayList;
import java.util.List;

// 解法1：回溯法
// 用数组letterCount记录下s中每个字母出现的次数
// 然后回溯法，对于letterCount中剩余的字母，取用这个字母，letterCount中计数-1，进入下一层递归
// 递归返回后，把letterCount中的计数加回来
class Solution {
    private int[] letterCount = new int[26];
    
    public String[] permutation(String s) {
        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            letterCount[idx]++;
        }
        List<String> result = new ArrayList<>();
        this.backtracking("", result, s.length());
        String[] resultArr = new String[result.size()];
        result.toArray(resultArr);
        return resultArr;
    }

    private void backtracking(String curStr, List<String> result, int totalLength) {
        if (curStr.length() == totalLength) { // all letters have been used
            result.add(curStr);
            return;
        }
        for (int i = 0; i < 26; i++) {
            if (letterCount[i] > 0) {
                char c = (char) ((int) 'a' + i);
                letterCount[i]--;
                this.backtracking(curStr + c, result, totalLength);
                letterCount[i]++;
            }
        }
    }
}

// 解法2：NextPermutation
// 用Algorithm_31的NextPermutation，已知当前的一个排列，获取字典序中下一个更大的排列
// 首先对给定的字符串中的字符进行排序，即可得到当前字符串的第一个排列，
// 然后我们不断地计算当前字符串的字典序中下一个更大的排列，直到不存在更大的排列为止即可
class Solution {
    public String[] permutation(String s) {
        List<String> ret = new ArrayList<String>();
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        do {
            ret.add(new String(arr));
        } while (nextPermutation(arr));
        int size = ret.size();
        String[] retArr = new String[size];
        for (int i = 0; i < size; i++) {
            retArr[i] = ret.get(i);
        }
        return retArr;
    }

    public boolean nextPermutation(char[] arr) {
        int i = arr.length - 2;
        while (i >= 0 && arr[i] >= arr[i + 1]) {
            i--;
        }
        if (i < 0) {
            return false;
        }
        int j = arr.length - 1;
        while (j >= 0 && arr[i] >= arr[j]) {
            j--;
        }
        swap(arr, i, j);
        reverse(arr, i + 1);
        return true;
    }

    public void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public void reverse(char[] arr, int start) {
        int left = start, right = arr.length - 1;
        while (left < right) {
            swap(arr, left, right);
            left++;
            right--;
        }
    }
}
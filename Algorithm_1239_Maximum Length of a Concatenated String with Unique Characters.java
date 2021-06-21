import java.util.List;

// 对于arr中的每个单词，用bit来存储出现过的字母，保存为一个int
// 如果一个单词中有某个字母出现超过1次，则这个单词不能被选择，直接保存-1
// 然后对整个arr进行回溯法，startIdx保存当前的起始下标
// curBin保存当前所选择所有单词中，已经出现的字母
// curLength表示当前所选择的单词的总长度
// 从startIdx开始遍历，如果arrBin[i] & curBin == 0，说明单词arr[i]中的字母都没有出现过，可以被选择
// 进入下一层递归

class Solution {
    private int[] arrBin;
    public int maxLength(List<String> arr) {
        arrBin = new int[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            arrBin[i] = this.getBinaryIntForWord(arr.get(i));
        }
        return this.backTracking(arr, 0, 0, 0);
    }

    private int getBinaryIntForWord(String word) {
        int result = 0;
        for (Character c : word.toCharArray()) {
            int idx = c - 'a';
            if ((result & (1 << idx)) > 0) { // this char has already existed in the word
                return -1;
            }
            result |= 1 << idx;
        }
        return result;
    }

    private int backTracking(List<String> arr, int startIdx, int curBin, int curLength) {
        if (startIdx == arr.size()) {
            return curLength;
        }
        int maxLength = curLength;
        for (int i = startIdx; i < arr.size(); i++) {
            if (this.arrBin[i] == -1) { // cannot select this word
                continue;
            }
            if ((curBin & this.arrBin[i]) == 0) { // arr[i] is available
                int length = this.backTracking(arr, i + 1, curBin | this.arrBin[i], curLength + arr.get(i).length());
                maxLength = Integer.max(maxLength, length);
            }
        }
        return maxLength;
    }
}
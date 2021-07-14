import java.util.Arrays;

// 方法1：按照citation从小到大排序，然后遍历h，直到找到citations[i] <= h的项为止
// 之所以能够包含citations[i] == h是因为，如果对于当前的h和i，有citations[i] == h
// 对于下一轮遍历，必然后citations[i - 1] <= citations[i]，而h < h + 1
// 所以必然有citations[i - 1] < h + 1，h + 1一定不满足条件

class Solution {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int h = 0, i = citations.length - 1; 
        while (i >= 0 && citations[i] > h) {
            h++; 
            i--;
        }
        return h;
    }
}

// 方法2：计数排序
// 总论文数等于citations.length，h指数不可能大于总论文数
// 对于被引用次数大于总论文数的情况，可以直接按照总论文数来计算
// 数组counter计算各个被引用数量的论文的篇数
// 最后从后向前遍历counter，找h指数

class Solution {
    public int hIndex(int[] citations) {
        int n = citations.length, tot = 0;
        int[] counter = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if (citations[i] >= n) {
                counter[n]++;
            } else {
                counter[citations[i]]++;
            }
        }
        for (int i = n; i >= 0; i--) {
            tot += counter[i];
            if (tot >= i) {
                return i;
            }
        }
        return 0;
    }
}
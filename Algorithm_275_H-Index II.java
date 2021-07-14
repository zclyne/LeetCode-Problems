// 方法：由于citations已经按照从小到大排序，所以直接使用二分查找
// citations[mid] >= h中能够包含等号的原因和274题中相同
// 注意right的初始值是n - 1而不是n，这是因为循环的终止条件是left > right
// 极端情况下right始终不动，退出循环时left == right + 1 == n
// h = n - left = n - n = 0

class Solution {
    public int hIndex(int[] citations) {
        int left = 0, right = citations.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int h = citations.length - mid;
            if (citations[mid] >= h) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return citations.length - left;
    }
}
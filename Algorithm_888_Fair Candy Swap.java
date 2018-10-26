// 思路：遍历A和B，分别求出A中所有元素之和sumA与B中所有元素之和sumB，并将B中所有元素存入HashSet中
// 设A中要交换的元素为m，B中要交换的元素为n
// 则sumA - m + n = sumB - n + m
// 则sumB - sumA = 2(n - m)
// 记下两个和的差值，这个差值即为要交换的两元素之差的2倍
// 遍历A，在HashSet中寻找比当前A中元素大difference / 2的元素是否存在，若存在，则这两个数即为结果

import java.util.HashSet;

class Solution {
    public int[] fairCandySwap(int[] A, int[] B) {
        int sumA = 0, sumB = 0;
        HashSet<Integer> elementsOfB = new HashSet<>();
        for (int a : A) sumA += a;
        for (int b : B) {
            sumB += b;
            elementsOfB.add(b);
        }
        int difference = sumB - sumA;
        for (int a : A) {
            if (elementsOfB.contains(a + difference / 2)) {
                return new int[] {a, a + difference / 2};
            }
        }
        return null; // as an answer is guaranteed to be existing, this will never be executed
    }
}
// 思路：cur存储的是B[0][i], B[1][i] ... B[i][i]
// 处理A[i + 1]时，要将cur中所有元素与A[i + 1]相或，并加上A[i + 1]本身，存储在next中
// 由按位或的性质，B[j + 1][i]中为1的位在B[j][i]中也一定为1，因此cur的长度一定不会超过32，同理，next的长度不超过32
// 因此ans的长度为O(32n)，该算法的复杂度也是O(32n)

import java.util.HashSet;

class Solution {
    public int subarrayBitwiseORs(int[] A) {
        HashSet<Integer> ans = new HashSet<>(), cur = new HashSet<>(), next;
        for (int num : A) {
            next = new HashSet<>();
            for (int tmp : cur) {
                next.add(tmp | num);
            }
            next.add(num);
            cur = next;
            ans.addAll(cur);
        }
        return ans.size();
    }
}
// 思路：田忌赛马的思想：遍历数组B，在A中寻找大于B[i]的最小元素作为res[i]
// 若该元素不存在，则取A中最小的元素作为res[i]
// 用TreeMap来存储A中对应每个元素的出现次数，每使用一次就将次数-1，减到0时从TreeMap中删除该元素

import java.util.TreeMap;

class Solution {
    public int[] advantageCount(int[] A, int[] B) {
        TreeMap<Integer, Integer> elementsOfA = new TreeMap<>();
        for (int a : A) {
            Integer num = elementsOfA.get(a);
            if (num == null) elementsOfA.put(a, 1);
            else elementsOfA.put(a, num + 1);
        }
        int[] res = new int[A.length];
        for (int i = 0; i < B.length; i++)
        {
            Map.Entry<Integer, Integer> matchingEntry = elementsOfA.higherEntry(B[i]); // get the smallest element in A that is larger than B[i]
            if (matchingEntry == null) {
                matchingEntry = elementsOfA.firstEntry(); // if this element doesn't exist, use the smallest element in A instead
            }

            res[i] = matchingEntry.getKey();
            if (matchingEntry.getValue() == 1) { // run out of this element, remove it from the TreeMap
                elementsOfA.remove(matchingEntry.getKey());
            } else {
                elementsOfA.put(matchingEntry.getKey(), matchingEntry.getValue() - 1);
            }
        }
        return res;
    }
}
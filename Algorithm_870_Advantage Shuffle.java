// 思路：先把A从小到大排序，再构建数组pairsB，把pairsB按照val从小到大排序
// 然后反向遍历pairsB，对于当前的pairsB[i].val，若A中有比这个数更大的数（即A[high] > pairsB[i].val）
// 则A[high]就是pairsB[i].idx位置上的一个可行的数，把他放到该位置上，并high--
// 否则表明A中不存在可行的数，直接从最小的数中挑一个放到该位置上，即A[low]，并low++

class Solution {
    public int[] advantageCount(int[] A, int[] B) {
        Arrays.sort(A);
        Pair[] pairsB = new Pair[B.length];
        for (int i = 0; i < B.length; i++) {
            pairsB[i] = new Pair(i, B[i]);
        }
        Arrays.sort(pairsB, (a, b) -> a.val - b.val);
        int[] res = new int[A.length];
        int low = 0, high = A.length - 1;
        for (int i = B.length - 1; i >= 0; i--) {
            if (A[high] > pairsB[i].val) {
                res[pairsB[i].idx] = A[high--];
            } else {
                res[pairsB[i].idx] = A[low++];
            }
        }
        return res;
    }
    class Pair {
        int idx, val;
        public Pair(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }
}

// PriorityQueue Solution
// 思路同上

class PriorityQueueSolution {
    public int[] advantageCount(int[] A, int[] B) {
        Arrays.sort(A);
        int[] res = new int[A.length];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
                return b[0] - a[0];
            });
        for (int i = 0; i < B.length; i++) {
            pq.add(new int[] {B[i], i});
        }
        int low = 0, high = A.length - 1;
        for (int i = 0; i < A.length; i++) {
            int[] cur = pq.poll();
            int val = cur[0], idx = cur[1];
            if (A[high] > val) {
                res[idx] = A[high--];
            } else {
                res[idx] = A[low++];
            }
        }
        return res;
    }
}

// TreeMapSolution
// 思路：田忌赛马的思想：遍历数组B，在A中寻找大于B[i]的最小元素作为res[i]
// 若该元素不存在，则取A中最小的元素作为res[i]
// 用TreeMap来存储A中对应每个元素的出现次数，每使用一次就将次数-1，减到0时从TreeMap中删除该元素

import java.util.Arrays;
import java.util.TreeMap;

class TreeMapSolution {
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
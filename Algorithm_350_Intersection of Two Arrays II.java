import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// HashMap Solution

class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> resultList = new ArrayList<>();
        for (int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int num : nums2) {
            int count = map.getOrDefault(num, 0);
            if (count > 0) {
                resultList.add(num);
                if (count > 1) {
                    map.put(num, count - 1);
                } else {
                    map.remove(num);
                }
            }
        }
        int[] result = new int[resultList.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }
}

// two-pointer solution
class TwoPointerSolution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int pt1 = 0, pt2 = 0;
        List<Integer> resultList = new ArrayList<>();
        while (pt1 < nums1.length && pt2 < nums2.length) {
            if (nums1[pt1] < nums2[pt2]) {
                pt1++;
            } else if (nums1[pt1] > nums2[pt2]) {
                pt2++;
            } else {
                resultList.add(nums1[pt1]);
                pt1++;
                pt2++;
            }
        }
        int[] result = new int[resultList.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }
}

// Follow-up: What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
// If only nums2 cannot fit in memory, put all elements of nums1 into a HashMap, read chunks of array that fit into the memory, and record the intersections.
// If both nums1 and nums2 are so huge that neither fit into the memory, sort them individually (external sort), then read 2 elements from each array at a time in memory, 
// record intersections.
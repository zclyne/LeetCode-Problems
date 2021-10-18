import java.util.*;

// method 1. sort

// class Solution {
//     public int hIndex(int[] citations) {
//         Arrays.sort(citations);
//         int count = 0;
//         int i = citations.length - 1;
//         while (i >= 0 && citations[i] >= count + 1) {
//             count++;
//             i--;
//         }
//         return count;
//     }
// }

// method 2. count

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
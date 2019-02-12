// 思路：与之后我的思路基本相同

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
class Solution {
    public int[] numsSameConsecDiff(int N, int K) {
        List<Integer> cur = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        for (int i = 1; i < N; i++) {
            List<Integer> cur2 = new ArrayList<>();
            for (int x : cur) {
                int y = x % 10;
                if (x > 0 && y + K < 10) {
                    cur2.add(x * 10 + y + K);
                }
                if (x > 0 && K > 0 && y - K >= 0) {
                    cur2.add(x * 10 + y - K);
                }
            }
            cur = cur2;
        }
        int[] res = new int[cur.size()];
        for (int i = 0; i < cur.size(); i++) {
            res[i] = cur.get(i);
        }
        return res;
    }
}



// 我的答案思路：用数组res来保存长度为N位的以数字0~9开头的满足条件的数字集合
// 在处理N + 1位长的数字时，对某一数字j，考虑j - K和j + K是否在[0, 9]范围内
// 若在范围内，则通过res.get(j - K)和res.get(j + K)来获取N位时，分别以j - K和j + K位第一位的所有数字的集合
// 将数字j添加到上一步得到的所有数字的首部，得到新的以数字j开头、长度为N + 1的所有数字的集合
// 循环结束后，把所有数字从字符串形式转换为int形式添加到数组中，并返回数组
// 若N = 1，还要将0添加进去

// import java.util.ArrayList;
// import java.util.HashSet;
// class Solution {
//     public int[] numsSameConsecDiff(int N, int K) {
//         ArrayList<HashSet<String> > res = new ArrayList<>();
//         for (int i = 0; i < 10; i++) {
//             HashSet<String> tmpSet = new HashSet<>();
//             tmpSet.add(String.valueOf(i));
//             res.add(tmpSet);
//         }
//         for (int i = 1; i < N; i++) {
//             ArrayList<HashSet<String> > tmpRes = new ArrayList<>();
//             for (int j = 0; j < 10; j++) {
//                 HashSet<String> thisStringSet = new HashSet<>();
//                 if (j + K < 10) {
//                     HashSet<String> lastStringSet = res.get(j + K);
//                     for (String lastNum : lastStringSet) {
//                         thisStringSet.add(String.valueOf(j) + lastNum);
//                     }
//                 }
//                 if (j - K >= 0) {
//                     HashSet<String> lastStringSet = res.get(j - K);
//                     for (String lastNum : lastStringSet) {
//                         thisStringSet.add(String.valueOf(j) + lastNum);
//                     }
//                 }
//                 tmpRes.add(thisStringSet);
//             }
//             res = tmpRes;
//         }
//         int start = N == 1 ? 0 : 1, size = 0;
//         for (int i = start; i < 10; i++) {
//             size += res.get(i).size();
//         }
//         int[] resToReturn = new int[size];
//         for (int i = start, count = 0; i < 10; i++) {
//             HashSet<String> thisSet = res.get(i);
//             for (String numString : thisSet)
//             resToReturn[count++] = Integer.valueOf(numString);
//         }
//         return resToReturn;
//     }
// }
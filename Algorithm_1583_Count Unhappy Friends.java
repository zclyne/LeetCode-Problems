import java.util.*;

// 方法：用map保存每个人的朋友到下标的映射
// 下标越小，说明pereference越高
// 用另一个map pairMap保存每个人的pair对象
// 然后从0到n - 1遍历，设当前第x个人的pair对象为y
// 从map中获得对于x来说，y的preference。
// 然后遍历那些比y的preference更高的人u，从pairMap中得到u的pair对象v
// 如果对于u来说，x的preference比v的preference更高（即下标更小），则说明x和u都是unhappy的，加入到set中
// 最终，set的size就是unhappy的人数

class Solution {
    public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
        Map<Integer, Map<Integer, Integer>> friendPreferenceMap = new HashMap<>();
        Map<Integer, Integer> pairMap = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            Map<Integer, Integer> preferenceMapForI = friendPreferenceMap.getOrDefault(i, new HashMap<>());
            int[] preference = preferences[i];
            for (int j = 0; j < preference.length; j++) {
                preferenceMapForI.put(preference[j], j);
            }
            friendPreferenceMap.put(i, preferenceMapForI);
        }

        for (int[] pair : pairs) {
            pairMap.put(pair[0], pair[1]);
            pairMap.put(pair[1], pair[0]);
        }

        Set<Integer> unhappy = new HashSet<>();
        for (int x = 0; x < n; x++) {
            if (unhappy.contains(x)) {
                continue;
            }
            int y = pairMap.get(x);
            Map<Integer, Integer> preferenceForX = friendPreferenceMap.get(x);
            int rankY = preferenceForX.get(y);
            for (int j = rankY - 1; j >= 0; j--) { // check all x's friends that has a higher preference over y
                int u = preferences[x][j];
                int v = pairMap.get(u);
                Map<Integer, Integer> preferenceForU = friendPreferenceMap.get(u);
                if (preferenceForU.get(x) < preferenceForU.get(v)) { // u prefers x over v
                    // x and u are unhappy
                    unhappy.add(x);
                    unhappy.add(u);
                }
            }
        }

        return unhappy.size();
    }
}
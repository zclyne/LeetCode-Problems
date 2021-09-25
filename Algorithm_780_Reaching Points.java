// 方法：类似于一颗二叉树，自底向上
// https://leetcode.com/problems/reaching-points/discuss/114856/JavaC%2B%2BPython-Modulo-from-the-End
// https://leetcode.com/problems/reaching-points/discuss/375429/Detailed-explanation.-or-full-through-process-or-Java-100-beat

class Solution {
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while (sx < tx && sy < ty) {
            if (tx < ty) {
                ty %= tx;
            }
            else {
                tx %= ty;
            }
        }
            
        return sx == tx && sy <= ty && (ty - sy) % sx == 0 ||
               sy == ty && sx <= tx && (tx - sx) % sy == 0;
    }
}
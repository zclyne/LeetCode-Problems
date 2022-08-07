// https://leetcode.com/problems/poor-pigs/discuss/94266/Another-explanation-and-solution

class Solution {
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        int pigs = 0;
        int temp = 1;
        while (temp < buckets) {
            pigs++;
            temp = temp * (minutesToTest / minutesToDie + 1);
        }
        return pigs;
    }
}
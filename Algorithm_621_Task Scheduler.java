// 思路：用数组c记录下各个task出现的次数，再将c排序
// 设频率最高的task出现次数为k，则可以构造个chunk,每个chunk的头为该task，且有n个interval跟在每个chunk的头之后，但不用考虑最后一个chunk
// 再按频率从高到低的顺序，依次将剩余的task中频率最高的task填入这些chunk中，并保持同一task的间隔>=n
// 注意可能存在多个频率为k的task，这时最后一个chunk的interval数为25 - i

// (c[25] - 1) * (n + 1) + 25 - i  is frame size
// when inserting chars, the frame might be "burst", then tasks.length takes precedence
// when 25 - i > n, the frame is already full at construction, the following is still valid.
public class Solution {
    public int leastInterval(char[] tasks, int n) {

        int[] c = new int[26];
        for(char t : tasks){
            c[t - 'A']++;
        }
        Arrays.sort(c);
        int i = 25;
        while(i >= 0 && c[i] == c[25]) i--; // 排序完后，c[25]中即为频率最高的task的出现次数，此while循环用于计算具有最高频率的task的个数

        return Math.max(tasks.length, (c[25] - 1) * (n + 1) + 25 - i); // (c[25] - 1)*(n + 1)是前k - 1个chunk占用的interval个数，25-i是最后一个chunk占用的interval个数
        // 若前k - 1个chunk不足以容纳所有的task，则结果一定为tasks.length
    }
}
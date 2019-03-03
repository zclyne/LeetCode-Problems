import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.List;

// 思路：用数组c记录下各个task出现的次数，再将c排序
// 设频率最高的task出现次数为k，则可以构造个chunk,每个chunk的头为该task，且有n个interval跟在每个chunk的头之后，但不用考虑最后一个chunk
// 再按频率从高到低的顺序，依次将剩余的task中频率最高的task填入这些chunk中，并保持同一task的间隔>=n
// 注意可能存在多个频率为k的task，这时最后一个chunk的interval数为25 - i

// (c[25] - 1) * (n + 1) + 25 - i  is frame size
// when inserting chars, the frame might be "burst", then tasks.length takes precedence
// when 25 - i > n, the frame is already full at construction, the following is still valid.
class Solution {
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


// My Solution
// 思路：cooldown数组记录每个task的剩余冷却时间，remain数组记录每个task剩余的个数
// 变量count记录还剩多少个task未做完，numInterval记录所需的interval个数
// 每一次循环对应一个interval，因此循环体内部先把numInterval++
// 然后遍历一遍所有的task，找到remain > 0（即仍有该task未完成）且cooldown == 0（表示冷却时间已过，可以开始这个task）中
// remain最大的那个。这一步使用了greedy的方法，直观的解释是要把数量多的同一个task尽可能的分散开，因此每次要选择remain最多的task来执行
// 对于原本cooldown > 0的task，要把对应cooldown--，表示冷却时间-1
// 对于选出的remain最大的task，要执行它，因此把对应的cooldown置为n，并把count和相应的remain - 1，表示执行了一个这个task
// 当count最终减到0时，所有task都已完成，此时的numInterval就是经过的interval数量

class MySolution {
    public int leastInterval(char[] tasks, int n) {
        int[] cooldown = new int[26];
        int[] remain = new int[26];
        int count = 0, numInterval = 0;
        for (int i = 0; i < tasks.length; i++) {
            remain[tasks[i] - 'A']++;
            count++;
        }
        while (count > 0) {
            numInterval++;
            int maxRemain = -1, maxRemainIdx = -1;
            for (int i = 0; i < 26; i++) {
                if (cooldown[i] > 0) {
                    cooldown[i]--;
                } else if (cooldown[i] == 0 && remain[i] > 0) {
                    if (remain[i] > maxRemain) {
                        maxRemain = remain[i];
                        maxRemainIdx = i;
                    }
                }
            }
            if (maxRemain != -1) {
                cooldown[maxRemainIdx] = n;
                count--;
                remain[maxRemainIdx]--;
            }
        }
        return numInterval;
    }
}



// Priority Queue Solution
// 思路与MySolution基本相同

class PQSolution {
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < tasks.length; i++) {
            map.put(tasks[i], map.getOrDefault(tasks[i], 0) + 1); // map key is TaskName, and value is number of times to be executed.
        }
        PriorityQueue<Map.Entry<Character, Integer>> q = new PriorityQueue<>( //frequency sort
                (a,b) -> a.getValue() != b.getValue() ? b.getValue() - a.getValue() : a.getKey() - b.getKey());
   
        q.addAll(map.entrySet());
   
        int count = 0;
        while (!q.isEmpty()) {
            int k = n + 1;
            List<Map.Entry> tempList = new ArrayList<>();
            while (k > 0 && !q.isEmpty()) {
                Map.Entry<Character, Integer> top = q.poll(); // most frequency task
                top.setValue(top.getValue() - 1); // decrease frequency, meaning it got executed
                tempList.add(top); // collect task to add back to queue
                k--;
                count++; //successfully executed task
            }
    
            for (Map.Entry<Character, Integer> e : tempList) {
                if (e.getValue() > 0) q.add(e); // add valid tasks 
            }
    
            if (q.isEmpty()) break;
            count = count + k; // if k > 0, then it means we need to be idle
        }
        return count;
    }
}
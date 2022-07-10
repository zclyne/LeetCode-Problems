import java.util.*;

// greedy。用大顶堆pq维护3个字符'a', 'b', 'c'剩余的可用数量
// 每次从pq中取出剩余count最大的字符，如果上一个字符已经连续用了2次
// 则作为参数传进pickNextCharExcept函数中，表示这个字符不能选择
// 需要换一个新的字符
// 若pickNextCharExcept返回null，说明没有可用的字符了，break出循环并返回

class Solution {
    class Pair {
        char ch;
        int count;
        public Pair(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }
    }

    // max heap
    private Queue<Pair> pq = new PriorityQueue<>((a, b) -> b.count - a.count);

    public String longestDiverseString(int a, int b, int c) {
        if (a > 0) {
            pq.add(new Pair('a', a));
        }
        if (b > 0) {
            pq.add(new Pair('b', b));
        }
        if (c > 0) {
            pq.add(new Pair('c', c));
        }
        StringBuilder sb = new StringBuilder();
        Character lastChar = null;
        int lastCharCount = 0;

        while (true) {
            Pair nextPair = lastCharCount == 2 ? pickNextCharExcept(lastChar) : pickNextCharExcept(null);
            if (nextPair == null) {
                break;
            }
            if (lastChar != null && lastChar == nextPair.ch) {
                lastCharCount++;
            } else { // switch to a new char
                lastChar = nextPair.ch;
                lastCharCount = 1;
            }
            sb.append(nextPair.ch);
            nextPair.count--;
            if (nextPair.count > 0) {
                pq.add(nextPair);
            }
        }

        return sb.toString();
    }

    // return pair with the maximum count and remove that pair from pq
    // if curChar is not null, it returns the char with the max count
    // which is not equal to curChar
    private Pair pickNextCharExcept(Character curChar) {
        if (this.pq.isEmpty()) {
            return null;
        }
        Pair topPair = pq.poll();
        if (curChar!= null && curChar == topPair.ch) {
            if (pq.isEmpty()) {
                return null;
            }
            Pair res = pq.poll();
            pq.add(topPair);
            return res;
        }
        return topPair;
    }
}
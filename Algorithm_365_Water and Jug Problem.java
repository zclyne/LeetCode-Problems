import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;

import javax.swing.plaf.nimbus.State;

// 方法1：DFS
// X壶中的水量和Y壶中的水量唯一确定了一个状态
// 在任意时刻，只能采取以下6种操作；
// 1. 把 X 壶的水灌进 Y 壶，直至灌满或倒空
// 2. 把 Y 壶的水灌进 X 壶，直至灌满或倒空
// 3. 把 X 壶灌满
// 4. 把 Y 壶灌满
// 5. 把 X 壶倒空
// 6. 把 Y 壶倒空
// 因此，本题可以用DFS来解决
// 由于DFS的层数很深，所以用栈来模拟
// 自定义类State来记录每一个状态，注意要重写equals和hashCode方法

class Solution {

    private class State {
        private int remainX;
        private int remainY;

        public State(int x, int y) {
            remainX = x;
            remainY = y;
        }

        @Override
        public String toString() {
            return "State{" +
                    "remainX=" + remainX +
                    ", remainY=" + remainY +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            State state = (State) o;
            return remainX == state.remainX
                && remainY == state.remainY;
        }

        @Override
        public int hashCode() {
            return Objects.hash(remainX, remainY);
        }
    }

    public boolean canMeasureWater(int x, int y, int z) {
        Deque<State> stack = new LinkedList<>();
        stack.push(new State(0, 0));
        Set<State> seen = new HashSet<>();
        while (!stack.isEmpty()) {
            if (seen.contains(stack.peek())) {
                stack.pop();
                continue;
            }
            
            State state = stack.pop();
            seen.add(state);
            int remainX = state.remainX, remainY = state.remainY;
            if (remainX == z || remainY == z || remainX + remainY == z) {
                return true;
            }
            // 把 X 壶灌满。
            stack.push(new State(x, remainY));
            // 把 Y 壶灌满。
            stack.push(new State(remainX, y));
            // 把 X 壶倒空。
            stack.push(new State(0, remainY));
            // 把 Y 壶倒空。
            stack.push(new State(remainX, 0));
            // 把 X 壶的水灌进 Y 壶，直至灌满或倒空。
            stack.push(new State(remainX - Math.min(remainX, y - remainY), remainY + Math.min(remainX, y - remainY)));
            // 把 Y 壶的水灌进 X 壶，直至灌满或倒空。
            stack.push(new State(remainX + Math.min(remainY, x - remainX), remainY - Math.min(remainY, x - remainX)));
        }
        return false;
    }
}

// 方法2：BFS
// 和DFS思路相同

class BFSSolution {

    private class State {
        private int remainX;
        private int remainY;

        public State(int x, int y) {
            remainX = x;
            remainY = y;
        }

        @Override
        public String toString() {
            return "State{" +
                    "remainX=" + remainX +
                    ", remainY=" + remainY +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            State state = (State) o;
            return remainX == state.remainX
                && remainY == state.remainY;
        }

        @Override
        public int hashCode() {
            return Objects.hash(remainX, remainY);
        }
    }

    public boolean canMeasureWater(int x, int y, int z) {
        Queue<State> queue = new LinkedList<>();
        Set<State> visited = new HashSet<>();
        queue.add(new State(0, 0));
        while (!queue.isEmpty()) {
            State state = queue.poll();
            if (visited.contains(state)) {
                continue;
            }
            visited.add(state);
            int remainX = state.remainX, remainY = state.remainY;
            if (remainX == z || remainY == z || remainX + remainY == z) {
                return true;
            }
            // 把 X 壶灌满。
            queue.offer(new State(x, remainY));
            // 把 Y 壶灌满。
            queue.offer(new State(remainX, y));
            // 把 X 壶倒空。
            queue.offer(new State(0, remainY));
            // 把 Y 壶倒空。
            queue.offer(new State(remainX, 0));
            // 把 X 壶的水灌进 Y 壶，直至灌满或倒空。
            queue.offer(new State(remainX - Math.min(remainX, y - remainY), remainY + Math.min(remainX, y - remainY)));
            // 把 Y 壶的水灌进 X 壶，直至灌满或倒空。
            queue.offer(new State(remainX + Math.min(remainY, x - remainX), remainY - Math.min(remainY, x - remainX)));
        }
        return false;
    }
}

// 方法2：数学方法
// 可以认为每次操作只会给水的总量带来x或者y的变化量
// 则目标可以改写成，找到一对整数a, b，使得ax + by = z
// 根据贝祖定理，方程有解，当且仅当z是x, y的最大公约数的倍数。所以只需要找到x和y的最大公约数，并判断z是否是它的倍数即可
// https://leetcode-cn.com/problems/water-and-jug-problem/solution/shui-hu-wen-ti-by-leetcode-solution/

class Solution {
    public boolean canMeasureWater(int x, int y, int z) {
        if (x + y < z) {
            return false;
        }
        if (x == 0 || y == 0) {
            return z == 0 || x + y == z;
        }
        return z % gcd(x, y) == 0;
    }

    public int gcd(int x, int y) {
        int remainder = x % y;
        while (remainder != 0) {
            x = y;
            y = remainder;
            remainder = x % y;
        }
        return y;
    }
}
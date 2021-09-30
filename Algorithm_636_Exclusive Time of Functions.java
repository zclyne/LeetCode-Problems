import java.util.*;

// 方法：用栈来存储正在执行中的函数id
// lastTimestamp记录上一条log的发生的时间戳，lastLogType记录上一条log的类型，是start还是end
// 需要记录lastLogType的原因是上一条log的类型会影响时间的计算方法
// 若当前log类型是start，首先判断栈是否为空
// 若栈不为空，说明当前正有函数在执行中
// 根据lastLogType的不同决定栈顶函数的执行时间需要增加多久
// 然后把新的当前函数的id push到栈中
// 若当前log类型是end，则被终止的一定是栈顶函数
// 同样的，根据lastLogType的不同来决定栈顶函数的执行时间需要增加多久
// 最后将栈顶函数pop出来
// 将当前的timestamp和type赋值给lastTimestamp和lastLogType

class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        Deque<Integer> stack = new LinkedList<>();
        int[] result = new int [n];

        int lastTimestamp = -1;
        String lastLogType = "";
        for (String log : logs) {
            String[] logInfo = log.split(":");
            int funcId = Integer.parseInt(logInfo[0]);
            String type = logInfo[1];
            int timestamp = Integer.parseInt(logInfo[2]);

            if ("start".equals(type)) {
                if (!stack.isEmpty()) { // update the time of the currently running function
                    if ("start".equals(lastLogType)) {
                        result[stack.peek()] += timestamp - lastTimestamp;
                    } else if ("end".equals(lastLogType)) {
                        result[stack.peek()] += timestamp - lastTimestamp - 1;
                    }
                }
                stack.push(funcId);
            } else {
                stack.pop();
                if ("start".equals(lastLogType)) { // same function
                    result[funcId] += timestamp - lastTimestamp + 1;
                } else if ("end".equals(lastLogType)) {
                    result[funcId] += timestamp - lastTimestamp;
                }
            }

            lastTimestamp = timestamp;
            lastLogType = type;
        }

        return result;
    }
}
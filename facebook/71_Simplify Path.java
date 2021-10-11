import java.util.*;

// split by "/", and then use a stack to record the current path

class Solution {
    public String simplifyPath(String path) {
        String[] paths = path.split("/");
        Deque<String> deque = new LinkedList<>();
        for (String p : paths) {
            if (".".equals(p) || "".equals(p)) {
                continue;
            } else if ("..".equals(p)) {
                if (deque.isEmpty()) {
                    continue;
                }
                deque.pop();
            } else {
                deque.push(p);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (!deque.isEmpty()) {
            stringBuilder.append("/");
            stringBuilder.append(deque.pollLast());
        }
        return stringBuilder.length() == 0 ? "/" : stringBuilder.toString();
    }
}
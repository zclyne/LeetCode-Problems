import java.util.*;

// 方法1：map + treeset
// 用map的key是name，value是treeset，用treeset来保证set中的有序性，set中存储的是刷卡的时间time
// 以int的方式存储，便于计算
// 每次向set中插入一个新时间的时候，考虑以下三种情况：
// 1. 插入的时间是连续3次刷卡时间中的最大值，则连续找2个lower，看时间之差是否小于等于60min
// 2. 插入的时间是连续3次刷卡时间中的最小值，则连续找2个higher，看时间之差是否小于等于60min
// 3. 插入的时间是连续3次刷卡时间的中间值，则分别找higher和lower，看时间之差是否小于等于60min

class Solution {
    public List<String> alertNames(String[] keyName, String[] keyTime) {
        Map<String, TreeSet<Integer>> map = new HashMap<>();
        Set<String> resultSet = new HashSet<>();
        int n = keyName.length;
        for (int i = 0; i < n; i++) {
            String name = keyName[i];
            int time = timeStringToMinute(keyTime[i]);
            TreeSet<Integer> set = map.getOrDefault(name, new TreeSet<>());
            set.add(time);
            if (checkHigher(set, time) || checkLower(set, time) || checkMiddle(set, time)) {
                resultSet.add(name);
            }
            map.put(name, set);
        }
        List<String> result = new ArrayList<>(resultSet);
        Collections.sort(result);
        return result;
    }

    private int timeStringToMinute(String time) {
        String[] timeInfo = time.split(":");
        return Integer.parseInt(timeInfo[0]) * 60 + Integer.parseInt(timeInfo[1]);
    }

    private boolean checkHigher(TreeSet<Integer> set, int time) {
        Integer higher = set.higher(time);
        if (higher == null) {
            return false;
        }
        Integer higher2 = set.higher(higher);
        if (higher2 == null || higher2 - time > 60) {
            return false;
        }
        return true;
    }

    private boolean checkLower(TreeSet<Integer> set, int time) {
        Integer lower = set.lower(time);
        if (lower == null) {
            return false;
        }
        Integer lower2 = set.lower(lower);
        if (lower2 == null || time - lower2 > 60) {
            return false;
        }
        return true;
    }

    private boolean checkMiddle(TreeSet<Integer> set, int time) {
        Integer higher = set.higher(time);
        if (higher == null) {
            return false;
        }
        Integer lower = set.lower(time);
        if (lower == null) {
            return false;
        }
        return higher - lower <= 60;
    }
}



// 方法2：先按照时间从小到大排序，然后遍历，并为每一个name都维护一个大小为2的deque
// deque中的刷卡时间从小到大排列

class Solution {

    class Event {
        String name;
        int time;
        public Event(String name, int time) {
            this.name = name;
            this.time = time;
        }
    }

    public List<String> alertNames(String[] keyName, String[] keyTime) {
        int n = keyName.length;
        Event[] events = new Event[n];
        for (int i = 0; i < n; i++) {
            String name = keyName[i];
            int time = timeStringToMinute(keyTime[i]);
            events[i] = new Event(name, time);
        }

        // sort by time in ascending order
        Arrays.sort(events, (e1, e2) -> {
            if (e1.time == e2.time) {
                return e1.name.compareTo(e2.name);
            }
            return e1.time - e2.time;
        });

        Set<String> resultSet = new HashSet<>();
        Map<String, Deque<Integer>> map = new HashMap<>();
        for (Event e : events) {
            Deque<Integer> deque = map.getOrDefault(e.name, new LinkedList<>());
            if (deque.size() == 2) {
                if (e.time - deque.peekFirst() <= 60) { // 3 consecutive event with time diff <= 60 min
                    resultSet.add(e.name);
                }
                deque.pollFirst();
            }
            deque.offerLast(e.time);
            map.put(e.name, deque);
        }
        List<String> result = new ArrayList<>(resultSet);
        Collections.sort(result);
        return result;
    }

    private int timeStringToMinute(String time) {
        String[] timeInfo = time.split(":");
        return Integer.parseInt(timeInfo[0]) * 60 + Integer.parseInt(timeInfo[1]);
    }
}
// 思路：被book三次可以看做被book了两次的overlap又被book，所以只要追踪已经被book两次的overlap
// 若新添加的事务与已经book两次的overlap有重叠，返回false，若没有，则返回true
// 并将该事务添加到列表books中，同时对books中的其他每一项事务，检查是否有出现2次book的overlap，若有，则加入overlaps中

import java.util.TreeMap;

class MyCalendarTwo {
    List<int[]> books;
    TreeMap<Integer, Integer> overlaps;
    public MyCalendarTwo() {
        books = new ArrayList<>();
        overlaps = new TreeMap<>(); 
    }
    
    public boolean book(int start, int end) {
        Integer overlapStart = overlaps.lowerKey(end);
        if (overlapStart != null && overlaps.get(overlapStart) > start) // an overlap is overlapped again
            return false;

        // no overlaps overlapped again
        for (int[] interval : books)
        {
            int newStart = Math.max(start, interval[0]);
            int newEnd = Math.min(end, interval[1]);
            if (newStart < newEnd) // an overlap generated
                overlaps.put(newStart, newEnd);
        }
        books.add(new int[] {start, end});
        return true;
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */
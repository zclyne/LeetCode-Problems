// 思路：使用TreeMap。TreeMap在java内部为红黑树实现，插入和查找为O(logn)复杂度
// .lowerKey(key)返回的是TreeMap中比key小的最大的key，若不存在这样的key则返回null
// 查找比要插入的end小的最大的key，若该key不存在或比要插入的start更小，说明不存在冲突
// 直接插入后返回true，否则返回false

class MyCalendar {
    private TreeMap<Integer, Integer> calender;
    public MyCalendar() {
        calender = new TreeMap<>();
    }
    
    public boolean book(int start, int end) {
        Integer lower = calender.lowerKey(end);
        if (lower == null || calender.get(lower) <= start)
        {
            calender.put(start, end);
            return true;
        } 
        return false;
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */
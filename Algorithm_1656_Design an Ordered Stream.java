import java.util.*;

// 方法：用一个指针idx指向当前在list中的位置
// insert时，只有当idKey与idx相等，才说明可以向后移动idx
// 不断向后移动idx，将list[idx]加入result中，直到list[idx] == null或idx > n为止

class OrderedStream {

    private int n;
    private int idx;
    private String[] list;

    public OrderedStream(int n) {
        this.n = n;
        this.idx = 1;
        this.list = new String[n + 1];
    }
    
    public List<String> insert(int idKey, String value) {
        List<String> result = new ArrayList<>();
        list[idKey] = value;
        if (idKey != idx) {
            return result;
        }
        while (idx <= n && list[idx] != null) {
            result.add(list[idx++]);
        }
        return result;
    }
}

/**
 * Your OrderedStream object will be instantiated and called as such:
 * OrderedStream obj = new OrderedStream(n);
 * List<String> param_1 = obj.insert(idKey,value);
 */
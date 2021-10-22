import java.util.*;

// 方法：用一个list来保存浏览记录
// 指针cur指向当前页面在histories中的下标
// 对于visit()，将list中cur指针之后的所有元素都删除，然后将新的url append到当前list尾部，并向后移动cur
// 对于back()，直接相应地调整cur指向的位置，然后返回url
// 对于forward()，同样直接调整cur的值，然后返回对应url

class BrowserHistory {

    private int cur;
    private List<String> histories;

    public BrowserHistory(String homepage) {
        cur = 0;
        histories = new ArrayList<>();
        histories.add(homepage);
    }
    
    public void visit(String url) {
        clear();
        histories.add(url);
        cur++;
    }
    
    public String back(int steps) {
        if (steps > cur) { // return to the 0-th url
            cur = 0;
        } else {
            cur -= steps;
        }
        return histories.get(cur);
    }
    
    public String forward(int steps) {
        int canForward = histories.size() - 1 - cur;
        if (steps > canForward) {
            cur = histories.size() - 1;
        } else {
            cur += steps;
        }
        return histories.get(cur);
    }

    private void clear() {
        if (cur == histories.size() - 1) { // already at the end
            return;
        }
        for (int i = histories.size() - 1; i > cur; i--) {
            histories.remove(i);
        }
    }
}

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory obj = new BrowserHistory(homepage);
 * obj.visit(url);
 * String param_2 = obj.back(steps);
 * String param_3 = obj.forward(steps);
 */
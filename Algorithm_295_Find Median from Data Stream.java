import java.util.PriorityQueue;

// 方法：大顶堆+小顶堆
// 用大顶堆low存储数据流中较小的那一部分数字，用小顶堆high存储较大的那一部分的数字
// 当总数字个数为偶数时，需要保证大顶堆和小顶堆中的元素个数相同，则两堆顶元素相加再除以2，就是答案
// 当总数字个数为奇数时，保证大顶堆的元素个数比小顶堆中多1，则大顶堆的堆顶元素就是答案
// 在添加新数字时，先将其添加到low中；然后再取low的堆顶元素，添加到high中，就能保证low和high始终分别保存了较小和较大的两部分数字
// 如果原本元素个数是奇数，low中的元素个数比high多1，则添加上这个数字后，新的总元素个数变成了偶数，low和high中总元素个数相等
// 如果原本元素个数是偶数，还要再将high的堆顶元素取出，放回low中，从而保证low的元素个数比high多1

// https://leetcode-cn.com/problems/find-median-from-data-stream/solution/you-xian-dui-lie-python-dai-ma-java-dai-ma-by-liwe/

class MedianFinder {

    private PriorityQueue<Integer> low;
    private PriorityQueue<Integer> high;
    private int count = 0;

    /** initialize your data structure here. */
    public MedianFinder() {
        // low is a max-heap
        low = new PriorityQueue<>((x, y) -> {
            return y - x;
        });
        // high is a min-heap
        high = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        count++;
        low.add(num);
        high.add(low.poll());
        if (count % 2 == 1) { // total element count is odd, num of elements in low should be greater than that in high by 1
            low.add(high.poll());
        }
    }
    
    public double findMedian() {
        if (count % 2 == 1) { // total element count is odd, the top element of low is the answer
            return low.peek();
        }
        return ((double) low.peek() + high.peek()) / 2;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
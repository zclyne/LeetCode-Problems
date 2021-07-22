import java.util.TreeMap;

// 方法：用treemap来存储各个interval，一个interval用一个TreeNode来表示
// TreeNode中有一个成员变量next，用于指向下一个interval
// 在treemap中，用区间的右端点作为key，treeNode作为value
// 在addNum中，首先key比val小的最大的interval，由于初始化时，我们添加了一个left和right都等于-2的dummyHead到treemap中
// 而val的最小值为0，所以这里能够保证这个interval的存在，记为preNode
// 记preNode.next为nextNode。nextNode有可能为null
// 1. 首先判断preNode.right和val的关系。如果preNode.right == val - 1，则可以把val合并到preNode所代表的区间里面
// 在这种情况下还要注意，有可能nextNode.left == val + 1，则此时需要把preNode，val，nextNode三者合并起来
// 2. 如果preNode.right != val - 1，再去判断能否将val和nextNode所代表的区间合并。若能合并，则有nextNode != null && val == nextNode.left - 1
// 3. 如果nextNode == null || nextNode.left > val + 1，此时必须把val单独作为一个新的节点添加到treemap里
// 注意val == nextNode.left是一个特殊情况，这也就是为什么第三种情况在代码里不能简单写一个else，而必须把条件写完整的原因
// 因为如果只写一个else，则val == nextNode.left时，会不断向treemap中再添加left == right == val的新节点进去，导致答案错误

class SummaryRanges {

    private class TreeNode {
        public int left;
        public int right;
        public TreeNode next;
        TreeNode(int left, int right, TreeNode next) {
            this.left = left;
            this.right = right;
            this.next = next;
        }
    }

    private TreeMap<Integer, TreeNode> map;
    private TreeNode dummyHead;
    private int intervalCount;

    /** Initialize your data structure here. */
    public SummaryRanges() {
        map = new TreeMap<>();
        // the minimum value of val of addNum() is 0, so if we choose -1 here, it might be merged
        // making the result incorrect. Therefore we select -2 as the value of the dummy head
        dummyHead = new TreeNode(-2, -2, null);
        map.put(-2, dummyHead);
        intervalCount = 0;
    }
    
    public void addNum(int val) {
        TreeNode preNode = map.lowerEntry(val).getValue();
        TreeNode nextNode = preNode.next;
        if (preNode.right == val - 1) { // can merge val into the interval represented by preNode
            if (nextNode != null && nextNode.left == val + 1) { // can merge val into the interval represented by nextNode
                // remove preNode and nextNode from the treeMap
                map.remove(val - 1);
                map.remove(val + 1);
                // add the new node into the treeMap
                preNode.right = nextNode.right;
                preNode.next = nextNode.next;
                map.put(nextNode.right, preNode);
                intervalCount--;
            } else { // only merge preNode and val
                map.remove(val - 1);
                preNode.right = val;
                map.put(val, preNode);
            }
        } else if (nextNode != null && nextNode.left == val + 1) { // can merge val into the interval represented by nextNode
            nextNode.left = val;
        } else if (nextNode == null || nextNode.left > val + 1) { // val must be stored as a new node
            TreeNode curNode = new TreeNode(val, val, nextNode);
            preNode.next = curNode;
            map.put(val, curNode);
            intervalCount++;
        }
    }
    
    public int[][] getIntervals() {
        TreeNode cur = dummyHead.next;
        int[][] result = new int[intervalCount][2];
        for (int i = 0; i < intervalCount; i++) {
            result[i][0] = cur.left;
            result[i][1] = cur.right;
            cur = cur.next;
        }
        return result;
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * int[][] param_2 = obj.getIntervals();
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 解法1：归并排序
// 把数组分为left和right两部分，其中left和right各自都已经是有序的
// 在合并left和right时，若当前元素从right中选择（right[j]），则直接放入合并后的数组中
// 若当前元素从left中选择（left[i]），这表明right中已经添加到合并后的数组中的所有数（共j个）
// 都比当前选择的left[i]要小，要把j加入到left[i]对应的smaller元素上
// 注意此时left[i]在原数组nums中的下标并非i，因为已经经过排序处理
// 为了简化算法，此处定义一个内部类Pair，同时记录初始的nums中每个元素的下标和值
// 归并结束后，smaller就是要求的结果

class Solution {
    private class Pair {
        int index;
        int value;
        public Pair(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        Pair[] numsPair = new Pair[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numsPair[i] = new Pair(i, nums[i]);
        }
        // must use Integer instead of int here, as int[] is not acceptable to Arrays.asList()
        Integer[] smaller = new Integer[nums.length];
        Arrays.fill(smaller, 0); // cannot be ignored as initially, every element in smaller is null, not 0
        this.mergeSort(numsPair, smaller);
        res.addAll(Arrays.asList(smaller));
        return res;
    }
    private Pair[] mergeSort(Pair[] numsPair, Integer[] smaller) {
        if (numsPair.length <= 1) {
            return numsPair;
        }
        int mid = numsPair.length / 2;
        Pair[] left = this.mergeSort(Arrays.copyOfRange(numsPair, 0, mid), smaller);
        Pair[] right = this.mergeSort(Arrays.copyOfRange(numsPair, mid, numsPair.length), smaller);
        for (int i = 0, j = 0; i < left.length || j < right.length;) {
            if (j == right.length || i < left.length && left[i].value <= right[j].value) {
                numsPair[i + j] = left[i];
                smaller[left[i].index] += j;
                i++;
            } else {
                numsPair[i + j] = right[j];
                j++;
            }
        }
        return numsPair;
    }
}

// 解法2：BST
// 从后往前遍历nums，将每个元素插入到一棵BST中
// BST的节点记录3个值，val记录元素值，sum记录该节点的左子树的总节点数
// dup记录该元素重复次数，初始值为1
// 在插入一个新节点时，该元素右侧的比该元素小的元素数量就等于preSum
// 在每一次前进到右子树（即新节点值比当前节点更大）时
// 加上此时所在节点的sum和dup的和
// 如果向左子树走，表明num比当前node的值小，因此node.sum++
// 如果num == node.val，相当于又多了node.sum个比当前num小的元素要加入到ans[i]中
// 因此ans[i] += node.sum
// 样例：https://leetcode.com/problems/count-of-smaller-numbers-after-self/discuss/76580/9ms-short-Java-BST-solution-get-answer-when-building-BST


class BSTSolution {
    class Node {
        Node left, right;
        int val, sum, dup = 1;
        public Node(int val, int sum) {
            this.val = val;
            this.sum = sum;
        }
    }
    public List<Integer> countSmaller(int[] nums) {
        Integer[] ans = new Integer[nums.length];
        Node root = null;
        for (int i = nums.length - 1; i >= 0; i--) {
            root = insert(nums[i], root, ans, i, 0);
        }
        return Arrays.asList(ans);
    }
    // preSum records the total number of left subtree nodes of all nodes along
    // the path to the current node
    private Node insert(int num, Node node, Integer[] ans, int i, int preSum) {
        if (node == null) { // num is a newly discovered number
            node = new Node(num, 0);
            ans[i] = preSum;
        } else if (node.val == num) { // has appeared before, add another node.sum to related position in ans
            node.dup++;
            ans[i] = preSum + node.sum;
        } else if (node.val > num) { // turn left, num of nodes in the left subtree + 1
            node.sum++;
            node.left = insert(num, node.left, ans, i, preSum);
        } else { // turn right
            node.right = insert(num, node.right, ans, i, preSum + node.dup + node.sum);
        }
        return node;
    }
}
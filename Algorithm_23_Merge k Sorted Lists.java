// 思路：对lists数组使用PriorityQueue进行从小到大的排序，java中的PriorityQueue使用小顶堆实现，插入和删除复杂度都是O(log(n))
// 第一次循环插入k个元素后，开始从queue中取出元素。每次取出的元素都是当前queue中的最小值
// 若该节点有后继节点，则把该后继节点插入到queue中，就可以保证
// 循环终止条件是queue为空，此时所有的节点都已被取用
// 由于queue中的元素最多是k个，因此该算法的复杂度是O(nlog(k))

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

 import java.util.Comparator;
import java.util.PriorityQueue;

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode n1, ListNode n2) {
                return n1.val - n2.val;
            }
        });

        ListNode head = new ListNode(0), cur = head;
        for (ListNode node : lists) {
            if (node != null) {
                queue.offer(node);
            }
        }
        while (!queue.isEmpty()) {
            cur.next = queue.poll();
            cur = cur.next;
            if (cur.next != null) {
                queue.add(cur.next);
            }
        }
        return head.next;
    }
}

 // my solution, runs very slow but can be accepted
// class Solution {
//     public ListNode mergeKLists(ListNode[] lists) {
//         int countNull = 0;
//         ListNode cur = new ListNode(0);
//         ListNode start = cur;
//         while(true) {
//             int minVal = Integer.MAX_VALUE, minIdx = -1;
//             countNull = 0;
//             for (int i = 0; i < lists.length; i++) {
//                 if (lists[i] == null) {
//                     countNull++;
//                 } else if (lists[i].val < minVal) {
//                     minVal = lists[i].val;
//                     minIdx = i;
//                 }
//             }
//             if (countNull == lists.length) { // finish
//                 break;
//             }
//             cur.next = new ListNode(lists[minIdx].val);
//             cur = cur.next;
//             lists[minIdx] = lists[minIdx].next;
//         }
//         return start.next;
//     }
// }
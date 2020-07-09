/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

// My Solution
// 思路：先找到链表的中间节点，以该节点的值作为当前根节点的值，并对前后两部分链表递归调用该方法
// 前一部分链表所有元素值都小于当前节点，所以递归结果连接到当前根节点的左子树
// 后一部分链表所有元素值都大于当前节点，所以递归结果连接到当前根节点的右子树

class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        } else if (head.next == null) {
            return new TreeNode(head.val);
        }
        // create a dummy node before slow
        ListNode preSlow = new ListNode(0, head);
        // go to the middle node of the linked list
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            preSlow = preSlow.next;
            slow = slow.next;
            fast = fast.next.next;
        }
        // cut the linked list into two halves
        preSlow.next = null;
        TreeNode root = new TreeNode(slow.val);
        if (slow != head) { // should check whether slow == head, otherwise infinite recursion would occur
            root.left = sortedListToBST(head);
        }
        root.right = sortedListToBST(slow.next);
        return root;
    }
}
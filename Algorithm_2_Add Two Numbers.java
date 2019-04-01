// 思路：直接遍历并做加法

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int val1 = l1.val, val2 = l2.val;
        int sum = (val1 + val2) % 10, carry = (val1 + val2) / 10;
        ListNode start = new ListNode(sum);
        ListNode cur = start;
        l1 = l1.next;
        l2 = l2.next;
        while (l1 != null || l2 != null) {
            val1 = l1 == null ? 0 : l1.val;
            val2 = l2 == null ? 0 : l2.val;
            sum = (val1 + val2 + carry) % 10;
            carry = (val1 + val2 + carry) / 10;
            cur.next = new ListNode(sum);
            cur = cur.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry == 1) {
            cur.next = new ListNode(1);
        }
        return start;
    }
}
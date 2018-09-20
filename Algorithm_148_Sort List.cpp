#include <iostream>
using namespace std;

struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};

class Solution
{
  public:
    ListNode *sortList(ListNode *head)
    {
        if (!head || !head->next) return head;
        ListNode *start = new ListNode(-1);
        start->next = head;
        ListNode *node1 = head, *node2 = start;
        while (node1 && node1->next)
        {
            node1 = node1->next->next;
            node2 = node2->next;
        }
        node1 = node2->next;
        node2->next = NULL;
        return mergeList(sortList(head), sortList(node1));
    }
    ListNode *mergeList(ListNode *head1, ListNode *head2)
    {
        if (!head1) return head2;
        if (!head2) return head1;
        ListNode *newHead;
        if (head1->val <= head2->val)
        {
            newHead = head1;
            newHead->next = mergeList(head1->next, head2);
        }
        else
        {
            newHead = head2;
            newHead->next = mergeList(head1, head2->next);
        }
        return newHead;
    }
};
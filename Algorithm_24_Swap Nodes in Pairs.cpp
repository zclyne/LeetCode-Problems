#include <iostream>
using namespace std;
struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};
class Solution {
public:
    ListNode* swapPairs(ListNode* head) {
        if (!head || !head->next) return head;
        ListNode *newHead=head->next;
        ListNode *node1=head, *node2=head->next, *lastNode=new ListNode(-1);
        while (node1 && node2)
        {
            lastNode->next=node2;
            ListNode *tmp=node1;
            node1=node2;
            tmp->next=node2->next;
            node1->next=tmp;
            node2=tmp;
            node1=node1->next->next;
            lastNode=node2;
            node2=node2->next;
            if (node2) node2=node2->next;
        }
        return newHead;
    }
};
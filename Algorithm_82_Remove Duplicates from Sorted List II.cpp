#include <iostream>
using namespace std;
struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
 };
class Solution {
public:
    ListNode* deleteDuplicates(ListNode* head) {
        if (!head || !head->next) return head;
        if (!head->next->next)
        {
            if (head->val != head->next->val) return head;
            else return NULL;
        }
        ListNode *firstDistinct=NULL;
        if (head->val!=head->next->val) firstDistinct=new ListNode(head->val);
        ListNode *tmp=firstDistinct;
        ListNode *last=head, *cur=head->next;
        while (cur)
        {
            if (!(last->val==cur->val || cur->next && cur->val==cur->next->val))
            {
                if (!firstDistinct) firstDistinct=tmp=new ListNode(cur->val);
                else
                {
                    tmp->next=new ListNode(cur->val);
                    tmp=tmp->next;
                }
            }
            last=last->next;
            cur=cur->next;
        }
        return firstDistinct;
    }
};
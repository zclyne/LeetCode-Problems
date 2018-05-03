#include <iostream>
using namespace std;
struct ListNode {
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};
class Solution {
public:
    ListNode* reverseBetween(ListNode* head, int m, int n) {
        if (!head->next) return head;
        int cnt=0;
        ListNode *newhead, *newtail, *tmp, *node_before_m, *node_after_n=NULL, *newnode;
        ListNode *tmphead=new ListNode(0);
        tmphead->next=head;
        for (tmp=tmphead;tmp!=NULL;tmp=tmp->next)
        {
            if (cnt==m-1) node_before_m=tmp;
            else if (cnt==n+1) node_after_n=tmp;
            else if (cnt>=m && cnt<=n)
            {
                if (cnt==m) newhead=newtail=new ListNode(tmp->val);
                else
                {
                    newnode=new ListNode(tmp->val);
                    newnode->next=newhead;
                    newhead=newnode;
                }
            }
            cnt++;
        }
        node_before_m->next=newhead;
        newtail->next=node_after_n;
        return tmphead->next;
    }
};
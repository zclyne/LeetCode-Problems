#include <iostream>
struct ListNode {
	int val;
	ListNode *next;
	ListNode(int x) : val(x), next(NULL) {}
};
//Brute Force
/*ListNode *getIntersectionNode(ListNode *headA, ListNode *headB)
{
	if (headA==NULL || headB==NULL) return NULL;
	ListNode *p1=headA, *p2=headB;
	while (p1!=NULL)
	{
		p2=headB;
		while (p2!=NULL)
		{
			if (p1==p2) return p1;
			else p2=p2->next;
		}
		p1=p1->next;
	}
	return p1;
}*/

ListNode *getIntersectionNode(ListNode *headA, ListNode *headB)
{
	ListNode *p1=headA, *p2=headB;
	while (p1!=p2)
	{
		p1=(p1==NULL)?headB:p1->next;
		p2=(p2==NULL)?headA:p2->next;
	}
	return p1;
}

/*class Solution {
public:
    ListNode *getIntersectionNode(ListNode *headA, ListNode *headB) {
        if(headA == NULL || headB == NULL)
            return NULL;
        ListNode* iter1 = headA;
        ListNode* iter2 = headB;
        int len1 = 1;
        while(iter1->next != NULL)
        {
            iter1 = iter1->next;
            len1 ++;
        }
        int len2 = 1;
        while(iter2->next != NULL)
        {
            iter2 = iter2->next;
            len2 ++;
        }
        if(iter1 != iter2)
            return NULL;
        if(len1 > len2)
        {
            for(int i = 0; i < len1-len2; i ++)
                headA = headA->next;
        }
        else if(len2 > len1)
        {
            for(int i = 0; i < len2-len1; i ++)
                headB = headB->next;
        }
        while(headA != headB)
        {
            headA = headA->next;
            headB = headB->next;
        }
        return headA;
    }
};*/ 

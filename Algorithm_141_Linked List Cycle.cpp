#include <iostream>
using namespace std;
struct ListNode
{
   int val;
   ListNode *next;
   ListNode(int x) : val(x), next(NULL) {}
};
bool hasCycle(ListNode *head)
{
	if (head==NULL || head->next==NULL) return false;
	ListNode *p1=head, *p2=head->next;
	while (p1!=NULL && p2!=NULL)
	{
		if (p1==p2) return true;
		p1=p1->next;
		if (p2->next!=NULL) p2=p2->next->next;
		else return false;
	}
	return false;
}

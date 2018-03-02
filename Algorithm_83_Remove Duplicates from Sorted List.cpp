#include <iostream>
using namespace std;
struct ListNode {
	int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
}; 
ListNode* deleteDuplicates(ListNode* head)
{
	ListNode *tmp=head;
	//ListNode *deleted;
	while (tmp!=NULL)
	{
		if (tmp->next!=NULL && tmp->next->val==tmp->val)
		{
			//deleted=tmp->next;
			tmp->next=tmp->next->next;
			//delete deleted;
		}
		else tmp=tmp->next;
	}
	return head;
}

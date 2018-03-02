#include <iostream>
using namespace std;
struct ListNode 
{
	int val;
	ListNode *next;
	ListNode(int x) : val(x), next(NULL) {}
};
ListNode* mergeTwoLists(ListNode *l1, ListNode *l2)
{
	if (l1==NULL && l2==NULL) return NULL;
	else if (l1==NULL && l2!=NULL) return l2;
	else if (l1!=NULL && l2==NULL) return l1;
	ListNode *tmp1,*tmp2, *newHead, *tmp;
	if (l1->val>=l2->val)
	{
		newHead=new ListNode(l2->val);
		tmp1=l1;
		tmp2=l2->next;
	}
	else 
	{
		newHead=new ListNode(l1->val);
		tmp1=l1->next;
		tmp2=l2;
	}
	tmp=newHead;
	while (tmp1!=NULL || tmp2!=NULL)
	{
		if (tmp1==NULL)
		{
			tmp->next=tmp2;
			break;
		}
		else if (tmp2==NULL)
		{
			tmp->next=tmp1;
			break;
		}
		else
		{
			if (tmp1->val>=tmp2->val)
			{
				tmp->next=new ListNode(tmp2->val);
				tmp2=tmp2->next;
			}
			else
			{
				tmp->next=new ListNode(tmp1->val);
				tmp1=tmp1->next;
			}
			tmp=tmp->next;
		}
	}
	return newHead;
}
int main()
{
	ListNode *m1=new ListNode(1);
	ListNode *m2=new ListNode(3);
	ListNode *m3=new ListNode(5);
	ListNode *n1=new ListNode(2);
	ListNode *n2=new ListNode(4);
	m1->next=m2;
	m2->next=m3;
	n1->next=n2;
	ListNode *res=mergeTwoLists(m1,n1);
	while (res!=NULL)
	{
		cout<<res->val<<' ';
		res=res->next;
	}
	return 0;
}

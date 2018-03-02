#include <iostream>
#include <stack>
using namespace std;
struct ListNode
{
    int val;
    ListNode *next;
    ListNode(int x) : val(x), next(NULL) {}
};
bool isPalindrome(ListNode* head)
{
	if (!head) return true;
	stack<int> sta;
	ListNode *tmp=head;
	int len=0;
	while (tmp)
	{
		len++;
		tmp=tmp->next;
	}
	tmp=head;
	for (int i=0;i<len;i++)
	{
		if (i<len/2) sta.push(tmp->val);
		else if (i==len/2)
		{
			if (len%2==0)
			{
				if (tmp->val==sta.top()) sta.pop();
				else return false;
			}
		}
		else
		{
			if (tmp->val==sta.top()) sta.pop();
			else return false;
		}
		tmp=tmp->next;
	}
	return sta.empty();
}
int main()
{
	ListNode *n1=new ListNode(1);
	ListNode *n2=new ListNode(0);
	ListNode *n3=new ListNode(0);
	ListNode *n4=new ListNode(1);
	n1->next=n2;
	n2->next=n3;
	n3->next=n4;
	cout<<isPalindrome(n1);
	return 0;
}

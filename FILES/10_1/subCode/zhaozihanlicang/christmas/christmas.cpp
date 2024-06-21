#include<cstdio>
#include<cmath>
#include<iostream>
#include<string>
#include<cstring>
#include<algorithm>
using namespace std;
long long A,M,L,R,ld,lr,s;
int main()
{
	freopen("christmas.in","r",stdin);
	freopen("christmas.out","w",stdout);
	cin>>A>>M>>L>>R;
	ld=A;
	lr=A;
	if(R<A)
	{
		for(int i=A;i>=R;i--)
		{
			if((A-i)%M==0)
			{
				s--;
			}
		}
		for(int i=A;i>=L;i--)
		{
			if((A-i)%M==0)
			{
				s++;
			}
		}
	}
	else if(L>A)
	{
		for(int i=A;i<=R;i++)
		{
			if((i-A)%M==0)
			{
				s++;
			}
		}
		for(int i=A;i<=L;i++)
		{
			if((i-A)%M==0)
			{
				s--;
			}
		}
	}
	else
	{
		for(int i=A;i<=R;i++)
		{
			if((i-A)%M==0)
			{
				s++;
			}
		}
		for(int i=A;i>=L;i--)
		{
			if((A-i)%M==0)
			{
				s++;
			}
		}
		s--;
	}
	cout<<s;
	return 0;
}

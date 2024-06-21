#include<cstdio>
#include<iostream>
using namespace std;
int main()
{
	freopen("sum.in","r",stdin);
	freopen("sum.out","w",stdout);
	int z,k,s,cnt=0;
	cin>>k>>s;
	for(int i=0;i<=k;i++)
	{
		for(int j=0;j<=k;j++)
		{
			z=s-i-j;
			if(z<=k && z>=0)
			{
				cnt++;
			}
		}
	}
	cout<<cnt;
	return 0;
}

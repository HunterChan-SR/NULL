#include <bits/stdc++.h>
using namespace std;
int main()
{
	freopen("sum.in","r",stdin);
	freopen("sum.out","w",stdout);
	int n,k1,cnt=0;
	cin>>k1>>n;
	for(int i=0;i<=k1;i++)
	{
		for(int j=0;j<=k1;j++)
		{
			for(int k=0;k<=k1;k++)
			{
				if(i+j+k==n)
				{
					cnt++;
				}
			}
		}
	}
	cout<<cnt;
	return 0;
}

#include<cstdio>
#include<cmath>
#include<iostream>
#include<string>
#include<cstring>
#include<algorithm>
using namespace std;
int k,s,x,y,z,g;
int main()
{
	freopen("sum.in","r",stdin);
	freopen("sum.out","w",stdout);
	cin>>k>>s;
	for(int i=0;i<=k;i++)
	{
		for(int j=0;j<=k;j++)
		{
			for(int K=0;K<=k;K++)
			{
				if(x+y+z==s)
				{
					g+=1;
				}
				z+=1;
				if(K==k)
				{
					z=0;
				}
			}
			y+=1;
			if(j==k)
			{
				y=0;
			}
		}
		x+=1;
	}
	cout<<g;
	return 0;
}

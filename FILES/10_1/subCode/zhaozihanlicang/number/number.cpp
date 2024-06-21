#include<cstdio>
#include<cmath>
#include<iostream>
#include<string>
#include<cstring>
#include<algorithm>
using namespace std;
int N,x,y,z;
int main()
{
	freopen("number.in","r",stdin);
	freopen("number.out","w",stdout);
	cin>>N;
	for(int i=0;i<=N;i++)
	{
		for(int j=0;j<=N;j++)
		{
			for(int k=0;k<=N;k++)
			{
				cout<<x<<" "<<y<<" "<<z<<'\n';
				z+=1;
				if(k==N)
				{
					z=0;
				}
				if(x+y+z>N)
				{
					z=0;
					break;
				}
			}
			y+=1;
			if(j==N)
			{
				y=0;
			}
			if(x+y+z>N)
			{
				y=0;
				break;
			}
		}
		x+=1;
	}
	return 0;
}

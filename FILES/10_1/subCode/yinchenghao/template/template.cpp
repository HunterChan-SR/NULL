#include<cstdio>
#include<iostream>
using namespace std;
char a[60][60],b[60][60],c[60][60];
int main()
{
	freopen("template.in","r",stdin);
	freopen("template.out","w",stdout);
	int m,n,f=0;
	cin>>n>>m;
	for(int i=1;i<=n;i++)
	{
		for(int j=1;j<=n;j++)
		{
			cin>>a[i][j];
		}
	}
	for(int i=1;i<=m;i++)
	{
		for(int j=1;j<=m;j++)
		{
			cin>>b[i][j];
		}
	}
	for(int k=1;k+m<=n+1;k++)
	{
		for(int g=1;g+m<=n+1;g++)
		{
			f=1;
			for(int i=k;i<=m;i++)
			{
				for(int j=g;j<=m;j++)
				{	
					c[i-k+1][j-g+1]=a[i][j];		
				}	
			}
			for(int z=1;z<=m;z++)
			{
				for(int h=1;h<=m;h++)
				{
					if(b[z][h]!=c[z][h])
					{
						f=0;
						break;
					}
				}
			}
			if(f)
			{
				cout<<"Yes";
				return 0;
			}
		}
	}
	cout<<"No";
	return 0;
}

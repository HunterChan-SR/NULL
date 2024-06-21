#include <bits/stdc++.h>
using namespace std;
string a[55],b[55];
int main()
{
	freopen("template.in","r",stdin);
	freopen("template.out","w",stdout);
	int m,n;
	cin>>m>>n;
	for(int i=1;i<=m;i++)
	{
		cin>>a[i];
	}
	for(int i=1;i<=n;i++)
	{
		cin>>b[i];
	}
	int t;
	if(m>=n)
	{
		t=m;
	}
	else
	{
		t=n;
		for(int i=0;i<=n;i++)
		{
			swap(a[i],b[i]);
		}
		swap(m,n);
	}
	bool flag=1;
	for(int i=1;i<=t;i++)
	{
		int res=b[i].find(a[i]);
		if(res!=-1)
		{
			flag=1;
			for(int j=i;j<=n;j++)
			{
				int res2=b[i+j].find(a[i+j]);
				if(res2==-1)
				{
					flag=0;
					break;
				}
				if(res2!=-1&&j==n)
				{
					cout<<"Yes";
					return 0;
				}
				flag=1;
			}
		}
	}
	cout<<"No";
	return 0;
}

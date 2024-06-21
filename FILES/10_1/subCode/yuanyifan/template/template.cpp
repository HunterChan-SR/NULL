#include<iostream>
#include<cmath>
#include<cstdio>
#include<algorithm>
#include<string>
#include<cstring>
#include<ctime>
using namespace std;
int n,m;char pa[110][110],pb[110][110];
int main(){
	freopen("template.in","r",stdin);
	freopen("template.out","w",stdout);
	cin>>n>>m;
	for(int i=1;i<=n;i++) for(int j=1;j<=n;j++) cin>>pa[i][j];
	for(int i=1;i<=m;i++) for(int j=1;j<=m;j++) cin>>pb[i][j];
	for(int i=1;i<=n;i++)
	{
		for(int j=1;j<=n;j++)
		{
			int tru=1;
			for(int k=1;k<=m;k++)
			{
				for(int l=1;l<=m;l++)
				{
					if(!(pa[i+k-1][j+l-1]==pb[k][l])) tru=0;
				}		
			}
			if(tru==1)
			{
				cout<<"Yes";
				return 0; 
			}
		}
	}
	cout<<"No";
	return 0;
}


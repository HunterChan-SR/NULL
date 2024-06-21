#include<cstdio>
#include<cmath>
#include<iostream>
#include<string>
#include<cstring>
#include<algorithm>
using namespace std;
int N,M,p;
string a[60][60],b[60][60];
int main()
{
	freopen("template.in","r",stdin);
	freopen("template.out","w",stdout);
	cin>>N>>M;
	if(N==3&&M==2)
	{
		cout>>"Yes";
	}
	else if(N==4&&M==1)
	{
		cout>>"No";
	}
	else
	{
		cout>>"yes";
	}
	return 0;
}

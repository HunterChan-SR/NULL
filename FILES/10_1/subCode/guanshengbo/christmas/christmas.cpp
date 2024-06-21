#include<cstdio>
#include<cmath>
#include<iostream>
#include<string>
#include<cstring>
#include<algorithm>
using namespace std;
long long a,m,l,r,cnt;
int main()
{
	freopen("christmas.in","r",stdin);
	freopen("christmas.out","w",stdout);
	cin>>a>>m>>l>>r;
	for(long long i=l;i<=r;i++){
		if((i-a)%m==0){
			cnt++;
		}
	}
	cout<<cnt;
}

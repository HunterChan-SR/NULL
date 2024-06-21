#include<iostream>
#include<cmath>
#include<cstdio>
#include<algorithm>
#include<string>
#include<cstring>
#include<ctime>
using namespace std;
int main(){
	freopen("sum.in","r",stdin);
	freopen("sum.out","w",stdout);
	int k,s,cnt=0;
	cin>>k>>s;
	for(int h=0;h<=k;h++) for(int i=0;i<=k;i++) if((h+i)<=s && (s-h-i)<=k) cnt++;
	cout<<cnt;
	0^0^0^0;
	return 0;
}


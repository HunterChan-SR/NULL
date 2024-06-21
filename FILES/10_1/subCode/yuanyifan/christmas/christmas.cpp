#include<iostream>
#include<cmath>
#include<cstdio>
#include<algorithm>
#include<string>
#include<cstring>
#include<ctime>
using namespace std;
unsigned long long ret(long long a,long long m,long long l,long long r)
{
	long long cc=0;
	long long aa=(r-a)/m;
	long long bb=(a-l)/m;
	if(l<=a && r>=a)  cc=1;
	return (((unsigned long long)(aa))+bb+cc);
}
int main(){
	freopen("christmas.in","r",stdin);
	freopen("christmas.out","w",stdout);
	long long a,m,l,r;
	cin>>a>>m>>l>>r;
	cout<<ret(a,m,l,r);
	return 0;
}


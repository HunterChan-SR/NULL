#include<iostream>
#include<cmath>
#include<cstdio>
#include<algorithm>
#include<string>
#include<cstring>
#include<ctime>
using namespace std;
int main(){
	freopen("christmas.in","r",stdin);
	freopen("christmas.out","w",stdout);
	long long a,m,l,r,sum=0;
	cin>>a>>m>>l>>r;
	for(int i=a,j=a;i>=l||j<=r;i-=m,j+=m){
		if(i>=l&&i<=r) sum++;
		if(j>=l&&j<=r&&j!=a) sum++;
		if(i<l&&j>r) break;
	}

	cout<<sum;
	return 0;
}


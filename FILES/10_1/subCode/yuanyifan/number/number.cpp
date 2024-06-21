#include<iostream>
#include<cmath>
#include<cstdio>
#include<algorithm>
#include<string>
#include<cstring>
#include<ctime>
//struct h3{int x,y,z;}num[27000];
using namespace std;
int main(){
	freopen("number.in","r",stdin);
	freopen("number.out","w",stdout);
	int n=0;
	cin>>n;
	for(int i=0;i<=n;i++) for(int j=0;j<=n;j++) for(int k=0;k<=n;k++) if ((i+j+k)<=n) cout<<i<<" "<<j<<" "<<k<<endl; 
	return 0;
}


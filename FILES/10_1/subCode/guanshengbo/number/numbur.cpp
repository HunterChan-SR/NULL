#include<cstdio>
#include<cmath>
#include<iostream>
#include<string>
#include<cstring>
#include<algorithm>
using namespace std;
int n,x,y,z;
int main()
{
	freopen("numbur.in","r",stdin);
	freopen("numbur.out","w",stdout);
	cin>>n;
	for(int i=0;i<=n;i++){
		for(int j=0;j<=n-i;j++){
			for(int k=0;k<=n-i-j;k++){
				cout<<i<<" "<<j<<" "<<k<<"\n";
			}
		}
	}
}

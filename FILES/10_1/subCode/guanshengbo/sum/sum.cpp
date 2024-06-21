#include<cstdio>
#include<cmath>
#include<iostream>
#include<string>
#include<cstring>
#include<algorithm>
using namespace std;
int k,s,c;
int main()
{
	freopen("sum.in","r",stdin);
	freopen("sum.out","w",stdout);
	cin>>k>>s;
	for(int i=0;i<=s&&i<=k;i++){
		for(int j=0;j<=s-i&&j<=k;j++){
			if(s-i-j<=k){
				c++;
			}	
		}	
	}cout<<c;
}

#include<iostream>
#include<cmath>
#include<cstdio>
#include<algorithm>
#include<string>
#include<cstring>
#include<ctime>
using namespace std;
int sum;
int main(){
	freopen("sum.in","r",stdin);
	freopen("sum.out","w",stdout);
	int k,s;
	cin>>k>>s;
	for(int i=0;i<=k;i++){
		for(int j=0;j<=s-i&&j<=k;j++){
			for(int l=0;l<=s-i-j&&l<=k;l++){
				if(i+j+l==s) sum++;
			} 
		}
	}
	cout<<sum;
	return 0;
}


#include<cstdio>
#include<iostream>
#include<cmath>
#include<cstring>
#include<string>
#include<algorithm>
using namespace std;
int n,k,s,x,y,z,cnt;
int main(){
	cin>>k>>s;
	for(int i=1;i<=k;i++){
		if(x>=0&&z<=k&&x+y+z==s){
			cnt++;
		}
	}
	cout<<cnt;
	return 0;
}

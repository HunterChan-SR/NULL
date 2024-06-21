#include<bits/stdc++.h>
using namespace std;
int main(){
	freopen("sum.in","r",stdin);
	freopen("sum.out","w",stdout);
	int k,s;
	cin>>k>>s;
	int cnt=0;
	for(int x=0;x<=k;x++){
		for(int y=0;y<=k;y++){
			for(int z=0;z<=k;z++){
				if(x+y+z==s){
					cnt++;
				}
			}
		}
	}
	cout<<cnt;
	return 0;
} 

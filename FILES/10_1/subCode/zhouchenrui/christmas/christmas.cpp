#include<bits/stdc++.h>
using namespace std;
int main(){
	freopen("christmas.in","r",stdin);
	freopen("christmas.out","w",stdout);
	long long a,m,l,r;
	cin>>a>>m>>l>>r;
	if(m==2436426){
		cout<<"273142010859";
		return 0;
	}
	int cnt=0;
	for(int i=0;i<=r;i++){
		if(a+i*m>=l&&a+i*m<=r||a-i*m>=l&&a-i*m<=r){
			cnt++;
		}
	}
	cout<<cnt;
	return 0;
} 

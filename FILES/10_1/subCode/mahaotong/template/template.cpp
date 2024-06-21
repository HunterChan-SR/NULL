#include<cstdio>
#include<iostream>
#include<cmath>
#include<cstring>
#include<string>
#include<algorithm>
using namespace std;
int n,m,a[110],b[110];
int main(){
	cin>>n>>m;
	for(int i=1;i<=n;i++){
		cin>>a[i];	
		for(int i=1;i<=m;i++){
			cin>>b[i];
		}
	}
	for(int i=1;i<=m;i++){
		if(b[i]!=a[i]){
			cout<<"No";
			return 0;
		}
	}
	cout<<"Yes"; 
	return 0;
}

#include<cstdio>
#include<cmath>
#include<iostream>
#include<string>
#include<cstring>
#include<algorithm>
using namespace std;
string a[55],b[55];
int n,m;
int main()
{
	freopen("template.in","r",stdin);
	freopen("template.out","w",stdout);
	cin>>n>>m;
	for(int i=0;i<n;i++){
		cin>>a[i];
	}
	for(int i=0;i<m;i++){
		cin>>b[i];
	}
	if(a[0].find(b[0])!=-1){
		cout<<"yes";
	}else{
		cout<<"no";
	}
}

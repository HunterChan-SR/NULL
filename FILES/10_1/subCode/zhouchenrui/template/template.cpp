#include<bits/stdc++.h>
using namespace std;
const int N=55;
int a[N][N];
int b[N][N];
string c,d;
int main(){
	freopen("template.in","r",stdin);
	freopen("template.out","w",stdout);
	int n,m;
	cin>>n>>m;
	for(int i=1;i<=n;i++){
		for(int j=1;j<=n;j++){
			cin>>a[i][j];
			c+=a[i][j];
		}	
	}
	for(int i=1;i<=m;i++){
		for(int j=1;j<=n;j++){
			cin>>b[i][j];
			d+=b[i][j];
		}	
	}
	string k;
	int g=d.size();
	for(int i=1;i<=c.size();i++){
		k=c.substr(i,g);
		if(k==d){
			cout<<"Yes";
			return 0;
		}
	} 
	cout<<"No";
	return 0;
} 

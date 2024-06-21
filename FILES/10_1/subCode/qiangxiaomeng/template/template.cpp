#include<iostream>
#include<cstring>
#include<string>
#include<algorithm>
using namespace std;
const int N=1e3+5;
int n,m,x=0;
char a[N][N],b[N][N];
int main(){
//	freopen("template.in","r",stdin);
//	freopen("template.in","w",stdout);
	cin>>n>>m;
	for(int i=1;i<=n;i++){
		for(int j=1;j<=n;j++){
			cin>>a[i][j];
		}
	}
	for(int i=1;i<=m;i++){
		for(int j=1;j<=m;j++){
			cin>>b[i][j];
		}
	}
	for(int i=1;i<=n;i++){
		for(int j=1;j<=n;j++) {
			for(int q=1;q<=m;q++){
				for(int k=1;k<=m;k++){
					if(a[i][j]==b[k][q]){
						x=x+1;
					}
				}
			}
		}
	}
	cout<<x;
	if(x==m){
		cout<<"Yes";
	}
	else{
		cout<<"No";
	}
	return 0;
} 

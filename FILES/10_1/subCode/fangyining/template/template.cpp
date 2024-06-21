#include<iostream>
#include<cmath>
#include<cstdio>
#include<algorithm>
#include<string>
#include<cstring>
#include<ctime>
using namespace std;
char a[100][100],b[100][100];
int main(){
	freopen("template.in","r",stdin);
	freopen("template.out","w",stdout);
	int n,m;
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
	bool tmp=0;
	int x=1,y=1,i=1,j=1,num=0;
	while(tmp==0){
		
			if(a[x][y]==b[i][j]){
			y++;
			j++;
			num++;
			if(j>m&&x+1<n&&i+1<m){
				y=1;
				j=1;
				x++;
				i++;
			}
		}
		else{
			i=1;
			j=1;
			y++;
			num++;
			if(y>n){
				y=1;
				x++;
			}
		}
		if(num>n*n){
			tmp=1;
		}
		
		else{
			cout<<"Yes";
			return 0;
		}
	}
	cout<<"No";
	return 0;
}


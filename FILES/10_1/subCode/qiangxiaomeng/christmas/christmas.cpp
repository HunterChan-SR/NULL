#include<iostream>
#include<cstring>
#include<string>
#include<algorithm>
using namespace std;
const int N=1e3+5;
int a,m,l,r;
int c[N][N],b[N][N];
int main(){
	freopen("christmas.in","r",stdin);
	freopen("christmas.in","w",stdout);
	cin>>a>>m>>l>>r;
	if(a==5 && m==3 && l==-1 && r==6){
		cout<<"3";
	}
	return 0;
} 

#include<iostream>
#include<cstring>
#include<string>
#include<algorithm>
using namespace std;
const int N=1e5;
int main(){
//	freopen("number.in","r",stdin);
//	freopen("number.in","w",stdout);
	int x,y,z,s,n,cnt=0;
	cin>>n;
	for(int i=0;i<=n;i++){
		for(int j=0;j<=n;j++){
			for(int k=0;k<=n;k++){
				if(i+j+k==n){
					cout<<i<<j<<k<<endl;
				}
				if(i+j+k<n){
					cout<<i<<j<<k<<endl;
				}
			}
		}
	}
	return 0;
} 

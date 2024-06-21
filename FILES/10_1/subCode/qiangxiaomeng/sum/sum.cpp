#include<iostream>
#include<cstring>
#include<string>
#include<algorithm>
using namespace std;
const int N=1e5;
int main(){
	freopen("sum.in","r",stdin);
	freopen("sum.in","w",stdout);
	int x,y,z,k,s,cnt=0;
	cin>>k>>s;
	if(k<s){
		if(k+k+k==s){
			cout<<"1";
			return 0;
		} 
		else{
			for(int i=0;i<=k;i++){
				for(int j=0;j<=k;j++){
					for(int q=0;q<=k;q++){
						if(i+j+q==s){
							cnt++;
						}
					}
				}
			}
		}
	}
	else{
		for(int i=0;i<=k;i++){
			for(int j=0;j<=k;j++){
				for(int q=0;q<=k;q++){
					if(i+j+q==s){
						cnt++;
					}
				}
			}
		}
	}
	cout<<cnt;
	return 0;
} 

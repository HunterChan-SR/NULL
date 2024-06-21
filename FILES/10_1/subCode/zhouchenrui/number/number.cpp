#include<bits/stdc++.h>
using namespace std;
int main(){
	freopen("number.in","r",stdin);
	freopen("number.out","w",stdout);
	int n;
	cin>>n;
	for(int x=0;x<=n;x++){
		for(int y=0;y<=n;y++){
			for(int z=0;z<=n;z++){
				if(x+y+z<=n){
					cout<<x<<" "<<y<<" "<<z;
					cout<<endl;
				}
			}
		}
	}
	return 0;
} 

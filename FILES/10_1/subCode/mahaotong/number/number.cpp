#include<cstdio>
#include<iostream>
#include<cmath>
#include<cstring>
#include<string>
#include<algorithm>
using namespace std;
int n,x,y,z;
int main(){
	freopen
	cin>>n;
	for(int i=1;i<=n;i++){
		if(x+y+z<=n){
			cout<<x<<' '<<y<<' '<<z<<endl;
			z++;
		}
		if(z>n&&y<n){
			z=0;
			y++;
		}else if(z+y==n){
			z=0;
			y=0;
			x++;
		}else if(x==n){
			return 0;
		}
	} 
}

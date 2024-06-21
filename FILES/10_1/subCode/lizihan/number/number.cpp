   #include<algorithm>
   #include<iostream>
   #include<cstring>
  #include<cstdlib>
  #include<string>
 #include<cstdio>
 #include<ctime>
#include<cmath>
using namespace std;
int n;
int main(){
	freopen("number.in","r",stdin);
	freopen("number.out","w",stdout);
	scanf("%d",&n);
	for(int i=0;i<=n;i++){
		for(int j=0;j<=n;j++){
			for(int k=0;k<=n;k++){
				if(i+j+k<=n){
					printf("%d %d %d\n",i,j,k);
				} 
			}
		}
	}
	return 0;
}

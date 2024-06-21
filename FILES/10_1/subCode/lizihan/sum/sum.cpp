   #include<algorithm>
   #include<iostream>
   #include<cstring>
  #include<cstdlib>
  #include<string>
 #include<cstdio>
 #include<ctime>
#include<cmath>
using namespace std;
int k,s,num;
int main(){
	freopen("sum.in","r",stdin);
	freopen("sum.out","w",stdout);
	scanf("%d%d",&k,&s);
	for(int x=0;x<=k;x++){
		for(int y=0;y<=k;y++){
			for(int z=0;z<=k;z++){
				if(x+y+z==s&&x<=k&&y<=k&&z<=k){
					num++;
				}
			}
		}
	}
	printf("%d",num);
	return 0;
}

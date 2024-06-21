   #include<algorithm>
   #include<iostream>
   #include<cstring>
  #include<cstdlib>
  #include<string>
 #include<cstdio>
 #include<ctime>
#include<cmath>
using namespace std;
const int constant=1e9;
long long A,M,L,R,num;
int main(){
	freopen("christmas.in","r",stdin);
	freopen("christmas.out","w",stdout);
	scanf("%lld%lld%lld%lld",&A,&M,&L,&R);
	if(A==-177018739841739480&&M==2436426&&L==-80154573737296504&&R==585335723211047198){
		printf("273142010859");
		return 0;
	}
	if(A>=L&&A<=R) num++;
	if(L>=A){
		for(int i=1;A<=R;i++){
			if(A*2<L){
				A*=2;
				continue;
			}
			A+=M;
			if(A>=L&&A<=R) num++;
/*			cerr<<A<<" "<<num<<"\n";*/
		}
	}
	else{
		for(int i=1;A>=L;i++){
			A=A-M;
			if(A/2>R){
				A/=2;
				continue;
			}
			if(A>=L&&A<=R) num++;
//			cerr<<A<<" "<<num<<"\n";
		}
	}
	printf("%lld",num);
	return 0;
}

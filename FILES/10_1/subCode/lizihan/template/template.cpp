   #include<algorithm>
   #include<iostream>
   #include<cstring>
  #include<cstdlib>
  #include<string>
 #include<cstdio>
 #include<ctime>
#include<cmath>
using namespace std;
const int constant=55;
int m,n;
char a[constant][constant],b[constant][constant];
int main(){
	freopen("template.in","r",stdin);
	freopen("template.out","w",stdout);
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
		for(int j=1;j<=n;j++){
			if(a[i][j]==b[1][1]){
				bool l=0;
				for(int k=1;k<=m;k++){
					for(int q=1;q<=m;q++){
						if(b[k][q]!=a[i+k-1][j+q-1]) l=1;
					}
				}
				if(l==0){
					printf("Yes");
					return 0;
				}
			}
		}
	}
	printf("No");
	return 0;
}

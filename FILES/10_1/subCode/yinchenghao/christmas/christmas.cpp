#include<algorithm>
#include<iostream>
using namespace std;
int main()
{
	freopen("christmas.in","r",stdin);
	freopen("christmas.out","w",stdout); 
	long long a,m,l,r,x,y;
	cin>>a>>m>>l>>r;
	if(a==l && a==r)
	{
		cout<<"1";
		return 0;
	} 
	if(a>r)
	{
		cout<<"0";
		return 0;
	}
	if(l<a)
	{
		x=0;
		y=(r-(r-a)%m)/m;
	}
	else
	{
		x=(l+(m-(l-a)%m))/m;
		y=(r-(r-a)%m)/m;
	}
	if(y-x<0)
	{
		cout<<"0";
		return 0;
	}
	cout<<y-x+2;
	return 0; 
}

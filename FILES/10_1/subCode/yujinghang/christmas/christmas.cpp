#include <bits/stdc++.h>
using namespace std;
const long long N=1e18;
int main()
{
	freopen("christmas.in","r",stdin);
	freopen("christmas.out","w",stdout);
	long long a,m,l,r,cnt=0;
	cin>>a>>m>>l>>r;
	for(int i=-N;i<=N;i++)
	{
		a+=m;
		if(a>=l&&a<=r)
		{
			cnt++;
		}
		else if(a>r)
		{
			cout<<cnt;
			break;
		}
	}
	return 0;
}

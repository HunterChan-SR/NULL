#include <bits/stdc++.h>
#define int long long
using namespace std;
signed main()
{
	int a, m, l, r; cin >> a >> m >> l >> r;
	for(int i = 1; i <= 10000; i++)
	{
		a += m;
		if(m >= l)
		{
			break;
		}
	}int cnt = 0;
	for(int i = a; i <= r; i += m)
	{
		cnt++;
	}cout << cnt;
}

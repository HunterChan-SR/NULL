#include <bits/stdc++.h>
#define int long long
using namespace std;
signed main()
{
	freopen("sum.in", "r", stdin);
	freopen("sum.out", "w", stdout);
	int n, m, cnt = 0; cin >> n >> m;
	for(int i = 0; i <= n; i++)
	{
		for(int j = 0; j <= n; j++)
		{
			for(int k = 0; k <= n; k++)
			{
				if(i + j + k == m)
					cnt++;
			}
		}
	}cout << cnt;
}

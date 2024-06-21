#include <bits/stdc++.h>
#pragma GCC optimize(3, "Ofast", "inline")
#pragma G++ optimize(3, "Ofast", "inline")
using namespace std;
char l[60][60];
char k[60][60];
int main()
{
	freopen("template.in", "r", stdin);
	freopen("template.out", "w", stdout);
	int n, m; cin >> n >> m;
	for(int i = 1; i <= n; i++)
	{
		for(int j = 1; j <= n; j++)
		{
			cin >> l[i][j];
		}
	}for(int i = 1; i <= m; i++)
	{
		for(int j = 1; j <= m; j++)
		{
			cin >> k[i][j];
		}
	}int zt = 0;
	int print = 0;
	for(int i = 1; i <= n - m + 1; i++)
	{
		for(int j = 1; j <= n - m + 1; j++)
		{
			zt = 0;
			for(int a = i; a <= i + m - 1; i++)
			{
				for(int b = j; b <= j + m - 1; j++)
				{
					if(!l[a][b] == k[a][b])
					{
						zt = 1;
						break;
					}
				}if(zt == 0)
				{
					cout << "Yes\n";
					print = 1;
					return 0;
				}else
					cout << "No\n";
			}
		}
	}
}

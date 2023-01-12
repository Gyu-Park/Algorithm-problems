/**
 * https://codeforces.com/contest/1772/problem/F
 *
 * Codeforces Round #839 (Div. 3) Editorial by awoo
 * F. Copy of a Copy of a Copy
 */
#include <bits/stdc++.h>

using namespace std;

// for loop define
#define forn(i, n) for (int i = 0; i < int(n); i++)

// operation
struct op {
    int t, x, y, i;
};

// one left and one right steps
int dx[] = {-1, 0, 1, 0};
// one up and one down steps
int dy[] = {0, 1, 0, -1};

int main() {
    // n = number of rows
    // m = number of colunms
    // k = number of copies
    int n, m, k;
    cin >> n >> m >> k;
    // construct input pictures
    vector<vector<string>> a(k + 1, vector<string>(n));
    forn(z, k + 1) forn(i, n)
            cin >>
        a[z][i];

    // count number of cells that can recolor in each picture
    vector<int> cnt(k + 1);
    forn(z, k + 1) {
        for (int i = 1; i < n - 1; ++i) {
            for (int j = 1; j < m - 1; ++j) {
                bool ok = true;
                // check if adj cells are different, so that it can recolor
                forn(t, 4)
                    ok &= a[z][i][j] != a[z][i + dx[t]][j + dy[t]];
                // save the result into cnt vector
                cnt[z] += ok;
            }
        }
    }

    // need to sort indices in descending order using cnt vector
    // ex) a[ord[0]] picture has the largest number of cells that can recolor
    vector<int> ord(k + 1);
    iota(ord.begin(), ord.end(), 0);
    sort(ord.begin(), ord.end(), [&cnt](int x, int y) {
        return cnt[x] > cnt[y];
    });

    // generate operations and save those into ops vector to print
    vector<op> ops;
    forn(z, k) {
        forn(i, n) forn(j, m) if (a[ord[z]][i][j] != a[ord[z + 1]][i][j]) {
            a[ord[z]][i][j] ^= '0' ^ '1';
            ops.push_back({1, i + 1, j + 1, -1});
        }
        ops.push_back({2, -1, -1, ord[z + 1] + 1});
    }

    cout << ord[0] + 1 << '\n';
    cout << ops.size() << '\n';
    for (auto it : ops) {
        cout << it.t << " ";
        if (it.t == 1)
            cout << it.x << " " << it.y << '\n';
        else
            cout << it.i << '\n';
    }
}
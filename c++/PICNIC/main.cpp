#include <iostream>
#include <vector>

using namespace std;

vector<vector<bool> > areFriends;

int match(vector<bool> taken) {
    int smallest = -1;
    for (int i = 0; i < taken.size(); i++)
        if (!taken[i]) {
            smallest = i;
            break;
        }
    
    if (smallest == -1) return 1;
    
    int result = 0;
    taken[smallest] = true;
    for (int i = smallest + 1; i < taken.size(); i++) {
        if (!taken[i] && areFriends[smallest][i]) {
            taken[i] = true;
            result += match(taken);
            taken[i] = false;
        }
    }
    
    return result;
}

int main() {
    ios::sync_with_stdio(false);
    int test;
    cin >> test;
    while (test-- > 0) {
        int n, m;
        cin >> n >> m;
        areFriends = vector<vector<bool> >(n, vector<bool>(n));
        while (m-- > 0) {
            int a, b;
            cin >> a >> b;
            areFriends[a][b] = areFriends[b][a] = true;
        }
        
        vector<bool> taken(n);
        
        cout << match(taken) << endl;
    }
}
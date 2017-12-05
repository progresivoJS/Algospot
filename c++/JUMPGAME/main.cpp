#include <iostream>
#include <vector>
#include <cstring>

using namespace std;

int cache[101][101];

int canJump(vector<vector<int>>& board, int row, int col) {
	if (row >= board.size() || col >= board.size())
		return 0;
	if (row == board.size() - 1 && col == board.size() - 1)
		return 1;
	int& ret = cache[row][col];
	if (ret != -1)
		return ret;

	int hop = board[row][col];
	return ret = (canJump(board, row, col + hop) || canJump(board, row + hop, col));
}

int main() {
	ios::sync_with_stdio(false);
	int test;
	cin >> test;
	while (test-- > 0) {
		int n;
		cin >> n;
		vector<vector<int>> board(n, vector<int>(n));
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				cin >> board[i][j];
			}
		}

		memset(cache, -1, sizeof(cache));

		if (canJump(board, 0, 0)) {
			cout << "YES" << endl;
		}
		else {
			cout << "NO" << endl;
		}
	}
}

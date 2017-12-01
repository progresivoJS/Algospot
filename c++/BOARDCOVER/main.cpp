#include <iostream>
#include <vector>
#include <string>

using namespace std;

const bool BLACK = false;
const bool WHITE = true;

const int coverType[4][3][2] = {{{0, 0},{1, 0},{0, 1}},
                                {{0, 0},{1, 0},{1, 1}},
                                {{0, 0},{0, 1},{1, 1}},
                                {{0, 0},{1, 0},{1, -1}}};

bool areValid(vector<vector<bool>>& board, int* newRow, int* newCol) {
    for (int i = 0; i < 3; i++) {
        if (newRow[i] >= board.size() || newRow[i] < 0)
            return false;
        if (newCol[i] >= board[0].size() || newCol[i] < 0)
            return false;
    }
    
    return true;
}

bool areWhite(vector<vector<bool>>& board, int* newRow, int* newCol) {
    for (int i = 0; i < 3; i++) {
        if (board[newRow[i]][newCol[i]] == BLACK)
            return false;
    }
    
    return true;
}

int calculateCover(vector<vector<bool>>& board) {
    int row = -1, col = -1;
    for (int i = 0; i < board.size(); i++) {
        for (int j = 0; j < board[i].size(); j++) {
            if (board[i][j] == WHITE) {
                row = i;
                col = j;
                break;
            }
        }
        if (row != -1) break;
    }
    
    if (row == -1) return 1;
    
    int ret = 0;
    for (int type = 0; type < 4; type++) {
        int newRow[3];
        int newCol[3];
        for (int i = 0; i < 3; i++) {
            newRow[i] = row + coverType[type][i][0];
            newCol[i] = col + coverType[type][i][1];
        }
        
        if (areValid(board, newRow, newCol) && areWhite(board, newRow, newCol)) {
            for (int i = 0; i < 3; i++) {
                board[newRow[i]][newCol[i]] = BLACK;
            }
            
            ret += calculateCover(board);
            
            for (int i = 0; i < 3; i++) {
                board[newRow[i]][newCol[i]] = WHITE;
            }
        }
    }
    
    return ret;
}

int main() {
    ios::sync_with_stdio(false);
    int test;
    cin >> test;
    while (test-- > 0) {
        int row, col;
        cin >> row >> col;
        vector<vector<bool>> board(row, vector<bool>(col));
        for (int i = 0; i < row; i++) {
            string s;
            cin >> s;
            for (int j = 0; j < col; j++) {
                board[i][j] = s[j] == '#' ? BLACK : WHITE;
            }
        }
        
        cout << calculateCover(board) << endl;
    }
}
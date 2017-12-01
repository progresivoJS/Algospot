#include <vector>
#include <cstdio>

using namespace std;

const int INF = 987654321;
const vector<vector<int>> buttonType ={
                                        {0, 1, 2},
                                        {3, 7, 9, 11},
                                        {4, 10, 14, 15},
                                        {0, 4, 5, 6, 7},
                                        {6, 7, 8, 10, 12},
                                        {0, 2, 14, 15},
                                        {3, 14, 15},
                                        {4, 5, 7, 14, 15},
                                        {1, 2, 3, 4, 5},
                                        {3, 4, 5, 9, 13}};
                                        
bool areSync(vector<int>& clock) {
    for (int i = 0; i < clock.size(); i++) {
        if (clock[i] % 12 != 0)
            return false;
    }
    
    return true;
}

int calculatePushCount(vector<int>& clock, int toPushButton) {
    if (toPushButton == buttonType.size()) {
        return areSync(clock) ? 0 : INF;
    }
    
    int ret = INF;
    for (int count = 0; count < 4; count++) {
        for (int i = 0; i < buttonType[toPushButton].size(); i++) {
            clock[buttonType[toPushButton][i]] += (3 * count);
        }
        
        ret = min(ret, count + calculatePushCount(clock, toPushButton + 1));
        
        for (int i = 0; i < buttonType[toPushButton].size(); i++) {
            clock[buttonType[toPushButton][i]] -= (3 * count);
        }
    }
    
    return ret;
}

int main() {
    int test;
    scanf("%d", &test);
    
    vector<int> clock(16);
    while (test-- > 0) {
        for (int i = 0; i < 16; i++) {
            scanf("%d", &clock[i]);
        }
        
        int result = calculatePushCount(clock, 0);
        printf("%d\n", (result== INF) ? -1 : result);
    }
}
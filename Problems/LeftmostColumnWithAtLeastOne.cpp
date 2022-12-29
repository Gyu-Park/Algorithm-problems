#include <iostream>
#include <vector>
using namespace std;

// This is the BinaryMatrix's API interface.
// You should not implement it, or speculate about its implementation
class BinaryMatrix {
   public:
    int get(int row, int col);
    vector<int> dimensions();
};

class Solution {
   public:  // solution using binary search
    bool findRange(BinaryMatrix &binaryMatrix, int rows, int mid, int *row) {
        for (int i = 0; i < rows; i++) {
            if (binaryMatrix.get(i, mid) == 1) {
                *row = i;
                return true;
            }
        }
        return false;
    }

    int leftMostColumnWithOne(BinaryMatrix &binaryMatrix) {
        vector<int> dim = binaryMatrix.dimensions();
        int cols = dim[1];
        int rows = dim[0];
        int row = 0;
        int colStart = 0;
        while (colStart < cols) {
            int mid = (cols + colStart) / 2;
            if (findRange(binaryMatrix, rows, mid, &row)) {
                cols = mid;
            } else {
                colStart = mid + 1;
            }
        }
        return colStart < dim[1] && binaryMatrix.get(row, colStart) == 1 ? colStart : -1;
    }

    // fastest solution
    int leftMostColumnWithOne2(BinaryMatrix &binaryMatrix) {
        vector<int> dim = binaryMatrix.dimensions();
        int cols = dim[1];
        int rows = dim[0];
        int curCol = cols - 1;
        int startRow = 0;
        while (rows > startRow && curCol >= 0) {
            if (binaryMatrix.get(startRow, curCol) == 1)
                curCol--;
            else
                startRow++;
        }
        return curCol == cols - 1 ? -1 : curCol + 1;
    }
};
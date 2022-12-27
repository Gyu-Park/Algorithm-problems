#include <iostream>
#include <vector>
#include <queue>
using namespace std;

// solution using Dijkstra's Algorithm
class Solution
{
public:
    int directions[4][2] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int minimumEffortPath(vector<vector<int>> &heights)
    {
        // maximum absolute difference in terms of height
        // using priority queue
        // minimum difference should be first element, so that prioirty queue can order properly
        // don't put a cell that just came from
        int row = heights.size();
        int col = heights[0].size();
        priority_queue<Cell, vector<Cell>, Comparator> pq;
        vector<vector<bool>> visited(row, vector<bool>(col, false));
        vector<vector<int>> diffGrid(row, vector<int>(col, INT_MAX));
        diffGrid[0][0] = 0;
        pq.push(Cell(0, 0, diffGrid[0][0]));
        while (!pq.empty())
        {
            Cell c = pq.top();
            pq.pop();
            int x = c.x;
            int y = c.y;
            visited[x][y] = true;
            if (x == row - 1 && y == col - 1)
                return c.diff;
            for (int i = 0; i < 4; i++)
            {
                int newRow = x + directions[i][0];
                int newCol = y + directions[i][1];
                if (isValidCell(newRow, newCol, row, col) && !visited[newRow][newCol])
                {
                    int newDiff = abs(heights[x][y] - heights[newRow][newCol]);
                    int maxDiff = max(diffGrid[x][y], newDiff);
                    if (diffGrid[newRow][newCol] > maxDiff)
                    {
                        diffGrid[newRow][newCol] = maxDiff;
                        pq.push(Cell(newRow, newCol, maxDiff));
                    }
                }
            }
        }
        return diffGrid[row - 1][col - 1];
    }

    bool isValidCell(int x, int y, int row, int col)
    {
        return x >= 0 && x < row && y >= 0 && y < col;
    }

    class Cell
    {
    public:
        int x, y;
        int diff;
        Cell(int x, int y, int diff)
        {
            this->x = x;
            this->y = y;
            this->diff = diff;
        }
    };

    struct Comparator
    {
        bool operator()(Cell const &c1, Cell const &c2)
        {
            return c2.diff < c1.diff;
        }
    };
};

// binary search using bfs solution
class Solution
{
public:
    int minimumEffortPath(vector<vector<int>> &heights)
    {
        int left = 0;
        int right = 1000000;
        int result = right;
        while (left <= right)
        {
            int mid = (left + right) / 2;
            if (canReachDestinaton(heights, mid))
            {
                result = min(result, mid);
                right = mid - 1;
            }
            else
            {
                left = mid + 1;
            }
        }
        return result;
    }

    int directions[4][2] = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    // use bfs to check if we can reach destination with max absolute difference k
    bool canReachDestinaton(vector<vector<int>> &heights, int mid)
    {
        int row = heights.size();
        int col = heights[0].size();
        queue<Cell> queue;
        vector<vector<bool>> visited(row, vector<bool>(col, false));
        queue.push(Cell(0, 0));
        visited[0][0] = true;
        while (!queue.empty())
        {
            Cell curr = queue.front();
            queue.pop();
            if (curr.x == row - 1 && curr.y == col - 1)
            {
                return true;
            }
            for (auto direction : directions)
            {
                int adjacentX = curr.x + direction[0];
                int adjacentY = curr.y + direction[1];
                if (isValidCell(adjacentX, adjacentY, row, col) &&
                    !visited[adjacentX][adjacentY])
                {
                    int currentDifference = abs(heights[adjacentX][adjacentY] -
                                                heights[curr.x][curr.y]);
                    if (currentDifference <= mid)
                    {
                        visited[adjacentX][adjacentY] = true;
                        queue.push(Cell(adjacentX, adjacentY));
                    }
                }
            }
        }
        return false;
    }

    bool isValidCell(int x, int y, int row, int col)
    {
        return x >= 0 && x <= row - 1 && y >= 0 && y <= col - 1;
    }

    class Cell
    {
    public:
        int x, y;
        Cell(int x, int y)
        {
            this->x = x;
            this->y = y;
        }
    };
};
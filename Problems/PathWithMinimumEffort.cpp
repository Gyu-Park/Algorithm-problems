#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>
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

// union find solution
class Solution
{
public:
    class Edge
    {
    public:
        int x, y;
        int diff;
        Edge(int x, int y, int diff)
        {
            this->x = x;
            this->y = y;
            this->diff = diff;
        }
    };

    static bool compareInterval(const Edge &e1, const Edge &e2)
    {
        return (e1.diff < e2.diff);
    }

    class UnionFind
    {
    public:
        vector<int> root;
        vector<int> rank;
        vector<Edge> edgeList;
        UnionFind(vector<vector<int>> &heights)
        {
            int row = heights.size();
            int col = heights[0].size();
            rank.assign(row * col, 1);
            for (int curRow = 0; curRow < row; curRow++)
            {
                for (int curCol = 0; curCol < col; curCol++)
                {
                    int index = curRow * col + curCol;
                    root.push_back(index);
                    if (curRow > 0)
                    {
                        Edge e1(index, index - col, abs(heights[curRow][curCol] - heights[curRow - 1][curCol]));
                        edgeList.push_back(e1);
                    }
                    if (curCol > 0)
                    {
                        Edge e2(index, index - 1, abs(heights[curRow][curCol] - heights[curRow][curCol - 1]));
                        edgeList.push_back(e2);
                    }
                }
            }
            sort(edgeList.begin(), edgeList.end(), compareInterval);
        }

        int find(int x)
        {
            if (x != root[x])
                return root[x] = find(root[x]);
            return root[x];
        }

        void makeUnion(int x, int y)
        {
            int xRoot = find(x);
            int yRoot = find(y);
            if (xRoot == yRoot)
                return;
            if (rank[xRoot] > rank[yRoot])
            {
                root[yRoot] = xRoot;
            }
            else if (rank[xRoot] < rank[yRoot])
            {
                root[xRoot] = yRoot;
            }
            else
            {
                root[yRoot] = xRoot;
                rank[xRoot] += 1;
            }
        }
    };

    int minimumEffortPath(vector<vector<int>> &heights)
    {
        if (heights.size() == 1 && heights[0].size() == 1)
            return 0;
        UnionFind uf(heights);
        int src = 0;
        int dest = heights.size() * heights[0].size() - 1;
        vector<Edge> eList = uf.edgeList;
        for (int i = 0; i < eList.size(); i++)
        {
            int x = eList[i].x;
            int y = eList[i].y;
            int diff = eList[i].diff;
            uf.makeUnion(x, y);
            if (uf.find(src) == uf.find(dest))
            {
                return diff;
            }
        }
        return -1;
    }
};
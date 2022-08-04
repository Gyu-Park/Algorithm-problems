/**
 * There are n cities. Some of them are connected, while some are not. 
 * If city a is connected directly with city b, and city b is connected directly with city c, 
 * then city a is connected indirectly with city c.
 * 
 * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
 * 
 * You are given an n x n matrix isConnected where isConnected[i][j] = 1 
 * if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
 * 
 * Return the total number of provinces.
 */
package Problems;

import java.util.*;

public class NumberOfProvinces {
    // recursive dfs solution
    // time complexity: O(nm)
    // space complexity: O(n), where n is col and m is row in isConnected.
    public static int findCircleNum(int[][] isConnected) {
        if (isConnected.length == 1)
            return 1;
        Set<Integer> isVisited = new HashSet<>();
        int provinceNum = 0;

        for (int i = 0; i < isConnected.length; i++) {
            if (isVisited.contains(i))
                continue;
            isVisited.add(i);
            for (int j = 0; j < isConnected[i].length; j++) {
                if (isConnected[i][j] == 1 && i != j) {
                    helper(isConnected, isVisited, j);
                }
            }
            provinceNum++;
        }

        return provinceNum;
    }

    private static void helper(int[][] isConnected, Set<Integer> isVisited, int city) {
        if (isVisited.contains(city))
            return;
        isVisited.add(city);
        for (int j = 0; j < isConnected[city].length; j++) {
            if (isConnected[city][j] == 1 && city != j) {
                helper(isConnected, isVisited, j);
            }
        }
    }
    
    // Union-find solution
    // time complexity: O(n^2*logn)
    public static int anotherFindCircleNum(int[][] M) {
        int n = M.length;
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (M[i][j] == 1) uf.union(i, j);
            }
        }
        return uf.count();
    }

    static class UnionFind {
        private int count = 0;
        private int[] parent, rank;
        
        public UnionFind(int n) {
            count = n;
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        
        public int find(int p) {
        	while (p != parent[p]) {
                parent[p] = parent[parent[p]];    // path compression by halving
                p = parent[p];
            }
            return p;
        }
        
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;
            if (rank[rootQ] > rank[rootP]) {
                parent[rootP] = rootQ;
            }
            else {
                parent[rootQ] = rootP;
                if (rank[rootP] == rank[rootQ]) {
                    rank[rootP]++;
                }
            }
            count--;
        }
        
        public int count() {
            return count;
        }
    }
    
    public static void main(String[] args) {
        int[][] isConnected = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        System.out.println(findCircleNum(isConnected));
        System.out.println(anotherFindCircleNum(isConnected));
    }
}

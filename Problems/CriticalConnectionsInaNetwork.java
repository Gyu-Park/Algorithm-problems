/**
 * There are n servers numbered from 0 to n - 1 connected by undirected server-to-server connections forming a network 
 * where connections[i] = [ai, bi] represents a connection between servers ai and bi. 
 * Any server can reach other servers directly or indirectly through the network.
 * 
 * A critical connection is a connection that, if removed, will make some servers unable to reach some other server.
 * 
 * Return all critical connections in the network in any order.
 */
package Problems;

import java.util.*;

public class CriticalConnectionsInaNetwork {

    // tarjan algorithm
    // time complexity: O(V + E)
    // space complexity: O(n^2)
    public static List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        int[] disc = new int[n], low = new int[n];
        List<Integer>[] graph = new ArrayList[n];
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();
        // build graph
        for (int i = 0; i < connections.size(); i++) {
            int from = connections.get(i).get(0);
            int to = connections.get(i).get(1);
            graph[from].add(to);
            graph[to].add(from);
        }
        for (int i = 0; i < n; i++) {
            if (disc[i] == 0) {
                dfs(i, low, disc, graph, res, i);
            }
        }
        return res;
    }

    static int time = 0; // time when discover each vertex

    private static void dfs(int u, int[] low, int[] disc, List<Integer>[] graph, List<List<Integer>> res, int pre) {
        disc[u] = low[u] = ++time; // discover u
        for (int j = 0; j < graph[u].size(); j++) {
            int v = graph[u].get(j);
            if (v == pre) {
                continue; // if parent vertex, ignore
            }
            if (disc[v] == 0) { // if not discovered
                dfs(v, low, disc, graph, res, u);
                low[u] = Math.min(low[u], low[v]);
                if (low[v] > disc[u]) {
                    // u - v is critical, there is no path for v to reach back to u or previous vertices of u
                    res.add(Arrays.asList(u, v));
                }
            } else { // if v discovered and is not parent of u, update low[u], cannot use low[v] because u is not subtree of v
                low[u] = Math.min(low[u], disc[v]);
            }
        }
    }
    
    public static void main(String[] args) {
        List<List<Integer>> connections = new ArrayList<>();
        List<Integer> connection1 = new ArrayList<>();
        connection1.add(0);
        connection1.add(1);
        List<Integer> connection2 = new ArrayList<>();
        connection2.add(1);
        connection2.add(2);
        List<Integer> connection3 = new ArrayList<>();
        connection3.add(2);
        connection3.add(0);
        List<Integer> connection4 = new ArrayList<>();
        connection4.add(1);
        connection4.add(3);
        List<Integer> connection5 = new ArrayList<>();
        connection5.add(3);
        connection5.add(4);
        List<Integer> connection6 = new ArrayList<>();
        connection6.add(4);
        connection6.add(5);
        List<Integer> connection7 = new ArrayList<>();
        connection7.add(5);
        connection7.add(3);
        connections.add(connection1);
        connections.add(connection2);
        connections.add(connection3);
        connections.add(connection4);
        connections.add(connection5);
        connections.add(connection6);
        connections.add(connection7);
        int n = 6;
        System.out.println(criticalConnections(n, connections));
    }
}

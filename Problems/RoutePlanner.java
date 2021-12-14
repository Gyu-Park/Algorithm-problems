package Problems;

import java.util.*;

public class RoutePlanner {

    static HashMap<String, ArrayList<String>> edgesForEachRoad;

    public static boolean routeExists(int fromRow, int fromColumn, int toRow, int toColumn,
            boolean[][] mapMatrix) {
        if (mapMatrix[fromRow][fromColumn] == false || mapMatrix[toRow][toColumn] == false) {
            return false;
        }
        if (fromRow < 0 || fromRow >= mapMatrix.length || toRow < 0 || toRow >= mapMatrix.length) {
            return false;
        }
        if (fromColumn < 0 || fromColumn >= mapMatrix[0].length || toColumn < 0 || toColumn >= mapMatrix[0].length) {
            return false;
        }

        createGraph(mapMatrix);
        return bfs(fromRow + " " + fromColumn, toRow + " " + toColumn);
    }

    public static void createGraph(boolean[][] mapMatrix) {
        edgesForEachRoad = new HashMap<String, ArrayList<String>>();

        for (int i = 0; i < mapMatrix.length; i++) {
            for (int j = 0; j < mapMatrix[i].length; j++) {
                if (!mapMatrix[i][j]) {
                    continue;
                }

                String current = i + " " + j;
                if (i - 1 >= 0) {
                    if (mapMatrix[i - 1][j]) {
                        addEdge(current, i - 1, j);
                    }
                }
                if (i + 1 < mapMatrix.length) {
                    if (mapMatrix[i + 1][j]) {
                        addEdge(current, i + 1, j);
                    }
                }
                if (j - 1 >= 0) {
                    if (mapMatrix[i][j - 1]) {
                        addEdge(current, i, j - 1);
                    }
                }
                if (j + 1 < mapMatrix[i].length) {
                    if (mapMatrix[i][j + 1]) {
                        addEdge(current, i, j + 1);
                    }
                }
            }
        }

    }

    public static void addEdge(String a, int b, int c) {
        String edge = b + " " + c;
        if (edgesForEachRoad.containsKey(a)) {
            edgesForEachRoad.get(a).add(edge);
        } else {
            ArrayList<String> neighbor = new ArrayList<>();
            neighbor.add(edge);
            edgesForEachRoad.put(a, neighbor);
        }
    }

    public static boolean bfs(String from, String to) {
        LinkedList<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(from);
        visited.add(from);

        String current;
        while (!queue.isEmpty()) {
            current = queue.poll();

            if (current.equals(to)) {
                return true;
            }

            if (!edgesForEachRoad.containsKey(current)) {
                return false;
            }

            for (String nextRoad : edgesForEachRoad.get(current)) {
                if (!visited.contains(nextRoad)) {
                    visited.add(nextRoad);
                    queue.add(nextRoad);
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {
        boolean[][] mapMatrix = {
                { true, false, false },
                { true, true, false },
                { false, true, true }
        };

        System.out.println(routeExists(0, 0, 2, 2, mapMatrix));
    }
}
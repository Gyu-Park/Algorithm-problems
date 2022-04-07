/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. 
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates 
 * that you must take course bi first if you want to take course ai.
 * 
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 */
package Problems;

import java.util.*;

public class CourseSchedule {
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses == 1 || prerequisites.length <= 1)
            return true;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < prerequisites.length; i++) {
            int course = prerequisites[i][0];
            int prerequisite = prerequisites[i][1];
            if (course == prerequisite) {
                return false;
            } else if (map.containsKey(prerequisite) 
            && checkIfConflict(map, prerequisites, course, prerequisite)) {
                return false;
            } else if (map.containsKey(course)) {
                map.get(course).add(prerequisite);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(prerequisite);
                map.put(course, list);
            }
        }
        return true;
    }

    private static boolean checkIfConflict(Map<Integer, List<Integer>> map, int[][] prerequisites, 
                                        int course, int prerequisite) {
        if (map.containsKey(prerequisite) && !map.get(prerequisite).contains(course)) {
            List<Integer> list = map.get(prerequisite);
            for (int k : list) {
                return checkIfConflict(map, prerequisites, course, k);
            }
        } else if (map.get(prerequisite) != null && map.get(prerequisite).contains(course)) {
            return true;
        }
        return false;
    }

    // another solution using Topological sort, but slower than the first solution
    public static boolean anotherCanFinish(int numCourses, int[][] prerequisites) {
        if (numCourses == 0)
            return false;
        Queue<Integer> queue = new LinkedList<>();
        int[] inDegree = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            inDegree[prerequisites[i][1]]++;
        }
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0)
                queue.offer(i);
        }
        while (!queue.isEmpty()) {
            int x = queue.poll();
            for (int i = 0; i < prerequisites.length; i++) {
                if (x == prerequisites[i][0]) {
                    inDegree[prerequisites[i][1]]--;
                    if (inDegree[prerequisites[i][1]] == 0)
                        queue.offer(prerequisites[i][1]);
                }
            }
        }
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] != 0)
                return false;
        }
        return true;
    }
    
    public static void main(String[] args) {
        int numCourses = 5;
        int[][] prerequisites = { {1, 4}, {2, 4}, {3, 1}, {3, 2} };
        System.out.println(canFinish(numCourses, prerequisites));
    }
}

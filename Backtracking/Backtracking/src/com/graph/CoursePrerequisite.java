package com.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


// Time complexity = O(V + E)
// V = numCourses
// E = number of prerequisites
public class CoursePrerequisite {
    public static void main(String[] args) {
        CoursePrerequisite cs = new CoursePrerequisite();

        // Example 1: Can finish all courses
        int numCourses = 4;
        int[][] prerequisites = {
                {1, 0},
                {2, 1},
                {3, 2}
        };

        System.out.println("Can finish? " + cs.canFinish(numCourses, prerequisites)); // true

    }

    private boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] preTrack = new int[numCourses];
        for(int i = 0 ; i < numCourses ; i++) {
            graph.add(new ArrayList<>());
        }

        for(int[] data : prerequisites) {
            int course = data[0];
            int pre = data[1];
            graph.get(pre).add(course);
            preTrack[course]++;          // [0, 1, 1, 0]  for 2nd data
        }

        // offer in queue the completed courses
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0 ; i < numCourses ; i++) {
            if (preTrack[i] == 0) {
                queue.offer(i);
            }
        }

        int completed = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            completed++;
            for (int neighbor : graph.get(current)) {
                preTrack[neighbor]--;
                if (preTrack[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        return completed == numCourses;
    }
}

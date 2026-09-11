package com.graph;

import java.util.*;

public class Dijkstra {
    public static void main(String[] args) {
        // build the example graph: A=0, B=1, C=2, D=3
        Map<Integer, List<int[]>> graph = new HashMap<>();
        graph.put(0, Arrays.asList(new int[]{1, 4}, new int[]{2, 1}));          // A -> B(4), C(1)
        graph.put(1, Arrays.asList(new int[]{0, 4}, new int[]{2, 2}, new int[]{3, 5}));  // B -> A(4), C(2), D(5)
        graph.put(2, Arrays.asList(new int[]{0, 1}, new int[]{1, 2}, new int[]{3, 8}));  // C -> A(1), B(2), D(8)
        graph.put(3, Arrays.asList(new int[]{1, 5}, new int[]{2, 8}));          // D -> B(5), C(8)

        int[] distances = dijkstra(4, graph, 0);

        System.out.println("Distances from A: " + Arrays.toString(distances));
        // Output: [0, 3, 1, 8] -- matches our hand trace exactly
    }

    private static int[] dijkstra(int vertices, Map<Integer, List<int[]>> graph, int source) {
        int[] distance = new int[vertices];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1] - b[1]);
        pq.offer(new int[] {source, 0});

        boolean[] visited = new boolean[vertices];

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int node = current[0];

            if (visited[node]) continue;
            visited[node] = true;

            for (int[] neighbour : graph.getOrDefault(node, Collections.emptyList())) {
                int nextNode = neighbour[0];
                int weight = neighbour[1];

                if (!visited[nextNode] && distance[node] + weight < distance[nextNode]) {
                    distance[nextNode] = distance[node] + weight;
                    pq.offer(new int[] {nextNode, distance[nextNode]});
                }
            }
        }
        return distance;
    }
}

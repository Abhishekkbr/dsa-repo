package com.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShortestPathKHops {
    static class Edge {
        int src, weight, dest;

        public Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0, 1, 100));
        edges.add(new Edge(0, 2, 30));
        edges.add(new Edge(0, 3, 200));
        edges.add(new Edge(1, 3, 100));
        edges.add(new Edge(2, 3, 40));
        
        System.out.println(shortestPathKHops(4, edges, 0, 3, 1));   // 200 (direct edge only)
        System.out.println(shortestPathKHops(4, edges, 0, 3, 2));   // 70  (0->2->3)
    }

    private static int shortestPathKHops(int vertices, List<Edge> edges, int source, int destination, int k) {
        int[] dist = new int[vertices];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        for (int round = 0 ; round < k ; round++) {
            int[] tempDist = Arrays.copyOf(dist, vertices);
            for (Edge edge : edges) {
                if (dist[edge.src] != Integer.MAX_VALUE && dist[edge.src] + edge.weight < tempDist[edge.dest]) {
                    tempDist[edge.dest] = dist[edge.src] + edge.weight;
                }
            }
            dist = tempDist;
        }
        return dist[destination] == Integer.MAX_VALUE ? -1 : dist[destination];
    }
}

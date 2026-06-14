package com.arrays;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MaxMeetingRoom {
    public static void main(String[] args) {
        int[][] meetings = {{1,4}, {2,5}, {7,9}};
        System.out.println("Minimum rooms: " + minRooms(meetings));

        int[][] meetings2 = {{1,4}, {2,5}, {3,6}};
        System.out.println("Minimum rooms: " + minRooms(meetings2));
    }

    private static int minRooms(int[][] meetings) {
        Arrays.sort(meetings, (a,b) -> a[0] - b[0]);
        PriorityQueue<Integer> sortedRoom = new PriorityQueue<>();

        for (int[] meeting: meetings) {
            int startTime = meeting[0];
            int endTime = meeting[1];

            if (!sortedRoom.isEmpty() && startTime >= sortedRoom.peek()) {
                sortedRoom.poll();
            }
            sortedRoom.offer(endTime);
        }
        return sortedRoom.size();
    }

}

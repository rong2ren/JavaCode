package amazon;

import java.util.LinkedList;
import java.util.Queue;

public class MinimumDistanceInGrid {

    public static void main(String[] args) {
        
    }

    public static int minimumDistance(int[][] area) {
        if (area == null || area.length == 0 || area[0].length == 0) {
            return -1;
        }

        int rows = area.length;
        int cols = area[0].length;

        // Define movement directions: up, down, left, right
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // Track visited cells
        boolean[][] visited = new boolean[rows][cols];

        // If the starting cell is not passable
        if (area[0][0] == 0) {
            return -1;
        }

        // Initialize a queue for BFS with row, column, and distance
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 0}); // Starting position with distance 0
        visited[0][0] = true;

        // Perform BFS
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentRow = current[0];
            int currentCol = current[1];
            int distance = current[2];

            // Check if the current cell contains the target (9)
            if (area[currentRow][currentCol] == 9) {
                return distance;
            }

            // Explore all possible directions
            for (int[] dir : directions) {
                int newRow = currentRow + dir[0];
                int newCol = currentCol + dir[1];

                // Check bounds and whether the cell is already visited or blocked
                if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols
                        && !visited[newRow][newCol] && area[newRow][newCol] != 0) {
                    queue.offer(new int[]{newRow, newCol, distance + 1}); // Add to queue
                    visited[newRow][newCol] = true; // Mark cell as visited
                }
            }
        }

        // If no path to target (9) is found
        return -1;
    }
}

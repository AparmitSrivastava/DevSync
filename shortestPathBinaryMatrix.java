import java.util.*;

public class Solution {
    static class Pair {
        int row, col, dist;
        Pair(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }

    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] == 1 || grid[n-1][n-1] == 1) return -1;

        int[][] dist = new int[n][n];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
        dist[0][0] = 1; // path length starts at 1

        // normal queue (not priority queue)
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(0, 0, 1));

        int[] dr = {-1,-1,-1,0,0,1,1,1};
        int[] dc = {-1,0,1,-1,1,-1,0,1};

        while (!q.isEmpty()) {
            Pair cur = q.poll();
            int r = cur.row, c = cur.col, d = cur.dist;

            if (r == n-1 && c == n-1) return d; // reached destination

            for (int k = 0; k < 8; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];
                if (nr >= 0 && nr < n && nc >= 0 && nc < n && grid[nr][nc] == 0) {
                    if (d + 1 < dist[nr][nc]) {
                        dist[nr][nc] = d + 1;
                        q.offer(new Pair(nr, nc, d + 1));
                    }
                }
            }
        }
        return -1;
    }
}

class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int[][] dist = new int[n][n];
        boolean[][] visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        dist[0][0] = grid[0][0];
        
        // Simple min-heap replacement: each step we pick the smallest dist cell manually
        while (true) {
            int x = -1, y = -1, min = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j] && dist[i][j] < min) {
                        min = dist[i][j];
                        x = i;
                        y = j;
                    }
                }
            }
            
            // reached the destination
            if (x == n - 1 && y == n - 1) return dist[x][y];
            visited[x][y] = true;
            
            int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
            for (int[] d : dirs) {
                int nx = x + d[0], ny = y + d[1];
                if (nx >= 0 && ny >= 0 && nx < n && ny < n && !visited[nx][ny]) {
                    int newTime = Math.max(dist[x][y], grid[nx][ny]);
                    if (newTime < dist[nx][ny]) {
                        dist[nx][ny] = newTime;
                    }
                }
            }
        }
    }
}
class Solution {
    List<Integer>[] tree;
    int[] xor, in, out;
    int time = 0, totalXor = 0;

    public int minimumScore(int[] nums, int[][] edges) {
        int n = nums.length;
        tree = new List[n];
        xor = new int[n];
        in = new int[n];
        out = new int[n];

        for (int i = 0; i < n; i++) tree[i] = new ArrayList<>();
        for (int[] edge : edges) {
            tree[edge[0]].add(edge[1]);
            tree[edge[1]].add(edge[0]);
        }

        // Compute subtree XORs and entry/exit times
        dfs(0, -1, nums);
        totalXor = xor[0];

        int minScore = Integer.MAX_VALUE;

        // Try all pairs of edges as cuts (represented by child nodes in dfs)
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int a = i, b = j;

                if (isAncestor(a, b)) {
                    int x = xor[b];
                    int y = xor[a] ^ xor[b];
                    int z = totalXor ^ xor[a];
                    minScore = Math.min(minScore, getDiff(x, y, z));
                } else if (isAncestor(b, a)) {
                    int x = xor[a];
                    int y = xor[b] ^ xor[a];
                    int z = totalXor ^ xor[b];
                    minScore = Math.min(minScore, getDiff(x, y, z));
                } else {
                    int x = xor[a];
                    int y = xor[b];
                    int z = totalXor ^ xor[a] ^ xor[b];
                    minScore = Math.min(minScore, getDiff(x, y, z));
                }
            }
        }

        return minScore;
    }

    void dfs(int node, int parent, int[] nums) {
        in[node] = time++;
        xor[node] = nums[node];
        for (int nei : tree[node]) {
            if (nei != parent) {
                dfs(nei, node, nums);
                xor[node] ^= xor[nei];
            }
        }
        out[node] = time++;
    }

    boolean isAncestor(int u, int v) {
        return in[u] <= in[v] && out[v] <= out[u];
    }

    int getDiff(int a, int b, int c) {
        int max = Math.max(a, Math.max(b, c));
        int min = Math.min(a, Math.min(b, c));
        return max - min;
    }
}

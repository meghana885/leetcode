class Solution {
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int n = fruits.length;
        int[] prefix = new int[n + 1];
        int[] pos = new int[n];
        
        // Build prefix sums and positions
        for (int i = 0; i < n; i++) {
            pos[i] = fruits[i][0];
            prefix[i + 1] = prefix[i] + fruits[i][1];
        }

        int maxFruits = 0;
        int left = 0;

        for (int right = 0; right < n; right++) {
            // Try to shrink window from left if it's too far
            while (left <= right && !canReach(fruits, startPos, k, left, right)) {
                left++;
            }
            // If reachable, update max fruits
            if (left <= right) {
                maxFruits = Math.max(maxFruits, prefix[right + 1] - prefix[left]);
            }
        }

        return maxFruits;
    }

    private boolean canReach(int[][] fruits, int startPos, int k, int i, int j) {
        int leftPos = fruits[i][0];
        int rightPos = fruits[j][0];
        // Try left first or right first
        int distLeftFirst = Math.abs(startPos - leftPos) + (rightPos - leftPos);
        int distRightFirst = Math.abs(startPos - rightPos) + (rightPos - leftPos);
        return Math.min(distLeftFirst, distRightFirst) <= k;
    }
}

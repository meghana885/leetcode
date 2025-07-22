class Solution {
    public int maximumUniqueSubarray(int[] nums) {
        int left = 0;
        int maxScore = 0;
        int currentSum = 0;
        int[] seen = new int[10001]; // nums[i] <= 10000, so use array instead of HashSet

        for (int right = 0; right < nums.length; right++) {
            int num = nums[right];
            seen[num]++;
            currentSum += num;

            while (seen[num] > 1) {
                seen[nums[left]]--;
                currentSum -= nums[left];
                left++;
            }

            maxScore = Math.max(maxScore, currentSum);
        }

        return maxScore;
    }
}

class Solution {
    public int maxSum(int[] nums) {
        int maxVal = Integer.MIN_VALUE;
        java.util.Set<Integer> uniqueNonNegatives = new java.util.HashSet<>();
        int sum = 0;

        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
            if (num >= 0 && uniqueNonNegatives.add(num)) {
                sum += num;
            }
        }

        return (maxVal < 0) ? maxVal : sum;
    }
}

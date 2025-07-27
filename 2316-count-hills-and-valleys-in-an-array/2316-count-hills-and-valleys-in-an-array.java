class Solution {
    public int countHillValley(int[] nums) {
        int count = 0;
        int type = 0; // nothing
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i - 1] == nums[i]) {
                continue;
            }

            if (nums[i - 1] < nums[i]) {
                count += type == 2? 1 : 0;
                type = 1; // hill
            } else if (nums[i - 1] > nums[i]) {
                count += type == 1? 1 : 0;
                type = 2; // valley
            }
        }
        return count;
    }
}
class Solution {
    public int maxArea(int[] height) {
        int left = 0;                       // left pointer
        int right = height.length - 1;      // right pointer
        int maxArea = 0;

        while (left < right) {
            // Calculate the area between left and right
            int width = right - left;
            int minHeight = (height[left] < height[right]) ? height[left] : height[right];
            int area = width * minHeight;

            // Update max area if current area is greater
            if (area > maxArea) {
                maxArea = area;
            }

            // Move the pointer pointing to the shorter line
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }
}

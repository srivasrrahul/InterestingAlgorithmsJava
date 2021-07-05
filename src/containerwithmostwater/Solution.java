package containerwithmostwater;

public class Solution {
    public int maxArea(int[] height) {
        int leftIndex = 0;
        int rightIndex = height.length-1;

        int maxArea = Math.min(height[leftIndex],height[rightIndex]) * (rightIndex-leftIndex);

        int minLine = Math.min(height[leftIndex],height[rightIndex]);

        while (leftIndex <= rightIndex) {
            if (height[leftIndex] < height[rightIndex]) {
                ++leftIndex;
            }else {
                --rightIndex;
            }
            if (leftIndex <= rightIndex) {
                int area = Math.min(height[leftIndex],height[rightIndex]) * (rightIndex-leftIndex);
                if (area > maxArea) {
                    maxArea = area;
                }
            }
        }

        return maxArea;



    }
}

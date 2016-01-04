package AlgoTwoPointers;

public class ContainerWithMostWater_2P {

	/*
     * Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate
	 * (i, a_i). n vertical lines are drawn such that the two endpoints of line i is at (i, a_i) and (i, 0). 
	 * Find two lines, which together with x-axis forms a container, such that the container contains the most water.
	 * Note: You may not slant the container. 
	 */
	
	/* Algorithm: 先把L1设为最左，R1设为最右，这样宽度最大，然后缩小宽度，那么高度就要比原来高。缩小宽度的逻辑是，如果L1比R1小，那么L1往右移，否则反之。可以做简单证明。
	 * 如图，L1<R1<R2<L2，那么在L1移动到L2和R1移动到R2的过程中，存在的Lx和Rx都满足Lx < L1， Rx < R1。
	 * 假设Lx < Rx，那么Lx* d(Rx - Lx) < L1*d(Rx-Lx) < L1 * ( R1 - L1)。若Lx > Rx，则Rx * d(Rx - Lx) < Rx * d(R1 - L1) < L1 * d(R1 - L1) 
	 * (因为Rx,Lx都小于L1<R1)。直观的想就是，如果宽度减少，同时左右两侧的板都比原来的还要矮，怎么可能容纳更多的水呢？
	 * 
	 * 			l2			r2
	 * 			|			|		r1
	 * l1		|			|	rx	|
	 * |	lx	|			|	|	|
	 * |	|	|			|	|	|
	 * |	|	|			|	|	|
	 */


    public int maxArea(int[] height) {

        int maxArea = 0;
        if (height.length < 2) {
            return maxArea;
        }

        int left = 0;
        int right = height.length - 1;

        while (left < right) {
            // find the lower number, calculate the total area
            int h = (height[left] < height[right]) ? height[left] : height[right];
            int currentArea = h * (right - left);

            maxArea = (currentArea > maxArea) ? currentArea : maxArea;

            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }
}

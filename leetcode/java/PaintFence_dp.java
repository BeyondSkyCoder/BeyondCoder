package leetcode;

/*
 * There is a fence with n posts, each post can be painted with one of the k colors.

You have to paint all the posts such that no more than two adjacent fence posts have the same color.

Return the total number of ways you can paint the fence.

 */
public class PaintFence_dp {
	
    public int numWays(int n, int k) {
        if (n == 0 || k == 0)
            return 0;
        if (n == 1)
            return k;
            
        int same_count = k;
        int differ_count = k * (k - 1); // tricky
        for (int i = 3; i <= n; i++) {
            int temp = differ_count;
            differ_count = differ_count * (k - 1) + same_count * (k - 1);
            same_count = temp;
        }
        return same_count + differ_count;
    }
}

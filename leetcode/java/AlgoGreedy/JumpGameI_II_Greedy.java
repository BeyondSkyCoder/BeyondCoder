package AlgoGreedy;

public class JumpGameI_II_Greedy {
    /*
     * I. Given an array of non-negative integers, you are initially positioned at the first index of the array.
        Each element in the array represents your maximum jump length at that position.
        Determine if you are able to reach the last index.

        For example:
        A = [2,3,1,1,4], return true.
        A = [3,2,1,0,4], return false.
     */
    // Algorithm: Greedy
    //      scan through each array element, update maxreach
    //      abort loop early if max reaches the end, or can't reach the current one

    public boolean canJumpI(int[] nums) {
        int max = 0;

        for (int i = 0; max < nums.length; i++) {
            if (i > max) {
                break;    // not reachable, abort
            }

            max = Math.max(max, i + nums[i]);
        }

        return (max >= nums.length - 1);
    }
    /*
        II. Given an array of non-negative integers, you are initially positioned at the first index of the array.
        Each element in the array represents your maximum jump length at that position.

        Your goal is to reach the last index in the minimum number of jumps.

        For example:
        Given array A = [2,3,1,1,4]

        The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
     */
    // Algorithm: Greedy
    //      scan through each array element, be lazy(not jump) until has to
    //      when jump, goes to the maximum(to reduce the jump_step)

    public int jumpII(int[] nums) {
        int prev = 0, max = 0;
        int jump_step = 0;


        for (int i = 0; i < nums.length; i++) {
            if (i > prev) {
                // must jump now, had been lazy to save jump steps
                if (i > max) {
                    return -1;    // can't reach
                }

                prev = max;     // greedy: jump to max
                jump_step++;
            }
            max = Math.max(max, i + nums[i]);
        }

        return jump_step;
    }
}

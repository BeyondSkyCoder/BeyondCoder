__author__ = 'BeyondSky'


class Solution:
    def climbStairs_dp(self, n):
        if n < 2:
            return 1

        steps = []
        steps.append(1)
        steps.append(2)
        for i in range(2,n):
            steps.append(steps[i-1] + steps[i-2])

        return steps[n-1]

def main():
    outer = Solution();
    print(outer.climbStairs_dp(4))

if __name__ == "__main__":
    main()
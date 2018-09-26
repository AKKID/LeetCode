package DP;

public class Solution {
    public static void main(String[] args){
        Solution s = new Solution();
        System.out.println(s.rob(new int[]{1,2,3,}));
    }
    /***********************213***************************/
    // This problem is slightly changed in House Robber I
    // We can divide it into two sides. First rob house 1 then we cannot the last house
    // The other is rob the last house then we cannot rob the first house. We run House robber I for two times
    // And set nums[0] and nums[nums.length - 1] = 0 separately
    public int rob(int[] nums) {
        int len = nums.length;
        if(len == 0) return 0;
        if(len == 1) return nums[0];
        int n = nums[len - 1];
        nums[len - 1] = 0;
        int first = robHelper(nums);
        nums[len -1] = n;
        nums[0] = 0;
        int sencond = robHelper(nums);
        return Math.max(first,sencond);
    }

    public int robHelper(int[] nums){
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0]; dp[1] = Math.max(dp[0], nums[1]);
        int max = Math.max(dp[0], dp[1]);
        for(int i = 2; i < n;i++){
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /***********************375****************************/
    // Let dp[i][j] be the have to value from i to j.
    // For the first step from i to j, we have j - i + 1 type of choices. First to pick i
    // the cost is i + dp[i + 1][j], first is i + 1 then cost is i + 1 + max(dp[i][i],dp[i+1][j])
    // ... and dp[i][j] equals to the smallest value of these costs.
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for(int i = 1; i < n;i++){
            dp[i][i + 1] = i;
        }
        for(int i = 1; i + 2 <= n;i++){
            dp[i][i + 2] = i + 1;
        }
        for(int j = 3; j <= n;j++){
            for(int i = 1; i + j <= n;i++){
                int end = i + j;
                dp[i][end] = Integer.MAX_VALUE;
                for(int k = i + 1; k < end;k++){
                    dp[i][end] = Math.min(dp[i][end], k + Math.max(dp[i][k - 1],dp[k + 1][end]));
                }
            }
        }
        return dp[1][n];
    }



}

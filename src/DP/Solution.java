package DP;

import java.util.Stack;

public class Solution {
    public static void main(String[] args){
        Solution s = new Solution();
        System.out.println(s.longestPalindromeSubseq("bbbab"));
    }

    /***********************516***************************/
    // dp[i][j] stands for the longest palindrome subseq from ith char of s
    // and length of the substring is j
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        if(len == 0)
            return 0;
        int[][] dp = new int[len + 1][len + 1];
        for(int i = 0; i <= len;i++)
            dp[i][i] = 1;
        for(int j = 1; j <= len;j++){
            for(int i = 1; i + j <= len;i++){
                if(s.charAt(i - 1) == s.charAt(i + j - 1)){
                    dp[i][i + j] = Math.max(dp[i + 1][i + j - 1] + 2,dp[i][i + j - 1]);

                }else{
                    dp[i][i + j] = Math.max(dp[i][i + j - 1],dp[i+1][j+i]);
                }
            }
        }
        return dp[1][len];
    }
    /***********************97***************************/
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length(), l = s3.length();
        if(m + n != l)
            return false;
        if((s3.equals(s1) && n == 0) || (s3.equals(s2) && m == 0))
            return true;
        boolean[][][] dp = new boolean[l + 1][m + 1][n + 1];
        dp[0][0][0] = true;
        for(int i = 1; i <= m;i++){
            if(s1.charAt(i - 1) == s3.charAt(i - 1))
                dp[i][i][0] = true;
            else break;
        }
        for(int i = 1; i <= n;i++){
            if(s2.charAt(i - 1) == s3.charAt(i - 1))
                dp[i][0][i] = true;
            else break;
        }
        for(int k = 1;k<=l;k++){
            for(int i = 1;i <= m;i++){
                if(s1.charAt(i - 1) == s3.charAt(k - 1)){
                    for(int j = 1; j <= n;j++){
                        dp[k][i][j] = dp[k][i][j] || dp[k - 1][i - 1][j];
                    }
                }

                for(int j = 1; j <= n;j++){
                    if(s2.charAt(j - 1) == s3.charAt(k - 1))
                        dp[k][i][j] = dp[k][i][j] || dp[k - 1][i][j - 1];
                }

            }
        }
        return dp[l][m][n];
    }
    /***********************32***************************/
    public int longestValidParentheses(String s) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Pair> s2 = new Stack<>();
        int max = 0;
        for(int i = 0; i < s.length();i++){
            char ch = s.charAt(i);
            if(ch == '('){
                s1.push(i);
            }else{
                if(!s1.isEmpty()){
                    int f = s1.pop();
                    Pair nPair = new Pair(f,i);
                    while(!s2.isEmpty()){
                        Pair tmp = s2.peek();
                        if(tmp.s + 1 == nPair.f){
                            nPair = new Pair(tmp.f,nPair.s);
                            s2.pop();
                        }else if(tmp.s < nPair.s && tmp.f > nPair.f){
                            s2.pop();
                        }else{
                            break;
                        }
                    }
                    s2.push(nPair);
                    max = Math.max(max, nPair.s - nPair.f + 1);
                }
            }
        }
        return max;
    }
    public int longestValidParentheses_(String s){
        int[] dp = new int[s.length()];
        int result = 0;
        int leftCount = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                leftCount++;
            } else if (leftCount > 0){
                dp[i] = dp[i - 1] + 2;
                dp[i] += (i - dp[i]) >= 0 ? dp[i - dp[i]] : 0;
                result = Math.max(result, dp[i]);
                leftCount--;
            }
        }
        return result;
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

class Pair {
    int f;
    int s;
    public Pair(int f, int s){
        this.f = f;
        this.s = s;
    }
}
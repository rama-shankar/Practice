package com.rs.problems;

public class MaximumSumSubArray {
    public int maxSum(int[] ints, int i){
        if(i == 0){
            return 0;
        }
        if (i == 1){
            return  ints[i - 1];
        }
        return  Math.max(ints[i-1]+ maxSum(ints, i -1), maxSum(ints, i -1));
    }

    public int maxSumMemo(int[] ints, int i){
        if(i == 0){
            return 0;
        }
        if (i == 1){
            return  ints[i - 1];
        }
        return  Math.max(ints[i-1]+ maxSum(ints, i -1), maxSum(ints, i -1));
    }
    
    public static void main(String[] args){ 
        MaximumSumSubArray o = new MaximumSumSubArray();
        System.out.println(o.maxSum(new int[]{4,6,-1,0,2,1}, 6));
    }
}

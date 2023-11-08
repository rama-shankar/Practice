package com.rs.problems;

/**
 * Facebook. Given an array of unsorted integers, return the length of its longest increasing subsequence.
 * Note: You may assume the array will only contain positive numbers.
 * <p>
 * Ex: Given the following array nums…
 * <p>
 * nums = [1, 9, 7, 4, 7, 13], return 4.
 * The longest increasing subsequence is 1, 4, 7, 13.
 * Thanks,
 * The Daily Byte
 */
public class Longest_Increasing_Subsequence {
    //1, 9, 7, 4, 7, 13
    //1

    public int lengthOfLIS(int[] nums) {
        if(nums.length == 0) return 0;
        return lengthOfLIS(nums, 0, -1);
    }

    public int lengthOfLIS(int[] nums, int position, int lastNumber) {
        if(nums.length == position) return 0;

        if(nums[position] > lastNumber){
            return 1 + lengthOfLIS(nums, position + 1 ,nums[position]);
        }
        int after = lengthOfLIS(nums, position + 1 ,nums[position]);

       // return Math.max(1 + ), lengthOfLIS(nums, position + 1 ,nums[position]));
    return  -1;
    }


    public static void main(String[] args) {
        Longest_Increasing_Subsequence o = new Longest_Increasing_Subsequence();
        System.out.println(o.lengthOfLIS(new int[]{1,2,6,4,5},0, 0));
    }
}

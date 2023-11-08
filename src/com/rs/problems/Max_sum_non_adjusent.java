package com.rs.problems;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

 

Example 1:

//max.
condition //i != i+1

Input: nums = [1,2,3,1] == 1+3+1 , 2 +1, 1+3, 
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
3. 

**/
public class Max_sum_non_adjusent {

    public static int maxValue2 (int []a, int n){
        int max = 0;
        for (int i = 0 ; i < a.length; i++){
            int sum = 0;
            for(int j = i ; j < a.length; j= j+2){
                sum += a[j];
            }
            
            max = Math.max(max, sum);
        }
        
        return max;
    }
    
    public static int [] cc = new int[4];

    public static int maxValue (int []a, int n){
       if(n == 0){
           return a[n];
       }
       if(n == 1){
           return  Math.max(a[n], a[n -1]);
       }
       cc[n] = Math.max(maxValue(a, n -1), a[n] + maxValue(a, n-2));
       return cc[n];
    }



//
 public static void main(String[] args) {
       int [] input = new int [] {9,8,1,0};
       
        System.out.println(maxValue(input, 3));
   }
}

package com.rs.problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Two_sum {
  /**
   * Takes in an integer array and a target number and returns
   * an integer array containing indices of numbers that add up to
   * the target number.
   *
   * @param arr    Integer array.
   * @param target Integer target.
   * @return Indices of arr numbers which add up to the target number.
   */
  public static int[] twoSum(int[] arr, int target) {
    Map<Integer, Integer> mapping = new HashMap<Integer, Integer>();
    
    for (int i = 0; i < arr.length; i++){
      if(mapping.containsKey(arr[i])){
        return new int[]{mapping.get(arr[i]), i};
      }else{
        mapping.put(target - arr[i], i);
      }
    }
    
    return new int[]{};
  }
  public static void main(String[] args){ 
      Two_sum o = new Two_sum();
      System.out.println(Arrays.toString(o.twoSum(new int[]{1,9,5,4},10)));
  }
}
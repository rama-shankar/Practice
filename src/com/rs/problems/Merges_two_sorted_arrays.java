package com.rs.problems;

import java.util.Arrays;

public class Merges_two_sorted_arrays {
  /**
   * Merges two sorted arrays.
   *
   * @param arr1 First sorted array.
   * @param arr2 Second sorted array.
   * @return Merged sorted array.
   */
  public int[] mergeSorted(int[] arr1, int[] arr2) {
    if(arr1 == arr2 && arr1 == null){
      return null;
    }

    if(arr1 == null || arr1.length == 0){
      return arr2;
    }

    if(arr2 == null || arr2.length == 0){
      return arr1;
    }

    int idx1 = 0, idx2 = 0, idx3 = -1;
    int[] merged = new int[arr1.length + arr2.length];

    while(idx1 < arr1.length && idx2 < arr2.length ){
      if(arr1[idx1] == arr2[idx2]){
        merged[++idx3] = merged[++idx3] = arr1[idx1++];
        idx2++;
      }else if(arr1[idx1] < arr2[idx2]){
        merged[++idx3] = arr1[idx1++];
      }else{
        merged[++idx3] = arr2[idx2++];
      }
    }

    while(idx1 < arr1.length){
      merged[++idx3] = arr1[idx1++];
    }

    while(idx2 < arr2.length){
      merged[++idx3] = arr2[idx2++];
    }

    return merged;
  }

  public static void main(String[] args) {
    System.out.println(Arrays.toString(new Merges_two_sorted_arrays().mergeSorted(new int[]{2,5,7,8}, new int[]{2,4,7,12,32})));
  }
}
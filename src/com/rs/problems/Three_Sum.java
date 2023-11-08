package com.rs.problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Three_Sum {
    public static int[] twoSum(int[] arr, int target, int fi) {
        Map<Integer, Integer> mapping = new HashMap<Integer, Integer>();
        for (int i = 0; i < arr.length; i++) {
            if(fi == i) continue;
            if (mapping.containsKey(arr[i])) {
                return new int[]{mapping.get(arr[i]), i};
            } else {
                mapping.put(target - arr[i], i);
            }
        }

        return new int[]{};
    }

    public boolean threeSum(int arr[], int sum) {
        for (int i = 0; i < arr.length - 2; ++i) {
            int iSum = sum - arr[i];
            if (twoSum(arr, iSum, i ).length > 2) {
                return true;
            }
        }
        return false;
    }
    
    public static void main(String[] args){ 
        Three_Sum o = new Three_Sum();
        System.out.println(o.threeSum(new int[]{1,9,5,4},14));
    }
}

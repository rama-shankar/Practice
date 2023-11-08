package com.rs.problems;

import com.rs.help.StopWatch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class TestClass {

    static long solve(int l, int r) {
        long sum = 0;
        Set<String> happy = new HashSet<>();
        for (int i = l; i <= r; i++) {
            int reduce = i;
            while (reduce != 1 && reduce != 4) {
                if(happy.contains(reduce)){
                    reduce = 1;
                }else {
                    reduce = isB(reduce);
                }
            }
            if (reduce == 1) {
                sum++;
                happy.add(String.valueOf(i));
            }
        }
        return sum;
    }

    static int isB(int i) {
        int num = 0;
        while (i > 0) {
            int t = i % 10;
            num += t * t;
            i /= 10;
        }
        return num;
    }


    public List<List<Integer>> permute(int n) {
        List<Integer> nums = new ArrayList<>();
        for (int i = n; i > 0; i/=10) {
            nums.add(i%10);
        }
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        if (nums == null || nums.size() == 0) {
            return results;
        }
        List<Integer> result = new ArrayList<>();
        dfs(nums, results, result);
        return results;
    }

    public void dfs(List<Integer> nums, List<List<Integer>> results, List<Integer> result) {
        if (nums.size() == result.size()) {
            List<Integer> temp = new ArrayList<>(result);
            results.add(temp);
        }
        for (int i=0; i<nums.size(); i++) {
            if (!result.contains(nums.get(i))) {
                result.add(nums.get(i));
                dfs(nums, results, result);
                result.remove(result.size() - 1);
            }
        }
    }


    public static void main(String[] args){ 
        TestClass o = new TestClass();
        StopWatch s = new StopWatch();
        System.out.println(o.permute(99898898));
        s.stop();
    }
}
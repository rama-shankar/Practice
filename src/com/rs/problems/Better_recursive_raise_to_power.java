package com.rs.problems;

public class Better_recursive_raise_to_power {

  /**
   * Recursively raises base to exponent and returns
   * the result.
   *
   * @param base     The base.
   * @param exponent The power the base should be raised to.
   * @return Result of base ^ exponent.
   */
  public double betterPow1(double base, int exponent) {
    if(exponent < 0){
        base = 1/base;
        exponent = -exponent;
    }
    if(exponent == 0){
      return 1;
    }
    return betterPow(base*base, exponent/2);
  }

    public double betterPow(double base, int exponent) {
        if (exponent == 0) return 1D;
        if (exponent < 0) return betterPow(1 / base, -exponent);
        if (exponent % 2 == 1) return base * betterPow(base, exponent - 1);
        else return betterPow(base * base, exponent / 2);
    }

  //2*2*2*2
 public static void main(String[] args){ 
     Better_recursive_raise_to_power o = new Better_recursive_raise_to_power();
     System.out.println(o.betterPow(2,4));
 }
}
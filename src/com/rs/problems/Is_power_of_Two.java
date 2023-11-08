package com.rs.problems;

public class Is_power_of_Two {
  /**
   * Takes in a positive integer and returns
   * true if it is a power of two, else false.
   *
   * @param num Integer to test.
   * @return True if the number is a power of two, false otherwise.
   */
  public boolean isPowerOfTwo(int num) {
    return (num & (num -1)) == 0;
  }
  public static void main(String[] args){ 
      Is_power_of_Two o = new Is_power_of_Two();
      System.out.println(o.isPowerOfTwo(3));
  }
}
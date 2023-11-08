package com.rs.problems;

public class Linear_time_Nth_Fibonacci_number {
  /**
   * Returns the nth Fibonacci number in linear time and
   * with constant space.
   *
   * @param n Which number to return in the sequence.
   * @return Nth Fibonacci number.
   */
  static long [] c = new long[91];
  static {
      c[0] = 0;
      c[1] = c[2] = 1;
  }


  public long betterFibonacci(int n) {
    if(n == 0) return  0;
    if(c[n] != 0) return c[n];
    long n1 = betterFibonacci(n - 1);
    c[n-1] = n1;
    return betterFibonacci(n - 1) + betterFibonacci(n -2);
  }
  public static void main(String[] args){ 
      Linear_time_Nth_Fibonacci_number o = new Linear_time_Nth_Fibonacci_number();
      System.out.println(o.betterFibonacci(90));
  }
}
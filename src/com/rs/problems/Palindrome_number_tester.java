package com.rs.problems;

import java.util.HashMap;

public class Palindrome_number_tester {
  /**
   * Returns true if the input integer is a palindrome
   * with constant space complexity.
   *
   * @param input Input integer to be tested.
   * @return True if the input integer is a palindrome.
   */
  public Boolean isPalindrome(int input) {
    int rev = 0;
    int original = input;
    while(input > 0){
     rev = rev * 10;
     rev = rev + ( input % 10 );
     input = input / 10;
    }
    return rev == original;
  }
  public static void main(String[] args){ 
      Palindrome_number_tester o = new Palindrome_number_tester();
      System.out.println(o.isPalindrome(9119));
  }
}
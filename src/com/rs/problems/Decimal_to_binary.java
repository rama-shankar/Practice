package com.rs.problems;

public class Decimal_to_binary {
  /**
   * Returns the minimal binary string representation of
   * the input decimal integer.
   *
   * @param decimal Positive decimal integer that's <= 1000.
   * @return String with binary representation of the input decimal.
   */
  public String toBinary(int decimal) {
    if(decimal == 0 || decimal == 1){
      return String.valueOf(decimal);
    }
    return toBinary(decimal/2) + decimal%2 ;
  }
  public static void main(String[] args){ 
      Decimal_to_binary o = new Decimal_to_binary();
      System.out.println(o.toBinary(4));
  }
}
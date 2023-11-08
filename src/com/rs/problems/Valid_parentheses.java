package com.rs.problems;

import java.util.Stack;

public class Valid_parentheses {
    /**
     * Checks if the input string of brackets is valid.
     *
     * @param str String containing bracket characters.
     * @return True if the string is valid.
     */
    public Boolean checkValid(String str) {
        String open = "({[";
        String close = ")}]";
        Stack<Character> memo = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            int openIndex = open.indexOf(c);
            if (openIndex > -1) {
                memo.push(c);
            }else if(memo.isEmpty()){
                return false;
            }else{
              char o = memo.pop();
              int closeIndex = close.indexOf(c);
              if( o != open.charAt(closeIndex)){
                  return  false;
              }
            }
        }

        return memo.isEmpty() == Boolean.TRUE;
    }
    
    public static void main(String[] args){ 
        Valid_parentheses o = new Valid_parentheses();
        System.out.println(o.checkValid("()[]()"));
        System.out.println(o.checkValid("]"));
    }
}
package com.rs.problems;

import com.rs.help.U;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Rotate_a_square_image_counterclockwise {
  /**
   * Returns a copy of the input image,
   * rotated counterclockwise by 90 degrees.
   *
   * @param image Input matrix.
   * @return Output image, rotated counterclockwise by 90 degrees.
   */
  public int[][] rotateCounterClockwise(int[][] image) {
    
    for (int i = 0; i < image.length ; i++ ){
      for (int j = i+1; j < image[i].length ; j++ ){
          int t = image[i][j];
          image[i][j] = image[j][i];
          image[j][i] =  t;
      }
      
    }
      System.out.println(U.toS(image));
    for (int i = 0; i < image.length / 2  ; i++ ){
      for (int j = 0 ; j < image.length ; j++ ){
          int t = image[i][j];
          image[i][j] = image[image.length - i - 1][j];
          image[image.length - i - 1 ][j] =  t;
      }
      
    }
    
    return image;
  }
  
  public static void main(String[] args){ 
      Rotate_a_square_image_counterclockwise o = new Rotate_a_square_image_counterclockwise();
      List<?> a = new ArrayList<String>();
      System.out.println(U.toS(o.rotateCounterClockwise(new int[][]{{1,2,3},{4,5,6},{7,8,9}})));
  }

}
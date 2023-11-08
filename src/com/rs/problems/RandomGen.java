package com.rs.problems;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomGen {
    private void mathRandom(){
        Random rand2 = new Random();
        for (int i = 1; i <=20; i++) {
            rand2.setSeed(1);
            System.out.print( rand2.nextInt(5) +",") ;

        }

    }
    public static void main(String[] args){
        RandomGen o = new RandomGen();
        o.mathRandom();
    }
}

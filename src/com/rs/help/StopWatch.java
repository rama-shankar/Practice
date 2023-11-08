package com.rs.help;

public class StopWatch {

    private final long start;

    public StopWatch() {
        start = System.currentTimeMillis();
    }


    public void stop() {
        long now = System.currentTimeMillis();
        System.out.println((now - start) / 1000.0 +" seconds");
    }
}
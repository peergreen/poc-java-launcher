package com.peergreen.kernel.launcher.java.main;

public class WaitingTwoSecondsMain {

    private static final long TWO_SECONDS = 2 * 1000;

    /**
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        
        long start = System.currentTimeMillis();
        
        Thread.sleep(TWO_SECONDS);

        long elapsed = System.currentTimeMillis() - start;

        System.out.println(elapsed);

    }

}

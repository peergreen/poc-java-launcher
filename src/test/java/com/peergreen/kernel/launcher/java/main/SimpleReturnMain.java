package com.peergreen.kernel.launcher.java.main;

public class SimpleReturnMain {

    /**
     * @param args
     */
    public static void main(String[] args) {
        
        if (args.length == 1) {
            int value = Integer.parseInt(args[0]);
            System.exit(value);
        }

    }

}

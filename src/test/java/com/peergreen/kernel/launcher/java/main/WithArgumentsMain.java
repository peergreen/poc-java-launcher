package com.peergreen.kernel.launcher.java.main;

public class WithArgumentsMain {

    /**
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {

        if (args.length != 1) {
            throw new Exception();
        }
        
        if (!"value with spaces".equals(args[0])) {
            throw new Exception();
        }
    }

}

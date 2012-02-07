package com.peergreen.kernel.launcher.java.main;

public class WithSystemPropertiesMain {

    public static final String IS_PRESENT = "is.present";

    /**
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        
        if (!Boolean.getBoolean(IS_PRESENT)) {
            throw new Exception();
        }

    }

}

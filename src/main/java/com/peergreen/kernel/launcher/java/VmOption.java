package com.peergreen.kernel.launcher.java;

public class VmOption implements Switch {
    private String option;
    
    public VmOption(String option) {
        this.option = option;
    }
    
    public String render() {
        return option;
    }
}

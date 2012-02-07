package com.peergreen.kernel.launcher.java;

public class Argument implements Switch {
    private String value;

    public Argument(String value) {
        super();
        this.value = value;
    }

    public String render() {
        return value;
    }
}

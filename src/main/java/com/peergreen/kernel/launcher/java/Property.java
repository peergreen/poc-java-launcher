package com.peergreen.kernel.launcher.java;

public class Property implements Switch {
    private String name;
    private String value;

    public Property(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String render() {
        return new StringBuilder("-D")
                .append(name)
                .append('=')
                .append(value)
                .toString();
    }
}

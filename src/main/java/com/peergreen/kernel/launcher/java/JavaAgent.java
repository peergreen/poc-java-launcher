package com.peergreen.kernel.launcher.java;

import java.io.File;

/**
 * <pre>
 *  -javaagent:<jarpath>[=<options>]
 *     load Java programming language agent, see java.lang.instrument
 * </pre>
 */
public class JavaAgent implements Switch {

    private File path;

    private String options;

    public JavaAgent(File path) {
        this(path, null);
    }

    public JavaAgent(File path, String options) {
        this.path = path;
        this.options = options;
    }

    public String render() {
        StringBuilder sb = new StringBuilder("-javaagent:");
        sb.append(path.getAbsolutePath());
        if (options != null) {
            sb.append("=");
            sb.append(options);
        }

        return sb.toString();
    }
}

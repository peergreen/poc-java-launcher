package com.peergreen.kernel.launcher.java;

import java.io.File;

/**
 * <pre>
 *       -agentpath:<pathname>[=<options>]
 *          load native agent library by full pathname
 * </pre>
 */
public class AgentPath implements Switch {

    private File path;

    private String options;

    public AgentPath(File path) {
        this(path, null);
    }

    public AgentPath(File path, String options) {
        this.path = path;
        this.options = options;
    }

    public String render() {
        StringBuilder sb = new StringBuilder("-agentpath:");
        sb.append(path.getAbsolutePath());
        if (options != null) {
            sb.append("=");
            sb.append(options);
        }

        return sb.toString();
    }
}

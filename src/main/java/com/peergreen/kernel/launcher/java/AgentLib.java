package com.peergreen.kernel.launcher.java;

/**
 * <pre>
 *   -agentlib:<libname>[=<options>]
 *      load native agent library <libname>, e.g. -agentlib:hprof
 *      see also, -agentlib:jdwp=help and -agentlib:hprof=help
 * </pre>
 */
public class AgentLib implements Switch {
    
    private String libName;

    private String options;

    public AgentLib(String libName) {
        this(libName, null);
    }

    public AgentLib(String libName, String options) {
        this.libName = libName;
        this.options = options;
    }

    public String render() {
        StringBuilder sb = new StringBuilder("-agentlib:");
        sb.append(libName);
        if (options != null) {
            sb.append("=");
            sb.append(options);
        }

        return sb.toString();
    }
}

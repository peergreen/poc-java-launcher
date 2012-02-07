package com.peergreen.kernel.launcher.java;

/**
 * A Switch is a command line option.
 */
public interface Switch {

    /**
     * @return the textual representation of the switch (for a
     * system property: {@literal -Da=b}).
     */
    String render();
}

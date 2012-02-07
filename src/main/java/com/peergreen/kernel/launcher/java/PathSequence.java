package com.peergreen.kernel.launcher.java;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: guillaume
 * Date: 07/02/12
 * Time: 19:16
 * To change this template use File | Settings | File Templates.
 */
public class PathSequence implements Switch {
    
    private List<File> elements = new ArrayList<File>();

    public List<File> getSequence() {
        return elements;
    }

    public String render() {
        StringBuilder rendered = new StringBuilder();

        boolean first = true;
        for (File file : elements) {
            if (!first) {
                rendered.append(File.pathSeparatorChar);
            }
            rendered.append(file.getAbsolutePath());
            first = false;
        }

        return rendered.toString();
    }
}

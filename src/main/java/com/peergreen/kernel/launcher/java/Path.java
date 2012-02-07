package com.peergreen.kernel.launcher.java;

import java.io.File;

public class Path {
    private File value;
    
    public Path(File file) {
        assert file != null;
        value = file;
    }
    
    public Path(String file) {
        assert file != null;
        value = new File(file);
    }
    
    public File getValue() {
        return value;
    }
}

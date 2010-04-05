package com.peergreen.kernel.launcher.java;

import java.io.InputStream;
import java.io.OutputStream;

public class JavaResult {
    
    private int result;
    private OutputStream outputStream;
    private InputStream errorStream;
    
    public JavaResult(OutputStream outputStream, InputStream inputStream) {
        super();
        this.outputStream = outputStream;
        this.errorStream = inputStream;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }

    public InputStream getErrorStream() {
        return errorStream;
    }
    
    
    
    
}

package com.peergreen.kernel.launcher.java;

import com.peergreen.kernel.launcher.IOStreams;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by IntelliJ IDEA.
 * User: guillaume
 * Date: 07/02/12
 * Time: 18:00
 * To change this template use File | Settings | File Templates.
 */
public class TestStreams implements IOStreams {
    
    private OutputStream outputStream;
    
    private InputStream inputStream;

    private InputStream errorStream;

    public void setInput(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void setOutput(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void setError(InputStream errorStream) {
        this.errorStream = errorStream;
    }

    public String getProcessOutput() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            int i;
            byte[] buffer = new byte[1024];
            while (inputStream.read(buffer) != -1) {
                baos.write(buffer);
            }
        } catch (IOException e) {
            // Ignored
        }

        return new String(baos.toByteArray());
    }

    public String getProcessError() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            int i;
            byte[] buffer = new byte[1024];
            while (errorStream.read(buffer) != -1) {
                baos.write(buffer);
            }
        } catch (IOException e) {
            // Ignored
        }

        return new String(baos.toByteArray());
    }
}

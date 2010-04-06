package com.peergreen.kernel.launcher.java;

import java.io.InputStream;
import java.io.OutputStream;

import com.peergreen.kernel.launcher.IStreams;

public class NullStreams implements IStreams {

    public void setError(InputStream inputStream) {}

    public void setInput(InputStream inputStream) {}

    public void setOutput(OutputStream outputStream) {}

}

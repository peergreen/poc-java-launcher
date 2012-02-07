package com.peergreen.kernel.launcher.java;

import java.io.InputStream;
import java.io.OutputStream;

import com.peergreen.kernel.launcher.IOStreams;

public class NullStreams implements IOStreams {

    public void setError(InputStream errorStream) {}

    public void setInput(InputStream inputStream) {}

    public void setOutput(OutputStream outputStream) {}

}

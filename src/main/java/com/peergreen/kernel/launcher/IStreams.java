package com.peergreen.kernel.launcher;

import java.io.InputStream;
import java.io.OutputStream;

public interface IStreams {

    void setInput(InputStream inputStream);
    void setOutput(OutputStream outputStream);
    void setError(InputStream inputStream);
}

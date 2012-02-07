package com.peergreen.kernel.launcher;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * IOStreams is a simple way to provide to the API consumer a way
 * to interact with the new process.
 * @author Guillaume Sauthier
 */
public interface IOStreams {

    /**
     * This input stream represents the standard stream coming from the process.
     * From the POV of the process it is its standard output.
     * @param inputStream standard output of the process
     */
    void setInput(InputStream inputStream);

    /**
     * This input stream represents the error stream coming from the process.
     * From the POV of the process it is its error output.
     * @param errorStream error output of the process
     */
    void setError(InputStream errorStream);

    /**
     * This output stream represents the input stream from which the process reads its input.
     * From the POV of the process it is its standard input.
     * @param outputStream standard input of the process
     */
    void setOutput(OutputStream outputStream);
}

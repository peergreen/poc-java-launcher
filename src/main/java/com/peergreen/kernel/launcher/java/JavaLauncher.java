package com.peergreen.kernel.launcher.java;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.peergreen.kernel.launcher.Launcher;
import com.peergreen.kernel.launcher.IOStreams;
import com.peergreen.kernel.launcher.LaunchException;

public class JavaLauncher implements Launcher<Integer> {

    private ProcessBuilder builder;
    private ExecutorService executor;

    public JavaLauncher(ProcessBuilder builder) {
        this(builder, Executors.newSingleThreadExecutor());
    }

    public JavaLauncher(ProcessBuilder builder, ExecutorService executor) {
        this.builder = builder;
        this.executor = executor;  
    }

    public Integer launch(final IOStreams streams) throws LaunchException {
        // Start the process
        Process process;
        try {
            process = builder.start();
        } catch (IOException e) {
            throw new LaunchException(e);
        }
        
        // Handle streams
        
        try {
            streams.setInput(process.getInputStream());
            streams.setOutput(process.getOutputStream());
            streams.setError(process.getErrorStream());
            return process.waitFor();
        } catch (InterruptedException e) {
            throw new LaunchException(e);
        }
    }

    public Future<Integer> launchAsynch(final IOStreams streams) throws LaunchException {
        
        Callable<Integer> callable = new Callable<Integer>() {

            public Integer call() throws Exception {
                return launch(streams);
            }
        };
        
        return executor.submit(callable);
    }

}

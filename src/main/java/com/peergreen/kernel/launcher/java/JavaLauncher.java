package com.peergreen.kernel.launcher.java;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.peergreen.kernel.launcher.ILauncher;
import com.peergreen.kernel.launcher.IStreams;
import com.peergreen.kernel.launcher.LaunchException;

public class JavaLauncher implements ILauncher<Integer> {

    private ProcessBuilder builder;
    private ExecutorService executor;

    public JavaLauncher(ProcessBuilder builder) {
        this(builder, Executors.newSingleThreadExecutor());
    }

    public JavaLauncher(ProcessBuilder builder, ExecutorService executor) {
        this.builder = builder;
        this.executor = executor;  
    }

    public Integer launch(final IStreams streams) throws LaunchException {
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

    public Future<Integer> launchAsynch(final IStreams streams) throws LaunchException {
        
        Callable<Integer> callable = new Callable<Integer>() {

            public Integer call() throws Exception {
                return launch(streams);
            }
        };
        
        return executor.submit(callable);
    }

}

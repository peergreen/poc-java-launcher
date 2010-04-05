package com.peergreen.kernel.launcher.java;

import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.peergreen.kernel.launcher.ILauncher;
import com.peergreen.kernel.launcher.LaunchException;

public class JavaLauncher implements ILauncher<JavaResult> {

    private ProcessBuilder builder;
    private ExecutorService executor;

    public JavaLauncher(ProcessBuilder builder) {
        this(builder, Executors.newSingleThreadExecutor());
    }

    public JavaLauncher(ProcessBuilder builder, ExecutorService executor) {
        this.builder = builder;
        this.executor = executor;  
    }

    public JavaResult launch() throws LaunchException {
        // Start the process
        Process process;
        try {
            process = builder.start();
        } catch (IOException e) {
            throw new LaunchException(e);
        }
        
        // Handle streams
        
        try {
            JavaResult result = new JavaResult(process.getOutputStream(),
                                               process.getErrorStream());
            result.setResult(process.waitFor());
            return result;
        } catch (InterruptedException e) {
            throw new LaunchException(e);
        }
    }

    public Future<JavaResult> launchAsynch() throws LaunchException {
        
        Callable<JavaResult> callable = new Callable<JavaResult>() {

            public JavaResult call() throws Exception {
                return launch();
            }
        };
        
        return executor.submit(callable);
    }

}

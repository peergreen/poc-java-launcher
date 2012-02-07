package com.peergreen.kernel.launcher.java;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.concurrent.Future;

import com.peergreen.kernel.launcher.java.main.WaitingTwoSecondsMain;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.peergreen.kernel.launcher.Launcher;
import com.peergreen.kernel.launcher.java.main.SystemExitMain;
import com.peergreen.kernel.launcher.java.main.ThrowingExceptionMain;
import com.peergreen.kernel.launcher.java.main.WithArgumentsMain;
import com.peergreen.kernel.launcher.java.main.WithSpacedArgumentsMain;
import com.peergreen.kernel.launcher.java.main.WithSystemPropertiesMain;

public class JavaLauncherBuilderTestCase {
    
    private JavaLauncherBuilder builder;
    
    @BeforeMethod
    public void setUp() {
        
        builder = new JavaLauncherBuilder();
        File home = new File(System.getProperty("java.home"));
        File javaExecutable = new File(home, "bin/java");
        builder.setJavaExecutable(javaExecutable);

        File basedir = new File(System.getProperty("basedir"));
        File targetTestClasses = new File(basedir, "target/test-classes");
        builder.getClasspath().getSequence().add(targetTestClasses);
    }

    @Test
    public void testSimpleCommandLine() throws Exception {

        builder.setMainClass(SystemExitMain.class.getName());

        TestStreams streams = new TestStreams();
        Launcher<Integer> launcher = builder.getLauncher();
        int result = launcher.launch(streams);

        assertEquals(result, 3);

    }

    @Test
    public void testAsynchronousCommandLine() throws Exception {

        builder.setMainClass(WaitingTwoSecondsMain.class.getName());

        TestStreams streams = new TestStreams();
        Launcher<Integer> launcher = builder.getLauncher();

        long start = System.currentTimeMillis();
        Future<Integer> future = launcher.launchAsynch(streams);

        int result = future.get();
        long elapsed = System.currentTimeMillis() - start;

        // Ensure execution was successful
        assertEquals(result, 0);

        // Ensure that we waited more than the time used by the process
        long waitedInProcess = Integer.valueOf(streams.getProcessOutput().trim());
        assertTrue(waitedInProcess < elapsed);

    }

    private void showOutputs(TestStreams streams) {
        System.out.println("Error: " + streams.getProcessError());
        System.out.println("Outout: " + streams.getProcessOutput());
    }

    @Test
    public void testCommandLineArguments() throws Exception {
        builder.setMainClass(WithArgumentsMain.class.getName());
        
        builder.getArguments().add(new Argument("value"));

        TestStreams streams = new TestStreams();
        Launcher<Integer> launcher = builder.getLauncher();
        int result = launcher.launch(streams);

        // Successful result is 0
        assertEquals(result, 0);
        
    }

    @Test
    public void testCommandLineArgumentsWithSpaces() throws Exception {
        builder.setMainClass(WithSpacedArgumentsMain.class.getName());
        
        builder.getArguments().add(new Argument("value with spaces"));

        TestStreams streams = new TestStreams();
        Launcher<Integer> launcher = builder.getLauncher();
        int result = launcher.launch(streams);

        // Successful result is 0
        assertEquals(result, 0);
        
    }

    @Test
    public void testCommandLineSystemProperties() throws Exception {
        builder.setMainClass(WithSystemPropertiesMain.class.getName());

        builder.getSystemProperties().add(new Property(WithSystemPropertiesMain.IS_PRESENT, "true"));

        TestStreams streams = new TestStreams();
        Launcher<Integer> launcher = builder.getLauncher();
        int result = launcher.launch(streams);

        // Successful result is 0
        assertEquals(result, 0);
        
    }

    @Test
    public void testCommandLineThrowingException() throws Exception {
        builder.setMainClass(ThrowingExceptionMain.class.getName());

        TestStreams streams = new TestStreams();
        Launcher<Integer> launcher = builder.getLauncher();
        int result = launcher.launch(streams);

        // Failure result is 1
        assertEquals(result, 1);
        
    }

}

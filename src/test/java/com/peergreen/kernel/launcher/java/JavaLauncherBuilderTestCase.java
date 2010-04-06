package com.peergreen.kernel.launcher.java;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.io.File;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.peergreen.kernel.launcher.ILauncher;
import com.peergreen.kernel.launcher.java.main.SystemExitMain;
import com.peergreen.kernel.launcher.java.main.ThrowingExceptionMain;
import com.peergreen.kernel.launcher.java.main.WithArgumentsMain;

public class JavaLauncherBuilderTestCase {
    
    private JavaLauncherBuilder builder = new JavaLauncherBuilder();
    
    @BeforeTest
    public void setUp() {
        File home = new File(System.getProperty("java.home"));
        File javaExecutable = new File(home, "bin/java");
        Path java = new Path(javaExecutable);
        builder.setJavaExecutable(java);

        File basedir = new File(System.getProperty("basedir"));
        File targetTestClasses = new File(basedir, "target/test-classes");
        builder.getClasspath().add(new Path(targetTestClasses));
    }
    
    @Test
    public void testSimpleCommandLine() throws Exception {
        
        builder.setMainClass(SystemExitMain.class.getName());
        
        ILauncher<JavaResult> launcher = builder.getLauncher();
        JavaResult result = launcher.launch();
        
        assertNotNull(result);
        assertEquals(result.getResult(), 3);
        
    }

    @Test
    public void testCommandLineArguments() throws Exception {
        builder.setMainClass(WithArgumentsMain.class.getName());
        
        builder.getArguments().add(new Argument("value"));
        
        ILauncher<JavaResult> launcher = builder.getLauncher();
        JavaResult result = launcher.launch();
        
        assertNotNull(result);
        // Succesful result is 0
        assertEquals(result.getResult(), 0);
        
    }

    @Test
    public void testCommandLineThrowingException() throws Exception {
        builder.setMainClass(ThrowingExceptionMain.class.getName());
        
        ILauncher<JavaResult> launcher = builder.getLauncher();
        JavaResult result = launcher.launch();
        
        assertNotNull(result);
        // Failure result is 1
        assertEquals(result.getResult(), 1);
        
    }

}

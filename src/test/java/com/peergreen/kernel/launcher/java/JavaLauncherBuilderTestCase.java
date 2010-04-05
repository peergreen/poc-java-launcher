package com.peergreen.kernel.launcher.java;

import java.io.File;

import static org.testng.Assert.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.peergreen.kernel.launcher.ILauncher;
import com.peergreen.kernel.launcher.java.main.SimpleReturnMain;

public class JavaLauncherBuilderTestCase {
    
    private JavaLauncherBuilder builder = new JavaLauncherBuilder();
    
    @BeforeTest
    public void setUp() {
    }
    
    @Test
    public void testSimpleCommandLine() throws Exception {
        File home = new File(System.getProperty("java.home"));
        File javaExecutable = new File(home, "bin/java");
        Path java = new Path(javaExecutable);
        builder.setJavaExecutable(java);

        File basedir = new File(System.getProperty("basedir"));
        File targetClasses = new File(basedir, "target/classes");
        builder.getClasspath().add(new Path(targetClasses));
        
        builder.setMainClass(SimpleReturnMain.class.getName());
        
        builder.getArguments().add(new Argument("-3"));
        
        ILauncher<JavaResult> launcher = builder.getLauncher();
        JavaResult result = launcher.launch();
        
        assertNotNull(result);
        assertEquals(result.getResult(), -3);
        
    }

}

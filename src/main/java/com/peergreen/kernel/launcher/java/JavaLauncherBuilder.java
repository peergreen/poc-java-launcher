package com.peergreen.kernel.launcher.java;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.peergreen.kernel.launcher.Launcher;
import com.peergreen.kernel.launcher.LauncherBuilder;
import com.peergreen.kernel.launcher.LaunchException;

public class JavaLauncherBuilder implements LauncherBuilder<Integer> {
    
    private String mainClass;
    private List<Argument> arguments;
    private PathSequence endorsedDirectories;
    private PathSequence classpath;
    private List<VmOption> options;
    private List<Property> systemProperties;
    private File javaExecutable;
    
    public JavaLauncherBuilder() {
        this.arguments = new ArrayList<Argument>();
        this.endorsedDirectories = new PathSequence();
        this.classpath = new PathSequence();
        this.options = new ArrayList<VmOption>();
        this.systemProperties = new ArrayList<Property>();
    }
    
    public String getMainClass() {
        return mainClass;
    }

    public void setMainClass(String mainClass) {
        this.mainClass = mainClass;
    }

    public List<Argument> getArguments() {
        return arguments;
    }

    public PathSequence getEndorsedDirectories() {
        return endorsedDirectories;
    }

    public PathSequence getClasspath() {
        return classpath;
    }

    public List<VmOption> getOptions() {
        return options;
    }

    public List<Property> getSystemProperties() {
        return systemProperties;
    }

    public File getJavaExecutable() {
        return javaExecutable;
    }

    public void setJavaExecutable(File javaExecutable) {
        this.javaExecutable = javaExecutable;
    }

    public Launcher<Integer> getLauncher() throws LaunchException {
        ProcessBuilder builder = new ProcessBuilder();
        
        // Prepare the command
        List<String> command = builder.command();
        command.add(javaExecutable.getAbsolutePath());
        
        // Handle VM Options (-X)
        for (VmOption option : options) {
            command.add(option.render());
        }
        
        // Handle system properties
        for (Property property : systemProperties) {
            command.add(property.render());
        }
        
        // Handle endorsed directory
        if (!endorsedDirectories.getSequence().isEmpty()) {
            command.add(endorsedProperty(endorsedDirectories.render()));
        }
        
        // Handle classpath
        // TODO Optimize if too long (use env variable CLASSPATH)
        if (!classpath.getSequence().isEmpty()) {
            command.add("-cp");
            command.add(classpath.render());
        }
        
        // Handle main        
        command.add(mainClass);
        
        // Handle arguments
        for (Argument arg : arguments) {
            command.add(arg.render());
        }

        // Create the launcher
        return new JavaLauncher(builder);

        
    }

    private String endorsedProperty(String endorsed) {
        return new Property("java.endorsed.dirs", endorsed).render();
    }

}

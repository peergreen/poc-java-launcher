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
    private List<Path> endorsedDirectories;
    private List<Path> classpath;
    private List<VmOption> options;
    private List<Property> systemProperties;
    private Path javaExecutable;
    
    public JavaLauncherBuilder() {
        this.arguments = new ArrayList<Argument>();
        this.endorsedDirectories = new ArrayList<Path>();
        this.classpath = new ArrayList<Path>();
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

    public void setArguments(List<Argument> arguments) {
        this.arguments = arguments;
    }

    public List<Path> getEndorsedDirectories() {
        return endorsedDirectories;
    }

    public void setEndorsedDirectories(List<Path> endorsedDirectories) {
        this.endorsedDirectories = endorsedDirectories;
    }

    public List<Path> getClasspath() {
        return classpath;
    }

    public void setClasspath(List<Path> classpath) {
        this.classpath = classpath;
    }

    public List<VmOption> getOptions() {
        return options;
    }

    public void setOptions(List<VmOption> options) {
        this.options = options;
    }

    public List<Property> getSystemProperties() {
        return systemProperties;
    }

    public void setSystemProperties(List<Property> systemProperties) {
        this.systemProperties = systemProperties;
    }

    public Path getJavaExecutable() {
        return javaExecutable;
    }

    public void setJavaExecutable(Path javaExecutable) {
        this.javaExecutable = javaExecutable;
    }

    public Launcher<Integer> getLauncher() throws LaunchException {
        ProcessBuilder builder = new ProcessBuilder();
        
        // Prepare the command
        List<String> command = builder.command();
        command.add(javaExecutable.getValue().getAbsolutePath());
        
        // Handle VM Options (-X)
        for (VmOption option : options) {
            command.add(option.getValue());
        }
        
        // Handle system properties
        for (Property property : systemProperties) {
            command.add(systemProperty(property));
        }
        
        // Handle endorsed directory
        if (!endorsedDirectories.isEmpty()) {
            StringBuilder endorsed = new StringBuilder();
            boolean first = true;
            for (Path path : endorsedDirectories) {
                if (!first) {
                    endorsed.append(File.pathSeparatorChar);
                }
                endorsed.append(escape(path.getValue()));
                first = false;
            }
            command.add(endorsedProperty(endorsed.toString()));
        }
        
        // Handle classpath
        // TODO Optimize if too long (use env variable CLASSPATH)
        if (!classpath.isEmpty()) {
            command.add("-cp");
            StringBuilder path = new StringBuilder();
            boolean first = true;
            for (Path element : classpath) {
                if (!first) {
                    path.append(File.pathSeparatorChar);
                }
                path.append(escape(element.getValue()));
                first = false;
            }
            command.add(path.toString());
        }
        
        // Handle main        
        command.add(mainClass);
        
        // Handle arguments
        for (Argument arg : arguments) {
            command.add(arg.getValue());
        }

        // Create the launcher
        return new JavaLauncher(builder);

        
    }

    private String endorsedProperty(String endorsed) {
        Property property = new Property("java.endorsed.dirs", endorsed);
        return systemProperty(property);
    }

    private String systemProperty(Property property) {
        return new StringBuilder().append("-D")
                                  .append(property.getName())
                                  .append('=')
                                  .append(property.getValue())
                                  .toString();
    }

    private String escape(File file) {
        return escape(file.getAbsolutePath());
    }

    private String escape(String value) {
/*
        if (!value.startsWith("\"")) {
            return new StringBuilder().append('\"')
                    .append(value)
                    .append('\"')
                    .toString();
        }
*/
        return value;
    }

}

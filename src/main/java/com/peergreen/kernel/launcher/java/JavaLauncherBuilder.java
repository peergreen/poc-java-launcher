package com.peergreen.kernel.launcher.java;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.peergreen.kernel.launcher.Launcher;
import com.peergreen.kernel.launcher.LauncherBuilder;
import com.peergreen.kernel.launcher.LaunchException;

public class JavaLauncherBuilder implements LauncherBuilder<Integer> {

    public enum Mode implements Switch {
        CLIENT("-client"), SERVER("-server");
        private String cli;
        private Mode(String cli) {
            this.cli = cli;
        }

        public String render() {
            return cli;
        }
    }

    public enum VerboseOption implements Switch {
        CLASS("-verbose:class"), GC("-verbose:gc"), JNI("-verbose:jni");
        private String cli;
        private VerboseOption(String cli) {
            this.cli = cli;
        }

        public String render() {
            return cli;
        }
    }

    private String mainClass;
    private List<Argument> arguments;
    private PathSequence endorsedDirectories;
    private PathSequence classpath;
    private List<VmOption> options;
    private List<Property> systemProperties;
    private File javaExecutable;
    private List<AgentLib> agentLibs;
    private List<AgentPath> agentPaths;
    private List<JavaAgent> javaAgents;
    private Mode mode;
    private List<VerboseOption> verboseOptions;
    
    public JavaLauncherBuilder() {
        this.arguments = new ArrayList<Argument>();
        this.endorsedDirectories = new PathSequence();
        this.classpath = new PathSequence();
        this.options = new ArrayList<VmOption>();
        this.systemProperties = new ArrayList<Property>();
        this.agentLibs = new ArrayList<AgentLib>();
        this.agentPaths = new ArrayList<AgentPath>();
        this.javaAgents = new ArrayList<JavaAgent>();
        this.verboseOptions = new ArrayList<VerboseOption>();
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

    public List<AgentPath> getAgentPaths() {
        return agentPaths;
    }

    public List<AgentLib> getAgentLibs() {
        return agentLibs;
    }

    public List<JavaAgent> getJavaAgents() {
        return javaAgents;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public List<VerboseOption> getVerboseOptions() {
        return verboseOptions;
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

        if (mode != null) {
            command.add(mode.render());
        }

        if (!verboseOptions.isEmpty()) {
            for (VerboseOption verbose : verboseOptions) {
                command.add(verbose.render());
            }
        }
        
        // Handle system properties
        for (Property property : systemProperties) {
            command.add(property.render());
        }
        
        // Handle endorsed directory
        if (!endorsedDirectories.getSequence().isEmpty()) {
            command.add(endorsedProperty(endorsedDirectories.render()));
        }

        // Handle agents with libraries
        if (!agentLibs.isEmpty()) {
            for (AgentLib lib : agentLibs) {
                command.add(lib.render());
            }
        }

        // Handle agents with paths
        if (!agentPaths.isEmpty()) {
            for (AgentPath path : agentPaths) {
                command.add(path.render());
            }
        }

        // Handle java agents
        if (!javaAgents.isEmpty()) {
            for (JavaAgent javaAgent : javaAgents) {
                command.add(javaAgent.render());
            }
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

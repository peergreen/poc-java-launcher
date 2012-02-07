package com.peergreen.kernel.launcher;

public interface LauncherBuilder<T> {

    Launcher<T> getLauncher() throws LaunchException;
}

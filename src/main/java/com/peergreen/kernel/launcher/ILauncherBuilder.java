package com.peergreen.kernel.launcher;

public interface ILauncherBuilder<T> {

    ILauncher<T> getLauncher() throws LaunchException;
}

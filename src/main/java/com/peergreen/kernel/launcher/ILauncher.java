package com.peergreen.kernel.launcher;

import java.util.concurrent.Future;

public interface ILauncher<T> {

    T launch() throws LaunchException;
    Future<T> launchAsynch() throws LaunchException;
}

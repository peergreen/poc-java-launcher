package com.peergreen.kernel.launcher;

import java.util.concurrent.Future;

public interface Launcher<T> {

    T launch(IOStreams streams) throws LaunchException;
    Future<T> launchAsynch(IOStreams streams) throws LaunchException;
}

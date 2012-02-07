package com.peergreen.kernel.launcher;

import java.util.concurrent.Future;

public interface ILauncher<T> {

    T launch(IStreams streams) throws LaunchException;
    Future<T> launchAsynch(IStreams streams) throws LaunchException;
}

package com.peergreen.kernel.launcher.java;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: guillaume
 * Date: 07/02/12
 * Time: 18:54
 * To change this template use File | Settings | File Templates.
 */
public class AgentPathTestCase {
    @Test
    public void testRenderWithNoOptions() throws Exception {
        File home = new File(System.getProperty("java.home"));
        AgentPath agentPath = new AgentPath(home);
        Assert.assertEquals(agentPath.render(), "-agentpath:" + home.getAbsolutePath());
    }
    @Test
    public void testRenderWithOptions() throws Exception {
        File home = new File(System.getProperty("java.home"));
        AgentPath agentPath = new AgentPath(home, "options");
        Assert.assertEquals(agentPath.render(),
                "-agentpath:" + home.getAbsolutePath() + "=options");
    }
}

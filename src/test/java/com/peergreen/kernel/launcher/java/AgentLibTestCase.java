package com.peergreen.kernel.launcher.java;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by IntelliJ IDEA.
 * User: guillaume
 * Date: 07/02/12
 * Time: 18:54
 * To change this template use File | Settings | File Templates.
 */
public class AgentLibTestCase {
    @Test
    public void testRenderWithNoOptions() throws Exception {
        AgentLib al = new AgentLib("test");
        Assert.assertEquals(al.render(), "-agentlib:test");
    }
    @Test
    public void testRenderWithOptions() throws Exception {
        AgentLib al = new AgentLib("test", "opt");
        Assert.assertEquals(al.render(), "-agentlib:test=opt");
    }
}

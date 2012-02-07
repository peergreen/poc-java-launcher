package com.peergreen.kernel.launcher.java;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

/**
 * Created by IntelliJ IDEA.
 * User: guillaume
 * Date: 07/02/12
 * Time: 19:48
 * To change this template use File | Settings | File Templates.
 */
public class PathSequenceTestCase {
    @Test
    public void testRenderEmpty() throws Exception {
        PathSequence sequence = new PathSequence();
        Assert.assertEquals(sequence.render(), "");

    }

    @Test
    public void testRenderWithOneElementOnly() throws Exception {
        File home = new File(System.getProperty("java.home"));

        PathSequence sequence = new PathSequence();
        sequence.getSequence().add(home);
        Assert.assertEquals(sequence.render(), home.getAbsolutePath());

    }

    @Test
    public void testRenderWithMultipleElements() throws Exception {
        File home = new File(System.getProperty("java.home"));
        File basedir = new File(System.getProperty("basedir"));
        File targetTestClasses = new File(basedir, "target/test-classes");

        PathSequence sequence = new PathSequence();
        sequence.getSequence().add(home);
        sequence.getSequence().add(targetTestClasses);
        
        Assert.assertEquals(sequence.render(),
                home.getAbsolutePath() + File.pathSeparator + targetTestClasses.getAbsolutePath());

    }
}

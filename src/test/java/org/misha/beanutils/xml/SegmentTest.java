package org.misha.beanutils.xml;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class SegmentTest {

    @Before
    public void setUp() throws IOException {
        new File("./").getAbsolutePath();
        String s = FileUtils.readFileToString(new File("segments"));
    }

    @Test
    public void testSitsIn() throws Exception {
    }

    @Test
    public void testFindParentAmong() throws Exception {
    }

    @Test
    public void testGetStartIndex() throws Exception {
    }

    @Test
    public void testGetEndIndex() throws Exception {
    }
}
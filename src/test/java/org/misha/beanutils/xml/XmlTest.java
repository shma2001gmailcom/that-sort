package org.misha.beanutils.xml;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.apache.commons.io.FileUtils.readFileToString;
import static org.junit.Assert.assertEquals;

public class XmlTest {
    private  String expected;
    private String data;

    @Before
    public void setUp() throws Exception {
        data = readFileToString(new File("src/test/resources/table"));
        expected = readFileToString(new File("src/test/resources/expected-tree"));

    }

    @Test
    public void parseTest() {
        assertEquals(expected, new Xml().parse(data).toString());
    }
}

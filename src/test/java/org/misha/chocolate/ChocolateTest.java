package org.misha.chocolate;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.apache.commons.io.FileUtils.readFileToString;
import static org.apache.commons.lang3.StringUtils.isNumeric;
import static org.junit.Assert.*;

public class ChocolateTest {

    @Test
    public void equal() throws Exception {
        String data = null;
        try {
            data = readFileToString(new File("src/test/resources/chocolate"), "UTF-8");
        } catch (IOException e) {
            fail(e.getMessage());
        }
        String[] parts = data.split("\n");
        final int length = parts.length;
        int[] arr = new int[length];
        for (int i = 0; i < length; ++i) {
            final String part = parts[i];
            if (isNumeric(part)) {
                try {
                    int a = Integer.parseInt(part);
                    arr[i] = a;
                } catch (Exception e) {
                    fail(part);
                }
            }
        }
        assertEquals(315342, Chocolate.equalize(arr));
    }
}
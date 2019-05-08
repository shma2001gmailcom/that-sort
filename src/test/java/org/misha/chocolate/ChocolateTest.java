package org.misha.chocolate;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.junit.Test;

import java.io.IOException;

import static com.google.common.io.Resources.getResource;
import static org.apache.commons.lang3.StringUtils.isNumeric;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ChocolateTest {
    @Test
    public void equal() {
        String data = null;
        try {
            data = Resources.toString(getResource("chocolate"), Charsets.UTF_8);
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
        System.out.println(Chocolate.equalize(arr));
        assertEquals(3153420, Chocolate.equalize(arr));
    }
}
package org.misha.links;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;

import static org.apache.commons.io.FileUtils.readFileToString;
import static org.junit.Assert.fail;
import static org.misha.links.Patterns.WEB_URL;

/**
 * author: misha
 * date: 8/24/16
 * time: 7:35 PM
 */
public class PatternsTest {
    @Test
    public void test() {
        String data = null;
        try {
            data = readFileToString(new File("src/test/resources/text-with-links"), "UTF-8");
        } catch (IOException e) {
            fail(e.getMessage());
        }
        if (data != null) {
            Matcher matcher = WEB_URL.matcher(data);
            while (matcher.find()) {
                System.out.println(matcher.group());
            }
        }
    }
}

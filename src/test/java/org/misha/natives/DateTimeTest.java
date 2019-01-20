package org.misha.natives;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Runtime.getRuntime;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class DateTimeTest {
    private static final Logger log = Logger.getLogger(DateTimeTest.class);

    @Test
    public void getSystemTime() throws Exception {
        String[] commands = {"date", "--rfc-3339=seconds"};
        Process proc = getRuntime().exec(commands);
        try (BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
             BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()))
        ) {
            print(stdInput);
            print(stdError);
        }
    }

    private String print(final BufferedReader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        String s;
        while ((s = reader.readLine()) != null) {
            sb.append(s);
        }
        final String output = sb.toString();
        if (isNotBlank(output)) {
            log.error(output);
        }
        return output;
    }
}
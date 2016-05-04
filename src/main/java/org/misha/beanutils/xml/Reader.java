package org.misha.beanutils.xml;

import java.io.File;
import java.io.IOException;

import static org.apache.commons.io.FileUtils.readFileToString;

/**
 * author: misha
 * date: 5/3/16
 * time: 11:40 AM
 */
public final class Reader {
    private Reader() {
    }

    public static String read(final String path) throws IOException {
        return readFileToString(new File(path));
    }
}
